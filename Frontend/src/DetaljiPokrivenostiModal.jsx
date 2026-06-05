import React, { useEffect, useState } from "react";
import axios from "axios";
import DodajNastavnikaNaPredmetModal from "./DodajNastavnikaNaPredmetModal";

const LIMITI = {
  Predavanja: 60,
  Vezbe: 60,
  "Laboratorijske vezbe": 30
};

const SORT_ORDER = {
  "Predavanja": 1,
  "Vezbe": 2,
  "Laboratorijske vezbe": 3
};

const DetaljiPokrivenostiModal = ({ detalji, setDetalji, onClose, onSave }) => {
  const [sviNastavnici, setSviNastavnici] = useState([]);
  const [obliciNastave, setObliciNastave] = useState([]);
  const [showDodajModal, setShowDodajModal] = useState(false);

  const sortirajDetalje = (lista) => {
    return [...lista].sort(
      (a, b) => SORT_ORDER[a.oblikNastave.tip] - SORT_ORDER[b.oblikNastave.tip]
    );
  };

  useEffect(() => {
    if (!detalji || detalji.length === 0) return;

    const predmetID = detalji[0].predmet.predmetID;

    axios.get(`/api/nastavnik/predmet/${predmetID}`)
         .then(res => setSviNastavnici(res.data))
         .catch(err => console.error(err));

    axios.get("/api/obliknastave")
         .then(res => setObliciNastave(res.data))
         .catch(err => console.error(err));
  }, [detalji]);

  if (!detalji) return null;

  const sumByOblik = tip =>
    detalji
      .filter(d => d.oblikNastave.tip === tip)
      .reduce((sum, d) => sum + (d.brojSatiNastave || 0), 0);

  const handleChangeSati = (id, value) => {
    const broj = parseInt(value) || 0;
    const row = detalji.find(d => d.pokrivenostNastaveID === id);
    const maxSati = LIMITI[row.oblikNastave.tip];

    if (broj > maxSati) {
      alert(`Maksimalno sati za ${row.oblikNastave.tip} je ${maxSati}`);
      return;
    }

    setDetalji(sortirajDetalje(
      detalji.map(d =>
        d.pokrivenostNastaveID === id
          ? { ...d, brojSatiNastave: broj }
          : d
      )
    ));
  };

  const handleChangeNastavnik = (id, noviNastavnik) => {
    setDetalji(sortirajDetalje(
      detalji.map(d =>
        d.pokrivenostNastaveID === id
          ? { ...d, nastavnik: noviNastavnik }
          : d
      )
    ));
  };

    const sacuvaj = async (row) => {
      try {
        const payload = {
          ...row,
          brojSatiNastave: Number(row.brojSatiNastave),
        };

        if (row.pokrivenostNastaveID) {
          await axios.put(
            `/api/pokrivenostnastave/detalji/${row.pokrivenostNastaveID}`,
            payload
          );
        } else {
          await axios.post("/api/pokrivenostnastave/detalji", payload);
        }

        if (onSave) onSave();

        alert("Izmene su uspešno sačuvane!");
      } catch (e) {
        console.error("Detalji greške:", e);

        let msg = "Greška pri čuvanju!";

        if (e.response) {
          if (typeof e.response.data === "string") {
            msg = e.response.data;
          } else if (e.response.data?.message) {
            msg = e.response.data.message;
          }
        } else if (e.message) {
          msg = e.message;
        }

        alert(msg);
      }
    };

  const obrisiJednog = async id => {
    if (!window.confirm("Obrisati?")) return;

    try {
      await axios.delete(`/api/pokrivenostnastave/${id}`);
      setDetalji(sortirajDetalje(detalji.filter(d => d.pokrivenostNastaveID !== id)));
      onSave?.();
    } catch {
      alert("Greška pri brisanju");
    }
  };

  return (
    <div className="modal-overlay">
      <div className="modal">

        <h3>Detalji pokrivenosti</h3>

        <div style={{ marginBottom: 12 }}>
          <b>Predavanja:</b> {sumByOblik("Predavanja")} / 60h<br/>
          <b>Vežbe:</b> {sumByOblik("Vezbe")} / 60h<br/>
          <b>Laboratorijske:</b> {sumByOblik("Laboratorijske vezbe")} / 30h
        </div>

        <button onClick={() => setShowDodajModal(true)}>
          + Dodaj nastavnika na predmet
        </button>

        <table>
          <thead>
            <tr>
              <th>Nastavnik</th>
              <th>Oblik</th>
              <th>Sati</th>
              <th>Akcije</th>
            </tr>
          </thead>
          <tbody>
            {sortirajDetalje (detalji).map(d => (
              <tr key={d.pokrivenostNastaveID}>
                <td>
                  <select
                    value={d.nastavnik?.nastavnikID || ""}
                    onChange={e =>
                      handleChangeNastavnik(
                        d.pokrivenostNastaveID,
                        sviNastavnici.find(n => n.nastavnikID == e.target.value)
                      )
                    }
                  >
                    <option value="">-- Izaberi --</option>
                    {sviNastavnici.map(n => (
                      <option key={n.nastavnikID} value={n.nastavnikID}>
                        {n.ime} {n.prezime}
                      </option>
                    ))}
                  </select>
                </td>

                <td>{d.oblikNastave.tip}</td>

                <td>
                  <input
                    type="number"
                    value={d.brojSatiNastave}
                    onChange={e =>
                      handleChangeSati(d.pokrivenostNastaveID, e.target.value)
                    }
                  />
                </td>

                <td>
                  <button onClick={() => sacuvaj(d)}>Sačuvaj</button>
                  <button onClick={() => obrisiJednog(d.pokrivenostNastaveID)}>
                    Obriši
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        <div style={{ textAlign: "right", marginTop: 10 }}>
          <button onClick={onClose}>Zatvori</button>
        </div>

        {showDodajModal && (
          <DodajNastavnikaNaPredmetModal
            predmetID={detalji[0].predmet.predmetID}
            obliciNastave={obliciNastave}
            postojeciDetalji={detalji}
            onClose={() => setShowDodajModal(false)}
            onAdded={novi => {
              setDetalji(sortirajDetalje([...detalji, novi]));
              onSave?.();
            }}
          />
        )}

      </div>
    </div>
  );
};

export default DetaljiPokrivenostiModal;

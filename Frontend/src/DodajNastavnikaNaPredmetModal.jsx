import React, { useEffect, useState } from "react";
import axios from "axios";

const LIMITI = {
  Predavanja: 60,
  Vezbe: 60,
  "Laboratorijske vezbe": 30
};

const DodajNastavnikaNaPredmetModal = ({
  predmetID,
  obliciNastave,
  postojeciDetalji,
  onClose,
  onAdded
}) => {
  const [nastavnici, setNastavnici] = useState([]);
  const [nastavnikID, setNastavnikID] = useState("");
  const [oblikID, setOblikID] = useState("");
  const [sati, setSati] = useState("");

  useEffect(() => {
    axios.get(`/api/nastavnik/predmet/${predmetID}`)
         .then(res => setNastavnici(res.data))
         .catch(err => console.error(err));
  }, [predmetID]);

  const izracunajZauzece = (oblikID) => {
    return postojeciDetalji
            .filter(d => d.oblikNastave.oblikNastaveID == oblikID)
            .reduce((sum, d) => sum + (d.brojSatiNastave || 0), 0);
  };

  const dodaj = async () => {
    if (!nastavnikID || !oblikID || !sati) {
      return alert("Popuni sva polja");
    }

    const oblik = obliciNastave.find(o => o.oblikNastaveID == oblikID);
    if (!oblik) return alert("Nevalidan oblik.");

    const vecZauzeto = izracunajZauzece(oblikID);
    const ukupno = vecZauzeto + Number(sati);
    const maxSati = LIMITI[oblik.tip];

    if (ukupno > maxSati) {
      return alert(
        `Za ${oblik.tip} već imaš ${vecZauzeto}h. Maksimalno je ${maxSati}h, ` +
        `možeš dodati još najviše ${maxSati - vecZauzeto}h.`
      );
    }

    const payload = {
       brojSatiNastave: Number(sati),
        predmet: { predmetID },
        nastavnik: { nastavnikID: Number(nastavnikID) },
        skolskaGodina: { skolskaGodinaID: postojeciDetalji[0].skolskaGodina.skolskaGodinaID },
        oblikNastave: { 
            oblikNastaveID: Number(oblikID),
            tip: oblik.tip
        }
    };

    try {
      const res = await axios.post("/api/pokrivenostnastave/detalji", payload);
      alert("Uspešno dodato!");
      onAdded(res.data); // Dodajemo novi red u postojeciDetalji
      onClose();
    } catch (e) {
      console.error(e);
      alert("Greška: " + (e.response?.data || e.message));
    }
  };

  return (
    <div className="modal-overlay">
      <div className="modal">
        <h3>Dodaj nastavnika</h3>

        <label>Nastavnik:</label>
        <select value={nastavnikID} onChange={e => setNastavnikID(e.target.value)}>
          <option value="">-- Izaberi --</option>
          {nastavnici.map(n => (
            <option key={n.nastavnikID} value={n.nastavnikID}>
              {n.ime} {n.prezime}
            </option>
          ))}
        </select>

        <label>Oblik nastave:</label>
        <select value={oblikID} onChange={e => setOblikID(e.target.value)}>
          <option value="">-- Izaberi --</option>
          {obliciNastave.map(o => (
            <option key={o.oblikNastaveID} value={o.oblikNastaveID}>
              {o.tip}
            </option>
          ))}
        </select>

        <label>Broj sati:</label>
        <input
          type="number"
          value={sati}
          onChange={e => setSati(e.target.value)}
        />

        <br /><br />
        <button onClick={dodaj}>Dodaj</button>
        <button onClick={onClose} style={{ marginLeft: 10 }}>Zatvori</button>
      </div>
    </div>
  );
};

export default DodajNastavnikaNaPredmetModal;

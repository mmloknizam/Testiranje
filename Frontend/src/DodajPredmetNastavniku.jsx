import { useEffect, useState } from "react";
import axios from "axios";

function DodajPredmetNastavniku({ onClose, onSaved }) {
  const [nastavnici, setNastavnici] = useState([]);
  const [predmeti, setPredmeti] = useState([]);

  const [nastavnikID, setNastavnikID] = useState("");
  const [predmetID, setPredmetID] = useState("");

  useEffect(() => {
    axios.get("/api/nastavnik")
      .then(res => setNastavnici(res.data))
      .catch(err => console.error(err));

    axios.get("/api/predmet")
      .then(res => setPredmeti(res.data))
      .catch(err => console.error(err));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!nastavnikID || !predmetID) {
      alert("Izaberite nastavnika i predmet!");
      return;
    }

    try {
      await axios.post("/api/nastavnik/predmet", {
        nastavnikID,
        predmetID
      });

      alert("Uspešno dodato!");
      onSaved();
      onClose();
    } catch (err) {
      console.error(err);
      alert("Greška!");
    }
  };

  return (
    <div style={overlay}>
      <div style={modal}>
        <h3>Dodaj predmet nastavniku</h3>

        <form onSubmit={handleSubmit}>
          <div>
            <label>Nastavnik:</label>
            <select
              value={nastavnikID}
              onChange={e => setNastavnikID(e.target.value)}
            >
              <option value="">-- Izaberi --</option>
              {nastavnici.map(n => (
                <option key={n.nastavnikID} value={n.nastavnikID}>
                  {n.ime} {n.prezime}
                </option>
              ))}
            </select>
          </div>

          <div>
            <label>Predmet:</label>
            <select
              value={predmetID}
              onChange={e => setPredmetID(e.target.value)}
            >
              <option value="">-- Izaberi --</option>
              {predmeti.map(p => (
                <option key={p.predmetID} value={p.predmetID}>
                  {p.naziv}
                </option>
              ))}
            </select>
          </div>

          <div style={{ marginTop: "10px" }}>            
            <button type="submit">Dodaj</button>
            <button type="button" onClick={onClose} style={{ marginLeft: "10px" }}>
              Otkaži
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

const overlay = {
  position: "fixed",
  top: 0,
  left: 0,
  width: "100%",
  height: "100%",
  background: "rgba(0,0,0,0.4)",
  display: "flex",
  justifyContent: "center",
  alignItems: "center"
};

const modal = {
  background: "#fff",
  padding: "20px",
  borderRadius: "10px"
};

export default DodajPredmetNastavniku;

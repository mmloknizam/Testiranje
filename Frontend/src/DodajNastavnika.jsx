import React, { useState, useEffect } from "react";
import axios from "axios";

function DodajNastavnika({ onClose, onSaved }) {
  const [ime, setIme] = useState("");
  const [prezime, setPrezime] = useState("");
  const [zvanja, setZvanja] = useState([]);
  const [selectedZvanje, setSelectedZvanje] = useState("");

  useEffect(() => {
    const fetchZvanja = async () => {
      try {
        const res = await axios.get("/api/zvanje");
        setZvanja(res.data);
      } catch (err) {
        console.error("Greška pri učitavanju zvanja:", err);
      }
    };
    fetchZvanja();
  }, []);

  const handleSave = async () => {
    if (!ime.trim() || !prezime.trim() || !selectedZvanje) {
      alert("Popunite sva polja!");
      return;
    }

    try {
      await axios.post("/api/nastavnik", {
        ime: ime.trim(),
        prezime: prezime.trim(),
        zvanjeID: parseInt(selectedZvanje)
      });

      alert("Nastavnik je uspešno dodat!");
      onSaved();
      onClose();
    } catch (err) {
      console.error("Greška pri dodavanju nastavnika:", err.response || err);
      alert(err.response?.data?.message || "Greška pri dodavanju nastavnika!");
    }
  };

  return (
    <div style={{
      position: "fixed",
      top: 0, left: 0, right: 0, bottom: 0,
      backgroundColor: "rgba(0,0,0,0.5)",
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      zIndex: 1000
    }}>
      <div style={{
        backgroundColor: "white",
        padding: "20px",
        borderRadius: "8px",
        minWidth: "300px"
      }}>
        <h3>Dodaj nastavnika</h3>
        <div style={{ marginBottom: "10px" }}>
          <label>Ime: </label>
          <input value={ime} onChange={e => setIme(e.target.value)} />
        </div>
        <div style={{ marginBottom: "10px" }}>
          <label>Prezime: </label>
          <input value={prezime} onChange={e => setPrezime(e.target.value)} />
        </div>
        <div style={{ marginBottom: "10px" }}>
          <label>Zvanje: </label>
          <select value={selectedZvanje} onChange={e => setSelectedZvanje(e.target.value)}>
            <option value="">-- Izaberite zvanje --</option>
            {zvanja.map(z => (
              <option key={z.zvanjeID} value={z.zvanjeID}>{z.naziv}</option>
            ))}
          </select>
        </div>
        <div style={{ textAlign: "right" }}>
          <button onClick={handleSave}>Dodaj</button>
          <button onClick={onClose} style={{ marginRight: "10px" }}>Otkaži</button>
        </div>
      </div>
    </div>
  );
}

export default DodajNastavnika;


import React, { useState } from "react";
import axios from "axios";

function DodajPredmet({ onClose, onSaved }) {
  const [naziv, setNaziv] = useState("");
  const [brojEspb, setBrojEspb] = useState("");
  const [fondPredavanja, setFondPredavanja] = useState("");
  const [fondVezbi, setFondVezbi] = useState("");
  const [fondLabVezbi, setFondLabVezbi] = useState("");

  const handleSave = async () => {
    if (!naziv || !brojEspb || !fondPredavanja || !fondVezbi || !fondLabVezbi) {
      alert("Popunite sva polja!");
      return;
    }

    try {
      await axios.post("/api/predmet", {
        naziv,
        brojEspb,
        fondPredavanja,
        fondVezbi,
        fondLabVezbi
      });

      alert("Predmet je uspešno dodat!");
      onSaved();
      onClose();
    } catch (err) {
      console.error("Greška pri dodavanju predmeta:", err);
      alert("Greška pri dodavanju predmeta!");
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
        minWidth: "320px"
      }}>
        <h3>Dodaj predmet</h3>

        <div style={{ marginBottom: "10px" }}>
          <label>Naziv: </label>
          <input value={naziv} onChange={(e) => setNaziv(e.target.value)} />
        </div>

        <div style={{ marginBottom: "10px" }}>
          <label>Broj ESPB: </label>
          <input type="number" value={brojEspb} onChange={(e) => setBrojEspb(e.target.value)} />
        </div>

        <div style={{ marginBottom: "10px" }}>
          <label>Fond predavanja: </label>
          <input type="number" value={fondPredavanja} onChange={(e) => setFondPredavanja(e.target.value)} />
        </div>

        <div style={{ marginBottom: "10px" }}>
          <label>Fond vežbi: </label>
          <input type="number" value={fondVezbi} onChange={(e) => setFondVezbi(e.target.value)} />
        </div>

        <div style={{ marginBottom: "10px" }}>
          <label>Fond laboratorijskih vežbi: </label>
          <input type="number" value={fondLabVezbi} onChange={(e) => setFondLabVezbi(e.target.value)} />
        </div>

        <div style={{ textAlign: "right" }}> 
          <button onClick={handleSave}>Dodaj</button>
          <button onClick={onClose} style={{ marginRight: "10px" }}>Otkaži</button>

        </div>
      </div>
    </div>
  );
}

export default DodajPredmet;


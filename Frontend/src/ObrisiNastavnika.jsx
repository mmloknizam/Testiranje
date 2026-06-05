import { useState } from "react";
import axios from "axios";

function ObrisiNastavnika({ nastavnici, onClose, onDeleted }) {
  const [selectedNastavnik, setSelectedNastavnik] = useState("");

  const handleDelete = async () => {
    if (!selectedNastavnik) {
      alert("Izaberite nastavnika!");
      return;
    }

    try {
      await axios.delete(`/api/nastavnik/nastavnici/${selectedNastavnik}`);
      alert("Nastavnik uspešno obrisan!");
      onDeleted();
      onClose();
    } catch (err) {
      console.error(err);
      alert("Došlo je do greške prilikom brisanja nastavnika!");
    }
  };

  return (
    <div className="modal-overlay">
      <div className="modal">
        <h3>Obriši nastavnika</h3>

        <div style={{ marginBottom: "10px" }}>
          <select
            value={selectedNastavnik}
            onChange={(e) => setSelectedNastavnik(e.target.value)}
          >
            <option value="">-- Izaberite nastavnika --</option>
            {nastavnici.map((n) => (
              <option key={n.nastavnikID} value={n.nastavnikID}>
                {n.ime} {n.prezime}
              </option>
            ))}
          </select>
        </div>

        <div>
          <button onClick={handleDelete}>Obriši</button>
          <button onClick={onClose} style={{ marginLeft: "10px" }}>
            Otkaži
          </button>
        </div>
      </div>

      <style>{`
        .modal-overlay {
          position: fixed;
          top:0; left:0;
          width:100%; height:100%;
          background: rgba(0,0,0,0.5);
          display:flex; align-items:center; justify-content:center;
          z-index:1000;
        }
        .modal {
          background:white; padding:20px; border-radius:8px;
          width:300px; box-shadow:0 2px 10px rgba(0,0,0,0.2);
        }
      `}</style>
    </div>
  );
}

export default ObrisiNastavnika;


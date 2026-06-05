import { useState } from "react";
import axios from "axios";

function ObrisiPredmet({ predmeti, onClose, onDeleted }) {
  const [selectedPredmet, setSelectedPredmet] = useState("");

  const handleDelete = async () => {
    if (!selectedPredmet) {
      alert("Izaberite predmet!");
      return;
    }

    try {
      await axios.delete(`/api/predmet/${selectedPredmet}`);
      alert("Predmet uspešno obrisan!");
      onDeleted();
      onClose();
    } catch (err) {
      console.error(err.response?.data || err.message);
      alert(err.response?.data || "Došlo je do greške prilikom brisanja predmeta!");
    }
  };

  return (
    <div className="modal-overlay">
      <div className="modal">
        <h3>Obriši predmet</h3>

        <div style={{ marginBottom: "10px" }}>
          <select
            value={selectedPredmet}
            onChange={(e) => setSelectedPredmet(e.target.value)}
          >
            <option value="">-- Izaberite predmet --</option>
            {predmeti && predmeti.map((p) => (
              <option key={p.predmetID} value={p.predmetID}>
                {p.naziv}
              </option>
            ))}
          </select>
        </div>

        <div>
          <button onClick={handleDelete} disabled={!selectedPredmet}>
            Obriši
          </button>
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

export default ObrisiPredmet;


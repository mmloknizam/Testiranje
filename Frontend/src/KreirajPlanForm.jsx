import { useEffect, useState } from "react";
import axios from "axios";

function KreirajPlanForm({ skolskeGodine, onCancel, onSuccess }) {
  const [godinaID, setGodinaID] = useState("");
  const [predmeti, setPredmeti] = useState([]);
  const [selectedPredmeti, setSelectedPredmeti] = useState([]);

  const [showCopyBox, setShowCopyBox] = useState(false);
  const [prethodnaGodinaID, setPrethodnaGodinaID] = useState("");

  useEffect(() => {
    axios.get("/api/predmet")
      .then(res => setPredmeti(res.data))
      .catch(() => alert("Greška pri učitavanju predmeta"));
  }, []);

  const togglePredmet = (id) => {
    setSelectedPredmeti(prev =>
      prev.includes(id)
        ? prev.filter(p => p !== id)
        : [...prev, id]
    );
  };

  const handleSubmit = async () => {
    if (!godinaID) return alert("Izaberi godinu!");
    if (selectedPredmeti.length === 0) return alert("Izaberi bar jedan predmet!");

    try {
      await axios.post("/api/pokrivenostnastave/plan/kreiraj", {
        skolskaGodinaID: godinaID,
        predmetIDs: selectedPredmeti
      });

      alert("Plan kreiran!");
      onSuccess();
    } catch (err) {
      alert(err.response?.data || "Greška!");
    }
  };

  const handleCopy = async () => {
    if (!godinaID) return alert("Izaberi novu godinu!");
    if (!prethodnaGodinaID) return alert("Izaberi prethodnu godinu!");

    try {
    await axios.post("/api/pokrivenostnastave/plan/kreiraj", {
  skolskaGodinaID: godinaID,
  kopirajPrethodnu: true
});


      alert("Plan kreiran na osnovu prethodne godine!");
      onSuccess();
    } catch (err) {
      alert(err.response?.data || "Greška!");
    }
  };

  return (
    <div>
      <h3>Kreiranje plana</h3>

      <label>Školska godina:</label>
      <select value={godinaID} onChange={(e) => setGodinaID(e.target.value)}>
        <option value="">-- Izaberi --</option>
        {skolskeGodine.map(g => (
          <option key={g.skolskaGodinaID} value={g.skolskaGodinaID}>
            {g.godina}
          </option>
        ))}
      </select>

      <br/><br/>

      <button
        style={{ background: "#4CAF50", color: "white", padding: "6px 12px" }}
        onClick={() => setShowCopyBox(!showCopyBox)}
      >
        Kreiraj na osnovu prethodne godine
      </button>

      {showCopyBox && (
        <div style={{ marginTop: "10px", padding: "10px", border: "1px solid gray" }}>
          <label>Prethodna godina:</label>
          <select
            value={prethodnaGodinaID}
            onChange={(e) => setPrethodnaGodinaID(e.target.value)}
          >
            <option value="">-- Izaberi --</option>
            {skolskeGodine.map(g => (
              <option key={g.skolskaGodinaID} value={g.skolskaGodinaID}>
                {g.godina}
              </option>
            ))}
          </select>

          <br/><br/>

          <button onClick={handleCopy} style={{ background: "#2196F3", color: "white" }}>
            Potvrdi kopiranje
          </button>
        </div>
      )}

      <br/><br/>

<h4>Predmeti (ručno dodavanje):</h4>
<div style={{ display: "flex", flexDirection: "column", alignItems: "flex-start" }}>
  {predmeti.map(p => (
    <label
      key={p.predmetID}
      style={{
        display: "flex",
        alignItems: "center",
        marginBottom: "6px",
        cursor: "pointer", // Da se vidi da je klikabilno
        width: "100%"
      }}
    >
      <input
        type="checkbox"
        checked={selectedPredmeti.includes(p.predmetID)}
        onChange={() => togglePredmet(p.predmetID)}
        style={{ 
          marginRight: "10px",
          marginTop: "0",
          marginBottom: "0",
          width: "auto" 
        }} 
      />
      <span style={{ whiteSpace: "nowrap" }}>{p.naziv}</span>
    </label>
  ))}
</div>




      <br/>

      <button onClick={handleSubmit}>Sačuvaj plan</button>
      <button onClick={onCancel}>Nazad</button>
    </div>
  );
}

export default KreirajPlanForm;

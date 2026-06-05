import React, { useState, useEffect } from "react";
import axios from "axios";

function PromenaUloge({ nastavnici, onClose, onSaved }) {
  const [selectedNastavnik, setSelectedNastavnik] = useState("");
  const [uloge, setUloge] = useState([]);
  const [selectedUloga, setSelectedUloga] = useState("");
  const [nastavnikImaProfil, setNastavnikImaProfil] = useState(false);

  useEffect(() => {
    const fetchUloge = async () => {
      try {
        const res = await axios.get("/api/uloga");
        setUloge(res.data);
      } catch (err) {
        console.error("Greška pri učitavanju uloga:", err);
      }
    };
    fetchUloge();
  }, []);

  const handleNastavnikChange = (e) => {
    const nastavnikID = e.target.value;
    setSelectedNastavnik(nastavnikID);

    if (!nastavnikID) {
      setNastavnikImaProfil(false);
      setSelectedUloga("");
      return;
    }

    const nastavnik = nastavnici.find(n => n.nastavnikID.toString() === nastavnikID);

    if (nastavnik?.korisnickiProfil?.korisnickiProfilID) {
      setNastavnikImaProfil(true);
      setSelectedUloga(nastavnik.korisnickiProfil.uloga?.ulogaID || "");
    } else {
      setNastavnikImaProfil(false);
      setSelectedUloga("");
    }
  };

  const handleSave = async () => {
    if (!selectedNastavnik) {
      alert("Izaberite nastavnika!");
      return;
    }

    if (!nastavnikImaProfil) {
      alert("Odabrani nastavnik nema korisnički profil, ne možete dodeliti ulogu.");
      return;
    }

    if (!selectedUloga) {
      alert("Izaberite ulogu!");
      return;
    }

    try {
      await axios.put(`/api/nastavnik/${selectedNastavnik}/uloga`, {
        ulogaID: parseInt(selectedUloga),
      });

      alert("Uloga uspešno promenjena!");
      const nastavnik = nastavnici.find(n => n.nastavnikID.toString() === selectedNastavnik);
      onSaved(nastavnik.nastavnikID, uloge.find(u => u.ulogaID === parseInt(selectedUloga))?.tip || "");
      onClose();
    } catch (err) {
      console.error("Greška pri promeni uloge:", err.response || err);
      alert(err.response?.data || "Greška pri promeni uloge!");
    }
  };

  return (
    <div style={{
      position: "fixed",
      top:0, left:0, right:0, bottom:0,
      backgroundColor:"rgba(0,0,0,0.5)",
      display:"flex", justifyContent:"center", alignItems:"center", zIndex:1000
    }}>
      <div style={{
        backgroundColor:"white", padding:"20px", borderRadius:"8px", minWidth:"300px"
      }}>
        <h3>Promeni ulogu</h3>

        <div style={{ marginBottom:"10px" }}>
          <label>Nastavnik: </label>
          <select value={selectedNastavnik} onChange={handleNastavnikChange}>
            <option value="">-- Izaberite nastavnika --</option>
            {nastavnici.map(n => (
              <option key={n.nastavnikID} value={n.nastavnikID}>
                {n.ime} {n.prezime}
              </option>
            ))}
          </select>
        </div>

        <div style={{ marginBottom:"10px" }}>
          <label>Uloga: </label>
          <select value={selectedUloga} onChange={e => setSelectedUloga(e.target.value)} disabled={!nastavnikImaProfil}>
            <option value="">-- Izaberite ulogu --</option>
            {uloge.map(u => (
              <option key={u.ulogaID} value={u.ulogaID}>{u.tip}</option>
            ))}
          </select>
        </div>

        <div style={{ textAlign:"right" }}>
          <button onClick={handleSave}>Promeni</button>
          <button onClick={onClose} style={{ marginLeft:"10px" }}>Otkaži</button>
        </div>
      </div>
    </div>
  );
}

export default PromenaUloge;


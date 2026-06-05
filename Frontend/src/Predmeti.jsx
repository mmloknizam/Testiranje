import { useEffect, useState } from "react";
import axios from "axios";

import DodajPredmet from "./DodajPredmet";
import DodajNastavnikaPredmetu from "./DodajNastavnikaPredmetu";
import ObrisiPredmet from "./ObrisiPredmet";
import ObrisiNastavnikaZaPredmet from "./ObrisiNastavnikaZaPredmet";

function Predmeti({ isAdmin }) {
  const [predmeti, setPredmeti] = useState([]);
  const [nastavnici, setNastavnici] = useState({});

  const [showDodajPredmet, setShowDodajPredmet] = useState(false);
  const [showDodajNastavnika, setShowDodajNastavnika] = useState(false);
  const [showObrisiPredmet, setShowObrisiPredmet] = useState(false);
  const [showObrisiNastavnika, setShowObrisiNastavnika] = useState(false);

  const fetchPredmeti = async () => {
    try {
      const response = await axios.get("/api/predmet");
      const data = Array.isArray(response.data) ? response.data : [];
      setPredmeti(data);

      for (const p of data) {
        getNastavnici(p.predmetID);
      }
    } catch (err) {
      console.error("Greška pri učitavanju predmeta:", err);
    }
  };

  const getNastavnici = async (predmetID) => {
    try {
      const response = await axios.get(`/api/predmet/${predmetID}/nastavnici`);
      const nastavniciData = Array.isArray(response.data) ? response.data : [];
      setNastavnici((prev) => ({ ...prev, [predmetID]: nastavniciData }));
    } catch (err) {
      console.error(`Greška pri učitavanju nastavnika za predmet ${predmetID}:`, err);
      setNastavnici((prev) => ({ ...prev, [predmetID]: [] }));
    }
  };

  useEffect(() => {
    fetchPredmeti();
  }, []);

  return (
    <div>
      <h2 style={{ marginBottom: "15px" }}>Lista predmeta</h2>

      {isAdmin && (
        <div style={{ marginBottom: "15px", display: "flex", gap: "10px" }}>
          <button onClick={() => setShowDodajPredmet(true)}>Dodaj predmet</button>
          <button onClick={() => setShowDodajNastavnika(true)}>Dodaj nastavnika</button>
          <button onClick={() => setShowObrisiPredmet(true)}>Obriši predmet</button>
          <button onClick={() => setShowObrisiNastavnika(true)}>Obriši nastavnika</button>
        </div>
      )}

      <table border="1" cellPadding="5" cellSpacing="0" style={{ width: "100%" }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Naziv</th>
            <th>Broj ESPB</th>
            <th>Fond predavanja</th>
            <th>Fond vežbi</th>
            <th>Fond laboratorijskih vežbi</th>
            <th>Nastavnici</th>
          </tr>
        </thead>
        <tbody>
          {predmeti.map((p) => (
            <tr key={p.predmetID}>
              <td>{p.predmetID}</td>
              <td>{p.naziv}</td>
              <td>{p.brojEspb}</td>
              <td>{p.fondPredavanja}</td>
              <td>{p.fondVezbi}</td>
              <td>{p.fondLabVezbi}</td>
              <td>
                {(nastavnici[p.predmetID] || []).map((n) => `${n.ime} ${n.prezime}`).join(", ") || "Nema nastavnika"}
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Modali */}
      {isAdmin && showDodajPredmet && (
        <DodajPredmet
          onClose={() => setShowDodajPredmet(false)}
          onSaved={fetchPredmeti}
        />
      )}

      {isAdmin && showDodajNastavnika && (
        <DodajNastavnikaPredmetu
          predmeti={predmeti}
          nastavnici={nastavnici}
          onClose={() => setShowDodajNastavnika(false)}
          onSaved={fetchPredmeti}
        />
      )}

      {isAdmin && showObrisiNastavnika && (
        <ObrisiNastavnikaZaPredmet
          predmeti={predmeti}
          nastavnici={nastavnici}
          onClose={() => setShowObrisiNastavnika(false)}
          onDeleted={fetchPredmeti}
        />
      )}

      {isAdmin && showObrisiPredmet && (
        <ObrisiPredmet
          predmeti={predmeti}
          onClose={() => setShowObrisiPredmet(false)}
          onDeleted={fetchPredmeti}
        />
      )}
    </div>
  );
}

export default Predmeti;



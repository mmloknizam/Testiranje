import React, { useState } from "react";
import PlanPokrivenostiNastaveForm from "./PlanPokrivenostiNastaveForm";
import KreirajPlanForm from "./KreirajPlanForm";
import DetaljiPokrivenostiModal from "./DetaljiPokrivenostiModal";
import Nastavnici from "./Nastavnici";
import Predmeti from "./Predmeti";
import KorisnickiProfil from "./KorisnickiProfil";
import axios from "axios";
import "./Sidebar.css";

const AdminForm = ({
  korisnickiProfilID,
  planovi,
  skolskeGodine,
  selectedGodinaID,
  onGodinaChange,
  obrisiRed,
  setPlanovi,
  isAdmin,
  setUser,
  setShowLogin,
  onObrisiPlan  
}) => {
  const [view, setView] = useState("plan");
  const [sidebarOpen, setSidebarOpen] = useState(true);

  const [showDetalji, setShowDetalji] = useState(false);
  const [detalji, setDetalji] = useState([]);

  const handleDetalji = async (predmetID) => {
    try {
      const res = await axios.get(
        `/api/pokrivenostnastave/detalji/${predmetID}/${selectedGodinaID}`
      );
      setDetalji(res.data);
      setShowDetalji(true);
    } catch {
      alert("Greška pri učitavanju detalja!");
    }
  };

  const fetchPlanovi = async () => {
    if (!selectedGodinaID) return;
    try {
      const res = await axios.get(
        `/api/pokrivenostnastave/plan/${selectedGodinaID}`
      );
      setPlanovi(res.data);
    } catch {
      alert("Greška prilikom osvežavanja plana!");
    }
  };

  return (
    <div className="layout-container">
      {/* SIDEBAR */}
      <div className={`sidebar ${sidebarOpen ? "open" : "closed"}`}>
        <button className="toggle-btn" onClick={() => setSidebarOpen(!sidebarOpen)}>
          ☰
        </button>

        {sidebarOpen && (
          <div className="menu-items">
            <h3>{isAdmin ? "Admin meni" : "Korisnički meni"}</h3>

            <button onClick={() => setView("profil")}>Profil</button>
            <button onClick={() => setView("plan")}>Plan pokrivenosti</button>

            <button onClick={() => setView("nastavnici")}>Nastavnici</button>
            <button onClick={() => setView("predmeti")}>Predmeti</button>

            {isAdmin && (
              <button onClick={() => setView("kreiraj")}>
                Kreiraj plan
              </button>
            )}
          </div>
        )}
      </div>

      <div className="content">
        {view === "profil" && (
          <KorisnickiProfil
            korisnickiProfilID={korisnickiProfilID}
            setUser={setUser}
            setShowLogin={setShowLogin}
          />
        )}

        {view === "plan" && (
          <PlanPokrivenostiNastaveForm
                planovi={planovi}
                skolskeGodine={skolskeGodine}
                selectedGodinaID={selectedGodinaID}
                onGodinaChange={onGodinaChange}
                obrisiRed={obrisiRed}
                isAdmin={isAdmin}
                onDetalji={handleDetalji}
                onObrisiPlan={onObrisiPlan}
          />
        )}

        {view === "kreiraj" && isAdmin && (
          <KreirajPlanForm
            skolskeGodine={skolskeGodine}
            onCancel={() => setView("plan")}
            onSuccess={async () => {
              await fetchPlanovi();
              setView("plan");
            }}
          />
        )}

        {view === "nastavnici" && <Nastavnici isAdmin={isAdmin} />}
        {view === "predmeti" && <Predmeti isAdmin={isAdmin} />}

        {showDetalji && (
          <DetaljiPokrivenostiModal
            detalji={detalji}
            setDetalji={setDetalji}
            onClose={() => setShowDetalji(false)}
            onSave={fetchPlanovi}
          />
        )}
      </div>
    </div>
  );
};

export default AdminForm;

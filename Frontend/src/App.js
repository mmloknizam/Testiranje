import React, { useEffect, useState } from "react";
import axios from "axios";

import LoginForm from "./LoginForm";
import RegisterForm from "./RegisterForm";
import AdminForm from "./AdminForm";

import "./App.css";

function App() {
  const [user, setUser] = useState(null);
  const [showLogin, setShowLogin] = useState(true);
  const [showRegister, setShowRegister] = useState(false);

  const [planovi, setPlanovi] = useState([]);
  const [skolskeGodine, setSkolskeGodine] = useState([]);
  const [selectedGodinaID, setSelectedGodinaID] = useState("");

  const handleLogout = () => {
    localStorage.removeItem("token");
    setUser(null);
    setShowLogin(true);
    setPlanovi([]);
    setSelectedGodinaID("");
  };

  const obrisiRed = async (ids) => {
    try {
      await axios.delete("/api/pokrivenostnastave", { data: ids });

      if (selectedGodinaID) {
        const res = await axios.get(
          `/api/pokrivenostnastave/plan/${selectedGodinaID}`
        );
        setPlanovi(res.data);
      }
    } catch (err) {
      console.error(err);
      alert(err.response?.data || "Greška pri brisanju reda!");
    }
  };

  const obrisiPlan = async (skolskaGodinaID) => {
    try {
      await axios.delete(
        `/api/pokrivenostnastave/godina/${skolskaGodinaID}`
      );

      alert("Plan uspešno obrisan!");

      setPlanovi([]);
      setSelectedGodinaID("");

      // reload godina (opciono)
      const res = await axios.get("/api/skolskagodina");
      setSkolskeGodine(res.data);

    } catch (err) {
      console.error(err);
      alert(err.response?.data || "Greška pri brisanju plana!");
    }
  };

  useEffect(() => {
    axios
      .get("/api/skolskagodina")
      .then((res) => setSkolskeGodine(res.data))
      .catch((err) => console.error(err));
  }, []);

  useEffect(() => {
    if (user && selectedGodinaID) {
      axios
        .get(`/api/pokrivenostnastave/plan/${selectedGodinaID}`)
        .then((res) => setPlanovi(res.data))
        .catch((err) => console.error(err));
    }
  }, [user, selectedGodinaID]);

  return (
    <div className="app-container">
      {!user ? (
        showRegister ? (
          <RegisterForm onBackToLogin={() => setShowRegister(false)} />
        ) : (
          showLogin && (
            <LoginForm
              setUser={setUser}
              setShowRegister={setShowRegister}
            />
          )
        )
      ) : (
        <>
          <div className="user-bar">
            <span className="user-info">
              <b>{user.email}</b> | Uloga: <b>{user.uloga}</b>
            </span>

            <button onClick={handleLogout}>Odjavi se</button>
          </div>

          <AdminForm
            korisnickiProfilID={user.korisnickiProfilID}
            planovi={planovi}
            skolskeGodine={skolskeGodine}
            selectedGodinaID={selectedGodinaID}
            onGodinaChange={(e) => setSelectedGodinaID(e.target.value)}
            obrisiRed={obrisiRed}
            setPlanovi={setPlanovi}
            isAdmin={user.uloga === "Administrator"}
            setUser={setUser}
            setShowLogin={setShowLogin}
            onObrisiPlan={obrisiPlan}
          />
        </>
      )}
    </div>
  );
}

export default App;

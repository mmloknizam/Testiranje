import React, { useState, useEffect } from "react";
import axios from "axios";

const KorisnickiProfil = ({ korisnickiProfilID, setUser, setShowLogin }) => {
  const [profil, setProfil] = useState(null);
  const [formData, setFormData] = useState({});
  const [isEdit, setIsEdit] = useState(false);
  const [showPasswordEdit, setShowPasswordEdit] = useState(false);
  const [showDeleteConfirm, setShowDeleteConfirm] = useState(false);
  const [zvanja, setZvanja] = useState([]);
  const [uloge, setUloge] = useState([]);
  const [poruka, setPoruka] = useState("");
  const [greska, setGreska] = useState("");

  useEffect(() => {
    const loadData = async () => {
      try {
        const profilRes = await axios.get(`/api/auth/profil/${korisnickiProfilID}`);
        setProfil(profilRes.data);

        setFormData({
          zvanjeID: profilRes.data.zvanjeID,
          staraLozinka: "",
          novaLozinka: "",
          potvrdaNoveLozinke: "",
          lozinkaZaBrisanje: ""
        });

        const zvanjaRes = await axios.get("/api/auth/zvanja");
        setZvanja(zvanjaRes.data);

        const ulogeRes = await axios.get("/api/auth/uloge");
        setUloge(ulogeRes.data);
      } catch (err) {
        setGreska(err.response?.data || "Greška pri učitavanju profila.");
      }
    };
    loadData();
  }, [korisnickiProfilID]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const resetMessages = () => {
    setPoruka("");
    setGreska("");
  };

  const handleEditProfile = () => {
    resetMessages();
    setIsEdit(true);
    setShowPasswordEdit(false);
    setShowDeleteConfirm(false);
  };

  const handleSaveProfile = async () => {
    try {
      const izmena = { zvanjeID: formData.zvanjeID };
      const res = await axios.put(`/api/auth/profil/${korisnickiProfilID}`, izmena);

      setProfil(res.data);
      setIsEdit(false);
      setPoruka("Profil uspešno sačuvan!");

      setUser(prev => ({
        ...prev,
        zvanjeID: res.data.zvanjeID
      }));

    } catch (err) {
      setGreska(err.response?.data || "Greška pri čuvanju profila.");
    }
  };

  const handleCancelProfileEdit = () => {
    setFormData({
      ...formData,
      zvanjeID: profil.zvanjeID
    });
    setIsEdit(false);
    resetMessages();
  };

  const handleOpenPasswordEdit = () => {
    resetMessages();
    setShowPasswordEdit(true);
    setIsEdit(false);
    setShowDeleteConfirm(false);
  };

  const handlePasswordChange = async () => {
    if (formData.novaLozinka !== formData.potvrdaNoveLozinke) {
      setGreska("Lozinke se ne poklapaju!");
      return;
    }
    try {
      await axios.put(`/api/auth/profil/${korisnickiProfilID}/lozinka`, {
        staraLozinka: formData.staraLozinka,
        novaLozinka: formData.novaLozinka
      });

      setPoruka("Lozinka uspešno promenjena!");

      setUser(prev => ({
        ...prev
      }));

      handleCancelPasswordEdit();
    } catch (err) {
      setGreska(err.response?.data || "Greška pri promeni lozinke.");
    }
  };

  const handleCancelPasswordEdit = () => {
    setFormData({
      ...formData,
      staraLozinka: "",
      novaLozinka: "",
      potvrdaNoveLozinke: ""
    });
    setShowPasswordEdit(false);
    resetMessages();
  };

  const handleOpenDelete = () => {
    resetMessages();
    setShowDeleteConfirm(true);
    setIsEdit(false);
    setShowPasswordEdit(false);
  };

  const handleDelete = async () => {
    if (!formData.lozinkaZaBrisanje) {
      setGreska("Unesite trenutnu lozinku!");
      return;
    }
    try {
      await axios.delete(`/api/auth/profil/${korisnickiProfilID}`, {
        data: { lozinka: formData.lozinkaZaBrisanje }
      });

      setPoruka("Profil uspešno obrisan!");
      setTimeout(() => {
        setUser(null);
        setShowLogin(true);
      }, 1000);
    } catch (err) {
      setGreska(err.response?.data || "Greška pri brisanju profila.");
    }
  };

  if (!profil) return <p>Učitavanje profila...</p>;

  return (
    <div style={{ maxWidth: "520px" }}>
      <h3>Moj profil</h3>

      {greska && <p style={{ color: "red" }}>{greska}</p>}
      {poruka && <p style={{ color: "green" }}>{poruka}</p>}

      <label>Ime</label>
      <input type="text" value={profil.ime} disabled />

      <label>Prezime</label>
      <input type="text" value={profil.prezime} disabled />

      <label>Email</label>
      <input type="email" value={profil.email} disabled />

      <label>Zvanje</label>
      <select
        name="zvanjeID"
        value={formData.zvanjeID}
        onChange={handleChange}
        disabled={!isEdit}
      >
        {zvanja.map((z) => (
          <option key={z.zvanjeID} value={z.zvanjeID}>
            {z.naziv}
          </option>
        ))}
      </select>

      <label>Uloga</label>
      <input
        type="text"
        value={uloge.find(u => u.ulogaID === profil.ulogaID)?.tip || ""}
        disabled
      />

      {!isEdit && !showPasswordEdit && !showDeleteConfirm && (
        <div style={{ marginTop: "15px" }}>
          <button onClick={handleEditProfile}>Izmeni profil</button>
          <button
            onClick={handleOpenPasswordEdit}
            style={{ marginLeft: "10px" }}
          >
            Promeni lozinku
          </button>
          <button
            onClick={handleOpenDelete}
            style={{ marginLeft: "10px" }}
          >
            Obriši profil
          </button>
        </div>
      )}

      {isEdit && (
        <div style={{ marginTop: "15px" }}>
          <button onClick={handleSaveProfile}>Sačuvaj</button>
          <button
            onClick={handleCancelProfileEdit}
            style={{ marginLeft: "10px" }}
          >
            Otkaži
          </button>
        </div>
      )}

      {showPasswordEdit && (
        <>
          <hr />
          <h4>Promena lozinke</h4>

          <label>Stara lozinka</label>
          <input
            type="password"
            name="staraLozinka"
            value={formData.staraLozinka}
            onChange={handleChange}
          />

          <label>Nova lozinka</label>
          <input
            type="password"
            name="novaLozinka"
            value={formData.novaLozinka}
            onChange={handleChange}
          />

          <label>Potvrdi novu lozinku</label>
          <input
            type="password"
            name="potvrdaNoveLozinke"
            value={formData.potvrdaNoveLozinke}
            onChange={handleChange}
          />

          <button onClick={handlePasswordChange}>Promeni lozinku</button>
          <button
            onClick={handleCancelPasswordEdit}
            style={{ marginLeft: "10px" }}
          >
            Otkaži
          </button>
        </>
      )}

      {showDeleteConfirm && (
        <>
          <hr />
          <h4>Potvrda brisanja profila</h4>

          <label>Trenutna lozinka</label>
          <input
            type="password"
            name="lozinkaZaBrisanje"
            value={formData.lozinkaZaBrisanje}
            onChange={handleChange}
          />

          <button onClick={handleDelete}>Potvrdi brisanje</button>
          <button
            onClick={() => setShowDeleteConfirm(false)}
            style={{ marginLeft: "10px" }}
          >
            Otkaži
          </button>
        </>
      )}
    </div>
  );
};

export default KorisnickiProfil;

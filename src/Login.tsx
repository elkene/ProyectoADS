import React, { useState } from "react";

// Importar las imágenes directamente
import mascotaImage from "./assets/mascotav2.png";
import cimarronImage from "./assets/cimarronv2.png";

const Login: React.FC = () => {
    const [tipoUsuario, setTipoUsuario] = useState("Estudiante");
    const [correo, setCorreo] = useState("");
    const [contrasena, setContrasena] = useState("");

    const handleLogin = () => {
        if (!correo || !contrasena) {
            alert("Por favor, complete todos los campos");
            return;
        }

        alert(`Tipo de Usuario: ${tipoUsuario}\nCorreo: ${correo}\nContraseña: ••••••`);
    };

    const handleRecuperar = () => {
        alert("Función de recuperación de contraseña en desarrollo");
    };

    return (
        <div style={styles.mainContainer}>
            {/* Panel superior */}
            <header style={styles.header}>
                <h2>UNIVERSIDAD AUTÓNOMA DE BAJA CALIFORNIA</h2>
            </header>

            {/* Panel central */}
            <div style={styles.centerPanel}>
                <div style={styles.centerPanel}>
                    {/* Panel izquierdo - imágenes */}
                    <div style={styles.imagePanel}>
                        <img
                            src={mascotaImage}
                            alt="Mascota"
                            style={styles.image}
                            onError={(e) => {
                                (e.target as HTMLImageElement).src =
                                    "https://via.placeholder.com/300x200/007934/FFFFFF?text=IMAGEN+DEPORTE+1";
                            }}
                        />
                        <img
                            src={cimarronImage}
                            alt="Cimarrón"
                            style={styles.image}
                            onError={(e) => {
                                (e.target as HTMLImageElement).src =
                                    "https://via.placeholder.com/300x200/CE8E00/FFFFFF?text=IMAGEN+DEPORTE+2";
                            }}
                        />
                    </div>
                </div>

                {/* Panel derecho - formulario */}
                <div style={styles.formPanel}>
                    <h3 style={styles.title}>FACULTAD DE DEPORTES</h3>

                    <label style={styles.label}>Tipo de Usuario:</label>
                    <select
                        value={tipoUsuario}
                        onChange={(e) => setTipoUsuario(e.target.value)}
                        style={styles.select}
                    >
                        <option>Estudiante</option>
                        <option>Profesor</option>
                        <option>Administrador</option>
                        <option>Invitado</option>
                    </select>

                    <label style={styles.label}>Dirección de Correo:</label>
                    <input
                        type="email"
                        value={correo}
                        onChange={(e) => setCorreo(e.target.value)}
                        style={styles.input}
                    />

                    <label style={styles.label}>Contraseña:</label>
                    <input
                        type="password"
                        value={contrasena}
                        onChange={(e) => setContrasena(e.target.value)}
                        style={styles.input}
                    />

                    <button onClick={handleRecuperar} style={styles.linkButton}>
                        Recuperar Contraseña
                    </button>

                    <button onClick={handleLogin} style={styles.loginButton}>
                        Iniciar Sesión
                    </button>
                </div>
            </div>

            {/* Panel inferior */}
            <footer style={styles.footer}>
                <div style={styles.footerText}>
                    CAKENJU ENTERPRISES | CAKENJU ENTERPRISES
                </div>
                <div style={styles.footerSubtext}>
                    © 2025 CAKENJU ENTERPRISES. Todos los derechos reservados.
                </div>
            </footer>
        </div>
    );
};

export default Login;

// 🎨 Estilos equivalentes al diseño original de Java Swing
const styles: { [key: string]: React.CSSProperties } = {
    mainContainer: {
        display: "flex",
        flexDirection: "column",
        height: "100vh",
        fontFamily: "Arial, sans-serif",
        backgroundColor: "#024731",
        color: "white",
    },
    header: {
        backgroundColor: "#007934",
        textAlign: "center",
        padding: "20px 0",
        fontSize: "20px",
        fontWeight: "bold",
    },
    centerPanel: {
        display: "grid",
        gridTemplateColumns: "1fr 1fr",
        flex: 1,
        backgroundColor: "#024731",
    },
    imagePanel: {
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        gap: "30px",
        padding: "40px",
    },
    image: {
        width: "300px",
        height: "200px",
        objectFit: "contain",
        backgroundColor: "#007934",
        borderRadius: "10px",
    },
    formPanel: {
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        padding: "40px 60px",
        backgroundColor: "#024731",
    },
    title: {
        textAlign: "center",
        marginBottom: "30px",
        fontSize: "24px",
        fontWeight: "bold",
    },
    label: {
        fontSize: "14px",
        marginTop: "10px",
        marginBottom: "4px",
    },
    select: {
        fontSize: "14px",
        padding: "8px",
        borderRadius: "5px",
        border: "none",
        width: "100%",
    },
    input: {
        fontSize: "14px",
        padding: "8px",
        borderRadius: "5px",
        border: "none",
        width: "100%",
    },
    linkButton: {
        marginTop: "10px",
        background: "none",
        border: "none",
        color: "white",
        textDecoration: "underline",
        cursor: "pointer",
        fontSize: "12px",
        alignSelf: "flex-end",
    },
    loginButton: {
        marginTop: "20px",
        backgroundColor: "#CE8E00",
        color: "white",
        fontSize: "16px",
        fontWeight: "bold",
        border: "none",
        borderRadius: "5px",
        padding: "10px",
        cursor: "pointer",
    },
    footer: {
        backgroundColor: "#007934",
        textAlign: "center",
        padding: "15px 0",
    },
    footerText: {
        fontSize: "12px",
    },
    footerSubtext: {
        fontSize: "10px",
        marginTop: "5px",
    },
};
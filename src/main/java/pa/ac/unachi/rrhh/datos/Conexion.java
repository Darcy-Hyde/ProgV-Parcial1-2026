package pa.ac.unachi.rrhh.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fábrica de conexiones JDBC. Ajuste URL, USUARIO y CLAVE a su instalación.
 */
public final class Conexion {

    private static final String URL = System.getProperty("rrhh.db.url",
            "jdbc:mysql://localhost:3306/rrhh_examen?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Panama");
    private static final String USUARIO = System.getProperty("rrhh.db.usuario", "root");
    private static final String CLAVE = System.getProperty("rrhh.db.clave", "");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("No se encontró el driver de MySQL: " + e.getMessage());
        }
    }

    private Conexion() {
    }

    public static Connection obtener() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}

package pa.ac.unachi.rrhh.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pa.ac.unachi.rrhh.modelo.Dotacion;

/**
 * Acceso a datos de la tabla dotacion. No valida reglas de negocio.
 */
public class DotacionDAO {

    /** Crea el registro de dotación (cantidad 0) de un departamento nuevo. */
    public void crearRegistro(int departamentoId) throws SQLException {
        String sql = "INSERT INTO dotacion (departamento_id, cantidad) VALUES (?, 0)";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, departamentoId);
            ps.executeUpdate();
        }
    }
    
    //Parte 3.3: Agregar columnas al Join
    public List<Dotacion> listar() throws SQLException {
        String sql = "SELECT t.id, t.departamento_id, t.cantidad, d.codigo, d.nombre, d.dotacion_maxima "
                + "FROM dotacion t JOIN departamento d ON d.id = t.departamento_id "
                + "ORDER BY d.codigo";
        List<Dotacion> lista = new ArrayList<>();
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearDotacion(rs));
            }
        }
        return lista;
    }
    
    //Parte 3.3: Agregar columnas al Join
    public Dotacion buscarPorDepartamento(int departamentoId) throws SQLException {
        String sql = "SELECT t.id, t.departamento_id, t.cantidad, d.codigo, d.nombre, d.dotacion_maxima "
                + "FROM dotacion t JOIN departamento d ON d.id = t.departamento_id "
                + "WHERE t.departamento_id = ?";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, departamentoId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapearDotacion(rs) : null;
            }
        }
    }

    public void actualizarCantidad(int departamentoId, int cantidad) throws SQLException {
        String sql = "UPDATE dotacion SET cantidad = ? WHERE departamento_id = ?";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cantidad);
            ps.setInt(2, departamentoId);
            ps.executeUpdate();
        }
    }

  //Parte 3.3: Agregar columnas a MapearDotacion
    private Dotacion mapearDotacion(ResultSet rs) throws SQLException {
        Dotacion t = new Dotacion();
        t.setId(rs.getInt("id"));
        t.setDepartamentoId(rs.getInt("departamento_id"));
        t.setCantidad(rs.getInt("cantidad"));
        t.setCodigoDepartamento(rs.getString("codigo"));
        t.setNombreDepartamento(rs.getString("nombre"));
        t.setDotacionMaximaDepartamento(rs.getInt("dotacion_maxima"));
        return t;
    }
}

package pa.ac.unachi.rrhh.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pa.ac.unachi.rrhh.modelo.Departamento;

/**
 * Acceso a datos de la tabla departamento. No valida reglas de negocio.
 */
public class DepartamentoDAO {

    /** Inserta el departamento y devuelve el id generado. */
	//Parte 3.1: Columnas nuevas en el Insert
    public int insertar(Departamento d) throws SQLException {
        String sql = "INSERT INTO departamento (codigo, nombre, salario_base, dotacion_maxima) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, d.getCodigo());
            ps.setString(2, d.getNombre());
            ps.setBigDecimal(3, d.getSalarioBase());
            ps.setInt(4, d.getDotacionMaxima());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

  //Parte 3.1: Columnas nuevas en Select 1
    public List<Departamento> listar() throws SQLException {
        String sql = "SELECT id, codigo, nombre, salario_base, dotacion_maxima FROM departamento ORDER BY codigo";
        List<Departamento> lista = new ArrayList<>();
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearDepartamento(rs));
            }
        }
        return lista;
    }

  //Parte 3.1: Columnas nuevas en Select 2
    public Departamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, codigo, nombre, salario_base, dotacion_maxima FROM departamento WHERE id = ?";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapearDepartamento(rs) : null;
            }
        }
    }

    //Parte 3.1: Columnas nuevas en el Update
    public void actualizar(Departamento d) throws SQLException {
        String sql = "UPDATE departamento SET codigo = ?, nombre = ?, salario_base = ?, dotacion_maxima = ? WHERE id = ?";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getCodigo());
            ps.setString(2, d.getNombre());
            ps.setBigDecimal(3, d.getSalarioBase());
            ps.setInt(4, d.getDotacionMaxima());
            ps.setInt(5, d.getId());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM departamento WHERE id = ?";
        try (Connection con = Conexion.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

  //Parte 3.2: Actualizacion de Mapear
    private Departamento mapearDepartamento(ResultSet rs) throws SQLException {
        Departamento d = new Departamento();
        d.setId(rs.getInt("id"));
        d.setCodigo(rs.getString("codigo"));
        d.setNombre(rs.getString("nombre"));
        d.setSalarioBase(rs.getBigDecimal("salario_base"));
        d.setDotacionMaxima(rs.getInt("dotacion_maxima"));
        return d;
    }
}

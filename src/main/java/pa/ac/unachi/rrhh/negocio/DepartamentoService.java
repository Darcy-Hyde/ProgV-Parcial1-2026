package pa.ac.unachi.rrhh.negocio;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import pa.ac.unachi.rrhh.datos.DepartamentoDAO;
import pa.ac.unachi.rrhh.datos.DotacionDAO;
import pa.ac.unachi.rrhh.modelo.Departamento;

/**
 * Reglas de negocio de los departamentos.
 */
public class DepartamentoService {

    private final DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private final DotacionDAO dotacionDAO = new DotacionDAO();

    public List<Departamento> listar() throws SQLException {
        return departamentoDAO.listar();
    }

    public Departamento buscar(int id) throws NegocioException, SQLException {
        Departamento d = departamentoDAO.buscarPorId(id);
        if (d == null) {
            throw new NegocioException("El departamento solicitado no existe.");
        }
        return d;
    }

    public void crear(Departamento d) throws NegocioException, SQLException {
        validar(d);
        try {
            int id = departamentoDAO.insertar(d);
            dotacionDAO.crearRegistro(id);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new NegocioException("Ya existe un departamento con el código " + d.getCodigo() + ".");
        }
    }

    public void actualizar(Departamento d) throws NegocioException, SQLException {
        validar(d);
        try {
            departamentoDAO.actualizar(d);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new NegocioException("Ya existe un departamento con el código " + d.getCodigo() + ".");
        }
    }

    public void eliminar(int id) throws SQLException {
        departamentoDAO.eliminar(id);
    }

    private void validar(Departamento d) throws NegocioException {
        if (d.getCodigo() == null || d.getCodigo().isBlank()) {
            throw new NegocioException("El código es obligatorio.");
        }
        if (!d.getCodigo().matches("DEP-\\d{3}")) {
            throw new NegocioException("El código debe tener el formato DEP-000.");
        }
        if (d.getNombre() == null || d.getNombre().isBlank()) {
            throw new NegocioException("El nombre es obligatorio.");
        }
        if (d.getNombre().length() > 100) {
            throw new NegocioException("El nombre no puede superar los 100 caracteres.");
        }

        //Parte 4.1: Agregar validacion de los nuevos atributos
        if (d.getSalarioBase() == null || d.getSalarioBase().compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegocioException("El salario base debe ser mayor a cero.");
        }
        if (d.getDotacionMaxima() < 1) {
            throw new NegocioException("La dotación máxima debe ser de al menos 1 colaborador.");
        }
    }
}


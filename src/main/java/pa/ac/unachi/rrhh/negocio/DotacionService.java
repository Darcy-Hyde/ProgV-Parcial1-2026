package pa.ac.unachi.rrhh.negocio;

import java.sql.SQLException;
import java.util.List;
import pa.ac.unachi.rrhh.datos.DotacionDAO;
import pa.ac.unachi.rrhh.modelo.Dotacion;

/**
 * Reglas de negocio de la dotación de personal: altas y bajas.
 */
public class DotacionService {

    private final DotacionDAO dotacionDAO = new DotacionDAO();

    public List<Dotacion> listar() throws SQLException {
        return dotacionDAO.listar();
    }

    /** Alta: ingresan colaboradores al departamento (contratación o traslado). */
    public void registrarAlta(int departamentoId, int cantidad) throws NegocioException, SQLException {
        if (cantidad <= 0) {
            throw new NegocioException("La cantidad de colaboradores debe ser mayor a cero.");
        }
        Dotacion actual = buscar(departamentoId);
        int nueva = actual.getCantidad() + cantidad;

        // Parte 4.2: Validar dotación máxima
        if (nueva > actual.getDotacionMaximaDepartamento()) {
            int vacantesDisponibles = actual.getDotacionMaximaDepartamento() - actual.getCantidad();
            throw new NegocioException("No se puede contratar a " + cantidad + " colaboradores en " 
                    + actual.getNombreDepartamento() + ". Solo puede contratar " 
                    + Math.max(0, vacantesDisponibles) + " colaboradores más.");
        }

        dotacionDAO.actualizarCantidad(departamentoId, nueva);
    }

    /** Baja: salen colaboradores del departamento (renuncia, despido o traslado). */
    public void registrarBaja(int departamentoId, int cantidad) throws NegocioException, SQLException {
        if (cantidad <= 0) {
            throw new NegocioException("La cantidad de colaboradores debe ser mayor a cero.");
        }
        Dotacion actual = buscar(departamentoId);
        if (cantidad > actual.getCantidad()) {
            throw new NegocioException("No se puede dar de baja a " + cantidad + " colaboradores: "
                    + actual.getNombreDepartamento() + " solo tiene " + actual.getCantidad() + ".");
        }
        dotacionDAO.actualizarCantidad(departamentoId, actual.getCantidad() - cantidad);
    }

    private Dotacion buscar(int departamentoId) throws NegocioException, SQLException {
        Dotacion t = dotacionDAO.buscarPorDepartamento(departamentoId);
        if (t == null) {
            throw new NegocioException("El departamento no tiene registro de dotación.");
        }
        return t;
    }
}


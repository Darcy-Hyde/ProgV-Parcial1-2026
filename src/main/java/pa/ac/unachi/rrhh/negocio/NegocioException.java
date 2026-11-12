package pa.ac.unachi.rrhh.negocio;

/**
 * Violación de una regla de negocio. El mensaje se muestra al usuario.
 */
public class NegocioException extends Exception {

    private static final long serialVersionUID = 1L;

    public NegocioException(String mensaje) {
        super(mensaje);
    }
}

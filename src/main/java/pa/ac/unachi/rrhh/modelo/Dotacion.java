package pa.ac.unachi.rrhh.modelo;

/**
 * Dotación de personal de un departamento (cantidad de colaboradores).
 * Los campos codigoDepartamento, nombreDepartamento y dotacionMaximaDepartamento 
 * son de solo lectura: se llenan con el JOIN para mostrarlos en el listado.
 */
public class Dotacion {

    private int id;
    private int departamentoId;
    private int cantidad;

    // Solo lectura (JOIN con departamento)
    private String codigoDepartamento;
    private String nombreDepartamento;
    
    // Parte 2.2 (Tema B): Campo de Lectura
    private int dotacionMaximaDepartamento;

    public Dotacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(int departamentoId) {
        this.departamentoId = departamentoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    // Parte 2.2 (Tema B): Getter y Setter
    public int getDotacionMaximaDepartamento() {
        return dotacionMaximaDepartamento;
    }

    public void setDotacionMaximaDepartamento(int dotacionMaximaDepartamento) {
        this.dotacionMaximaDepartamento = dotacionMaximaDepartamento;
    }
}


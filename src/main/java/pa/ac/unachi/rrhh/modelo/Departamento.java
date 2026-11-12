package pa.ac.unachi.rrhh.modelo;

import java.math.BigDecimal;

/**
 * Departamento de la empresa.
 */
public class Departamento {

    private int id;
    private String codigo;
    private String nombre;
    
    // (Modificacion segun parte 2.1)
    private BigDecimal salarioBase;
    private int dotacionMaxima;

    public Departamento() {
    }


    // Parte 2.1: Constructor con parámetros actualizado
    public Departamento(int id, String codigo, String nombre, BigDecimal salarioBase, int dotacionMaxima) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.salarioBase = salarioBase;
        this.dotacionMaxima = dotacionMaxima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Parte 2.1: Getters y Setters
    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
        this.salarioBase = salarioBase;
    }

    public int getDotacionMaxima() {
        return dotacionMaxima;
    }

    public void setDotacionMaxima(int dotacionMaxima) {
        this.dotacionMaxima = dotacionMaxima;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut4.modelo;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 *
 * @author Jaime Navarro
 */
// Indica que esta clase es una entidad de JPA
@Entity
// Relaciona esta clase con la tabla 'naves'
@Table(name = "naves")
// Toda la jerarquía se guarda en una única tabla.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Esta columna identifica qué subclase representa cada fila.
@DiscriminatorColumn(name = "tipo_nave")

public abstract class Nave implements Serializable {

    // Clave primaria, identifica cada nave
    @Id
    // Postgre genera el id automático
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nave_id")
    private int id;

    // Nombre obligatorio, con un máximo de 100 caracteres
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    // Modelo obligatorio, con un máximo de 50 caracteres
    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    // Estado de la nave, por ejemplo "Critico" o "Estable"
    @Column(name = "estado_sistema", nullable = false, length = 20)
    private String estadoSistema;
    
    // Muchas naves pueden pertenecer al mismo planeta.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planeta_origen_id")
    private Planeta planetaOrigen;

    public Nave() {
    }

    // NO indicamos el ID, ya que hemos indicado arriba que lo genera postgre automáticamente
    public Nave(String nombre, String modelo, String estadoSistema) {
        this.nombre = nombre;
        this.modelo = modelo;
        this.estadoSistema = estadoSistema;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the estadoSistema
     */
    public String getEstadoSistema() {
        return estadoSistema;
    }

    /**
     * @param estadoSistema the estadoSistema to set
     */
    public void setEstadoSistema(String estadoSistema) {
        this.estadoSistema = estadoSistema;
    }

    // Devuelve el planeta al que pertenece la nave.
    public Planeta getPlanetaOrigen() {
        return planetaOrigen;
    }

    // Asigna un planeta a la nave.
    public void setPlanetaOrigen(Planeta planetaOrigen) {
        this.planetaOrigen = planetaOrigen;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nombre
                + " (" + modelo + ") - Estado: " + estadoSistema;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut4.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaime Navarro
 */
@Entity
@Table(name = "planetas")
public class Planeta {

    // Identificador generado automáticamente.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planeta_id")
    private int id;

    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombrePlaneta;

    @Column(name = "afiliacion", length = 50)
    private String afiliacion;

    @Column(name = "clima", length = 50)
    private String clima;

    // Un planeta puede tener muchas naves.
    // mappedBy indica el atributo de Nave que contiene la relación.
    // PERSIST permite guardar también sus naves al guardar el planeta.
    @OneToMany(mappedBy = "planetaOrigen", cascade = CascadeType.PERSIST)
    private List<Nave> naves = new ArrayList<>();

    // Constructor vacío para Hibernate.
    public Planeta() {
    }

    // Constructor para crear un planeta con sus datos.
    public Planeta(String nombrePlaneta, String afiliacion) {
        this.nombrePlaneta = nombrePlaneta;
        this.afiliacion = afiliacion;
    }

    public int getId() {
        return id;
    }

    public String getNombrePlaneta() {
        return nombrePlaneta;
    }

    public void setNombrePlaneta(String nombrePlaneta) {
        this.nombrePlaneta = nombrePlaneta;
    }

    public String getAfiliacion() {
        return afiliacion;
    }

    public void setAfiliacion(String afiliacion) {
        this.afiliacion = afiliacion;
    }

    public List<Nave> getNaves() {
        return naves;
    }

    public void agregarNave(Nave nave) {
        // Añadimos la nave a la lista de este planeta.
        naves.add(nave);

        // Indicamos a la nave que pertenece a este planeta.
        nave.setPlanetaOrigen(this);
    }

    /**
     * @return the clima
     */
    public String getClima() {
        return clima;
    }

    /**
     * @param clima the clima to set
     */
    public void setClima(String clima) {
        this.clima = clima;
    }
}

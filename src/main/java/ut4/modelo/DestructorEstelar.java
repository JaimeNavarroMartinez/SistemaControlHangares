/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut4.modelo;

import jakarta.persistence.*;

/**
 *
 * @author Jaime Navarro
 */

@Entity
// Identifica las filas que representan destructores.
@DiscriminatorValue("DESTRUCTOR")
public class DestructorEstelar extends Nave {
    // Atributo específico de los destructores.
    @Column(name = "capacidad_escudos")
    private int capacidadEscudos;

    // Constructor vacío para Hibernate.
    public DestructorEstelar() {
    }

    public DestructorEstelar(String nombre, String modelo,
            String estadoSistema, int capacidadEscudos) {

        // Inicializamos los datos comunes mediante Nave.
        super(nombre, modelo, estadoSistema);

        // Inicializamos el dato específico del destructor.
        this.capacidadEscudos = capacidadEscudos;
    }

    public int getCapacidadEscudos() {
        return capacidadEscudos;
    }

    public void setCapacidadEscudos(int capacidadEscudos) {
        this.capacidadEscudos = capacidadEscudos;
    }
}

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
// Identifica las filas de la tabla naves que representan cazas.
@DiscriminatorValue("CAZA")
public class CazaLigero extends Nave{
    // Atributo específico de los cazas.
    @Column(name = "potencia_fuego")
    private int potenciaFuego;

    // Constructor vacío para Hibernate.
    public CazaLigero() {
    }

    public CazaLigero(String nombre, String modelo,
            String estadoSistema, int potenciaFuego) {

        // El constructor de Nave inicializa los datos comunes.
        super(nombre, modelo, estadoSistema);

        // Inicializamos el dato específico del caza.
        this.potenciaFuego = potenciaFuego;
    }

    public int getPotenciaFuego() {
        return potenciaFuego;
    }

    public void setPotenciaFuego(int potenciaFuego) {
        this.potenciaFuego = potenciaFuego;
    }
}

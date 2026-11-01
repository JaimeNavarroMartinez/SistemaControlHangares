/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut4.principal;

import java.util.List;
import ut4.controlador.HoloNetControl;
import ut4.modelo.Nave;
import ut4.util.HibernateUtil;
import ut4.modelo.CazaLigero;

/**
 *
 * @author Jaime Navarro
 */
public class MainHoloNet {

    public static void main(String[] args) {
        // Creamos el controlador que ejecuta las consultas.
        HoloNetControl control = new HoloNetControl();

        try {
            // Buscamos las naves cuyo planeta pertenece al Imperio.
            List<CazaLigero> naves = control.obtenerCazasOperativosPorBando("Imperio");

            // Mostramos cada nave usando su método toString().
            for (CazaLigero nave : naves) {
                System.out.println(nave);
            }

            System.out.println("--- NAVES CRITICAS Y SUS PLANETAS ---");

            // Recuperamos las naves críticas con sus planetas ya cargados.
            List<Nave> navesCriticas = control.obtenerFlotaCriticaPolimorfica();

            for (Nave nave : navesCriticas) {
                // Podemos acceder al planeta aunque la sesión del método ya esté cerrada.
                System.out.println(nave.getNombre() + " | Planeta: "
                        + nave.getPlanetaOrigen().getNombrePlaneta());
            }

            System.out.println("--- REPORTE DE NAVES Y PLANETAS ---");

            // Cada fila contiene el nombre de la nave y el de su planeta.
            List<Object[]> reporte = control.obtenerReporteGeneralFiltrado();

            for (Object[] fila : reporte) {
                // Cada fila contiene los tres valores del SELECT.
                System.out.println("Nave: " + fila[0]
                        + " | Tipo: " + fila[1]
                        + " | Planeta: " + fila[2]);
            }
        } finally {
            // Cerramos Hibernate aunque ocurra un error.
            HibernateUtil.shutdown();
        }
    }
}

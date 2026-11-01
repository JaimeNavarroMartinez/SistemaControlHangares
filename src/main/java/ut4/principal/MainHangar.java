/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ut4.principal;

import java.util.List;
import ut4.controlador.HangarControl;
import ut4.modelo.Nave;
import ut4.util.HibernateUtil;

/**
 *
 * @author Jaime Navarro
 */
public class MainHangar {

    public static void main(String[] args) {
        // Creamos un objeto de la clase HangarControl
        HangarControl control = new HangarControl();

        /*        
        // Creamos una nave en memoria
        Nave nave = new Nave("TIE Interceptor", "TI-v2", 1, "Crítico");
        
        // Guardamos la nave en Postgre
        control.registrarNuevaNave(nave);
         */

        /*        
        // Reparamos la nave recién guardada utilizando su ID generado
        control.actualizarEstadoNave(1, "Estable");
        System.out.println("Datos actualizados correctamente.");
         */
 
        // Buscamos las naves del modelo TI-v2 y guardamos el resultado.
        List<Nave> naves = control.obtenerCazasPorModelo("TI-v2");

        // Recorremos la lista y mostramos cada nave usando su toString().
        for (Nave nave : naves) {
            System.out.println(nave);
        }
        
        // Cerramos la fábrica al terminar
        HibernateUtil.shutdown();
    }
}

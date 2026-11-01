/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut4.principal;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ut4.modelo.Nave;
import ut4.modelo.Planeta;
import ut4.util.HibernateUtil;
import ut4.modelo.CazaLigero;

/**
 *
 * @author Jaime Navarro
 */
public class MainLogistica {

    public static void main(String[] args) {
        // Creamos el planeta.
        Planeta planeta = new Planeta("Astillero de Corellia", "Mundos del Núcleo");

        // Creamos cazas concretos, porque Nave ahora es abstracta.
        // El último argumento representa su potencia de fuego.
        Nave nave1 = new CazaLigero("Caza TIE Básico", "TIE", "Estable", 40);
        Nave nave2 = new CazaLigero("TIE Bomber", "TIE Bomber", "Estable", 80);

        // El helper conecta cada nave con el planeta en ambos sentidos.
        planeta.agregarNave(nave1);
        planeta.agregarNave(nave2);

        // Abrimos una sesión para guardar los objetos.
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaccion = null;

            try {
                transaccion = session.beginTransaction();

                // Guardamos el planeta.
                // CascadeType.PERSIST guarda también las naves de su lista.
                session.persist(planeta);

                // Confirmamos los cambios en la base de datos.
                transaccion.commit();

                System.out.println("Planeta y naves guardados. ID: " + planeta.getId());

            } catch (Exception e) {
                // Si falla, deshacemos la transacción.
                if (transaccion != null && transaccion.isActive()) {
                    transaccion.rollback();
                }

                throw e;
            }
        }

        // Abrimos otra sesión para recuperar los datos de PostgreSQL.
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Buscamos el planeta por el ID que se generó al guardarlo.
            Planeta planetaRecuperado = session.get(Planeta.class, planeta.getId());

            if (planetaRecuperado != null) {
                System.out.println("Planeta: " + planetaRecuperado.getNombrePlaneta());

                // Recorremos sus naves mientras la sesión sigue abierta.
                for (Nave nave : planetaRecuperado.getNaves()) {
                    System.out.println(nave);
                }
            }
        }

        // Cerramos Hibernate al terminar.
        HibernateUtil.shutdown();
    }
}

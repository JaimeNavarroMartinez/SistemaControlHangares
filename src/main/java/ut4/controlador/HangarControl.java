/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut4.controlador;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ut4.modelo.Nave;
import ut4.util.HibernateUtil;

/**
 *
 * @author Jaime Navarro
 */
public class HangarControl {

    public void registrarNuevaNave(Nave nave) {
        // Abrimos una sesión para trabajar con la base de datos
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction transaccion = null;

            try {
                // Iniciamos la transacción
                transaccion = session.beginTransaction();

                // Hibernate gestiona la nave y realiza su inserción
                session.persist(nave);

                // Confirmamos los cambios en la base de datos
                transaccion.commit();

                System.out.println("Nave registrada: " + nave);

            } catch (Exception e) {
                // Deshacemos los cambios si la transacción sigue activa
                if (transaccion != null && transaccion.isActive()) {
                    transaccion.rollback();
                }

                System.err.println("Error al registrar la nave: " + e.getMessage());
            }
        }
    }

    public void actualizarEstadoNave(int idNave, String nuevoEstado) {

        // Abrimos una sesión que se cerrará automáticamente
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction transaccion = null;

            try {
                // Iniciamos la transacción
                transaccion = session.beginTransaction();

                // Buscamos la nave por su clave primaria
                Nave nave = session.get(Nave.class, idNave);

                // Si existe, modificamos su estado
                if (nave != null) {
                    nave.setEstadoSistema(nuevoEstado);
                    System.out.println("Se ha modificado la nave con el id: " + nave.getId());
                } else {
                    System.out.println("No existe una nave con ID: " + idNave);
                }

                // Confirmamos los cambios
                transaccion.commit();

            } catch (Exception e) {
                // Si falla, deshacemos los cambios
                if (transaccion != null && transaccion.isActive()) {
                    transaccion.rollback();
                }

                System.err.println("Error al actualizar: " + e.getMessage());
            }
        }
    }

    public List<Nave> obtenerCazasPorModelo(String modeloBuscado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // HQL consulta la clase Nave y su atributo modelo.
            // :modelo es un parámetro cuyo valor asignamos después.
            return session.createQuery(
                    "FROM Nave WHERE modelo = :modelo", Nave.class)
                    .setParameter("modelo", modeloBuscado)
                    .getResultList();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut4.controlador;

import java.util.List;
import ut4.modelo.Nave;
import ut4.util.HibernateUtil;
import org.hibernate.Session;
import ut4.modelo.CazaLigero;

/**
 *
 * @author Jaime Navarro
 */
public class HoloNetControl {

    public List<CazaLigero> obtenerCazasOperativosPorBando(String bando) {

        // Buscamos cazas cuyo planeta pertenece al bando indicado.
        String hql = "SELECT n FROM CazaLigero n "
                + "INNER JOIN n.planetaOrigen p "
                + "WHERE TYPE(n) = CazaLigero "
                + "AND p.afiliacion = :bando "
                + "AND n.estadoSistema != 'Mantenimiento'";

        // Abrimos la sesión y la cerramos automáticamente.
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(hql, CazaLigero.class)
                    .setParameter("bando", bando)
                    .getResultList();
        }
    }

    public List<Nave> obtenerFlotaCriticaPolimorfica() {
        // FETCH carga el planeta junto con la nave en la misma consulta.
        String hql = "SELECT n FROM Nave n "
                + "INNER JOIN FETCH n.planetaOrigen "
                + "WHERE n.estadoSistema = 'Critico'";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(hql, Nave.class).getResultList();
        }
    }

    public List<Object[]> obtenerReporteGeneralFiltrado() {

    // Proyectamos el nombre, la clase concreta y el nombre del planeta.
    String hql = "SELECT n.nombre, TYPE(n), p.nombrePlaneta "
            + "FROM Nave n "
            + "LEFT JOIN n.planetaOrigen p";

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        return session.createQuery(hql, Object[].class)
                .getResultList();
    }
}
}

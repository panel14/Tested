package app.connection;

import app.pointClass.Point;
import jakarta.persistence.PersistenceContext;

import javax.persistence.EntityManager;

public class DBConnecter {

    @PersistenceContext(unitName = "Tested")
    private static EntityManager entityManager;

    public static void addToDB(Point point){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(point);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
        }
    }
}

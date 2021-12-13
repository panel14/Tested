package app.connection;

import app.pointClass.NewEntity;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.Data;

@ManagedBean
@ApplicationScoped
@Data
public class DBConnecter {
    private static final String PERSISTENCE_UNIT_NAME = "data";
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private boolean isInit = false;

    public DBConnecter(){}

    private void connection() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void addToDB(NewEntity point){
        if (!isInit){
            connection();
            isInit = true;
        }

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

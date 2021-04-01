package com.example.CovidTracker.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StateLocation implements StateDao {

    private String state;
    @PersistenceContext
    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory emf){
        this.entityManagerFactory = emf;

    }

    @Override
    public List findByState() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try{
            Query query = entityManager.createQuery("from location_data as l where l.state = ?1");
         query.setParameter(1,state);
         return query.getResultList();
        }
        finally {
            if(entityManagerFactory!=null);
            entityManagerFactory.close();
        }

    }
}

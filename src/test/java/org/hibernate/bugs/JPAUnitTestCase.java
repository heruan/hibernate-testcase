package org.hibernate.bugs;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        this.entityManagerFactory = Persistence
                .createEntityManagerFactory("h2");
    }

    @After
    public void destroy() {
        this.entityManagerFactory.close();
    }

    @Test
    public void spatialConverterTest() throws SQLException {
        EntityManager em = this.entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        SpatialEntity spatialEntity = new SpatialEntity();
        spatialEntity.setCoordinates(new Coordinates(45.5455, 11.5354));
        em.persist(spatialEntity);
        em.getTransaction().commit();
    }

}

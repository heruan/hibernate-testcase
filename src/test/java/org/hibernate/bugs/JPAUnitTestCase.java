package org.hibernate.bugs;

import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        this.entityManagerFactory = Persistence
                .createEntityManagerFactory("h2");
        EntityManager em = this.entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Foo foo = new Foo();
        foo.id = "foo";
        foo.bar = new Bar();
        foo.bar.id = "bar";
        foo.bar.name = "bar";

        em.persist(foo.bar);
        em.persist(foo);

        em.getTransaction().commit();
        em.close();
    }

    @After
    public void destroy() {
        this.entityManagerFactory.close();
    }

    @Test
    public void testTemplate() throws SQLException {
        EntityManager em = this.entityManagerFactory.createEntityManager();
        CriteriaQuery<Foo> criteriaQuery = em.getCriteriaBuilder()
                .createQuery(Foo.class);
        criteriaQuery.from(Foo.class);
        em.createQuery(criteriaQuery).getResultList();
    }

}

@Entity
class Foo {

    @Id
    String id;

    @OneToOne
    Bar bar;

}

@Entity
class Bar {

    @Id
    String id;

    String name;

}

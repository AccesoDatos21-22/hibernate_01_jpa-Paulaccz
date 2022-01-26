package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Utilidades {

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("Persistencia");

    private static EntityManager manager;
    private static EntityTransaction transaction;


    public static EntityManager getConexion() {
        manager = factory.createEntityManager();
        return manager;
    }

}

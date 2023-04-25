package Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {
    private static EntityManagerFactory factory;
    private static EntityManager em;

    public static EntityManagerFactory getFactory() {
         factory =
                Persistence.createEntityManagerFactory("ASS_Java4");
        return factory;
    }

    public static EntityManager getEntityManager() {
        em = JpaUtils.getFactory()
                .createEntityManager();

        return em;
    }
}

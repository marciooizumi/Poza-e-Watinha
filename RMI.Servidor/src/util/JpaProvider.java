package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaProvider {

    private static EntityManagerFactory emf;

    public static EntityManagerFactory getInstance() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ServidorPU");
        }
        return emf;
    }
}

package dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Settings {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityManager loadDB() throws DALException{
        if(emf == null){
            emf = javax.persistence.Persistence.createEntityManagerFactory("traitement-data");
            try{
                em = emf.createEntityManager();
            } catch (Exception e){
                throw new DALException("Erreur lors de la connexion avec entityManager");
            }
        }
        return em;
    }
    public static EntityManager getProperty() throws DALException{
        return loadDB();
    }
}
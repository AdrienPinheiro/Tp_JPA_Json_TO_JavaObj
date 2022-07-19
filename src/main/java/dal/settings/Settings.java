package dal.settings;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Try to open database connection
 */
public class Settings {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    /**
     * Return one instance if they don't exist of EntityManager
     * @return EntityManager
     */
    private static EntityManager loadDB(){
        if(emf == null){
            emf = javax.persistence.Persistence.createEntityManagerFactory("traitement-data");
            try{
                em = emf.createEntityManager();
            } catch (Exception e){ //technical exception // runtimeexception
                throw new TechnicalException("Impossible de se connecter à la base de donnée - EntityManager", e);
            }
        }
        return em;
    }

    /**
     * Return one instance of EntityManager
     * @return EntityManager
     */
    public static EntityManager getProperty(){
        return loadDB();
    }
}
import bll.json.JsonReader;
import bll.manager.ActeurManager;
import bo.Acteur;

import dal.DALException;
import dal.settings.Settings;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws DALException, IOException {
        ActeurManager acteurManager = ActeurManager.getInstance();
        try{
            Settings.getProperty();
            List<Acteur> acteurObj = JsonReader.JsonReaderFilm();
            for(Acteur acteur : acteurObj){
                acteurManager.addActeur(acteur);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

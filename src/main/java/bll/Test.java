package bll;

import bll.manager.ActeurManager;
import bll.manager.RealisateurManager;
import bo.Acteur;
import bo.Film;
import bo.Realisateur;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dal.DALException;
import dal.Settings;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Test {
    public static void main(String[] args) throws DALException, IOException {
        ActeurManager acteurManager = ActeurManager.getInstance();
        RealisateurManager realisateurManager = RealisateurManager.getInstance();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            /*byte[] mapData = Files.readAllBytes(Paths.get("src/main/resources/films.json"));
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Acteur> myObj = objectMapper.readValue(mapData,objectMapper.getTypeFactory().constructCollectionType(List.class, Acteur.class));
            System.out.println(myObj);*/
            Settings.getProperty();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
            objectMapper.registerModule(new JavaTimeModule());
            Path of = Path.of("src/main/resources/films.json");
            List<Acteur> acteurObj = objectMapper.readValue(of.toFile(), new TypeReference<List<Acteur>>(){});
            for(Acteur acteur : acteurObj){
                acteurManager.addActeur(acteur);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import bll.manager.ActeurManager;
import bll.manager.FilmManager;
import bo.Acteur;
import bo.Film;
import dal.DALException;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        FilmManager filmManager = FilmManager.getInstance();
        ActeurManager acteurManager = ActeurManager.getInstance();
        int choice = 0;
        do{
            Menu();
            choice = scan.nextInt();
            switch (choice) {
                case 1 -> {
                    // filmographie d'un artiste : Good
                    System.out.println("Quel artiste voulez-vous consulter ?");
                    scan.nextLine();
                    String acteur = scan.nextLine();
                    System.out.println(acteur);
                    List<Film> films = null;
                    try {
                        films = filmManager.getActeurFilm(acteur);
                        if (!films.isEmpty()) {
                            System.out.println(acteur + "a joué dans ces films :");
                            for (Film film : films) {
                                System.out.println(film);
                            }
                            break;
                        }
                        System.out.println("L'acteur n'a pas été trouvé.");
                    } catch (DALException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> {
                    // casting d'un film : Good
                    System.out.println("Quel film voulez-vous voir ?");
                    scan.nextLine();
                    String film = scan.nextLine();
                    List<Acteur> casting = null;
                    try {
                        casting = acteurManager.getCasting(film);
                        if (!casting.isEmpty()) {
                            System.out.println("Le casting du film " + film + " est :");
                            for (Acteur acteurCasting : casting) {
                                System.out.println(acteurCasting);
                            }
                            break;
                        }
                        System.out.println("Le film n'a pas été trouvé.");
                    } catch (DALException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 -> {
                    // Film entre deux années données : Good
                    System.out.println("Quel est l'année de départ ?");
                    int startYear = scan.nextInt();
                    System.out.println("Quel est l'année de fin ?");
                    int endYear = scan.nextInt();
                    List<Film> filmsBetween = null;
                    try {
                        filmsBetween = filmManager.selectFilmBetweenYear(startYear, endYear);
                        if (!filmsBetween.isEmpty()) {
                            System.out.println("Les films entre " + startYear + " et " + endYear + " sont :");
                            for (Film filmBetween : filmsBetween) {
                                System.out.println(filmBetween);
                            }
                            break;
                        }
                        System.out.println("Vous avez donné une valeur de départ plus grande que la valeur fin");
                    } catch (DALException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 4 -> {
                    //film commun a 2 acteur : Not Good
                    System.out.println("Premier acteur ?");
                    scan.nextLine();
                    String firstActeur = scan.nextLine();
                    System.out.println("Deuxième acteur ?");
                    String secondActeur = scan.nextLine();
                    List<Film> filmsList = null;
                    try {
                        filmsList = filmManager.selectFilmTwoActeur(firstActeur, secondActeur);
                        if (!filmsList.isEmpty()) {
                            System.out.println("Les films communs aux deux acteurs / actrices sont :");
                            for (Film filmActeur : filmsList) {
                                System.out.println(filmActeur);
                            }
                            break;
                        }
                        System.out.println("Aucun film n'a été trouvé");
                    } catch (DALException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 5 -> {
                    // film entre 2 années + acteur actrice commun : Not Good
                    System.out.println("L'acteur que vous voulez retrouver dans le film ?");
                    String acteur1 = scan.nextLine();
                    System.out.println("Année de départ ?");
                    int yearStart = scan.nextInt();
                    System.out.println("Année de fin ?");
                    int yearEnd = scan.nextInt();
                    List<Film> filmList = null;
                    try {
                        filmList = filmManager.selectFilmBetweenYearAndWithTwoActeur(yearStart, yearEnd, acteur1);
                        if (!filmList.isEmpty()) {
                            System.out.println("Les films entre " + yearStart + " et " + yearEnd + " dont l'acteur / actrice " + acteur1 + " est présent sont :");
                            for (Film acteurfilm : filmList) {
                                System.out.println(acteurfilm);
                            }
                            break;
                        }
                        System.out.println("Vous avez mis une date de départ plus grand que celle de fin OU votre acteur n'a pas été trouvé.");
                    } catch (DALException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 6 -> System.out.println("Aurevoir !");
            }
        } while(choice != 6);
        scan.close();
    }

    private static void Menu(){
        System.out.println("--- Menu de recherche de film ---");
        System.out.println("1. Filmographie d'un acteur");
        System.out.println("2. Casting d'un film");
        System.out.println("3. Films sortis entre 2 années");
        System.out.println("4. Films communs à 2 acteurs/actrices");
        System.out.println("5. Films sortis entre 2 années et qui ont un acteur/actrice commun au casting");
        System.out.println("6. Sortir");
    }

}

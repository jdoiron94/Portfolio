package semester_iii.game_database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jacob Doiron
 * Main file used to test the entirety of the application
 */
public class Application {

    /**
     * @param list The list to be queried
     * @return Returns the names of the games from the list (solely the names, strips extraneous info from game objects)
     */
    private static String getNames(List<? extends Game> list) {
        StringBuilder builder = new StringBuilder(500);
        for (int i = 0; i < list.size(); i++) {
            builder.append(i == list.size() - 1 ? list.get(i).getTitle() : list.get(i).getTitle() + ", ");
        }
        return builder.toString();
    }

    /**
     * Tests the application as a whole
     *
     * @param args command-line arguments containing the filename
     */
    public static void main(String... args) {
        if (args.length > 0) {
            Scanner scanner = new Scanner(System.in);
            File file = new File(args[0]);
            if (file.exists()) {
                System.out.println(file.getAbsoluteFile() + " exists");
                List<Banking> banking = new ArrayList<>(5);
                List<Trick> trick = new ArrayList<>(5);
                List<Patience> patience = new ArrayList<>(5);
                List<Tile> tile = new ArrayList<>(5);
                List<Capture> capture = new ArrayList<>(5);
                List<Strategy> strategy = new ArrayList<>(5);
                List<FPS> fps = new ArrayList<>(5);
                List<RPG> rpg = new ArrayList<>(5);
                List<Sport> sport = new ArrayList<>(5);
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.replaceAll("[\\W]|_", "").isEmpty()) {
                            continue;
                        }
                        String[] split = line.split(", ");
                        switch (split[1]) {
                            case "Banking":
                                banking.add(new Banking(Integer.parseInt(split[2]), Integer.parseInt(split[6]), split[0], split[1], split[4], split[5]));
                                break;
                            case "Trick":
                                trick.add(new Trick(Integer.parseInt(split[2]), Integer.parseInt(split[6]), split[0], split[1], split[4], split[5]));
                                break;
                            case "Patience":
                                patience.add(new Patience(Integer.parseInt(split[2]), Integer.parseInt(split[6]), split[0], split[1], split[4], split[5]));
                                break;
                            case "Tile":
                                tile.add(new Tile(Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[7]), split[0], split[1], split[4], split[5], split[6]));
                                break;
                            case "Capture":
                                capture.add(new Capture(Integer.parseInt(split[2]), Integer.parseInt(split[7]), split[0], split[1], split[4], split[5], split[6], split[3]));
                                break;
                            case "Strategy":
                                strategy.add(new Strategy(Integer.parseInt(split[2]), Integer.parseInt(split[7]), split[0], split[1], split[4], split[5], split[6]));
                                break;
                            case "FPS":
                                fps.add(new FPS(Integer.parseInt(split[2]), Integer.parseInt(split[3]), split[0], split[1], split[4], split[5], split[6], split[7]));
                                break;
                            case "RPG":
                                rpg.add(new RPG(Integer.parseInt(split[2]), Integer.parseInt(split[3]), split[0], split[1], split[4], split[5], split[6], split[7]));
                                break;
                            case "Sport":
                                sport.add(new Sport(Integer.parseInt(split[2]), split[0], split[1], split[4], split[5], split[6], split[7], split[3]));
                                break;
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("\nBanking: " + banking.size() + "\nTrick: " + trick.size() + "\nPatience: " + patience.size() + "\nTile: " + tile.size() + "\nCapture: " + capture.size() + "\nStrategy: " + strategy.size() + "\nFPS: " + fps.size() + "\nRPG: " + rpg.size() + "\nSport: " + sport.size());
                System.out.println("\nBoard: " + (tile.size() + capture.size() + strategy.size()) + "\nCard: " + (banking.size() + trick.size() + patience.size()) + "\nVideo: " + (fps.size() + rpg.size() + sport.size()));
                outer:
                while (true) {
                    System.out.print("\nEnter a genre name (banking, trick, patience, etc.): ");
                    String genre = scanner.nextLine().toLowerCase();
                    switch (genre) {
                        case "banking":
                            Collections.sort(banking, Game.COMPARATOR);
                            System.out.println("Banking: [" + getNames(banking) + "]");
                            break outer;
                        case "trick":
                            Collections.sort(trick, Game.COMPARATOR);
                            System.out.println("Trick: [" + getNames(trick) + "]");
                            break outer;
                        case "patience":
                            Collections.sort(patience, Game.COMPARATOR);
                            System.out.println("Patience: [" + getNames(patience) + "]");
                            break outer;
                        case "tile":
                            Collections.sort(tile, Game.COMPARATOR);
                            System.out.println("Tile: [" + getNames(tile) + "]");
                            break outer;
                        case "capture":
                            Collections.sort(capture, Game.COMPARATOR);
                            System.out.println("Capture: [" + getNames(capture) + "]");
                            break outer;
                        case "strategy":
                            Collections.sort(strategy, Game.COMPARATOR);
                            System.out.println("Strategy: [" + getNames(strategy) + "]");
                            break outer;
                        case "fps":
                            Collections.sort(fps, Game.COMPARATOR);
                            System.out.println("FPS: [" + getNames(fps) + "]");
                            break outer;
                        case "rpg":
                            Collections.sort(rpg, Game.COMPARATOR);
                            System.out.println("RPG: [" + getNames(rpg) + "]");
                            break outer;
                        case "sport":
                            Collections.sort(sport, Game.COMPARATOR);
                            System.out.println("Sport: [" + getNames(sport) + "]");
                            break outer;
                        default:
                            System.out.println("\"" + genre + "\" is not a valid genre");
                            break;
                    }
                }
            } else {
                System.out.println("Invalid file (" + file.getAbsolutePath() + " does not exist), enter a valid filename next time");
            }
        } else {
            System.out.println("No command-line arguments specified");
        }
    }
}
package pokemon.daycare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Crawler {

    public static void main(String[] args) {
        Set<Pokemon> a = new TreeSet<>(new PokemonComparator());
        try {
            URL[] urls = {new URL("http://bulbapedia.bulbagarden.net/wiki/Category:Pok%C3%A9mon_in_the_Erratic_experience_group"), new URL("http://bulbapedia.bulbagarden.net/wiki/Category:Pok%C3%A9mon_in_the_Fast_experience_group"), new URL("http://bulbapedia.bulbagarden.net/wiki/Category:Pok%C3%A9mon_in_the_Medium_Fast_experience_group"), new URL("http://bulbapedia.bulbagarden.net/w/index.php?title=Category:Pok%C3%A9mon_in_the_Medium_Fast_experience_group&pagefrom=Primeape+%28Pok%C3%A9mon%29#mw-pages"), new URL("http://bulbapedia.bulbagarden.net/wiki/Category:Pok%C3%A9mon_in_the_Medium_Slow_experience_group"), new URL("http://bulbapedia.bulbagarden.net/wiki/Category:Pok%C3%A9mon_in_the_Slow_experience_group"), new URL("http://bulbapedia.bulbagarden.net/wiki/Category:Pok%C3%A9mon_in_the_Fluctuating_experience_group")};
            for (int i = 0; i < urls.length; i++) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urls[i].openStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("(Pokémon)")) {
                        Relation type = null;
                        switch (i) {
                            case 0:
                                type = Relation.ERRATIC;
                                break;
                            case 1:
                                type = Relation.FAST;
                                break;
                            case 2:
                            case 3:
                                type = Relation.MEDIUM_FAST;
                                break;
                            case 4:
                                type = Relation.MEDIUM_SLOW;
                                break;
                            case 5:
                                type = Relation.SLOW;
                                break;
                            case 6:
                                type = Relation.FLUCTUATING;
                                break;
                        }
                        a.add(new Pokemon(line.split("/wiki/")[1].split("_")[0], type));
                    }
                    if (line.contains("</table>")) {
                        break;
                    }
                }
                reader.close();
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        System.out.print(a.size() + ": " + Arrays.toString(a.toArray()));
    }
}
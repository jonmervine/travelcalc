import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by darkmage530 on 10/22/2015.
 */
public class ParseList {

    public static final String NATURE_CRITS = "Pixie|Centaur|Nature Spirit|Vulpes|Dryad|Troll|Ogre|Elf|Nature Familiar|Nymph/";
    public static final String ELEMENTAL_CRITS = "Salamander|Behemoth|Sea Dweller|Titan|Hrimthur|Wyrm|Siren|Nix|Elemental Familiar|Tetramorph/";
    public static final String DIABOLIC_CRITS = "Skeleton|Cursed Being|Shade|Demon|Cerberus|Spectre|Wight|Wraith|Diabolic Familiar|Shinigami/";
    public static final String MYSTIC_CRITS = "Griffin|Djinn|Harpy|Manticore|Phoenix|Basilisk|Valkyrie|Dragon|Mystic Familiar|Pegasus/";

    public ParsedList parseList(BufferedReader in, JTextField status) {
        String str = "";
        boolean error = false;
        int critCount = 0;
        List<Crit> critList = new ArrayList<>();

        try {
            while ((str = in.readLine()) != null) {

                Pattern patCheckEnd = Pattern.compile("Please copy");
                Matcher matCheckEnd = patCheckEnd.matcher(str);

                if (matCheckEnd.find()) {
                    break;
                }

                Pattern p0 = Pattern.compile("N\\d{1,3}/D\\d{1,3}/M\\d{1,3}/E\\d{1,3}");
                Matcher m0 = p0.matcher(str);

                str = str.replaceAll("\t", "  ");
                str = str.replaceAll("&#37", "  ");
                str = str.replaceAll(";", "  ");
                str = str.replaceAll(" \\(", "");
                str = str.replaceAll("\\)", "");
                str = str.replaceAll("sacrifice, ", "");
                str = str.replaceAll("sacrifice", "");
                str = str.replaceAll("kingdom defense", "");
                str = str.replaceAll("defense", "");
                str = str.replaceAll("curse", "");
                str = str.replaceAll("cast ", "");
                str = str.replaceAll("morph ", "");
                str = str.replaceAll("     ", "  ");
                str = str.trim();

                if (str.length() > 0 && m0.find()) {

                    Pattern p = Pattern.compile("L\\d{1,3}");
                    Matcher m = p.matcher(str);
                    m.find();
                    String level = m.group();
                    String[] kant = p.split(str);

                    p = Pattern.compile("Elemental|Diabolic|Mystic|Nature");
                    m = p.matcher(kant[0]);
                    m.find();
                    String mageClass = m.group();

                    if (mageClass.equals("Mystic")) {
                        p = Pattern.compile(MYSTIC_CRITS);
                    } else if (mageClass.equals("Diabolic")) {
                        p = Pattern.compile(DIABOLIC_CRITS);
                    } else if (mageClass.equals("Elemental")) {
                        p = Pattern.compile(ELEMENTAL_CRITS);
                    } else if (mageClass.equals("Nature")) {
                        p = Pattern.compile(NATURE_CRITS);
                    }
                    m = p.matcher(kant[0]);
                    m.find();
                    String race = m.group().replaceAll("/", "");

                    p = Pattern.compile("/" + mageClass);
                    String[] kantlinks = p.split(str);

                    System.out.println(kantlinks[0]);

                    p = Pattern.compile("  ");
                    String[] kantlinksa = p.split(str);

                    String name = kantlinksa[0].trim();

                    p = Pattern.compile("\\d{1,5}/\\d{1,5}");
                    m = p.matcher(kant[1]);
                    m.find();
                    String[] damhea = m.group().split("/");
                    int damage = Integer.parseInt(damhea[0]);
                    int health = Integer.parseInt(damhea[1]);

                    p = Pattern.compile("N\\d{1,3}");
                    m = p.matcher(kant[1]);
                    m.find();
                    int natureDef = Integer.parseInt(m.group().replaceAll("N", ""));

                    p = Pattern.compile("/D\\d{1,3}");
                    m = p.matcher(kant[1]);
                    m.find();
                    int diabolicDef = Integer.parseInt(m.group().replaceAll("/D", ""));

                    p = Pattern.compile("/M\\d{1,3}");
                    m = p.matcher(kant[1]);
                    m.find();
                    int mysticDef = Integer.parseInt(m.group().replaceAll("/M", ""));

                    p = Pattern.compile("/E\\d{1,3}");
                    m = p.matcher(kant[1]);
                    m.find();
                    int elementalDef = Integer.parseInt(m.group().replaceAll("/E", ""));

                    p = Pattern.compile("N\\d{1,3}/D\\d{1,3}/M\\d{1,3}/E\\d{1,3}");

                    String[] rechts = p.split(kant[1]);

                    String item = "";
                    String enchantType = "";
                    String enchantAgainst = "";

                    if (rechts.length > 1) {
                        p = Pattern.compile("\\*Immunity|\\*Rage|\\*Tranquility");
                        String[] rechtskant = p.split(rechts[1]);
                        item = rechtskant[0].trim();

                        if (rechtskant.length > 1) {
                            m = p.matcher(kant[1]);
                            m.find();

                            enchantType = m.group().replaceAll("\\*", "");
                            enchantAgainst = rechtskant[1].trim();
                        }
                    }

                    if (item.startsWith("Blank")) {
                        item = "";
                    }
                    if (item.contains("*")) {
                        item = item.substring(0, item.indexOf('*'));
                    }
                    if (enchantAgainst.startsWith("vs ")) {
                        enchantAgainst= enchantAgainst.replace("vs ", "");
                    }

                    critList.add(new Crit(name, level, mageClass, race, damage, health, elementalDef, diabolicDef, mysticDef, natureDef, item, enchantType, enchantAgainst));
                    critCount++;
                    if (name.equals("") || mageClass.equals("") || race.equals("") || (damage == 0 && health == 0) || (elementalDef == 0 && diabolicDef == 0 && mysticDef == 0 && natureDef == 0)) {
                        status.setText("ERROR: Your list in dB has wrong syntax! (copy/paste it again & restart this program!)");
                        critCount = 0;
                    }

                }
            }
            in.close();

        } catch (IllegalStateException e) {
            status.setText("ERROR: Your list in dB has wrong syntax! (copy/paste it again & restart this program!)");
            critCount = 0;
            error = true;

        } catch (ArrayIndexOutOfBoundsException e) {
            status.setText("ERROR: Your list in dB has wrong syntax! (copy/paste it again & restart this program!)");
            critCount = 0;
            error = true;

        } catch (IOException e) {
            status.setText("ERROR: Wrong Input provided (copy/paste the entire encounter please!)");
            error = true;
            critCount = 0;
        }

        return new ParsedList(error, critCount, critList);
    }

    public class ParsedList {
        private int critCount;
        private List<Crit> critList = new ArrayList<>();
        boolean error = false;

        protected ParsedList(boolean error, int critCount, List<Crit> critList) {
            this.error = error;
            this.critCount = critCount;
            this.critList = critList;
        }

        public int getCritCount() {
            return critCount;
        }

        public List<Crit> getCritList() {
            return critList;
        }

        public boolean hasErrors() {
            return error;
        }
    }
}

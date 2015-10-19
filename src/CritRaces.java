/**
 * Created by darkmage530 on 10/18/2015.
 */
public enum CritRaces {

    PIXIE("Pixie"),
    CENTAUR("Centaur"),
    NATURE_SPIRIT("Nature Spirit"),
    VULPES("Vulpes"),
    DRYAD("Dryad"),
    TROLL("Troll"),
    OGRE("Ogre"),
    ELF("Elf"),
    NYMPH("Nymph"),
    NATURE_FAMILIAR("Nature Familiar"),
    SKELETON("Skeleton"),
    CURSED_BEING("Cursed Being"),
    SHADE("Shade"),
    DEMON("Demon"),
    CERBERUS("Cerberus"),
    SPECTRE("Spectre"),
    WIGHT("Wight"),
    WRAITH("Wraith"),
    DIABOLIC_FAMiLIAR("Diabolic Familiar"),
    SHINIMGAI("Shinigami"),
    GRIFFIN("Griffin"),
    DJINN("Djinn"),
    HARPY("Harpy"),
    MANTICORE("Manticore"),
    PHOENIX("Phoenix"),
    BASILiSK("Basilisk"),
    VALKYRIE("Valkyrie"),
    DRAGON("Dragon"),
    MYSTIC_FAMILIAR("Mystic Familiar"),
    PEGASUS("Pegasus"),
    SALAMANDER("Salamander"),
    BEHEMOTH("Behemoth"),
    SEA_DWELLER("Sea Dweller"),
    TITAN("Titan"),
    HRIMTHUR("Hrimthur"),
    WYRM("Wyrm"),
    SIREN("Siren"),
    NIX("Nix"),
    ELEMENTAL_FAMILIAR("Elemental Familiar"),
    TETRAMORPH("Tetramorph"),
    OTHER("OTHER");

    private String race;

    private CritRaces(String race) {
        this.race = race;
    }

    public static CritRaces fromString(String text) {
        if (text != null) {
            for (CritRaces b : CritRaces.values()) {
                if (text.equalsIgnoreCase(b.race)) {
                    return b;
                }
            }
        }
        throw new IllegalArgumentException("derp derp");
    }
}

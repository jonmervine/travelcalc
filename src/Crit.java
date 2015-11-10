/*
 * Crit.java
 *
 * Created on 1 februari 2007, 2:17
 *
 */

import java.util.logging.Logger;

/**
 *
 * @author ORi
 */
public class Crit {

    private String name = "";
    private String level = "";
    private String mageClass = "";
    private CritRaces race;
    private int damage=0;
    private int health=0;
    private int mysticDef =0;
    private int natureDef =0;
    private int diabolicDef =0;
    private int elementalDef =0;
    private String itemtype ="";
    private String enchantType="";
    private String enchantAgainst="";

    /** Creates a new instance of Crit */
    public Crit() {

    }
    public Crit(String name, String level, String mageClass, String race, int damage, int health, int elemental,int diabolic, int mystic, int nature, String item, String enchantType, String enchantAgainst) {
        this.name = name;
        this.level = level;
        this.mageClass = mageClass;
        this.damage = damage;
        this.health = health;
        this.mysticDef =mystic;
        this.natureDef =nature;
        this.diabolicDef =diabolic;
        this.elementalDef =elemental;
        this.itemtype=item;
        this.enchantAgainst=enchantAgainst;
        this.enchantType=enchantType;

        try {
            this.race = CritRaces.fromString(race);
        } catch (IllegalArgumentException ex) {
            this.race = CritRaces.OTHER;
        }
    }
    public int getDamage(){
        return damage ;
    }
    public int getHealth(){
        return health;
    }
    public int getElementalDef(){
        return elementalDef;
    }
    public int getDiabolicDef(){
        return diabolicDef;
    }
    public int getMysticDef(){
        return mysticDef;
    }
    public int getNatureDef(){
        return natureDef;
    }
    public String getLevel(){
        return level;
    }
    public String getName(){
        return name;
    }
    public String getMageClass(){
        return mageClass;
    }
    public CritRaces getRace() { return race; }
    public String getRaceFormat() { return race.race; }
    public String getItem(){
        return itemtype;
    }
    public String getEnchantType() { return enchantType; }
    public String getEnchantAgainst() { return enchantAgainst; }
}

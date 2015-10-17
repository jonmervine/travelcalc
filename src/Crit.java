/*
 * Crit.java
 *
 * Created on 1 februari 2007, 2:17
 *
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ORi
 */
public class Crit {

    private String name = "";
    private String level = "";
    private String race = "";
    private String type = "";
    private int damage=0;
    private int health=0;
    private int mysticDef =0;
    private int natureDef =0;
    private int diabolicDef =0;
    private int elementalDef =0;
    private String itemtype ="";

    /** Creates a new instance of Crit */
    public Crit() {

    }
    public Crit(String name, String level, String race, String type, int damage, int health, int elemental,int diabolic, int mystic, int nature, String item) {
        this.name = name;
        this.level = level;
        this.race = race;
        this.type = type;
        this.damage = damage;
        this.health = health;
        this.mysticDef =mystic;
        this.natureDef =nature;
        this.diabolicDef =diabolic;
        this.elementalDef =elemental;
        this.itemtype=item;

//        if(enchant.length()>0){
//            Pattern p = Pattern.compile("MaBe|MaIm");
//            Matcher m = p.matcher(enchant);
//            m.find();
//            String mabeORmaim = m.group();
//
//            String ench = enchant.replaceAll(mabeORmaim,"");
//
//            //System.out.println(enchant.length()+" "+ench);
//
//            if(mabeORmaim.equals("MaIm")){
//                maim=ench;
//            } else if(mabeORmaim.equals("MaBe")){
//                mabe=ench;
//            }
//        }
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
    public String getRace(){
        return race;
    }
    public String getType(){
        return type;
    }
    public String getItem(){
        return itemtype;
    }

}

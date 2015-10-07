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

    private String critname = "";
    private String critlvl = "";
    private String crittype = "";
    private String critfamily = "";
    private String maim = "";
    private String mabe = "";
    private int damage=0;
    private int health=0;
    private int airdef=0;
    private int earthdef=0;
    private int deathdef=0;
    private int forestdef=0;
    private String itemtype ="";
    private int IthEff=0;
    private boolean used=false;
    private ItemModificator myItem;
    private ItemModificator enemyItem;

    /** Creates a new instance of Crit */
    public Crit() {

    }
    public Crit(String name, String lvl, String type, String soort, int dam, int hea, int forest,int death, int air, int earth, String item, String enchant, int IEff) {
        critname = name;
        critlvl = lvl;
        crittype = type;
        critfamily = soort;
        damage = dam;
        health = hea;
        airdef=air;
        earthdef=earth;
        deathdef=death;
        forestdef=forest;
        itemtype=item;
        IthEff=IEff;

        //System.out.println(enchant);

        if(enchant.length()>0){
            Pattern p = Pattern.compile("MaBe|MaIm");
            Matcher m = p.matcher(enchant);
            m.find();
            String mabeORmaim = m.group();

            String ench = enchant.replaceAll(mabeORmaim,"");

            //System.out.println(enchant.length()+" "+ench);

            if(mabeORmaim.equals("MaIm")){
                maim=ench;
            } else if(mabeORmaim.equals("MaBe")){
                mabe=ench;
            }
        }
    }
    public int getDamage(){
        return damage ;
    }
    public int getHealth(){
        return health;
    }
    public int getForestDefence(){
        return forestdef;
    }
    public int getDeathDefence(){
        return deathdef;
    }
    public int getAirDefence(){
        return airdef;
    }
    public int getEarthDefence(){
        return earthdef;
    }
    public String getLevel(){
        return critlvl;
    }
    public String getName(){
        return critname;
    }
    public String getType(){
        return crittype;
    }
    public String getFamily(){
        return critfamily;
    }
    public void setHealth(int hea){
        health=hea;
    }
    public String getItem(){
        return itemtype;
    }
    public String getMaIm(){
        return maim;
    }
    public String getMaBe(){
        return mabe;
    }
    public void setUsed(boolean u){
        used=u;
    }
    public boolean getUsed(){
        return used;
    }
    public int getIthEff(){
        return IthEff;
    }
}

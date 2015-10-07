import java.util.HashMap;
/**
 *
 * @author Robbe
 */
public class ItemDB {

    HashMap<String, ItemModificator> items = new HashMap<String, ItemModificator>();

    private static ItemDB instance;

    private ItemDB() {
        centaurItems();
        spiderItems();
        vulpesItems();
        elfItems();
        trollItems();


        cursedItems();
        skeletonItems();


    }

    static ItemDB getInstance(){
        if(instance == null){
            instance = new ItemDB();
        }
        return instance;
    }

    private void centaurItems(){
        ItemModificator im = new ItemModificator();
        im.setHealth(30*2);
        im.setPerCritType(true);
        items.put("Bronze Armour", im);

        im = new ItemModificator();
        im.setHealth(36*2);
        im.setPerCritType(true);
        items.put("Silver Armour", im);

        im = new ItemModificator();
        im.setHealth(42*2);
        im.setPerCritType(true);
        items.put("Gold Armour", im);


        im = new ItemModificator();
        im.setHealth(48*2);
        im.setPerCritType(true);
        items.put("Platinum Armour", im);


        im = new ItemModificator();
        im.setHealth(54*2);
        im.setPerCritType(true);
        items.put("Titanium Armour", im);

        im = new ItemModificator();
        im.setHealth(60*2);
        im.setPerCritType(true);
        items.put("Diamond Armour", im);

    }

    private void spiderItems() {
        ItemModificator im = new ItemModificator();
        im.setHealthPercent(-1.10);
        im.setExtraPerTurn(true);
        im.setOponent(true);
        items.put("Mild Poison", im);

        im = new ItemModificator();
        im.setHealthPercent(-1.15);
        im.setExtraPerTurn(true);
        im.setOponent(true);
        items.put("Strong Poison", im);

        im = new ItemModificator();
        im.setHealthPercent(-1.15);
        im.setExtraPerTurn(true);
        im.setOponent(true);
        items.put("Deadly Poison", im);
    }

    private void vulpesItems() {
        ItemModificator im = new ItemModificator();
        im.setHealth(50);
        im.setExtraPerTurn(true);
        items.put("Cry", im);

        im = new ItemModificator();
        im.setHealth(80);
        im.setExtraPerTurn(true);
        items.put("Bark", im);

        im = new ItemModificator();
        im.setHealth(110);
        im.setExtraPerTurn(true);
        items.put("Snarl", im);

        im = new ItemModificator();
        im.setHealth(140);
        im.setExtraPerTurn(true);
        items.put("Growl", im);

        im = new ItemModificator();
        im.setHealth(170);
        im.setExtraPerTurn(true);
        items.put("Bellow", im);

        im = new ItemModificator();
        im.setHealthPercent(1.25);
        im.setExtraPerTurn(true);
        items.put("Howl", im);

    }

    private void elfItems() {
        //TODO: CREATE ELF ITEMS YOU BITCH.

    }

    private void trollItems() {
        ItemModificator im = new ItemModificator();
        im.setDamagePercent(-1.25);
        im.setLowHealthOnly(true);
        im.setOponent(true);
        items.put("Rough Skin", im);

        im = new ItemModificator();
        im.setDamagePercent(-1.43);
        im.setLowHealthOnly(true);
        im.setOponent(true);
        items.put("Sturdy Skin", im);

        im = new ItemModificator();
        im.setDamagePercent(-1.61);
        im.setLowHealthOnly(true);
        im.setOponent(true);
        items.put("Stone Skin", im);

        im = new ItemModificator();
        im.setDamagePercent(-1.80);
        im.setLowHealthOnly(true);
        im.setOponent(true);
        items.put("Rock Skin", im);

    }

    private void cursedItems() {
        ItemModificator im = new ItemModificator();
        im.setDamage(15*2);
        im.setPerCritType(true);
        items.put("Weak Link", im);


        im = new ItemModificator();
        im.setDamage(18*2);
        im.setPerCritType(true);
        items.put("Abominable Link", im);


        im = new ItemModificator();
        im.setDamage(21*2);
        im.setPerCritType(true);
        items.put("Frightning Link", im);

        im = new ItemModificator();
        im.setDamage(24*2);
        im.setPerCritType(true);
        items.put("Nightmarish Link", im);


        im = new ItemModificator();
        im.setDamage(27*2);
        im.setPerCritType(true);
        items.put("Horrifying Link", im);

        im = new ItemModificator();
        im.setDamage(30*2);
        im.setPerCritType(true);
        items.put("Terrifying Link", im);
    }

    private void skeletonItems() {

        ItemModificator im = new ItemModificator();
        im.setDamage(20);
        im.setOnce(true);
        items.put("Dull Dagger", im);


        im = new ItemModificator();
        im.setDamage(60);
        im.setOnce(true);
        items.put("Sharp Dagger", im);

        im = new ItemModificator();
        im.setDamage(120);
        im.setOnce(true);
        items.put("Enchanted Dagger", im);

        im = new ItemModificator();
        im.setDamage(200);
        im.setOnce(true);
        items.put("Sacrificial Dagger", im);

        im = new ItemModificator();
        im.setDamage(300);
        im.setOnce(true);
        items.put("Crystal Dagger", im);

        im = new ItemModificator();
        im.setDamage(500);
        im.setOnce(true);
        items.put("Blood Dagger", im);

        im = new ItemModificator();
        im.setDamagePercent(2.0);
        im.setOnce(true);
        items.put("Infernal Dagger", im);
    }





}
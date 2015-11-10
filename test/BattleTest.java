import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darkmage530 on 10/18/2015.
 */
public class BattleTest {

    @Test
    public void testBattles() {
        int ownDamage = 22;
        ownDamage = Math.round(ownDamage * 0.2f);
        Assert.assertEquals(4, ownDamage);

        ownDamage = 23;
        ownDamage = Math.round(ownDamage * 0.2f);
        Assert.assertEquals(5, ownDamage);
    }

    @Test
    public void warScythe() {
        System.out.println("WarScythe");

        String battleOutput = "The Defender did 79 damage -> the Attacker has 921 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 900 health left.\n" +
                "The Defender did 79 damage -> the Attacker has 842 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 800 health left.\n" +
                "The Defender did 79 damage -> the Attacker has 763 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 700 health left.\n" +
                "The Defender did 79 damage -> the Attacker has 684 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 600 health left.\n" +
                "The Defender did 79 damage -> the Attacker has 605 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 500 health left.\n" +
                "The Defender did 79 damage -> the Attacker has 526 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 400 health left.\n" +
                "The Defender did 79 damage -> the Attacker has 447 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 300 health left.\n" +
                "The Defender did 79 damage -> the Attacker has 368 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 200 health left.\n" +
                "The Defender did 79 damage -> the Attacker has 289 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 100 health left.\n" +
                "The Defender did 79 damage -> the Attacker has 210 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 0 health left.\n" +
                "The Defender got killed by the Attacker.";

        StringBuilder sb = new StringBuilder();
        Battle battle = new Battle();

        Crit myCrit = new Crit("Defender", "20", "Diabolic", "Wraith", 100, 1000, 50, 50, 50, 50, "War Scythe", "", "");
        Crit enemyCrit = new Crit("Attacker", "20", "Diabolic", "Wraith", 100, 1000, 100, 100, 100, 100, "", "", "");

        CombatResult cr = battle.doBattle(myCrit, enemyCrit, 1, sb, true, new ArrayList<>(), new ArrayList<>(), true);
        System.out.println(cr);
        System.out.println(sb.toString());
        Assert.assertEquals(CombatResult.DEAD, cr);
        Assert.assertEquals(battleOutput, sb.toString());

        sb = new StringBuilder();
        battle = new Battle();

        myCrit = new Crit("Defender", "20", "Diabolic", "Wraith", 100, 1000, 50, 50, 50, 50, "Sickle", "", "");
        enemyCrit = new Crit("Attacker", "20", "Diabolic", CritRaces.SHINIMGAI.race, 100, 1000, 150, 150, 150, 150, "", "", "");

        cr = battle.doBattle(myCrit, enemyCrit, 1, sb, true, new ArrayList<>(), new ArrayList<>(), true);
        System.out.println(cr);
        System.out.println(sb.toString());
        Assert.assertEquals(CombatResult.DEAD, cr);

    }

    @Test
    public void testDivineFavor() {
        System.out.println("DivineFavor");

        String battleOutput = "The Defender did 100 damage -> the Attacker has 900 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 900 health left.\n" +
                "The Defender did 135 damage -> the Attacker has 765 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 800 health left.\n" +
                "The Defender did 182 damage -> the Attacker has 583 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 700 health left.\n" +
                "The Defender did 246 damage -> the Attacker has 337 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 600 health left.\n" +
                "The Defender did 332 damage -> the Attacker has 5 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 500 health left.\n" +
                "The Defender did 332 damage -> the Attacker has 0 health left.\n" +
                "The Attacker did 100 damage -> the Defender has 400 health left.\n" +
                "The Defender killed the Attacker.";

        StringBuilder sb = new StringBuilder();
        Battle battle = new Battle();

        Crit myCrit = new Crit("Defender", "20", "Mystic", "Valkyrie", 100, 1000, 50, 50, 50, 50, "Divine Favor", "", "");
        Crit enemyCrit = new Crit("Attacker", "20", "Diabolic", "Wraith", 100, 1000, 50, 50, 50, 50, "", "", "");

        CombatResult cr = battle.doBattle(myCrit, enemyCrit, 1, sb, true, new ArrayList<>(), new ArrayList<>(), true);

        System.out.println(cr);
        System.out.println(sb.toString());
        Assert.assertEquals(CombatResult.ALIVE, cr);
        Assert.assertEquals(battleOutput, sb.toString());
    }

    @Test
    public void testShield() {
        System.out.println("FeebleShield");
//        Attack does 105
        String battleOutput = "The Defender did 100 damage -> the Attacker has 900 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 1000 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 800 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 990 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 700 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 885 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 600 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 780 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 500 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 675 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 400 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 570 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 300 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 465 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 200 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 360 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 100 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 255 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 0 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 150 health left.\n" +
                "The Defender killed the Attacker.";

        StringBuilder sb = new StringBuilder();
        Battle battle = new Battle();

        Crit myCrit = new Crit("Defender", "20", "Diabolic", "Demon", 100, 1000, 50, 50, 50, 50, "Feeble Shield", "", "");
        Crit enemyCrit = new Crit("Attacker", "20", "Nature", "Elf", 105, 1000, 50, 50, 50, 50, "", "", "");

        CombatResult cr = battle.doBattle(myCrit, enemyCrit, 1, sb, true, new ArrayList<>(), new ArrayList<>(), true);

        System.out.println(cr);
        System.out.println(sb.toString());
        Assert.assertEquals(CombatResult.ALIVE, cr);
        Assert.assertEquals(battleOutput, sb.toString());

        System.out.println("GraterShield");
        //attacker mystic 105 defender with greatershield
        battleOutput = "The Defender did 100 damage -> the Attacker has 900 health left.\n" +
                "The Attacker did 137 damage -> the Defender has 1000 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 800 health left.\n" +
                "The Attacker did 137 damage -> the Defender has 1000 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 700 health left.\n" +
                "The Attacker did 134 damage -> the Defender has 992 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 600 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 887 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 500 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 782 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 400 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 677 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 300 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 572 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 200 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 467 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 100 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 362 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 0 health left.\n" +
                "The Attacker did 105 damage -> the Defender has 257 health left.\n" +
                "The Defender killed the Attacker.";


        sb = new StringBuilder();

        myCrit = new Crit("Defender", "20", "Diabolic", "Demon", 100, 1000, 50, 50, 50, 50, "Greater Shield", "", "");
        enemyCrit = new Crit("Attacker", "20", "Mystic", "Griffin", 105, 1000, 50, 50, 50, 50, "", "", "");

        cr = battle.doBattle(myCrit, enemyCrit, 1, sb, true, new ArrayList<>(), new ArrayList<>(), true);

        System.out.println(cr);
        System.out.println(sb.toString());
        Assert.assertEquals(CombatResult.ALIVE, cr);
        Assert.assertEquals(battleOutput, sb.toString());

        // attacker 275 Nature, defender Arche Shield
        battleOutput = "The Defender did 100 damage -> the Attacker has 900 health left.\n" +
                "The Attacker did 206 damage -> the Defender has 1000 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 800 health left.\n" +
                "The Attacker did 206 damage -> the Defender has 1000 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 700 health left.\n" +
                "The Attacker did 206 damage -> the Defender has 1000 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 600 health left.\n" +
                "The Attacker did 275 damage -> the Defender has 725 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 500 health left.\n" +
                "The Attacker did 275 damage -> the Defender has 450 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 400 health left.\n" +
                "The Attacker did 275 damage -> the Defender has 175 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 300 health left.\n" +
                "The Attacker did 275 damage -> the Defender has 0 health left.\n" +
                "The Defender got killed by the Attacker.";


        sb = new StringBuilder();

        myCrit = new Crit("Defender", "20", "Diabolic", "Demon", 100, 1000, 50, 50, 50, 50, "Arche Shield", "", "");
        enemyCrit = new Crit("Attacker", "20", "Nature", "Elf", 275, 1000, 50, 50, 50, 50, "", "", "");

        cr = battle.doBattle(myCrit, enemyCrit, 1, sb, true, new ArrayList<>(), new ArrayList<>(), true);

        System.out.println(cr);
        System.out.println(sb.toString());
        Assert.assertEquals(CombatResult.DEAD, cr);
        Assert.assertEquals(battleOutput, sb.toString());
    }

    @Test
    public void testImmunityEnchant() {

        StringBuilder sb = new StringBuilder();
        Battle battle = new Battle();

        String battleOutput = "The Defender did 100 damage -> the Attacker has 1400 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 1010 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 1300 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 960 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 1200 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 910 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 1100 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 860 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 1000 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 810 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 900 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 760 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 800 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 710 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 700 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 660 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 600 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 610 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 500 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 560 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 400 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 510 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 300 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 460 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 200 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 410 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 100 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 360 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 0 health left.\n" +
                "The Attacker did 50 damage -> the Defender has 310 health left.\n" +
                "The Defender killed the Attacker.";

        Crit myCrit = new Crit("Defender", "20", "Elemental", "Behemoth", 100, 1000, 0, 0, 0, 0, "Mash", "Immunity", "Elf");
        Crit enemyCrit = new Crit("Attacker", "20", "Nature", "Elf", 100, 1500, 50, 50, 50, 50, "", "", "");
        List<Crit> derp = new ArrayList<>();
        List<Crit> enemyDerp = new ArrayList<>();
        derp.add(myCrit);
        enemyDerp.add(myCrit);

        CombatResult cr = battle.doBattle(myCrit, enemyCrit, 1, sb, true, derp, enemyDerp, true);

        System.out.println(cr);
        System.out.println(sb.toString());
        Assert.assertEquals(CombatResult.ALIVE, cr);
        Assert.assertEquals(battleOutput, sb.toString());
    }

    @Test
     public void testRageEnchant() {
        StringBuilder sb = new StringBuilder();
        Battle battle = new Battle();

        String battleOutput =  	"The Defender did 200 damage -> the Attacker has 1300 health left.\n" +
                "The Attacker did 150 damage -> the Defender has 910 health left.\n" +
                "The Defender did 200 damage -> the Attacker has 1100 health left.\n" +
                "The Attacker did 150 damage -> the Defender has 760 health left.\n" +
                "The Defender did 200 damage -> the Attacker has 900 health left.\n" +
                "The Attacker did 150 damage -> the Defender has 610 health left.\n" +
                "The Defender did 200 damage -> the Attacker has 700 health left.\n" +
                "The Attacker did 150 damage -> the Defender has 460 health left.\n" +
                "The Defender did 200 damage -> the Attacker has 500 health left.\n" +
                "The Attacker did 150 damage -> the Defender has 310 health left.\n" +
                "The Defender did 200 damage -> the Attacker has 300 health left.\n" +
                "The Attacker did 150 damage -> the Defender has 160 health left.\n" +
                "The Defender did 200 damage -> the Attacker has 100 health left.\n" +
                "The Attacker did 150 damage -> the Defender has 10 health left.\n" +
                "The Defender did 200 damage -> the Attacker has 0 health left.\n" +
                "The Attacker did 150 damage -> the Defender has 0 health left.\n" +
                "The Defender killed the Attacker but died in the process.";

        Crit myCrit = new Crit("Defender", "20", "Elemental", "Behemoth", 100, 1000, 0, 0, 0, 0, "Mash", "Rage", "Elf");
        Crit enemyCrit = new Crit("Attacker", "20", "Nature", "Elf", 100, 1500, 50, 50, 50, 50, "", "", "");
        List<Crit> derp = new ArrayList<>();
        List<Crit> enemyDerp = new ArrayList<>();
        derp.add(myCrit);
        enemyDerp.add(myCrit);

        CombatResult cr = battle.doBattle(myCrit, enemyCrit, 1, sb, true, derp, enemyDerp, true);

        System.out.println(cr);
        System.out.println(sb.toString());
        Assert.assertEquals(CombatResult.DIP, cr);
        Assert.assertEquals(battleOutput, sb.toString());
    }

    @Test
    public void testTranquilityEnchant() {
        StringBuilder sb = new StringBuilder();
        Battle battle = new Battle();

        String battleOutput = "The Defender did 100 damage -> the Attacker has 1400 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 1925 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 1300 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 1790 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 1200 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 1655 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 1100 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 1520 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 1000 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 1385 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 900 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 1250 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 800 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 1115 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 700 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 980 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 600 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 845 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 500 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 710 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 400 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 575 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 300 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 440 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 200 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 305 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 100 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 170 health left.\n" +
                "The Defender did 100 damage -> the Attacker has 0 health left.\n" +
                "The Attacker did 135 damage -> the Defender has 35 health left.\n" +
                "The Defender killed the Attacker.";

        Crit myCrit = new Crit("Defender", "20", "Elemental", "Behemoth", 100, 1000, 15, 15, 15, 15, "Mash", "Tranquility", "Elf");
        Crit enemyCrit = new Crit("Attacker", "20", "Nature", "Elf", 100, 1500, 50, 50, 50, 50, "", "", "");
        List<Crit> derp = new ArrayList<>();
        List<Crit> enemyDerp = new ArrayList<>();
        derp.add(myCrit);
        enemyDerp.add(myCrit);

        CombatResult cr = battle.doBattle(myCrit, enemyCrit, 1, sb, true, derp, enemyDerp, true);

        System.out.println(cr);
        System.out.println(sb.toString());
        Assert.assertEquals(CombatResult.ALIVE, cr);
        Assert.assertEquals(battleOutput, sb.toString());
    }
}

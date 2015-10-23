import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

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
        /*
        Attacker: 100/1000 100/100/100/100
        Defenser: 100/1000 50/50/50/50 War Scythe
The Defender did 79 damage -> the Attacker has 921 health left.
The Attacker did 100 damage -> the Defender has 900 health left.
The Defender did 79 damage -> the Attacker has 842 health left.
The Attacker did 100 damage -> the Defender has 800 health left.
The Defender did 79 damage -> the Attacker has 763 health left.
The Attacker did 100 damage -> the Defender has 700 health left.
The Defender did 79 damage -> the Attacker has 684 health left.
The Attacker did 100 damage -> the Defender has 600 health left.
The Defender did 79 damage -> the Attacker has 605 health left.
The Attacker did 100 damage -> the Defender has 500 health left.
The Defender did 79 damage -> the Attacker has 526 health left.
The Attacker did 100 damage -> the Defender has 400 health left.
The Defender did 79 damage -> the Attacker has 447 health left.
The Attacker did 100 damage -> the Defender has 300 health left.
The Defender did 79 damage -> the Attacker has 368 health left.
The Attacker did 100 damage -> the Defender has 200 health left.
The Defender did 79 damage -> the Attacker has 289 health left.
The Attacker did 100 damage -> the Defender has 100 health left.
The Defender did 79 damage -> the Attacker has 210 health left.
The Attacker did 100 damage -> the Defender has 0 health left.
The Defender got killed by the Attacker.
         */

        StringBuilder sb = new StringBuilder();
        Battle battle = new Battle();

        Crit myCrit = new Crit("Defender", "20", "Diabolic", "Wraith", 100, 1000, 50, 50, 50, 50, "War Scythe");
        Crit enemyCrit = new Crit("Attacker", "20", "Diabolic", "Wraith", 100, 1000, 100, 100, 100, 100, "");

        CombatResult cr = battle.doBattle(myCrit, enemyCrit, 1, sb, true, new ArrayList<>(), new ArrayList<>(), true);
        System.out.println(cr);
        System.out.println(sb.toString());
        Assert.assertEquals(CombatResult.DEAD, cr);
    }

    @Test
    public void testDivineFavor() {
        System.out.println("DivineFavor");
        /* Attacker 100/1000 50/50/50/50
         Defender 100/1000 50/50/50/50 Valkyrie Lancer with Divine Favor
         	The Defender did 100 damage -> the Attacker has 900 health left.
The Attacker did 100 damage -> the Defender has 900 health left.
The Defender did 135 damage -> the Attacker has 765 health left.
The Attacker did 100 damage -> the Defender has 800 health left.
The Defender did 182 damage -> the Attacker has 583 health left.
The Attacker did 100 damage -> the Defender has 700 health left.
The Defender did 246 damage -> the Attacker has 337 health left.
The Attacker did 100 damage -> the Defender has 600 health left.
The Defender did 332 damage -> the Attacker has 5 health left.
The Attacker did 100 damage -> the Defender has 500 health left.
The Defender did 332 damage -> the Attacker has 0 health left.
The Attacker did 100 damage -> the Defender has 400 health left.
The Defender killed the Attacker.
         */

        StringBuilder sb = new StringBuilder();
        Battle battle = new Battle();

        Crit myCrit = new Crit("Defender", "20", "Mystic", "Valkyrie", 100, 1000, 50, 50, 50, 50, "Divine Favor");
        Crit enemyCrit = new Crit("Attacker", "20", "Diabolic", "Wraith", 100, 1000, 50, 50, 50, 50, "");

        CombatResult cr = battle.doBattle(myCrit, enemyCrit, 1, sb, true, new ArrayList<>(), new ArrayList<>(), true);

        System.out.println(cr);
        System.out.println(sb.toString());
        Assert.assertEquals(CombatResult.ALIVE, cr);
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

        Crit myCrit = new Crit("Defender", "20", "Diabolic", "Demon", 100, 1000, 50, 50, 50, 50, "Feeble Shield");
        Crit enemyCrit = new Crit("Attacker", "20", "Nature", "Elf", 105, 1000, 50, 50, 50, 50, "");

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

        myCrit = new Crit("Defender", "20", "Diabolic", "Demon", 100, 1000, 50, 50, 50, 50, "Greater Shield");
        enemyCrit = new Crit("Attacker", "20", "Mystic", "Griffin", 105, 1000, 50, 50, 50, 50, "");

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

        myCrit = new Crit("Defender", "20", "Diabolic", "Demon", 100, 1000, 50, 50, 50, 50, "Arche Shield");
        enemyCrit = new Crit("Attacker", "20", "Nature", "Elf", 275, 1000, 50, 50, 50, 50, "");

        cr = battle.doBattle(myCrit, enemyCrit, 1, sb, true, new ArrayList<>(), new ArrayList<>(), true);

        System.out.println(cr);
        System.out.println(sb.toString());
        Assert.assertEquals(CombatResult.DEAD, cr);
        Assert.assertEquals(battleOutput, sb.toString());
    }
}

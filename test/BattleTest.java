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

        CombatResult cr = battle.doBattle(myCrit, enemyCrit, 1, sb, true, new ArrayList<>(), new ArrayList<>());

        System.out.println(cr);
        System.out.println(sb.toString());
    }
}

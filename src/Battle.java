import java.util.logging.Logger;

/**
 * Created by darkmage530 on 10/17/2015.
 */
public class Battle {

    private static Logger theLogger = Logger.getLogger(Battle.class.getName());


    public CombatResult doBattle(Crit crit1, Crit crit2, int battleNum, StringBuilder sb, boolean ShowFullBattles, int raceCount) {

        /* defense CRIT1 ophalen */
        double damage1 = crit1.getDamage();
        double health1 = crit1.getHealth();
        int def1 = 0;
        /* def ophalen */
        if (crit2.getRace().equals("Nature")) {
            def1 = crit1.getElementalDef();
            //System.out.println("Crit2's class is 'forest'");
        } else if (crit2.getRace().equals("Diabolic")) {
            def1 = crit1.getDiabolicDef();
            //System.out.println("Crit2's class is 'death'");
        } else if (crit2.getRace().equals("Mystic")) {
            def1 = crit1.getMysticDef();
            //System.out.println("Crit2's class is 'air'");
        } else if (crit2.getRace().equals("Elemental")) {
            def1 = crit1.getNatureDef();
            //System.out.println("Crit2's class is 'earth'");
        } else {
            theLogger.warning("This Creature(2) Type is Not Found='" + crit2.getRace() + "'");
        }

        /* defense CRIT2 ophalen */
        double damage2 = crit2.getDamage();
        double health2 = crit2.getHealth();
        int def2 = 0;
        /* def ophalen */
        if (crit1.getRace().equals("Nature")) {
            def2 = crit2.getElementalDef();
            //System.out.println("Crit1's class is 'forest'");
        } else if (crit1.getRace().equals("Diabolic")) {
            def2 = crit2.getDiabolicDef();
            //System.out.println("Crit1's class is 'death'");
        } else if (crit1.getRace().equals("Mystic")) {
            def2 = crit2.getMysticDef();
            //System.out.println("Crit1's class is 'air'");
        } else if (crit1.getRace().equals("Elemental")) {
            def2 = crit2.getNatureDef();
            //System.out.println("Crit1's class is 'earth'");
        } else {
            theLogger.warning("This Creature(1) Type is Not Found='" + crit2.getRace() + "'");
        }

        /* CRIT1: MASS CRITS */
        if (crit1.getItem().equals("Dust Cloud") || crit1.getItem().equals("Weak Link")) {
            damage1 += ((15*2) * (raceCount));
        } else if (crit1.getItem().equals("Ash Cloud") || crit1.getItem().equals("Abominable Link")) {
            damage1 += ((18*2) * (raceCount));
        } else if (crit1.getItem().equals("Sand Cloud") || crit1.getItem().equals("Frightning Link")) {
            damage1 += ((21*2) * (raceCount));
        } else if (crit1.getItem().equals("Heat Mist") || crit1.getItem().equals("Nightmarish Link")) {
            damage1 += ((24*2) * (raceCount));
        } else if (crit1.getItem().equals("Poison Mist") || crit1.getItem().equals("Horrifying Link")) {
            damage1 += ((27*2) * (raceCount));
        } else if (crit1.getItem().equals("Acid Mist") || crit1.getItem().equals("Terrifying Link")) {
            damage1 += ((30*2) * (raceCount));
        }else if(crit1.getItem().equals("Bronze Armour")|| crit1.getItem().equals("Mash")){
            health1 += ((30*2) * (raceCount));
        }else if(crit1.getItem().equals("Silver Armour")|| crit1.getItem().equals("Stomp")){
            health1 += ((36*2) * (raceCount));
        }else if(crit1.getItem().equals("Gold Armour")|| crit1.getItem().equals("Squash")){
            health1 += ((42*2) * (raceCount));
        }else if(crit1.getItem().equals("Platinum Armour")|| crit1.getItem().equals("Smash")){
            health1 += ((48*2) * (raceCount));
        }else if(crit1.getItem().equals("Titanium Armour")|| crit1.getItem().equals("Trample")){
            health1 += ((54*2) * (raceCount));
        }else if(crit1.getItem().equals("Diamond Armour")|| crit1.getItem().equals("Crush")){
            health1 += ((60*2) * (raceCount));
        }


        /* toon welke crits het tegen elkaar opnemen */
        sb.append("<i>Battle&nbsp;" + battleNum + "</i>&nbsp;&nbsp;&nbsp;<b>" + crit1.getName() + " " + ((int) crit1.getDamage()) + "/" + ((int) crit1.getHealth()) + " " + crit1.getItem() + "</b><br>");
        sb.append("- <i>vs</i> -&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>" + crit2.getName() + " " + ((int) crit2.getDamage()) + "/" + ((int) crit2.getHealth()) + " " + crit2.getItem() + "</b><br>");

        /* intialisatie */
        int schade1, schade2;
        int teller = 50;

        /* starten met het sb */
        while (health1 > 0 && health2 > 0 && teller > 0) {

            /* item influence during battle */
            /* crit1: golem */

            if(crit1.getItem().equals("Sacrificial Dagger") && teller == 50){
                damage1 += 200;
            }else if(crit1.getItem().equals("Land Slide")){
                damage2 -= damage2*0.04;
            }else if(crit1.getItem().equals("Flash Flood")){
                damage2 -= damage2*0.06;
            }else if(crit1.getItem().equals("Fire Storm")){
                damage2 -= damage2*0.08;
            }else if(crit1.getItem().equals("Typhoon")){
                damage2 -= damage2*0.10;
            }else if(crit1.getItem().equals("Sand Storm")){
                damage2 -= damage2*0.12;
            }else if(crit1.getItem().equals("Deep Freeze")){
                damage2 -= damage2*0.15;
            }else if(crit1.getItem().equals("Cry")){
                damage1 += 50;
            }else if(crit1.getItem().equals("Bark")){
                damage1 += 80;
            }else if(crit1.getItem().equals("Snarl")){
                damage1 += 110;
            }else if(crit1.getItem().equals("Growl")){
                damage1 += 140;
            }else if(crit1.getItem().equals("Bellow")){
                damage1 += 170;
            }else if(crit1.getItem().equals("Howl")){
                damage1 += crit1.getDamage()*0.25;
            }else if(crit1.getItem().equals("Mild Poison")){
                damage1 += crit2.getHealth()*0.10;
            }else if(crit1.getItem().equals("Strong Poison")){
                damage1 += crit2.getHealth()*0.15;
            }else if(crit1.getItem().equals("Deadly Poison")){
                damage1 += crit2.getHealth()*0.20;
            }else if(crit1.getItem().equals("Deadly Poison")){
                damage1 += crit2.getHealth()*0.20;
            }


            /* limit */
            if (damage1 < 0) {
                damage1 = 0;
            }
            if (damage2 < 0) {
                damage2 = 0;
            }
            if (def1 < 0) {
                def1 = 0;
            }
            if (def2 < 0) {
                def2 = 0;
            }


            /* bereken schade da 1 aan 2 doet */
            double sch1 = (150 - def2) * damage1 / 100;
            schade1 = (int) Math.round(sch1);


            /* bereken schade da 2 aan 1 doet */
            double sch2 = (150 - def1) * damage2 / 100;
            schade2 = (int) Math.round(sch2);



            if(crit1.getItem().equals("Soaring Wings")){
                schade2 -= schade2*0.10;
            }else if(crit1.getItem().equals("Cleaving Talons")){
                schade2 -= schade2*0.15;
            }else if(crit1.getItem().equals("Menacing Beak")){
                schade2 -= schade2*0.20;
            }else if(crit1.getItem().equals("Slicing Tail")){
                schade2 -= schade2*0.25;
            }else if(crit1.getItem().equals("Fierce Manes")){
                schade2 -= schade2*0.30;
            }else if(crit1.getItem().equals("Deafening Roar")){
                schade2 -= schade2*0.35;
            }

            /* laat crit 1 op crit2 slaan */
            health2 = health2 - schade1;

            /* laat crit2 op crit1 slaan */
            health1 = health1 - schade2;

            if(crit1.getItem().equals("Sapping Touch")){
                health1 += schade1*0.05;
            }else if(crit1.getItem().equals("Draining Touch")){
                health1 += schade1*0.15;
            }else if(crit1.getItem().equals("Vampiric Touch")){
                health1 += schade1*0.25;
            }else if(crit1.getItem().equals("Sanguine Touch")){
                health1 += schade1*0.35;
            }


            if (health1 < 0) {
                if(crit1.getItem().equals("Flame of Renewal")){
                    health1 = crit1.getHealth()*0.35;
                    damage1 = crit1.getDamage()*0.35;
                }else if(crit1.getItem().equals("Fire of Renewal")){
                    health1 = crit1.getHealth()*0.45;
                    damage1 = crit1.getDamage()*0.45;
                }else if(crit1.getItem().equals("Inferno of Renewal")){
                    health1 = crit1.getHealth()*0.55;
                    damage1 = crit1.getDamage()*0.55;
                }else{
                    health1 = 0;
                }
            }
            if (health2 < 0) {
                health2 = 0;
            }

            /* Gevecht naar scherm sturen */
            if (ShowFullBattles) {
                sb.append("Your " + crit1.getName() + " did " + ((int) schade1) + " damage -> opponent's " + crit2.getName() + " has " + ((int) health2) + " health left.<br>");
                sb.append("Opponent's " + crit2.getName() + " did " + ((int) schade2) + " damage -> your " + crit1.getName() + " has " + ((int) health1) + " health left.<br>");
            }

            if(crit1.getItem().equals("SaDa") || crit1.getItem().equals("Sacrificial Dagger") && teller == 50){
                damage1 -= 200;
            }

            teller--;

        }

        if (health1 <= 0 && health2 > 0) {
            sb.append("<font color=\"red\">Your " + crit1.getName() + " got killed by your opponent's " + crit2.getName() + ".</font><br><br>");
            return CombatResult.DEAD;
        } else if (health1 != 0 && health2 <= 0) {
            sb.append("<font color=\"green\">Your " + crit1.getName() + " killed your opponent's " + crit2.getName() + ".</font><br><br>");
        } else if (health1 <= 0 && health2 <= 0) {
            sb.append("<font color=\"#CC6600\">Your " + crit1.getName() + " killed your opponent's " + crit2.getName() + " but died in the process.</font><br><br>");
            return CombatResult.DIP;
        } else if (teller == 0) {
            sb.append("<font color=\"red\">Your " + crit1.getName() + " got exhausted and died.</font><br><br>");
            return CombatResult.DEAD;
        }

        return CombatResult.ALIVE;
    }

}

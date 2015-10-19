import java.util.List;
import java.util.logging.Logger;

/**
 * Created by darkmage530 on 10/17/2015.
 */
public class Battle {

    private static Logger theLogger = Logger.getLogger(Battle.class.getName());


    public CombatResult doBattle(Crit ownCrit, Crit enemyCrit, int battleNum, StringBuilder sb, boolean ShowFullBattles,
                                 List<Crit> ownCrits, List<Crit> enemyCrits) {

        int enemyDamage = enemyCrit.getDamage();
        int enemyHealth = enemyCrit.getHealth();
        int ownDamage = ownCrit.getDamage();
        int ownHealth = ownCrit.getHealth();

        int dragonDefenseReduction = Integer.MIN_VALUE;

        int enemyDefense = 0;
        if (ownCrit.getMageClass().equals("Elemental")) {
            enemyDefense = enemyCrit.getElementalDef();
        } else if (ownCrit.getMageClass().equals("Diabolic")) {
            enemyDefense = enemyCrit.getDiabolicDef();
        } else if (ownCrit.getMageClass().equals("Mystic")) {
            enemyDefense = enemyCrit.getMysticDef();
        } else if (ownCrit.getMageClass().equals("Nature")) {
            enemyDefense = enemyCrit.getNatureDef();
        } else {
            theLogger.warning("This Creature(2) Type is Not Found='" + enemyCrit.getMageClass() + "'");
        }

        int ownDefense = 0;
        if (enemyCrit.getMageClass().equals("Elemental")) {
            ownDefense = ownCrit.getElementalDef();
        } else if (ownCrit.getMageClass().equals("Diabolic")) {
            ownDefense = ownCrit.getDiabolicDef();
        } else if (ownCrit.getMageClass().equals("Mystic")) {
            ownDefense = ownCrit.getMysticDef();
        } else if (ownCrit.getMageClass().equals("Nature")) {
            ownDefense = ownCrit.getNatureDef();
        } else {
            theLogger.warning("This Creature(1) Type is Not Found='" + enemyCrit.getMageClass() + "'");
        }

        switch (ownCrit.getRace()) {
            case CENTAUR:
            case BEHEMOTH:
                ownHealth = updateMassCritHealth(ownCrit.getItem(), ownHealth, countCritRace(ownCrit.getRace(), ownCrits, enemyCrits.size()));
                break;
            case DJINN:
            case CURSED_BEING:
                ownDamage = updateMassCritDamage(ownCrit.getItem(), ownDamage, countCritRace(ownCrit.getRace(), ownCrits, enemyCrits.size()));
                break;
            case DRAGON:
                if (ownCrit.getItem().equals("Heat Breath")) {
                    dragonDefenseReduction = 5;
                    ownDefense = ownDefense + 20;
                } else if (ownCrit.getItem().equals("Snow Breath")) {
                    dragonDefenseReduction = 9;
                    ownDefense = ownDefense + 35;
                } else if (ownCrit.getItem().equals("Fire Breath")) {
                    dragonDefenseReduction = 12;
                    ownDefense = ownDefense + 50;
                } else if (ownCrit.getItem().equals("Infernal Breath")) {
                    dragonDefenseReduction = 9;
                    ownDefense = ownDefense + 50;
                }
                break;
            case HRIMTHUR:
                if (ownCrit.getItem().equals("Club")) {
                    if (enemyCrit.getMageClass().equals("Mystic")) {
                        ownDamage = ownDamage * 2;
                    } else {
                        ownDamage += Math.round(ownDamage * 0.2f);
                    }
                } else if (ownCrit.getItem().equals("Stone Club")) {
                    if (enemyCrit.getMageClass().equals("Diabolic")) {
                        ownDamage = ownDamage * 2;
                    } else {
                        ownDamage += Math.round(ownDamage * 0.25f);
                    }
                } else if (ownCrit.getItem().equals("Spiked Club")) {
                    if (enemyCrit.getMageClass().equals("Elemental")) {
                        ownDamage = ownDamage * 2;
                    } else {
                        ownDamage += Math.round(ownDamage * 0.3f);
                    }
                }
                break;
            case WRAITH:
                if (ownCrit.getItem().equals("Sickle")) {
                    enemyDefense = (int)Math.floor(enemyDefense - ((140 - enemyDefense)/3.0));
                } else if (ownCrit.getItem().equals("Kama")) {
                    enemyDefense = (int)Math.floor(enemyDefense - ((155 - enemyDefense)/3.0));
                } else if (ownCrit.getItem().equals("Scythe")) {
                    enemyDefense = (int)Math.floor(enemyDefense - ((170 - enemyDefense)/3.0));
                } else if (ownCrit.getItem().equals("War Scythe")) {
                    enemyDefense = (int)Math.floor(enemyDefense - ((185 - enemyDefense)/3.0));
                }
                break;
            case ELF:
                int raceCount = (countCritRace(ownCrit.getRace(), ownCrits, enemyCrits.size()) - 1);
                if (ownCrit.getItem().equals("Elven Rapier")) {
                    int reduction = 7 * raceCount;
                    ownDamage += (int)Math.round(((25 - reduction)/100.0) * ownDamage);
                    ownHealth = (int)Math.round(((25 - reduction)/100.0) * ownHealth);
                } else if (ownCrit.getItem().equals("Elven Foil")) {
                    int reduction = 8 * raceCount;
                    ownDamage += (int)Math.round(((30 - reduction) / 100.0) * ownDamage);
                    ownHealth = (int)Math.round(((30 - reduction)/100.0) * ownHealth);
                } else if (ownCrit.getItem().equals("Elven Dagger")) {
                    int reduction = 9 * raceCount;
                    ownDamage += (int)Math.round(((35 - reduction) / 100.0) * ownDamage);
                    ownHealth = (int)Math.round(((35 - reduction) / 100.0) * ownHealth);
                } else if (ownCrit.getItem().equals("Elven Greatsword")) {
                    int reduction = 10 * raceCount;
                    ownDamage += (int)Math.round(((40 - reduction) / 100.0) * ownDamage);
                    ownHealth = (int)Math.round(((40 - reduction) / 100.0) * ownHealth);
                }
                break;
            case NATURE_SPIRIT:
                if (ownCrit.getItem().equals("Earth Skin") && enemyCrit.getMageClass().equals("Mystic")) {
                    ownHealth = ownHealth * 2;
                } else if (ownCrit.getItem().equals("Fierce Wind") && enemyCrit.getMageClass().equals("Elemental")) {
                    ownHealth = ownHealth * 2;
                } else if (ownCrit.getItem().equals("Tranquil Water") && enemyCrit.getMageClass().equals("Nature")) {
                    ownHealth = ownHealth * 2;
                }
                break;
            default:
                break;
        }

        ownDefense = checkDefenses(ownDefense);
        enemyDefense = checkDefenses(enemyDefense);
        sb.append("<i>Battle&nbsp;" + battleNum + "</i>&nbsp;&nbsp;&nbsp;<b>" + ownCrit.getName() + " " + ((int) ownCrit.getDamage()) + "/" + ((int) ownCrit.getHealth()) + " " + ownCrit.getItem() + "</b><br>");
        sb.append("- <i>vs</i> -&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>" + enemyCrit.getName() + " " + ((int) enemyCrit.getDamage()) + "/" + ((int) enemyCrit.getHealth()) + " " + enemyCrit.getItem() + "</b><br>");

        int schade1, schade2;
        int teller = 50;




        /* starten met het sb */
        while (ownHealth > 0 && enemyHealth > 0 && teller > 0) {

            if (dragonDefenseReduction != Integer.MIN_VALUE) {
                ownDefense = (ownDefense - dragonDefenseReduction) < 0 ? 0 : ownDefense - dragonDefenseReduction;
            }

            if (ownCrit.getItem().equals("Sacrificial Dagger") && teller == 50) {
                ownDamage += 200;
            } else if (ownCrit.getItem().equals("Land Slide")) {
                enemyDamage -= enemyDamage * 0.04;
            } else if (ownCrit.getItem().equals("Flash Flood")) {
                enemyDamage -= enemyDamage * 0.06;
            } else if (ownCrit.getItem().equals("Fire Storm")) {
                enemyDamage -= enemyDamage * 0.08;
            } else if (ownCrit.getItem().equals("Typhoon")) {
                enemyDamage -= enemyDamage * 0.10;
            } else if (ownCrit.getItem().equals("Sand Storm")) {
                enemyDamage -= enemyDamage * 0.12;
            } else if (ownCrit.getItem().equals("Deep Freeze")) {
                enemyDamage -= enemyDamage * 0.15;
            } else if (ownCrit.getItem().equals("Cry")) {
                ownDamage += 50;
            } else if (ownCrit.getItem().equals("Bark")) {
                ownDamage += 80;
            } else if (ownCrit.getItem().equals("Snarl")) {
                ownDamage += 110;
            } else if (ownCrit.getItem().equals("Growl")) {
                ownDamage += 140;
            } else if (ownCrit.getItem().equals("Bellow")) {
                ownDamage += 170;
            } else if (ownCrit.getItem().equals("Howl")) {
                ownDamage += ownCrit.getDamage() * 0.25;
            } else if (ownCrit.getItem().equals("Mild Poison")) {
                ownDamage += enemyCrit.getHealth() * 0.10;
            } else if (ownCrit.getItem().equals("Strong Poison")) {
                ownDamage += enemyCrit.getHealth() * 0.15;
            } else if (ownCrit.getItem().equals("Deadly Poison")) {
                ownDamage += enemyCrit.getHealth() * 0.20;
            } else if (ownCrit.getItem().equals("Deadly Poison")) {
                ownDamage += enemyCrit.getHealth() * 0.20;
            }


            /* limit */
            if (ownDamage < 0) {
                ownDamage = 0;
            }
            if (enemyDamage < 0) {
                enemyDamage = 0;
            }
            if (enemyDefense < 0) {
                enemyDefense = 0;
            }
            if (ownDefense < 0) {
                ownDefense = 0;
            }


            /* bereken schade da 1 aan 2 doet */
            double sch1 = (150 - enemyDefense) * ownDamage / 100;
            schade1 = (int) Math.round(sch1);


            /* bereken schade da 2 aan 1 doet */
            double sch2 = (150 - ownDefense) * enemyDamage / 100;
            schade2 = (int) Math.round(sch2);

            if (ownCrit.getItem().equals("Soaring Wings")) {
                schade2 -= schade2 * 0.10;
            } else if (ownCrit.getItem().equals("Cleaving Talons")) {
                schade2 -= schade2 * 0.15;
            } else if (ownCrit.getItem().equals("Menacing Beak")) {
                schade2 -= schade2 * 0.20;
            } else if (ownCrit.getItem().equals("Slicing Tail")) {
                schade2 -= schade2 * 0.25;
            } else if (ownCrit.getItem().equals("Fierce Manes")) {
                schade2 -= schade2 * 0.30;
            } else if (ownCrit.getItem().equals("Deafening Roar")) {
                schade2 -= schade2 * 0.35;
            }

            /* laat crit 1 op enemyCrit slaan */
            enemyHealth = enemyHealth - schade1;

            /* laat enemyCrit op ownCrit slaan */
            ownHealth = ownHealth - schade2;

            if (ownCrit.getItem().equals("Sapping Touch")) {
                ownHealth += schade1 * 0.05;
            } else if (ownCrit.getItem().equals("Draining Touch")) {
                ownHealth += schade1 * 0.15;
            } else if (ownCrit.getItem().equals("Vampiric Touch")) {
                ownHealth += schade1 * 0.25;
            } else if (ownCrit.getItem().equals("Sanguine Touch")) {
                ownHealth += schade1 * 0.35;
            }


            if (ownHealth < 0) {
                if (ownCrit.getItem().equals("Flame of Renewal")) {
                    ownHealth = (int)Math.round(ownCrit.getHealth() * 0.35);
                    ownDamage = (int)Math.round(ownCrit.getDamage() * 0.35);
                } else if (ownCrit.getItem().equals("Fire of Renewal")) {
                    ownHealth = (int)Math.round(ownCrit.getHealth() * 0.45);
                    ownDamage = (int)Math.round(ownCrit.getDamage() * 0.45);
                } else if (ownCrit.getItem().equals("Inferno of Renewal")) {
                    ownHealth = (int)Math.round(ownCrit.getHealth() * 0.55);
                    ownDamage = (int)Math.round(ownCrit.getDamage() * 0.55);
                } else {
                    ownHealth = 0;
                }
            }
            if (enemyHealth < 0) {
                enemyHealth = 0;
            }

            /* Gevecht naar scherm sturen */
            if (ShowFullBattles) {
                sb.append("Your " + ownCrit.getName() + " did " + ((int) schade1) + " damage -> opponent's " + enemyCrit.getName() + " has " + ((int) enemyHealth) + " health left.<br>");
                sb.append("Opponent's " + enemyCrit.getName() + " did " + ((int) schade2) + " damage -> your " + ownCrit.getName() + " has " + ((int) ownHealth) + " health left.<br>");
            }

            if (ownCrit.getItem().equals("SaDa") || ownCrit.getItem().equals("Sacrificial Dagger") && teller == 50) {
                ownDamage -= 200;
            }
            teller--;
        }

        if (ownHealth <= 0 && enemyHealth > 0) {
            sb.append("<font color=\"red\">Your " + ownCrit.getName() + " got killed by your opponent's " + enemyCrit.getName() + ".</font><br><br>");
            return CombatResult.DEAD;
        } else if (ownHealth != 0 && enemyHealth <= 0) {
            sb.append("<font color=\"green\">Your " + ownCrit.getName() + " killed your opponent's " + enemyCrit.getName() + ".</font><br><br>");
        } else if (ownHealth <= 0 && enemyHealth <= 0) {
            sb.append("<font color=\"#CC6600\">Your " + ownCrit.getName() + " killed your opponent's " + enemyCrit.getName() + " but died in the process.</font><br><br>");
            return CombatResult.DIP;
        } else if (teller == 0) {
            sb.append("<font color=\"red\">Your " + ownCrit.getName() + " got exhausted and died.</font><br><br>");
            return CombatResult.DEAD;
        }

        return CombatResult.ALIVE;
    }

    private int updateMassCritDamage(String item, int damage, int raceCount) {
        if (item.equals("Dust Cloud") || item.equals("Weak Link")) {
            return damage + ((15 * 2) * (raceCount));
        } else if (item.equals("Ash Cloud") || item.equals("Abominable Link")) {
            return damage + ((18 * 2) * (raceCount));
        } else if (item.equals("Sand Cloud") || item.equals("Frightning Link")) {
            return damage + ((21 * 2) * (raceCount));
        } else if (item.equals("Heat Mist") || item.equals("Nightmarish Link")) {
            return damage + ((24 * 2) * (raceCount));
        } else if (item.equals("Poison Mist") || item.equals("Horrifying Link")) {
            return damage + ((27 * 2) * (raceCount));
        } else if (item.equals("Acid Mist") || item.equals("Terrifying Link")) {
            return damage + ((30 * 2) * (raceCount));
        } else {
            return damage;
        }
    }

    private int updateMassCritHealth(String item, int health, int raceCount) {
        if (item.equals("Bronze Armour") || item.equals("Mash")) {
            return health + ((30 * 2) * (raceCount));
        } else if (item.equals("Silver Armour") || item.equals("Stomp")) {
            return health + ((36 * 2) * (raceCount));
        } else if (item.equals("Gold Armour") || item.equals("Squash")) {
            return health + ((42 * 2) * (raceCount));
        } else if (item.equals("Platinum Armour") || item.equals("Smash")) {
            return health + ((48 * 2) * (raceCount));
        } else if (item.equals("Titanium Armour") || item.equals("Trample")) {
            return health + ((54 * 2) * (raceCount));
        } else if (item.equals("Diamond Armour") || item.equals("Crush")) {
            return health + ((60 * 2) * (raceCount));
        } else {
            return health;
        }
    }

    private int checkDefenses(int defense) {
        if (defense > 140) {
            return 140;
        } else if (defense < 0 ) {
            return 0;
        }
        else {
            return defense;
        }
    }

    private int countCritRace(CritRaces race, List<Crit> crits, int enemySize) {
        int count = 0;
        for (int i = 0; i < enemySize; i++) {
            if (crits.get(i).getRace().equals(race)) {
                count++;
            }
        }
        return count;
    }
}

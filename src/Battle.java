import java.util.List;
import java.util.logging.Logger;

/**
 * Created by darkmage530 on 10/17/2015.
 */
public class Battle {

    private static Logger theLogger = Logger.getLogger(Battle.class.getName());


    public CombatResult doBattle(Crit ownCrit, Crit enemyCrit, int battleNum, StringBuilder sb, boolean ShowFullBattles,
                                 List<Crit> ownCrits, List<Crit> enemyCrits, boolean test) {

        int enemyDamage = enemyCrit.getDamage();
        int enemyHealth = enemyCrit.getHealth();
        int ownDamage = ownCrit.getDamage();
        int ownHealth = ownCrit.getHealth();

        int dragonDefenseReduction = Integer.MIN_VALUE;

        int enemyDefense = getDefense(enemyCrit, ownCrit);
        int ownDefense = getDefense(ownCrit, enemyCrit);


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
                    enemyDefense = (int) Math.floor(enemyDefense - ((140 - enemyDefense) / 3.0));
                } else if (ownCrit.getItem().equals("Kama")) {
                    enemyDefense = (int) Math.floor(enemyDefense - ((155 - enemyDefense) / 3.0));
                } else if (ownCrit.getItem().equals("Scythe")) {
                    enemyDefense = (int) Math.floor(enemyDefense - ((170 - enemyDefense) / 3.0));
                } else if (ownCrit.getItem().equals("War Scythe")) {
                    enemyDefense = (int) Math.floor(enemyDefense - ((185 - enemyDefense) / 3.0));
                }
                break;
            case ELF:
                int raceCount = (countCritRace(ownCrit.getRace(), ownCrits, enemyCrits.size()) - 1);
                if (ownCrit.getItem().equals("Elven Rapier")) {
                    int reduction = 7 * raceCount;
                    ownDamage += (int) Math.round(((25 - reduction) / 100.0) * ownDamage);
                    ownHealth += (int) Math.round(((25 - reduction) / 100.0) * ownHealth);
                } else if (ownCrit.getItem().equals("Elven Foil")) {
                    int reduction = 8 * raceCount;
                    ownDamage += (int) Math.round(((30 - reduction) / 100.0) * ownDamage);
                    ownHealth += (int) Math.round(((30 - reduction) / 100.0) * ownHealth);
                } else if (ownCrit.getItem().equals("Elven Dagger")) {
                    int reduction = 9 * raceCount;
                    ownDamage += (int) Math.round(((35 - reduction) / 100.0) * ownDamage);
                    ownHealth += (int) Math.round(((35 - reduction) / 100.0) * ownHealth);
                } else if (ownCrit.getItem().equals("Elven Greatsword")) {
                    int reduction = 10 * raceCount;
                    ownDamage += (int) Math.round(((40 - reduction) / 100.0) * ownDamage);
                    ownHealth += (int) Math.round(((40 - reduction) / 100.0) * ownHealth);
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
            case MANTICORE:
                if (ownCrit.getItem().equals("Blighted Sting")) {
                    enemyDamage = enemyDamage - Math.round(enemyDamage * 0.10f);
                } else if (ownCrit.getItem().equals("Miasmic Sting")) {
                    enemyDamage = enemyDamage - Math.round(enemyDamage * 0.15f);
                } else if (ownCrit.getItem().equals("Toxic Sting")) {
                    enemyDamage = enemyDamage - Math.round(enemyDamage * 0.20f);
                } else if (ownCrit.getItem().equals("Poisonous Sting")) {
                    enemyDamage = enemyDamage - Math.round(enemyDamage * 0.25f);
                } else if (ownCrit.getItem().equals("Venomous Sting")) {
                    enemyDamage = enemyDamage - Math.round(enemyDamage * 0.28f);
                } else if (ownCrit.getItem().equals("Deadly Sting")) {
                    enemyDamage = enemyDamage - Math.round(enemyDamage * 0.32f);
                }
                break;
            default:
                break;
        }

        ownDefense = checkDefenses(ownDefense);
        enemyDefense = checkDefenses(enemyDefense);
        if (!test) {
            sb.append("<i>Battle&nbsp;" + battleNum + "</i>&nbsp;&nbsp;&nbsp;<b>" + ownCrit.getName() + " " + (ownCrit.getDamage()) + "/" + (ownCrit.getHealth()) + " " + ownCrit.getItem() + "</b><br>");
            sb.append("- <i>vs</i> -&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>" + enemyCrit.getName() + " " + (enemyCrit.getDamage()) + "/" + (enemyCrit.getHealth()) + " " + enemyCrit.getItem() + "</b><br>");
        }

        int combatOwnDamage, combatEnemyDamage;
        int skeleTempDamage = ownDamage;
        int turns = 50;
        boolean trollTrigger = false;
        boolean wyrmTrigger = false;
        boolean phoenixTrigger = false;
        boolean demonTrigger = false;
        int demonHealth = 0, demonDefense = 0;
        boolean goodDemonShield = false;

        switch (ownCrit.getRace()) {
            case DEMON:
                demonHealth = ownHealth;
                demonDefense = ownDefense;
                if (ownCrit.getItem().equals("Feeble Shield")) {
                    ownHealth = 200;
                    ownDefense = getDefense(new DemonShield(50, 80, 20, 50), enemyCrit);
                } else if (ownCrit.getItem().equals("Sturdy Shield")) {
                    ownHealth = 300;
                    ownDefense = getDefense(new DemonShield(50, 80, 20, 50), enemyCrit);
                } else if (ownCrit.getItem().equals("Greater Shield")) {
                    ownHealth = 400;
                    ownDefense = getDefense(new DemonShield(50, 80, 20, 50), enemyCrit);
                } else if (ownCrit.getItem().equals("Elemental Shield")) {
                    ownHealth = 400;
                    ownDefense = getDefense(new DemonShield(100, 50, 0, 0), enemyCrit);
                    goodDemonShield = true;
                } else if (ownCrit.getItem().equals("Nature Shield")) {
                    ownHealth = 500;
                    ownDefense = getDefense(new DemonShield(0, 50, 0, 100), enemyCrit);
                    goodDemonShield = true;
                } else if (ownCrit.getItem().equals("Diabolic Shield")) {
                    ownHealth = 500;
                    ownDefense = getDefense(new DemonShield(0, 130, 0, 20), enemyCrit);
                    goodDemonShield = true;
                } else if (ownCrit.getItem().equals("Arche Shield")) {
                    ownHealth = 600;
                    ownDefense = getDefense(new DemonShield(50, 110, 0, 75), enemyCrit);
                    goodDemonShield = true;
                }
                break;
            default:
                break;
        }

        /* starten met het sb */
        while (ownHealth > 0 && enemyHealth > 0 && turns > 0) {

            switch (ownCrit.getRace()) {
                case DRAGON:
                    if (dragonDefenseReduction != Integer.MIN_VALUE) {
                        ownDefense = ownDefense - dragonDefenseReduction;
                    }
                    break;
                case VULPES:
                    if (ownCrit.getItem().equals("Cry")) {
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
                        ownDamage += Math.round(ownCrit.getDamage() * 0.25f);
                    }
                    break;
                case SKELETON:
                    if (turns == 50) {
                        skeleTempDamage = ownDamage;

                        if (ownCrit.getItem().equals("Dull Dagger")) {
                            ownDamage += 20;
                        } else if (ownCrit.getItem().equals("Sharp Dagger")) {
                            ownDamage += 60;
                        } else if (ownCrit.getItem().equals("Enchanted Dagger")) {
                            ownDamage += 120;
                        } else if (ownCrit.getItem().equals("Sacrificial Dagger")) {
                            ownDamage += 200;
                        } else if (ownCrit.getItem().equals("Crystal Dagger")) {
                            ownDamage += 300;
                        } else if (ownCrit.getItem().equals("Blood Dagger")) {
                            ownDamage += 500;
                        } else if (ownCrit.getItem().equals("Infernal Dagger")) {
                            ownDamage = ownDamage * 2;
                        }
                    } else {
                        ownDamage = skeleTempDamage;
                    }
                    break;
                case TROLL:
                    if (ownHealth <= ownCrit.getHealth() && !trollTrigger) {
                        trollTrigger = true;
                        if (ownCrit.getItem().equals("Rough Skin")) {
                            enemyDamage = enemyDamage - Math.round(enemyDamage * 0.65f);
                        } else if (ownCrit.getItem().equals("Sturdy Skin")) {
                            enemyDamage = enemyDamage - Math.round(enemyDamage * 0.72f);
                        } else if (ownCrit.getItem().equals("Stone Skin")) {
                            enemyDamage = enemyDamage - Math.round(enemyDamage * 0.78f);
                        } else if (ownCrit.getItem().equals("Rock Skin")) {
                            enemyDamage = enemyDamage - Math.round(enemyDamage * 0.84f);
                        }
                    }
                    break;
                case TITAN:
                    if (ownCrit.getItem().equals("Land Slide")) {
                        enemyDamage -= Math.round(enemyDamage * 0.04f);
                    } else if (ownCrit.getItem().equals("Flash Flood")) {
                        enemyDamage -= Math.round(enemyDamage * 0.06f);
                    } else if (ownCrit.getItem().equals("Fire Storm")) {
                        enemyDamage -= Math.round(enemyDamage * 0.08f);
                    } else if (ownCrit.getItem().equals("Typhoon")) {
                        enemyDamage -= Math.round(enemyDamage * 0.10f);
                    } else if (ownCrit.getItem().equals("Sand Storm")) {
                        enemyDamage -= Math.round(enemyDamage * 0.12f);
                    } else if (ownCrit.getItem().equals("Deep Freeze")) {
                        enemyDamage -= Math.round(enemyDamage * 0.15f);
                    }
                    break;
                case WYRM:
                    if (ownHealth <= ownCrit.getHealth() && !wyrmTrigger) {
                        wyrmTrigger = true;
                        if (ownCrit.getItem().equals("Air Form")) {
                            ownDamage = ownDamage + Math.round(ownDamage * 0.60f);
                        } else if (ownCrit.getItem().equals("Fire Form")) {
                            ownDamage = ownDamage + Math.round(ownDamage * 0.85f);
                        } else if (ownCrit.getItem().equals("Metal Form")) {
                            ownDamage = ownDamage + Math.round(ownDamage * 1.10f);
                        } else if (ownCrit.getItem().equals("Shadow Form")) {
                            ownDamage = ownDamage + Math.round(ownDamage * 1.35f);
                        }
                    }
                    break;
                default:
                    break;
            }

            ownDefense = checkDefenses(ownDefense);
            enemyDefense = checkDefenses(enemyDefense);

            //Calculate Damage for Combat
            combatOwnDamage = Math.round((150 - enemyDefense) * ownDamage / 100.0f);
            combatEnemyDamage = Math.round((150 - ownDefense) * enemyDamage / 100.0f);

            switch (ownCrit.getRace()) {
                case SHADE:
                    int bounceBack = 0;
                    if (ownCrit.getItem().equals("Black Grimoire")) {
                        bounceBack = Math.round(combatEnemyDamage * 0.10f);
                    } else if (ownCrit.getItem().equals("Noir Grimoire")) {
                        bounceBack = Math.round(combatEnemyDamage * 0.12f);
                    } else if (ownCrit.getItem().equals("Kaala Grimoire")) {
                        bounceBack = Math.round(combatEnemyDamage * 0.14f);
                    } else if (ownCrit.getItem().equals("Prieto Grimoire")) {
                        bounceBack = Math.round(combatEnemyDamage * 0.16f);
                    } else if (ownCrit.getItem().equals("Nero Grimoire")) {
                        bounceBack = Math.round(combatEnemyDamage * 0.18f);
                    } else if (ownCrit.getItem().equals("Musta Grimoire")) {
                        bounceBack = Math.round(combatEnemyDamage * 0.20f);
                    }
                    combatEnemyDamage -= bounceBack;
                    combatOwnDamage += bounceBack;
                    break;
                case BASILiSK:
                    if (ownCrit.getItem().equals("Weak Poison")) {
                        enemyHealth = enemyHealth - Math.round(enemyHealth * 0.07f);
                    } else if (ownCrit.getItem().equals("Mild Poison")) {
                        enemyHealth = enemyHealth - Math.round(enemyHealth * 0.12f);
                    } else if (ownCrit.getItem().equals("Strong Poison")) {
                        enemyHealth = enemyHealth - Math.round(enemyHealth * 0.17f);
                    } else if (ownCrit.getItem().equals("Deadly Poison")) {
                        enemyHealth = enemyHealth - Math.round(enemyHealth * 0.22f);
                    }
                    break;
                default:
                    break;
            }
            //Calculate Health After Combat
            enemyHealth = enemyHealth - combatOwnDamage;
            ownHealth = ownHealth - combatEnemyDamage;

            switch (ownCrit.getRace()) {
                case NIX:
                    if (ownCrit.getItem().equals("Sapping Touch")) {
                        ownHealth += Math.round(combatOwnDamage * 0.05f);
                    } else if (ownCrit.getItem().equals("Draining Touch")) {
                        ownHealth += Math.round(combatOwnDamage * 0.15f);
                    } else if (ownCrit.getItem().equals("Vampiric Touch")) {
                        ownHealth += Math.round(combatOwnDamage * 0.25f);
                    } else if (ownCrit.getItem().equals("Sanguine Touch")) {
                        ownHealth += Math.round(combatOwnDamage * 0.35f);
                    }
                    break;
                case PHOENIX:
                    if (ownHealth < 0 && !phoenixTrigger) {
                        phoenixTrigger = true;
                        if (ownCrit.getItem().equals("Flame of Renewal")) {
                            ownHealth = Math.round(ownCrit.getHealth() * 0.35f);
                            ownDamage = Math.round(ownCrit.getDamage() * 0.35f);
                        } else if (ownCrit.getItem().equals("Fire of Renewal")) {
                            ownHealth = Math.round(ownCrit.getHealth() * 0.45f);
                            ownDamage = Math.round(ownCrit.getDamage() * 0.45f);
                        } else if (ownCrit.getItem().equals("Inferno of Renewal")) {
                            ownHealth = Math.round(ownCrit.getHealth() * 0.55f);
                            ownDamage = Math.round(ownCrit.getDamage() * 0.55f);
                        }
                    }
                case VALKYRIE:
                    if (combatOwnDamage < Math.round(enemyCrit.getHealth() * 0.30f)) {
                        if (ownCrit.getItem().equals("Divine Blessing")) {
                            ownDamage += Math.round(ownDamage * 0.05f);
                        } else if (ownCrit.getItem().equals("Touch of Divinity")) {
                            ownDamage += Math.round(ownDamage * 0.15f);
                        } else if (ownCrit.getItem().equals("Light of Divinity")) {
                            ownDamage += Math.round(ownDamage * 0.25f);
                        } else if (ownCrit.getItem().equals("Divine Favor")) {
                            ownDamage += Math.round(ownDamage * 0.35f);
                        }
                    }
                    break;
                case SPECTRE:
                    if (ownCrit.getItem().equals("Ghostly Touch")) {
                        ownDamage += Math.round(combatEnemyDamage * 0.30f);
                    } else if (ownCrit.getItem().equals("Ghostly Twitch")) {
                        ownDamage += Math.round(combatEnemyDamage * 0.40f);
                    } else if (ownCrit.getItem().equals("Ghostly Shiver")) {
                        ownDamage += Math.round(combatEnemyDamage * 0.50f);
                    } else if (ownCrit.getItem().equals("Ghostly Tremor")) {
                        ownDamage += Math.round(combatEnemyDamage * 0.60f);
                    }
                    break;
                case DEMON:
                    if (ownHealth < 0 && !demonTrigger) {
                        demonTrigger = true;

                        if (!goodDemonShield) {
                            int leftover = Math.abs(ownHealth);
                            int trueLeftoverDamage = Math.round(leftover / ((150 - ownDefense) / 100.0f));
                            ownHealth = demonHealth - trueLeftoverDamage;
                            combatEnemyDamage = combatEnemyDamage - (leftover - trueLeftoverDamage);
                        } else {
                            ownHealth = demonHealth;
                        }

                        ownDefense = demonDefense;
                    }
                    break;
                default:
                    break;
            }

            if (enemyHealth < 0) {
                enemyHealth = 0;
            }
            if (ownHealth < 0) {
                ownHealth = 0;
            }

            /* Gevecht naar scherm sturen */
            if (ShowFullBattles) {
                if (test) {
                    sb.append("The " + ownCrit.getName() + " did " + (combatOwnDamage) + " damage -> the " + enemyCrit.getName() + " has " + (enemyHealth) + " health left.\n");
                    if (ownCrit.getRace() == CritRaces.DEMON && !demonTrigger) {
                        sb.append("The " + enemyCrit.getName() + " did " + (combatEnemyDamage) + " damage -> the " + ownCrit.getName() + " has " + (demonHealth) + " health left.\n");
                    } else {
                        sb.append("The " + enemyCrit.getName() + " did " + (combatEnemyDamage) + " damage -> the " + ownCrit.getName() + " has " + (ownHealth) + " health left.\n");
                    }
                } else {
                    sb.append("Your " + ownCrit.getName() + " did " + combatOwnDamage + " damage -> opponent's " + enemyCrit.getName() + " has " + enemyHealth + " health left.<br>");
                    sb.append("Opponent's " + enemyCrit.getName() + " did " + combatEnemyDamage + " damage -> your " + ownCrit.getName() + " has " + ownHealth + " health left.<br>");
                }
            }

            turns--;
        }

        if (ownHealth <= 0 && enemyHealth > 0) {
            if (test) {
                sb.append("The " + ownCrit.getName() + " got killed by the " + enemyCrit.getName() + ".");
            } else {
                sb.append("<font color=\"red\">Your " + ownCrit.getName() + " got killed by your opponent's " + enemyCrit.getName() + ".</font><br><br>");
            }
            return CombatResult.DEAD;
        } else if (ownHealth != 0 && enemyHealth <= 0) {
            if (test) {
                sb.append("The " + ownCrit.getName() + " killed the " + enemyCrit.getName() + ".");
            } else {
                sb.append("<font color=\"green\">Your " + ownCrit.getName() + " killed your opponent's " + enemyCrit.getName() + ".</font><br><br>");
            }
        } else if (ownHealth <= 0 && enemyHealth <= 0) {
            if (test) {
                sb.append("The " + ownCrit.getName() + " killed the " + enemyCrit.getName() + " but died in the process.");
            } else {
                sb.append("<font color=\"#CC6600\">Your " + ownCrit.getName() + " killed your opponent's " + enemyCrit.getName() + " but died in the process.</font><br><br>");
            }
            return CombatResult.DIP;
        } else if (turns == 0) {
            if (test) {
                sb.append("The " + ownCrit.getName() + " got exhausted and died.");
            } else {
                sb.append("<font color=\"red\">Your " + ownCrit.getName() + " got exhausted and died.</font><br><br>");
            }
            return CombatResult.DEAD;
        }

        return CombatResult.ALIVE;
    }

    private int getDefense(Crit defenseNeededCrit, Crit opposingCrit) {
        if (opposingCrit.getMageClass().equals("Elemental")) {
            return defenseNeededCrit.getElementalDef();
        } else if (opposingCrit.getMageClass().equals("Diabolic")) {
            return defenseNeededCrit.getDiabolicDef();
        } else if (opposingCrit.getMageClass().equals("Mystic")) {
            return defenseNeededCrit.getMysticDef();
        } else if (opposingCrit.getMageClass().equals("Nature")) {
            return defenseNeededCrit.getNatureDef();
        } else {
            theLogger.warning("This Creature Type is Not Found='" + opposingCrit.getMageClass() + "'");
            return 0;
        }
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
        } else if (defense < 0) {
            return 0;
        } else {
            return defense;
        }
    }

    private int countCritRace(CritRaces race, List<Crit> crits, int enemySize) {
        int count = 0;
        try {

        for (int i = 0; i < enemySize; i++) {
            if (crits.get(i).getRace().equals(race)) {
                count++;
            }
        }}
        catch (IndexOutOfBoundsException ex) {
            System.out.println("What the fuck: " + enemySize + crits.size() + race);
        }
        return count;
    }
}

/**
 *
 * @author Robbe
 */
public class ItemModificator {

    int mDef = 0, nDef =0, eDef, dDef = 0, health = 0, damage = 0;
    double defencePercent = 0.0, mDefPercent = 0.0, nDefPercent = 0.0, eDefPercent = 0.0, dDefPercent = 0.0, healthPercent = 0.0, damagePercent = 0.0;
    boolean perCritType = false , perSameCrit = false, extraPerTurn = false, once = false, oponent = false, lowHealthOnly = false;




    public boolean isLowHealthOnly() {
        return lowHealthOnly;
    }

    public void setLowHealthOnly(boolean lowHealthOnly) {
        this.lowHealthOnly = lowHealthOnly;
    }


    public double getDamagePercent() {
        return damagePercent;
    }

    public void setDamagePercent(double damagePercent) {
        this.damagePercent = damagePercent;
    }

    public boolean isPerSameCrit() {
        return perSameCrit;
    }

    public void setPerSameCrit(boolean perSameCrit) {
        this.perSameCrit = perSameCrit;
    }


    public int getmDef() {
        return mDef;
    }

    public void setmDef(int mDef) {
        this.mDef = mDef;
    }

    public int getnDef() {
        return nDef;
    }

    public void setnDef(int nDef) {
        this.nDef = nDef;
    }

    public int geteDef() {
        return eDef;
    }

    public void seteDef(int eDef) {
        this.eDef = eDef;
    }

    public int getdDef() {
        return dDef;
    }

    public void setdDef(int dDef) {
        this.dDef = dDef;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getDefencePercent() {
        return defencePercent;
    }

    public void setDefencePercent(double defencePercent) {
        this.defencePercent = defencePercent;
    }

    public double getmDefPercent() {
        return mDefPercent;
    }

    public void setmDefPercent(double mDefPercent) {
        this.mDefPercent = mDefPercent;
    }

    public double getnDefPercent() {
        return nDefPercent;
    }

    public void setnDefPercent(double nDefPercent) {
        this.nDefPercent = nDefPercent;
    }

    public double geteDefPercent() {
        return eDefPercent;
    }

    public void seteDefPercent(double eDefPercent) {
        this.eDefPercent = eDefPercent;
    }

    public double getdDefPercent() {
        return dDefPercent;
    }

    public void setdDefPercent(double dDefPercent) {
        this.dDefPercent = dDefPercent;
    }

    public double getHealthPercent() {
        return healthPercent;
    }

    public void setHealthPercent(double healthPercent) {
        this.healthPercent = healthPercent;
    }

    public boolean isPerCritType() {
        return perCritType;
    }

    public void setPerCritType(boolean perCritType) {
        this.perCritType = perCritType;
    }

    public boolean isExtraPerTurn() {
        return extraPerTurn;
    }

    public void setExtraPerTurn(boolean extraPerTurn) {
        this.extraPerTurn = extraPerTurn;
    }

    public boolean isOnce() {
        return once;
    }

    public void setOnce(boolean once) {
        this.once = once;
    }

    public boolean isOponent() {
        return oponent;
    }

    public void setOponent(boolean oponent) {
        this.oponent = oponent;
    }


}

package VIVA3;

public class BlackMage 
{
    private int hp;
    private final double evasion;
    private final double critResistance;

    // Empty constructor
    public BlackMage() 
    {
        this.hp = 100;
        this.evasion = 0.05;
        this.critResistance = 0.10;
    }

    // Accessor methods
    public int getHp() 
    {
        return hp;
    }

    public double getEvasion()
    {
        return evasion;
    }

    public double getCritResistance() 
    {
        return critResistance;
    }

    public void setHp(int hp) 
    {
        this.hp = hp;
    }
}

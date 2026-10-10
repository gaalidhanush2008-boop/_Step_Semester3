/**
 * Week 7 - Problem 1: The Health Bar
 * Health is private, clamped to [0, maxHealth], and has no setter.
 */
public class P1_HealthBar {

    public static void main(String[] args) {
        GameCharacter c = new GameCharacter(100);
        c.takeDamage(30);
        System.out.println("health = " + c.getHealth()); // 70
        c.heal(50);
        System.out.println("health = " + c.getHealth()); // 100 (capped)
        c.takeDamage(150);
        System.out.println("health = " + c.getHealth()); // 0 (floored)
    }
}

class GameCharacter {
    private final int maxHealth;
    private int health;

    GameCharacter(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;          // starts at full health
    }

    void takeDamage(int amount) {
        if (amount < 0) return;           // negative damage would secretly heal
        health = Math.max(0, health - amount);       // calculate, then clamp at 0
    }

    void heal(int amount) {
        if (amount < 0) return;
        health = Math.min(maxHealth, health + amount); // calculate, then clamp at max
    }

    int getHealth() {                     // read-only access, no setter
        return health;
    }
}

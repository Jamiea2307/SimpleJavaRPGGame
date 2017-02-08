import java.util.Random;
import java.util.Scanner;

/**
 * Created by C1542736 on 26/02/2016.
 */
public class Player {
    private static String name;
    private final String description;
    private final int maxHitPoints;
    private int hitPoints;
    private int numPotions;
    private int minDamage;
    private int maxDamage;
    private final Random random = new Random();

    private Player(String name, String description, int maxHitPoints, int minDamage, int maxDamage,
                   int numPotions) {
        this.name = name;
        this.description = description;
        this.maxHitPoints = maxHitPoints;
        this.minDamage = 100;
        this.maxDamage = 200;
        this.numPotions = numPotions;
        this.hitPoints = maxHitPoints;
    }

    public int attack() {
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    public void defend(Monster monster) {
        int attackStrength = monster.attack();
        hitPoints = (hitPoints > attackStrength) ? hitPoints - attackStrength : 0;
        System.out.printf("  " + name + " is hit for %d HP of damage (%s)\n", attackStrength,
                getStatus());
        if (hitPoints == 0) {
            System.out.println("  " + name + " has been defeated");
        }
    }

    public void heal() {
        if (numPotions > 0) {
            hitPoints = Math.min(maxHitPoints, hitPoints + 20);
            System.out.printf("  %s drinks healing potion (%s, %d potions left)\n", name,
                    getStatus(), --numPotions);
        } else {
            System.out.println("  You've exhausted your potion supply!");
        }
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    public String getStatus() {
        return "Player HP: " + hitPoints;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public static Player newInstance(){
        Scanner s = new Scanner(System.in);
        System.out.println("What is your name?");
        name = s.next();
        return new Player(name, "a feeble peasant" , 50,50,5,15);
    }
}

import java.util.Scanner;

/**
 * Created by C1542736 on 09/03/2016.
 */
public class Battle {
    public Battle(Player player, Monster monster) {
        Scanner s = new Scanner(System.in);
        System.out.println("You encounter " + monster + ": " + monster.getDescription() + "\n");
        System.out.println("Battle with " + monster + " starts (" + player.getStatus() + " / "
                + monster.getStatus() + ")");
        while (player.isAlive() && monster.isAlive()) {
            System.out.print("Attack (a) or heal (h)? ");
            String action = s.next();
            if (action.equals("h")) {
                player.heal();
            } else {
                monster.defend(player);
            }
            if (monster.isAlive()) {
                player.defend(monster);
            }
        }
    }

}

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by C1542736 on 07/03/2016.
 */
public class Room {

    private final String description;
    private final Monster monster;
    private final Boolean isBossRoom;
    private final static Random random = new Random();
    private final static Set<Integer> roomsSeen = new HashSet<Integer>();
    private final static int NUM_ROOMS = 7;

    private Room(String description, Monster monster, Boolean isBossRoom) {
        this.description = description;
        this.monster = monster;
        this.isBossRoom = isBossRoom;
    }

    public static Room newRegularInstance() {
        if (roomsSeen.size() == NUM_ROOMS) {
            roomsSeen.clear();
        }
        int i;
        do {
            i = random.nextInt(NUM_ROOMS);
        } while (roomsSeen.contains(i));
        roomsSeen.add(i);

        String roomDescription = null;
        switch(i){
            case 1: roomDescription = "a fetid, dank room teeming with foul beasts";
                break;
            case 2: roomDescription = "an endless mountain range where eagles soar looking for prey";
                break;
            case 3: roomDescription = "a murky swamp with a foul smelling odour";
                break;
            case 4: roomDescription = "a volcano with rivers of lava at all sides";
                break;
            case 5:  roomDescription = "a thick forest where strange voices call out from the trees high above";
                break;
            case 6: roomDescription = "an old abandoned sailing ship, littered with the remains of some unlucky sailors";
                break;
            case 7: roomDescription = "a cafe filled with hipster baristas who refuse to use encapsulation";
                break;
        }
        return new Room(roomDescription, Monster.newRandomInstance(), false);
    }

    public static Room newBossInstance() {
        return new Room("a huge cavern thick with the smell of sulfur", Monster.newBossInstance(),
                true);
    }

    public boolean isBossRoom() {
        return isBossRoom;
    }

    public boolean isComplete() {
        return !monster.isAlive();
    }

    @Override
    public String toString() {
        return description;
    }

    public void enter(Player player) {
        System.out.println("You are in " + description);
        if (monster.isAlive()) {
            new Battle(player, monster);
        }
    }

}

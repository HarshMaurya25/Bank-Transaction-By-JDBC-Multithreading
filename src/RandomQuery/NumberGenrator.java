package RandomQuery;
import java.util.Random;

public class NumberGenrator {
    private static final int atm = 5;
    private static final Random random = new Random();

    public static int atmGenerator(){
        return random.nextInt(1, atm+1);
    }
}

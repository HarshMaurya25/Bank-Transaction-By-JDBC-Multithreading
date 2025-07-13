package RandomQuery;

import Create.PrivateData;

import java.util.Random;

public class RandomAccount {
    private static final Random random= new Random();
    public int AccountGenerator(){
        int num = random.nextInt(0 , PrivateData.getAccount_detail().size());
        Integer[] keyArray = PrivateData.getAccount_detail().keySet().toArray(new Integer[0]);
        return keyArray[num];
    }
}

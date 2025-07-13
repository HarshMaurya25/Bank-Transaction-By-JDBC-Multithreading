package MultiThreading;

import RandomQuery.QueryGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreeading{

    public static void Threads(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 0 ; i < 50 ; i++){
            executorService.submit(()->
                    QueryGenerator.QueryGenrator()
            );
        }
        executorService.shutdown();
    }
}

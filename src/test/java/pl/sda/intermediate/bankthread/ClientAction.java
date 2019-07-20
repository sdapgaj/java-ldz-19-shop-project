package pl.sda.intermediate.bankthread;

import java.math.BigDecimal;
import java.util.Random;

public class ClientAction implements Runnable {

    @Override
    public void run() {

//        System.out.println(Thread.currentThread().getName());

        Random r = new Random();
        Integer b = r.nextInt(50);

        Bank.withdrow(b);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bank.deposit(b);

    }

}

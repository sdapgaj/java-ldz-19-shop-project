package pl.sda.intermediate.bankthread;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BankTest {

//    @Test
//    public void synchronizedBankTest() {
//
//        for (int i = 0; i < 10000; i++) {
//            ClientAction ca = new ClientAction();
//            ca.run();
//        }
//
//        System.out.println(Bank.getCounter());
//        System.out.println(Bank.getAccount());
//
//    }

    @Test
    public void threadsBankTest() {

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {

            ClientAction ca = new ClientAction();
            Thread th = new Thread(ca);
            threads.add(th);

        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Bank.getCounter());
        System.out.println(Bank.getAccount());

    }

}

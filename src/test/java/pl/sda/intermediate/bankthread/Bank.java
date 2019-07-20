package pl.sda.intermediate.bankthread;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Bank {

//    private static Integer account = 1000;
    private static AtomicInteger account = new AtomicInteger(1000); // zamiast synchronized ktore jest KOSZTOWNĄ operacja
    private static Integer counter = 0;

    static synchronized void withdrow(Integer howMuch) {

        account.addAndGet(-howMuch);
        System.out.println(String.format("Wypłata %s w thread %s", account, Thread.currentThread().getName()));

    }

    static synchronized void deposit(Integer howMuch) {

        account.addAndGet(howMuch);
        System.out.println(String.format("Wpłata %s w thread %s", account, Thread.currentThread().getName()));

        counter++;

    }

    public static String getAccount() {
        return String.format("############# Stan banku: %s", account);
    }

    public static String getCounter() {
        return String.format("############# Ilosc operacji: %s", counter);
    }

}

package pl.sda.intermediate.customers;

import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalExample {

    @Test
    public void bigDecTest() {

        double x = 0.02;
        double y = 0.03;

        System.out.println(x - y);

        BigDecimal a = new BigDecimal(x);
        BigDecimal b = new BigDecimal(y);

        System.out.println(a.subtract(b));

        BigDecimal c = BigDecimal.valueOf(x);
        BigDecimal d = BigDecimal.valueOf(y);

        System.out.println(c.subtract(d));

    }

}

package pl.sda.intermediate.testhashcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class HashcodeTest {

    @Test
    public void hashcodeTest() {

        CarConstantHashcode c1 = new CarConstantHashcode("fiat");
        CarConstantHashcode c2 = new CarConstantHashcode("audi");
        CarConstantHashcode c3 = new CarConstantHashcode("tipo");
        CarConstantHashcode c4 = new CarConstantHashcode("polo");
        CarConstantHashcode c5 = new CarConstantHashcode("fiat");

        CarRandomHascode r1 = new CarRandomHascode("fiat");
        CarRandomHascode r2 = new CarRandomHascode("audi");
        CarRandomHascode r3 = new CarRandomHascode("tipo");
        CarRandomHascode r4 = new CarRandomHascode("polo");
        CarRandomHascode r5 = new CarRandomHascode("fiat");

        Set<CarConstantHashcode> cars = new HashSet<>();
        cars.add(c1);
        cars.add(c2);
        cars.add(c3);
        cars.add(c4);
        cars.add(c5);

        System.out.println(cars.size());

        Set<CarRandomHascode> rcars = new HashSet<>();
        rcars.add(r1);
        rcars.add(r2);
        rcars.add(r3);
        rcars.add(r4);
        rcars.add(r5);

        System.out.println(rcars.size());

    }

}

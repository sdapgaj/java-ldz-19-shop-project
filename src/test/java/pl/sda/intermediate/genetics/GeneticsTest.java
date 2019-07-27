package pl.sda.intermediate.genetics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneticsTest {

    @Test
    void changePossibleTest() {

        String val1 = "QOOOOOOOOOOOOOOOOOOOO";
        String val2 = "OOOOOOOOOOOOOOOOOOOOQ";

        Genetics gen = new Genetics();

        assertEquals(gen.changePossible(val1, val2), true);

    }

    @Test
    void testMethod() {

        Genetics gen = new Genetics();
        gen.readLinesFromFile();

    }

    @Test
    void newTest() {
        Genetics gen = new Genetics();
        gen.newProgramParalel();
    }

}
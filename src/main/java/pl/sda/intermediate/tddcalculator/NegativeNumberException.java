package pl.sda.intermediate.tddcalculator;

import java.util.List;
import java.util.stream.Collectors;

public class NegativeNumberException extends RuntimeException {

    public NegativeNumberException(List<Integer> negativeNumbers) {
        super("Negative numbers found " + negativeNumbers.stream().map(e -> e.toString()).collect(Collectors.joining(", ")));
    }

}

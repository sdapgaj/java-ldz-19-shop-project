package pl.sda.intermediate.tddcalculator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringCalculator {

    public static int adding(String param) {

        String trimedParam = param.trim();

        String regex = "[,\n]";

        if (trimedParam.startsWith("//")) {
            regex = String.valueOf(trimedParam.charAt(2));
            String[] lines = trimedParam.split("\n");
            trimedParam = Arrays.stream(lines).skip(1).collect(Collectors.joining(regex));
        }

        String[] split = trimedParam.split(regex);

        return Arrays.stream(split)
                .map(number -> number.trim())
                .filter(e -> StringUtils.isNotBlank(e))
                .map(number -> Integer.parseInt(number))
                .reduce((a, b) -> a + b)
                .orElse(0);

    }

}

package pl.sda.intermediate.tddcalculator;

/**
 * 1. Stwórz prosty kalkulator String z metodą "int adding(String text)"
 * - Metoda może przyjąć od zera do dwóch liczb i zwróci ich sumę (dla pustego ciągu zwróci 0), na przykład "" lub "1" lub " 1,2 "
 * - Zacznij od najprostszego przypadku testowego pustego łańcucha i przejdź do 1 i 2 liczb
 * - Pamiętaj, aby rozwiązywać problemy tak prosto, jak to tylko możliwe, tak abyś zmusił się do napisania testów
 * - Pamiętaj, aby zrobić refaktor po każdej zmianie
 * 2. Metoda adding, powinna obsłużyć dowolną ilość liczb
 * 3. Metoda adding powinna obsługiwać nowe linie między liczbami (zamiast przecinków).
 * - Następujące dane wejściowe są ok: "1 \n2,3" (równa się 6)
 * - Następujące dane wejściowe NIE są OK: "1, \n" (nie trzeba tego udowadniać - wystarczy wyjaśnić)
 * 4. Obsługa różnych ograniczników
 * - W celu zmiany ogranicznika początek łańcucha będzie zawierał osobną linię, która wygląda następująco: "//[ogranicznik]\n[liczba...]" na przykład "//;\n1;2" powinien zwrócić trzy, gdzie ogranicznikiem jest ";".
 * - pierwsza linia z innym ogranicznikiem jest opcjonalna. wszystkie dotychczasowe scenariusze powinny być nadal obsługiwane
 * 5. Wywołanie opcji Dodaj liczbą ujemną spowoduje zgłoszenie wyjątku "negatywy niedozwolone" - i negatywu, który został przekazany. Jeśli istnieje wiele wykluczeń, pokaż je wszystkie w komunikacie wyjątku.
 * 6. Liczby większe niż 1000 powinny być ignorowane, więc dodanie 2 + 1001 = 2
 * 7. Ogranicznik może mieć dowolną długość w następującym formacie:
 * "//[delimiter]\n" na przykład: "//[***]\n1***2***3" powinien zwrócić 6
 * 8. Zezwalaj na wiele ograniczników w następujący sposób:
 * "//[delim1][delim2]\n" na przykład "//[*][%]\n1*2%3" powinno zwrócić 6.
 * 9. Spraw aby możliwe było obsługiwanie wielu ograniczników o długości dłuższej niż jeden znak
 */

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    public static int adding(String param) {

        String trimedParam = param.trim();

        String regex = "[,\n]";

        if (trimedParam.startsWith("//")) {

            Pattern pattern = Pattern.compile("(//)(.+)(\n)(.+)");
            Matcher matcher = pattern.matcher(trimedParam);
            matcher.matches();
            regex = matcher.group(2).replaceAll("[\\[\\]]", "");
            trimedParam = matcher.group(4);

        }

        String[] split = trimedParam.split(regex);

        List<Integer> numbers = Arrays.stream(split)
                .map(number -> number.trim())
                .filter(e -> StringUtils.isNotBlank(e))
                .map(number -> Integer.parseInt(number))
                .filter(e -> e <= 1000)
                .collect(Collectors.toList());


        List<Integer> negativeNumbers = numbers.stream()
                .filter(e -> e < 0)
                .collect(Collectors.toList());

        if (!negativeNumbers.isEmpty()) {
            throw new NegativeNumberException(negativeNumbers);
        }

        return numbers.stream()
                .reduce((a, b) -> a + b)
                .orElse(0);

    }

}

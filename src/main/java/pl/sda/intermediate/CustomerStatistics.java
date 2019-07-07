package pl.sda.intermediate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CustomerStatistics {

    private static Customer[] people = new Customer[]{
            new Customer("Anna", "Nowak   ", 33, "1200"),
            new Customer("Beata", "Kowalska", 22, "1200"),
            new Customer("Marek", " Nowak", 25, "1250 "),
            new Customer("Adam", "Twardowski", 33, "4100 "),
            new Customer("Monika  ", "Kos", 25, "2500"),
            new Customer("Adam ", "Rudy", 45, "3333"),
            new Customer("Marek", "Rudy", 15, 2210),
            new Customer("Adam", "Madej", 15, 3333),
            new Customer("Adam", "Nowak ", 15, null)
    };

    // 1. Napisz metodę, która zamieni tablicę people na listę people - ta metoda może być potem wykorzystywana przez Was w następnych metodach
    public static List<Customer> convertArrayToList() {

        return Arrays.asList(people);

    }

    // 2. Napisz metodę, która przyjmie tablicę people i zwróci listę Stringów <imię nazwisko> (elementem listy będzie np "Anna Nowak")
    public static List<String> getNames() {

        // List<String> out = new ArrayList<String>();

        // for (Customer person : people) {
        //     out.add(String.format("%s %s", person.getFirstName() + person.getLastName()));
        // }

        return Arrays.stream(people)
                .map(x -> x.getFirstName() + " " + x.getLastName())
                .collect(Collectors.toList());

    }

    // 3. Napisz metodę, która zwróci mapę osób <id,osoba>
    public static Map<Integer, Customer> mapOfPerson() {

        return Arrays.stream(people)
                .collect(Collectors.toMap(x -> x.getId(), x -> x));

    }

    // 4. Napisz metodę, która zwróci mapę osób według zarobków <zarobki,osoby_z_zarobkami>
    public static Map<BigDecimal, List<Customer>> getCustomerSalary() {

        Map<BigDecimal, List<Customer>> out = new HashMap<>();

        for (Customer customer : people) {

            if (out.containsKey(customer.getSalary())) {
                out.get(customer.getSalary()).add(customer);
            } else {
                List<Customer> customers = new ArrayList<>();
                customers.add(customer);
                out.put(customer.getSalary(), customers);
            }

        }

        return out;

//        return Arrays.stream(people)
//                .collect(Collectors.groupingBy(x -> x.getSalary()));

    }

    // 6. Napisz metodę, która zwróci mapę map <imię,<zarobki, liczba_osób_z_takimi_zarobkami>>
    public static Map<String, Map<BigDecimal, Long>> getSalaryStatiscticsMap() {

//        Map<String, Map<BigDecimal, Integer>> out = new HashMap<>();
//        for (Customer person : people) {
//
//            if (out.containsKey(person.getFirstName())) {
//
//                Map<BigDecimal, Integer> innerMap = out.get(person.getFirstName());
//
//                if (innerMap.containsKey(person.getSalary())) {
//
//                    Integer counter = innerMap.get(person.getSalary());
//                    innerMap.put(person.getSalary(), counter + 1);
//
//                } else {
//
//                    innerMap.put(person.getSalary(), 1);
//
//                }
//
//            } else {
//
//                Map<BigDecimal, Integer> innerMap = new HashMap<>();
//                innerMap.put(person.getSalary(), 1);
//                out.put(person.getFirstName(), innerMap);
//
//            }
//
//        }
//
//        return out;

        return Arrays.stream(people)
                .collect(Collectors
                  .groupingBy(p -> p.getFirstName(), Collectors
                    .groupingBy(p -> p.getSalary(), Collectors.counting())));

    }

}

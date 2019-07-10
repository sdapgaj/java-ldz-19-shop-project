package pl.sda.intermediate.customers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Customer {

    private static int counter = 1;

    @Setter(AccessLevel.NONE)
    private Integer id;

    private String firstName;
    private String lastName;
    private Integer age;
    private BigDecimal salary;

    {
        this.id = counter++;
    }

    public Customer(String firstName, String lastName, Integer age, String salary) {
        this.firstName = firstName.trim();
        this.lastName = lastName;
        this.age = age;

        if (salary != null) {
            this.salary = new BigDecimal(salary.trim());
        } else {
            this.salary = new BigDecimal("0");
        }
    }

    public Customer(String firstName, String lastName, Integer age, int salary) {
        this(firstName, lastName, age, String.valueOf(salary));
    }

}

package pl.sda.intermediate.shop.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private long serialVersionUID = 123L;

    private String firstName;
    private String lastName;
    private String eMail;
    private String passwordHash;
    private String birthDate;
    private String pesel;
    private String phone;
    private Boolean preferEmails;

    private UserAddress address;

}

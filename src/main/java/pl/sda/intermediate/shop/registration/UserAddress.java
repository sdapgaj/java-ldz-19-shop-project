package pl.sda.intermediate.shop.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAddress implements Serializable {

    private long serialVersionUID = 1223L;

    private String city;
    private String country;
    private String zipCode;
    private String street;

}

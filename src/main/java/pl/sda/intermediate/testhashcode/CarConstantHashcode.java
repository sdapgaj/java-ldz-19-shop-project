package pl.sda.intermediate.testhashcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class CarConstantHashcode {

    private String model;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarConstantHashcode that = (CarConstantHashcode) o;
        return Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return 5;
    }

}

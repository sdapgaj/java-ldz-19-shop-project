package pl.sda.intermediate.testhashcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.Random;

@Getter
@AllArgsConstructor
public class CarRandomHascode {

    private String marka;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarRandomHascode that = (CarRandomHascode) o;
        return Objects.equals(marka, that.marka);
    }

    @Override
    public int hashCode() {
        Random r = new Random();
        return r.nextInt();
    }

}

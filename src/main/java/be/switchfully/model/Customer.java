package be.switchfully.model;

import lombok.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Customer {
    private String id = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private int scoreDay;
    private int scoreMonth;

    public void setScoreDay(int scoreDay) {
        this.scoreDay+=scoreDay;
    }

    public void setScoreMonth(int scoreMonth) {
        this.scoreMonth+=scoreMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package be.switchfully.model;

import lombok.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Customer {
    private String id = UUID.randomUUID().toString();
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private int scoreDay;
    private int scoreMonth;

    public void setScoreDay(int scoreDay) {
        this.scoreDay+=scoreDay;
    }

    public void setScoreMonth(int scoreMonth) {
        this.scoreMonth+=scoreMonth;
    }

    public void resetDailyScore(){
        this.scoreDay = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return firstName.equalsIgnoreCase(customer.firstName) && lastName.equalsIgnoreCase(customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

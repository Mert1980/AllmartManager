package be.switchfully.model;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class Customer {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private int scoreDay;
    private int scoreMonth;
}

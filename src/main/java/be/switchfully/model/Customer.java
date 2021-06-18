package be.switchfully.model;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class Customer {
    @NonNull
    private String fullName;
    private int scoreDay;
    private int scoreMonth;
}

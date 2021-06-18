package be.switchfully.model;

import lombok.*;
import java.time.LocalDate;
import java.util.HashMap;

@RequiredArgsConstructor
@Getter
@Setter
public class Receipt {
    @NonNull
    private Customer customer;
    private LocalDate date = LocalDate.now();
    @NonNull
    private HashMap<Product, Integer> productAmountMap;
}

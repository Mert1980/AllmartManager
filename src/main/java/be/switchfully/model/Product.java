package be.switchfully.model;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class Product {
    @NonNull
    private String productName;
}

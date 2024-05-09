package model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Toy {
    private int toyId;
    private String toyName;
    private String toyCategory;
    private Double toy_Price;
    private int stock_Toys;
}


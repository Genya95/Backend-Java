package lesson5.dto;

import lombok.*;

import java.util.ArrayList;

@Data
public class Category {
    Integer id;
    String title;
    ArrayList<Product> products;

}

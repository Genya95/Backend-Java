
package lesson5.dto;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@ToString
public class Product {

    public Integer id;
    public String title;
    public Integer price;
    public String categoryTitle;



}

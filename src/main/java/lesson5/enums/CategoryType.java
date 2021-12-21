package lesson5.enums;

import lombok.Getter;

public enum CategoryType {
    FOOD("Food",1),
    ELECTRONIC("Electronic",2),
    FURNITURE("Furniture",3);

    @Getter
    private final String title;

    @Getter
    private final Integer id;

    CategoryType(String title,Integer id){
        this.id = id;
        this.title = title;
    }


}

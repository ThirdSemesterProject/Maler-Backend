package stud.kea.dk.malerbackend.search.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    private String name;
    private String category;
    private double price;
    private String description;
    private String brand;
    private String URL;
    private String subcategory;
}

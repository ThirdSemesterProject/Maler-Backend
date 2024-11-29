package stud.kea.dk.malerbackend.search;

public class SearchDto {
    private String name;
    private String category;
    private double price;
    private String description;
    private String brand;
    private String URL;

    public SearchDto(String name, String category, double price, String description, String brand, String URL) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.URL = URL;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getURL() {
        return URL;
    }
    public void setURL(String URL) {
        this.URL = URL;
    }
}

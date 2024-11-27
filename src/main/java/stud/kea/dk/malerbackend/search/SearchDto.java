package stud.kea.dk.malerbackend.search;

public class SearchDto {
    private String name; // Fra Products/Paint
    private String itemNo; // Fra PaintNo
    private String liters; // Fra PaintNo
    private String category; // Fra Products
    private double price; // Fra Products
    private String shine; // Fra Paint

    public SearchDto(String name, String itemNo, String liters, String category, double price, String shine) {
        this.name = name;
        this.itemNo = itemNo;
        this.liters = liters;
        this.category = category;
        this.price = price;
        this.shine = shine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getLiters() {
        return liters;
    }

    public void setLiters(String liters) {
        this.liters = liters;
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

    public String getShine() {
        return shine;
    }

    public void setShine(String shine) {
        this.shine = shine;
    }
}

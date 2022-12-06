package practice.shop;

public class Item {
    // Class Attributes
    private String name;
    private Integer quantity = 0;
    private Float price = 0.0f;
    
    // Constructors (Overloaded)
    public Item(String name) {
        this.name = name;
    }

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    
    public Item(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public Item(String name, int quantity, Float price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }


    // Methods
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
    
}

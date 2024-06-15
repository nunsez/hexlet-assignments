package exercise.dto;

// BEGIN
public final class ProductUpdateDTO {

    private String title;

    private int price;

    public ProductUpdateDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
// END

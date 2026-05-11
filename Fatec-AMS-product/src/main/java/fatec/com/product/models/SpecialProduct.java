package fatec.com.product.models;

public class SpecialProduct extends Product {

    private Double discountPercentage;

    public SpecialProduct() {
    }

    public SpecialProduct(Long id, String name, Double price, String description, Double discountPercentage) {
        super(id, name, price, description);
        this.discountPercentage = discountPercentage;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getFinalPrice() {
        return getPrice() * (1 - discountPercentage / 100);
    }
}
public class Cake {
    private String cakeCode;
    private String cakeName;
    private double price;

    public Cake(String cakeCode, String cakeName, double price) {
        this.cakeCode = cakeCode;
        this.cakeName = cakeName;
        this.price = price;
    }

    // Getters and setters
    public String getCakeCode() {
        return cakeCode;
    }
    public void setCakeCode(String cakeCode) {
        this.cakeCode = cakeCode;
    }
    public String getCakeName() {
        return cakeName;
    }
    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cake Code: " + cakeCode + ", Name: " + cakeName + ", Price: $" + String.format("%.2f", price);
    }
}
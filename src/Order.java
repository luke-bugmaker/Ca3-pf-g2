import java.time.LocalDate;

public class Order {
    private String orderId;
    private LocalDate orderDate;
    private Cake cake;
    private String cakeCode;
    private Customer customer;
    private int customerId;

    public Order(String orderId, LocalDate orderDate, String cakeCode, int customerId){
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cakeCode = cakeCode;
        this.customerId = customerId;
    }


    // Getters and setters
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public Cake getCake() {
        return cake;
    }
    public void setCake(Cake cake) {
        this.cake = cake;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public int getCustomerId() {
        return customerId;
    }
    public String getCakeCode() {
        return cakeCode;
    }


    public String toString(String date){
        return "Order ID: " + orderId + ", Cake Code: " + cakeCode + " Customer ID: " + customerId;
    }
    public String toString(){
        return "Order ID: " + orderId + ", Order Date: " + orderDate + " Customer ID: " + customerId;
    }
    public String toString(int customerId){
        return "Order ID: " + orderId + ", Order Date: " + orderDate + " Order Date: " + orderDate;
    }
}

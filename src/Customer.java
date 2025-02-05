public class Customer {
    private int customerId;
    private String fullName;
    private int contactNumber;
    private String Address;

    public Customer(int customerId, String fullName, int contactNumber, String Address) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.Address = Address;
    }

    // Getters and setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public int getContactNumber() { return contactNumber; }
    public void setContactNumber(int contactNumber) { this.contactNumber = contactNumber; }
    public String getAddress() { return Address; }
    public void setAddress(String Address) { this.Address = Address; }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + fullName + ", Contact: " + contactNumber + ", Address: " + Address;
    }
}

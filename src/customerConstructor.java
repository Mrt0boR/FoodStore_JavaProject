/*
 Name : James Bailey
 Student ID : 23673840
 */

/**
 * Represents a customer entity containing essential details like ID, name, business information, and address.
 */


public class customerConstructor {

    private int customerID;
    private String customerName;
    private String businessName;
    private String telephoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String country;
    private String postcode;

    /**
     * Initializes a customerConstructor object with the provided information.
     *
     * @param customerID       The unique identifier assigned to the customer.
     * @param customerName     The name of the customer.
     * @param businessName     The business name associated with the customer.
     * @param telephoneNumber  The contact number of the customer.
     * @param addressLine1     The first line of the customer's address.
     * @param addressLine2     The second line of the customer's address.
     * @param addressLine3     The third line of the customer's address.
     * @param country          The country where the customer is located.
     * @param postcode         The postal code of the customer's address.
     */
    public customerConstructor(int customerID, String customerName, String businessName,
                               String telephoneNumber, String addressLine1, String addressLine2,
                               String addressLine3, String country, String postcode) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.businessName = businessName;
        this.telephoneNumber = telephoneNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.country = country;
        this.postcode = postcode;
    }

    /**
     * Retrieves the unique identifier assigned to the customer.
     *
     * @return The customer's ID.
     */
    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public String getCountry() {
        return country;
    }

    public String getPostcode() {
        return postcode;
    }

    // Setters
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Overrides the toString() method to provide a string representation of the customerConstructor object.
     *
     * @return A formatted string containing the customer's details.
     */
    @Override
    public String toString() {
        return "ID: " + customerID +
                ", Customer Name: '" + customerName + '\'' +
                ", Business Name: '" + businessName + '\'' +
                ", Telephone Number: '" + telephoneNumber + '\'' +
                ", Address Line 1: '" + addressLine1 + '\'' +
                ", Address Line 2: '" + addressLine2 + '\'' +
                ", Address Line 3: '" + addressLine3 + '\'' +
                ", Country: '" + country + '\'' +
                ", Postcode: '" + postcode + "'\n";
    }


}

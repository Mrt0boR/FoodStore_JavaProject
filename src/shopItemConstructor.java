/*
 Name : James Bailey
 Student ID : 23673840
 */
/**
 * The shopItemConstructor class represents a shop item and provides methods to
 * get and set its attributes such as ID, SKU, Category, Description, and Price.
 */
public class shopItemConstructor {

    private int ID;
    public String SKU;
    public String Category;
    public String Description;
    public int Price;

    /**
     * Constructs a new shop item with the provided attributes.
     *
     * @param ID          The unique identifier for the shop item.
     * @param SKU         The stock-keeping unit code of the shop item.
     * @param Category    The category to which the shop item belongs.
     * @param Description A description of the shop item.
     * @param Price       The price of the shop item.
     */
    public shopItemConstructor(int ID, String SKU, String Category, String Description, int Price) {
        this.ID = ID;
        this.SKU = SKU;
        this.Description = Description;
        this.Category = Category;
        this.Price = Price;
    }

    /**
     * Retrieves the unique identifier of the shop item which is also the PK (Primary Key) of the Database.
     *
     * @return The ID of the shop item.
     */
    public int getID() {
        return ID;
    }

    /**
     * Retrieves the stock-keeping unit code of the shop item.
     *
     * @return The SKU of the shop item.
     */
    public String getSKU() {
        return SKU;
    }

    /**
     * Retrieves the category to which the shop item belongs.
     *
     * @return The category of the shop item.
     */
    public String getCategory() {
        return Category;
    }

    /**
     * Retrieves the description of the shop item.
     *
     * @return The description of the shop item.
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Retrieves the price of the shop item.
     *
     * @return The price of the shop item.
     */
    public int getPrice() {
        return Price;
    }

    /**
     * Sets the unique identifier of the shop item.
     *
     * @param ID The new ID to set.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Sets the stock-keeping unit code of the shop item.
     *
     * @param SKU The new SKU to set.
     */
    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    /**
     * Sets the category to which the shop item belongs.
     *
     * @param Category The new category to set.
     */
    public void setCategory(String Category) {
        this.Category = Category;
    }

    /**
     * Sets the description of the shop item.
     *
     * @param Description The new description to set.
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * Sets the price of the shop item.
     *
     * @param Price The new price to set.
     */
    public void setPrice(int Price) {
        this.Price = Price;
    }

    /**
     * Provides a string representation of the shop item.
     *
     * @return A string containing the details of the shop item.
     */
    @Override
    public String toString() {
        return "[ID : " + ID +
                " | SKU : '" + SKU +
                "' | Category : '" + Category +
                "' | Description : '" + Description +
                "' | Price : '" + Price + "']\n";
    }
}

enum ItemTypes {
  raw, manufactured, imported
}

class TaxData {
    private static Float normalTaxPercentage = 12.5f;
    private static Float extraManufacturedTaxPercentage = 2.5f;

    public static float getRawItemTax(float price){
        return ((price * normalTaxPercentage)/100) + price;
    }

    public static float getManufacturedItemTax(float price){
        float normalTax = (price * normalTaxPercentage)/100;
        return normalTax + price + (extraManufacturedTaxPercentage * normalTax)/100;
    }

    public static float getImportedItemTax(float price){
        float normalTax = (price * normalTaxPercentage)/100;
        return normalTax + price + (extraManufacturedTaxPercentage * normalTax)/100;
    }
}

public class Items {
    private String name;
    private Float price;
    private ItemTypes type;
    private Integer quantity;
    private Float tax;
    private Float totalPrice;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setType(ItemTypes type) {
        this.type = type;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public ItemTypes getType() {
        return type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Float getTax() {
        return tax;
    }

    public Float getTotalPrice() {
        return (price + calculateTax()) * quantity;
    }

    public Float calculateTax() {
        switch (type){
            case raw:
                return TaxData.getRawItemTax(price);
            case manufactured:
                return TaxData.getManufacturedItemTax(price);
            case imported:
                return  TaxData.getImportedItemTax(price);
            default:
                System.err.println(String.format("Not able to calculate Tax as type %s is not correct", type));
                return 0f;
        }
    }

    @Override
    public String toString() {
        return String.format("""
                Name: %s,
                Type: %s,
                Price: %2f,
                Quantity: %o,
                Final Price: %2f
                """,name, type, price, quantity, getTotalPrice());
    }
}

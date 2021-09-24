import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InventoryManager {
    private List<Items> itemsList;
    private boolean isMoreItemsRequired = true;
    private Scanner scanner = new Scanner(System.in);

    public void readItemData() {
        System.out.println("*********** Starting creation of Inventory. Please provide the Item details *************");
        while(isMoreItemsRequired){
            readItem();
        }
    }

    private void readItem() {
        itemsList = new ArrayList<>();
        Items currentItem = new Items();
        System.out.println("Enter the following Item details: ");
        System.out.println("Name of the Item: ");
        String itemName = readValidStringFromConsole();
        currentItem.setName(itemName);
        System.out.println("Type of the Item: ");
        String type = readValidTypeFromConsole();
        currentItem.setType(ItemTypes.valueOf(type));
        System.out.println("Price of the Item: ");
        Float price = readValidIntegerFromConsole("Float").floatValue();
        currentItem.setPrice(price);
        System.out.println("Quantity of the Item: ");
        Integer quantity = readValidIntegerFromConsole("Integer").intValue();
        currentItem.setQuantity(quantity);

        itemsList.add(currentItem);
        System.out.println("Item added successfully");
        checkMoreItemsRequired();

    }

    private void checkMoreItemsRequired() {
        System.out.println("Do you want to add more Items? (y/n): ");
        String moreLimitChoice = readValidStringFromConsole();
        if(isTruthy(moreLimitChoice)){
            isMoreItemsRequired = true;
        } else if(isFalsy(moreLimitChoice)){
            isMoreItemsRequired = false;
        } else {
            System.out.println("Invalid choice please try again:");
            moreLimitChoice = null;
            checkMoreItemsRequired();
        }
    }

    private boolean isTruthy(String value) {
        return Arrays.asList("Yes", "YES", "Y", "y").contains(value);
    }

    private boolean isFalsy(String value) {
        return Arrays.asList("No", "N", "n", "no").contains(value);
    }

    private String readValidTypeFromConsole() {
        String itemType = scanner.next();
        if(itemType!= null && itemType.trim().length() > 0 && !Arrays.asList("raw","manufactured","imported").contains(itemType)){
            System.out.println("Type can only be raw, imported or manufactured, Please try again...");
            return readValidTypeFromConsole();
        }
        return itemType;
    }

    private Number readValidIntegerFromConsole(String type) {
        Number userValue = null;
        String strVal = null;
        if("Integer".equals(type)){
            strVal = scanner.nextLine();
            try{
                userValue = Integer.parseInt(strVal);
            } catch (NumberFormatException e){
                System.out.println("Value should be valid number, Please try again...");
                return readValidIntegerFromConsole(type);
            }
        } else if("Float".equals(type)){
            strVal = scanner.nextLine();
            try{
                userValue = Float.parseFloat(strVal);
            } catch (NumberFormatException e){
                System.out.println("Value should be valid number, Please try again...");
                return readValidIntegerFromConsole(type);
            }
        }
        if(userValue == null || userValue.intValue() == 0){
            System.out.println("Value cannot be 0 or null, Please try again...");
            return readValidIntegerFromConsole(type);
        }
        return userValue;
    }

    private String readValidStringFromConsole() {
        String userString = scanner.nextLine();
        if(userString == null || userString.trim().length() == 0){
            System.out.println("Name cannot be empty or null, Please try again...");
            readValidStringFromConsole();
        }
        return userString;
    }

    public void printCurrentInventory() {
        System.out.println("********* current inventory ************");
        itemsList.stream().forEach((item) -> System.out.println(item.toString()));
    }
}

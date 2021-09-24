import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.readItemData();
        inventoryManager.printCurrentInventory();
    }
}

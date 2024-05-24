import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    @Test
    public void testNullAtStart() {
        List<Item> allItems = null;
        int payment = 100;
        RuntimeException exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems, payment));
        assertEquals("allItems list can't be null!", exception.getMessage());
    }

    @Test
    public void testNullName() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item(null, "123456", 100, 0));
        int payment = 100;
        assertTrue(SILab2.checkCart(allItems, payment));
    }

    @Test
    public void testNullBarcode() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item("item1", null, 100, 0));
        int payment = 100;
        RuntimeException exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems, payment));
        assertEquals("No barcode!", exception.getMessage());
    }


    @Test
    public void testSumGreaterThanPayment() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item("item1", "123456", 150, 0));
        int payment = 100;
        assertFalse(SILab2.checkCart(allItems, payment));
    }

    @Test
    public void testInvalidBarcode() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item("item1", "123a56", 100, 0));
        int payment = 100;
        RuntimeException exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems, payment));
        assertEquals("Invalid character in item barcode!", exception.getMessage());
    }

    @Test
    public void testValidBarcode() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item("item1", "123456", 100, 0));
        int payment = 100;
        assertTrue(SILab2.checkCart(allItems, payment));
    }

    @Test
    public void testDiscount() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item("item1", "123456", 100, 0.1f));
        int payment = 90;
        assertTrue(SILab2.checkCart(allItems, payment));
    }

    @Test
    public void testAdditionalDiscount() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item("item1", "012345", 400, 0.1f));
        int payment = 46;
        assertTrue(SILab2.checkCart(allItems, payment));
    }
}



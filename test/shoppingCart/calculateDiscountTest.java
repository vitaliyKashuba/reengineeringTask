package shoppingCart;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeMap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static shoppingCart.addItemTests.shoppingCart;
import static shoppingCart.ShoppingCart.Item;

@RunWith(Parameterized.class)
public class calculateDiscountTest
{
    private Item testItem;
    private int expectedResult;
    
    public calculateDiscountTest(Item item, Integer discount)
    {
        this.testItem = item;
        this.expectedResult = discount;
    }
    
    @Test
    public void calculateDiscountTest()
    {
        assertEquals(expectedResult, ShoppingCart.calculateDiscount(testItem));
    }
    
    @Parameters
    public static Collection testData()
    {
        return Arrays.asList(new Object[][] 
        {
            { new Item("iten", ShoppingCart.ITEM_REGULAR, 10, 1), 0 }, // 0 ?
            { new Item("iten", ShoppingCart.ITEM_SECOND_FREE, 10, 1), 0 },
            { new Item("iten", ShoppingCart.ITEM_SECOND_FREE, 10, 2), 50 },
            { new Item("iten", ShoppingCart.ITEM_DISCOUNT, 10, 1), 10 },
            { new Item("iten", ShoppingCart.ITEM_DISCOUNT, 10, 10), 20 },
            { new Item("iten", ShoppingCart.ITEM_DISCOUNT, 10, 20), 30 },
            { new Item("iten", ShoppingCart.ITEM_DISCOUNT, 10, 30), 40 },
            { new Item("iten", ShoppingCart.ITEM_DISCOUNT, 10, 40), 50 },
            { new Item("iten", ShoppingCart.ITEM_DISCOUNT, 10, 50), 50 },
            { new Item("iten", ShoppingCart.ITEM_FOR_SALE, 10, 1), 90 }, //80
            { new Item("iten", ShoppingCart.ITEM_REGULAR, 10, 100), 10 },
            { new Item("iten", ShoppingCart.ITEM_REGULAR, 10, 200), 20 },
            { new Item("iten", ShoppingCart.ITEM_REGULAR, 10, 300), 30 },
            { new Item("iten", ShoppingCart.ITEM_REGULAR, 10, 400), 40 },
            { new Item("iten", ShoppingCart.ITEM_REGULAR, 10, 500), 50 },
            { new Item("iten", ShoppingCart.ITEM_REGULAR, 10, 600), 60 },
            { new Item("iten", ShoppingCart.ITEM_REGULAR, 10, 700), 70 },
            { new Item("iten", ShoppingCart.ITEM_REGULAR, 10, 800), 80 },
            { new Item("iten", ShoppingCart.ITEM_REGULAR, 10, 900), 80 },
            { new Item("iten", ShoppingCart.ITEM_SECOND_FREE, 10, 100), 60 },
            { new Item("iten", ShoppingCart.ITEM_SECOND_FREE, 10, 200), 70 },
            { new Item("iten", ShoppingCart.ITEM_SECOND_FREE, 10, 300), 80 },
            { new Item("iten", ShoppingCart.ITEM_SECOND_FREE, 10, 400), 80 },
            { new Item("iten", ShoppingCart.ITEM_DISCOUNT, 10, 100), 60 },
            { new Item("iten", ShoppingCart.ITEM_DISCOUNT, 10, 200), 70 },
            { new Item("iten", ShoppingCart.ITEM_DISCOUNT, 10, 300), 80 },
            { new Item("iten", ShoppingCart.ITEM_DISCOUNT, 10, 400), 80 },
        });

    }
}

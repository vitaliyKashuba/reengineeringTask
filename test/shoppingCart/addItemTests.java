package shoppingCart;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class addItemTests
{
    static ShoppingCart shoppingCart;
    static ShoppingCart shoppingCartForOversizeTest;
    
    public addItemTests()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        shoppingCart = new ShoppingCart();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void addItemWithZeroTitleLenght()
    {
        shoppingCart.addItem("", 10, 10, 0);
    }
    
    @Test
    public void addItemTest1()
    {
        shoppingCart.addItem("t", 10, 10, 0);
    }
    
    @Test
    public void addItemTest2()
    {
        shoppingCart.addItem("title", 10, 10, 0);
    }
    
    @Test
    public void addItemTest3()
    {
        shoppingCart.addItem("titleWith32SymbolsLong_123456789", 10, 10, 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void addItemWithOversizedTitle()
    {
        shoppingCart.addItem("titleWith33SymbolsLong_1234567890", 10, 10, 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void addItemWithZeroPrice()
    {
        shoppingCart.addItem("title", 0, 10, 0);
    }
    
    @Test
    public void addItemTest4()
    {
        shoppingCart.addItem("title", 1, 10, 0);
    }
    
    @Test
    public void addItemTest5()
    {
        shoppingCart.addItem("title", 500, 10, 0);
    }
    
    @Test
    public void addItemTest6()
    {
        shoppingCart.addItem("title", 999, 10, 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void addItemWithOverprice()
    {
        shoppingCart.addItem("title", 1000, 10, 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void addItemWithZeroQuantity()
    {
        shoppingCart.addItem("title", 10, 0, 0);
    }
    
    @Test
    public void addItemTest7()
    {
        shoppingCart.addItem("title", 10, 1, 0);
    }
    
    @Test
    public void addItemTest8()
    {
        shoppingCart.addItem("title", 10, 500, 0);
    }
    
    @Test
    public void addItemTest9()
    {
        shoppingCart.addItem("title", 10, 1000, 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void addItemWithOvequantity()
    {
        shoppingCart.addItem("title", 10, 1001, 0);
    }
    
    @Test
    public void addItemTest10()
    {
        shoppingCart.addItem("title", 10, 10, ShoppingCart.ITEM_DISCOUNT);
    }
    
    @Test
    public void addItemTest11()
    {
        shoppingCart.addItem("title", 10, 10, ShoppingCart.ITEM_FOR_SALE);
    }
    
    @Test
    public void addItemTest12()
    {
        shoppingCart.addItem("title", 10, 10, ShoppingCart.ITEM_REGULAR);
    }
    
    @Test
    public void addItemTest13()
    {
        shoppingCart.addItem("title", 10, 10, ShoppingCart.ITEM_SECOND_FREE);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void addItemWithUnknownType()
    {
        shoppingCart.addItem("te", 10, 10, 5);
    }
    
    @Test
    public void capacityTest()
    {
        shoppingCartForOversizeTest = new ShoppingCart();
        for(int i = 0; i < 99; i++)
        {
            shoppingCartForOversizeTest.addItem("title", 10, 10, 0);
        }
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void capacityOversizeTest()
    {
        capacityTest();
        shoppingCartForOversizeTest.addItem("title", 10, 10, 0);
    }
            
}

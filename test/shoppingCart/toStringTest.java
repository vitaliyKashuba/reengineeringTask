/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingCart;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static shoppingCart.ShoppingCart.ItemTypes.*;
import static shoppingCart.addItemTests.shoppingCart;

public class toStringTest
{
    static ShoppingCart cart;
    
    @BeforeClass
    public static void setUpClass()
    {
        cart = new ShoppingCart();
    }
    
    @Test
    public void toStringTest1(){
        cart.addItem("Apple", 0.99, 5, ITEM_REGULAR);
        cart.addItem("Banana", 20.00, 4, ITEM_SECOND_FREE);
        cart.addItem("A long piece of toilet paper", 17.20, 1, ITEM_FOR_SALE);
        cart.addItem("Nails", 2.00, 500, ITEM_REGULAR);
        Assert.assertEquals(" # Item                   Price Quan. Discount      Total\n" +
                            "---------------------------------------------------------\n" +
                            " 1 Apple                   $.99    5         -      $4.95\n" +
                            " 2 Banana                $20.00    4       50%     $40.00\n" +
                            " 3 A long piece of t...  $17.20    1       90%      $1.72\n" +
                            " 4 Nails                  $2.00  500       50%    $500.00\n" +
                            "---------------------------------------------------------\n" +
                            " 4                                                $546.67", cart.toString());
    }
}

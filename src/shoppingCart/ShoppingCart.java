package shoppingCart;

import java.util.*;
import java.text.*;
import static shoppingCart.ShoppingCart.ItemTypes.*;
import sun.nio.cs.HistoricallyNamedCharset;

/**
 * Containing items and calculating price.
 */
public class ShoppingCart{
    
    /**
     * change constants
     */
    enum ItemTypes{
        ITEM_REGULAR, ITEM_DISCOUNT, ITEM_SECOND_FREE, ITEM_FOR_SALE;
    }
	/** Container for added items */
    private List<Item> items = new ArrayList<>();
	
	/** item info */
    public static class Item {
        private String title;
        private double price;
        private int quantity;
        private ItemTypes type;
        
        public Item(String title, ItemTypes type, double price, int quantity)
        {
            this.title = title;
            this.price = price;
            this.quantity = quantity;
            this.type = type;
        } 

        public String getTitle()
        {
            return title;
        }

        public double getPrice()
        {
            return price;
        }

        public int getQuantity()
        {
            return quantity;
        }

        public ItemTypes getType()
        {
            return type;
        }
        
    }
    
    /**
     * Tests all class methods.
     */
    public static void main(String[] args){
        // TODO: add tests here
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 0.99, 5, ITEM_REGULAR);
        cart.addItem("Banana", 20.00, 4, ITEM_SECOND_FREE);
        cart.addItem("A long piece of toilet paper", 17.20, 1, ITEM_FOR_SALE);
        cart.addItem("Nails", 2.00, 500, ITEM_REGULAR);
        System.out.println(cart.toString());
    }

    /**
     * Adds new item.
     *
     * @param title     item title 1 to 32 symbols
     * @param price     item ptice in cents, > 0, < 1000
     * @param quantity  item quantity, from 1 to 1000
     * @param type      item type, on of ShoppingCart.enum's
     *
     * @throws IndexOutOfBoundsException if total items added over 99
     * @throws IllegalArgumentException if some value is wrong
     */
    public void addItem(String title, double price, int quantity, ItemTypes type) {
        if (title == null || title.length() == 0 || title.length() > 32)
            throw new IllegalArgumentException("Illegal title");
            
        if (price < 0.01 || price >= 1000.00)
            throw new IllegalArgumentException("Illegal price");
            
        if (quantity <= 0 || quantity > 1000)
            throw new IllegalArgumentException("Illegal quantity");
            
        if (items.size() == 99)
            throw new IndexOutOfBoundsException("No more space in cart");
            
        items.add(new Item(title, type, price, quantity));
    }
    
    /**
     * add for old contructor compability
     * to avoid rewriting lot of unittests
     * @deprecated because use int type field instead of enum
     */
    @Deprecated
    public void addItem(String title, double price, int quantity, int type){
        switch(type){
            case 0:
                addItem(title, price, quantity, ITEM_REGULAR);
                break;
            case 1:
                addItem(title, price, quantity, ITEM_DISCOUNT);
                break;
            case 2:
                addItem(title, price, quantity, ITEM_SECOND_FREE);
                break;
            case 3:
                addItem(title, price, quantity, ITEM_FOR_SALE);
                break;
            default:
            throw new IllegalArgumentException("invalid type");  
        }
    }
    
    /**
     * Formats shopping price.
     *
     * @return  string as lines, separated with \n,
     *          first line:   # Item                   Price Quan. Discount      Total
     *          second line: ---------------------------------------------------------
     *          next lines:  NN Title                 $PP.PP    Q       DD%     $TT.TT
     *                        1 Some title              $.30    2         -       $.60
     *                        2 Some very long ti... $100.00    1       50%     $50.00
     *                       ...
     *                       31 Item 42              $999.00 1000         - $999000.00
     *          end line:    ---------------------------------------------------------
     *          last line:   31                                             $999050.60
     *
     *          Item title is trimmed to 20 chars adding '...'
     *          
     *          if no items in cart returns "No items." string.
     */
     public String toString() {
        StringBuffer sb = new StringBuffer();
        if (items.size() == 0)
            return "No items.";
            
        double total = 0.00;
        
        toStringBuildHeader(sb);
        toStringBuildBody(sb);
        toStringBuildFooter(sb);

        return sb.toString();
    }

    // --- private section -----------------------------------------------------

    private static final NumberFormat MONEY;
    
	static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        MONEY = new DecimalFormat("$#.00", symbols);
    }

    /**
     * Adds to string buffer given string, padded with spaces.
     * @return "    str".length() == width
     */
    private static void appendPaddedRight(StringBuffer sb, String str, int width){
        for (int i = str.length(); i < width; i++){
            sb.append(" ");
        }
        sb.append(str);
    }
    
    /**
     * Adds string to buffer, wills spaces to width.
     * If string is longer than width it is trimmed and ends with '...'
     */
    private static void appendPaddedLeft(StringBuffer sb, String str, int width){
        if (str.length() > width) {
            sb.append(str.substring(0, width-3));
            sb.append("...");
        }
        else {
            sb.append(str);
            for (int i = str.length(); i < width; i++)
                sb.append(" ");
        }
    }
    
    /**
     * add header to string buffer, used in toString()
     */
    private void toStringBuildHeader(StringBuffer sb)
    {
        sb.append(" # Item                   Price Quan. Discount      Total\n");
        sb.append("---------------------------------------------------------\n");
    }
    
    /**
     * add body to string buffer, used in toString()
     */
    private void toStringBuildBody(StringBuffer sb)
    {
        for (int i = 0; i < items.size(); i++) {
            Item item = (Item) items.get(i);
            int discount = calculateDiscount(item);
            
            appendPaddedRight(sb, String.valueOf(i + 1), 2);
            sb.append(" ");
            appendPaddedLeft(sb, item.title, 20);
            sb.append(" ");
            appendPaddedRight(sb, MONEY.format(item.price), 7);
            sb.append(" ");
            appendPaddedRight(sb, String.valueOf(item.quantity), 4);
            sb.append("  ");
            if (discount == 0)
                sb.append("       -");
            else {
                appendPaddedRight(sb, String.valueOf(discount), 7);
                sb.append("%");
            }
            sb.append(" ");
            appendPaddedRight(sb, MONEY.format(calculateItemTotal(item)), 10);
            sb.append("\n");
        }
    }
    
    /**
     * add footer to string buffer, used in toString()
     */
    private void toStringBuildFooter(StringBuffer sb)
    {
        sb.append("---------------------------------------------------------\n");
        appendPaddedRight(sb, String.valueOf(items.size()), 2);
        sb.append("                                             ");
        appendPaddedRight(sb, MONEY.format(calculateOrderTotal()), 10);
    }
    
    private double calculateItemTotal(Item i){
        return i.getPrice() * i.getQuantity() * (100.00 - calculateDiscount(i)) / 100.00;
    }
    
    private double calculateOrderTotal(){
        double total=0;
        for(Item i : items)
        {
            total = total + calculateItemTotal(i);
        }
        return total;
    }

    /**
     * Calculates item's discount.
     * For ITEM_REGULAR discount is 0%;
     * For ITEM_SECOND_FREE discount is 50% if quantity > 1
     * For ITEM_DISCOUNT discount is 10% + 10% for each full 10 items, but not more than 50% total
     * For ITEM_FOR_SALE discount is 90%
     * For each full 100 items item gets additional 10%, but not more than 80% total
     */    
    public static int calculateDiscount(Item item){
        int discount = 0;
        switch (item.type) {
            case ITEM_SECOND_FREE:
                if (item.quantity > 1)
                    discount = 50;
                break;
                
            case ITEM_DISCOUNT:
                discount = 10 + item.quantity / 10 * 10;
                if (discount > 50)
                    discount = 50;
                break;
                
            case ITEM_FOR_SALE:
                discount = 90;
                return discount;
        }
        discount += item.quantity / 100 * 10;
        if (discount > 80)
            discount = 80;
            
        return discount;
    }
}
















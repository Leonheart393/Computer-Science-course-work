/**
 * File: ChangeMaker.java
 * @Gajenthiran Gunasegaram
 */
import java.text.NumberFormat;// to format o/p as currency

/**
 * Computes change due --- using the minimum coinage 
 * possible --- when the user enters the amount of a
 * purchase and the amount tendered.
 */
public class ChangeMaker
{
    // instance var's
    private double amountDue;// total cost of purchases
    private double amountTendered;// amount paid
    private int dollarsDue;// number of dollar coins due in change
    private int quartersDue;// number of quarters due
    private int dimesDue;// number of dimes due
    private int nickelsDue;// number of nickels due
    private int penniesDue;// number of pennies due
    
    private NumberFormat formatter = NumberFormat.getCurrencyInstance();
    
    /**
     * Constructs a ChangeMaker object
     * @param totalDue the amount of the purchase
     * @param amountReceived the amount tendered (paid)
     */
    public ChangeMaker(double totalDue, double amountReceived)
    {
        // initialize instance var's
        amountDue = totalDue;
        amountTendered = amountReceived;
        // other instance var's automatically initialized to 0
        
        System.out.println("\nAmount Due: " + formatter.format(amountDue));
        System.out.println("Amount Received: "
                + formatter.format(amountTendered));
    }
    
    /**
     * Computes the number of coins of each denomination to be given as change
     */
    public void computeChange()
    {
        int changeDue;  // total change due, in cents
        
        if (amountDue == amountTendered)
        {
            System.out.println("\nExact change! Thanks and have a nice day!");
        }
        else if (amountDue > amountTendered)    // owes more money!
        {
            System.out.println("\nLo siento, you stil owe me another "
                    + formatter.format(amountDue - amountTendered));
        }
        else // change is due
        {
            // compute change due, in cents for easier calculations
            changeDue = (int) Math.round((amountTendered - amountDue) *100);
            
            // compute number of dollars due, and reduce change due accordingly
            dollarsDue = changeDue / 100; // note integer divide!
            changeDue = changeDue % 100;// ...and mode operation
            
            // now do the same for all other coins
            
            quartersDue = changeDue / 25;
            changeDue = changeDue % 25;
            
            dimesDue = changeDue / 10;
            changeDue = changeDue % 10;
            
            nickelsDue = changeDue / 5;
            changeDue = changeDue % 5;
            
            penniesDue = changeDue;             // change due is <= 4 cents
            
            // activate the change dispenser
            this.giveChange();
        }
      }
    
    // dispenses (i.e., prints) the coins comprising the change
    private void giveChange()
    {
        System.out.println("\nYour Change");
        if (dollarsDue !=0)
        {
            System.out.println("\t" + dollarsDue + " dollar(s)");
        }
        if (quartersDue != 0)
        {
            System.out.println("\t" + quartersDue + " quarter(s)");
        }
        if (dimesDue != 0)
        {
            System.out.println("\t" + dimesDue + " dime(s)");
        }
        if (nickelsDue != 0)
        {
            System.out.println("\t" + nickelsDue + " nickel(s)");
        }
        if (penniesDue != 0)
        {
            System.out.print("\t" + penniesDue);
            if (penniesDue == 1)
            {
                System.out.println(" pennies");
            }
            else
            {
                System.out.println(" pennies");
            }
        }
        System.out.println("\nThanks and have a nice day!");
    }
} //end of Changemaker class declaration

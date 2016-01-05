import javax.swing.JOptionPane;     // for input

/**
 * A test class for the ChangeMaker class
 */
class ChangeMakerTester {
    public static void main(String[] args) {
        double amountDue ;	// total cost of purchases
        double amountPaid ;	// amount paid
        String input ;		// returned by input dialog box
        
        // get amount due and amount paid from user
        
        input = JOptionPane.showInputDialog( "What is the amount due?") ;
        
        amountDue = Double.parseDouble(input) ;   
        
        input = JOptionPane.showInputDialog( "What is the amount paid?") ;
        
        amountPaid = Double.parseDouble(input) ; 
        
        // create a ChangeMaker object (myChangeMaker) using these values
        ChangeMaker myChangeMaker = new ChangeMaker(amountDue, amountPaid) ;

        // call computeChange method for myChangeMaker object
        myChangeMaker.computeChange() ;
    }
}   // end of ChangeMakerTester class declaration

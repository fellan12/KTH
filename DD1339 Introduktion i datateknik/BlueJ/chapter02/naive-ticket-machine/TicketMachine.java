/**
 * TicketMachine models a naive ticket machine that issues
 * flat-fare tickets.
 * The price of a ticket is specified via the constructor.
 * It is a naive machine in the sense that it trusts its users
 * to insert enough money before trying to print a ticket.
 * It also assumes that users enter sensible amounts.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class TicketMachine
{
    // The price of a ticket from this machine.
    private int price;
    // The amount of money entered by a customer so far.
    private int balance;
    // The total amount of money collected by this machine.
    private int total;

    /**
     * Create a machine that issues tickets of the given price.
     * Note that the price must be greater than zero, and there
     * are no checks to ensure this.
     */
    public TicketMachine(int cost)
    {
        price = cost;
        balance = 0;
        total = 0;
    }

    /**
     * Create a machine that issues tickets of the set price of 100 cents.
     * Note that the price is default set to 100 cents
     */
    public TicketMachine()
    {
        price = 100;
        balance = 0;
        total = 0;
    }

    public int refundBalance(){
        int refundAmount = balance;
        balance = 0;
        return refundAmount;
    }    
    
    /**
     * Return the price of a ticket.
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * Return the amount of money already inserted for the
     * next ticket.
     */
    public int getBalance()
    {
        return balance;
    }

    /**
     * Return the total of money
     */
    public int getTotal()
    {
        return total;
    }

    /**
     * emptying the machine, setting the totalt to zero
     */
    public int empty()
    {
        int amountInMachine = total;
        total=0;
        return amountInMachine;
    }

    /**
     * Receive an amount of money from a customer.
     */
    public void insertMoney(int amount)
    {
        if (amount > 0){
            balance = balance + amount;
        }else{
            System.out.println("Use a positive amount rahter then: " + amount);
        }
    }

    /**
     * Set a new price for the ticket.
     */
    public void setPrice(int cost)
    {
        price = cost;
    }

    /**
     * Reduce price by the given amount.
     */
    public void discount(int amount){
        price -= amount;
    }
    
    /**
     * Print a ticket.
     * Update the total collected and
     * reduce the balance to zero.
     */
    public void printTicket()
    {
        int amountLeftToPay = (price-balance);
        if(amountLeftToPay<0){
            // Simulate the printing of a ticket.
            System.out.println("##################");
            System.out.println("# The BlueJ Line");
            System.out.println("# Ticket");
            System.out.println("# " + price + " cents.");
            System.out.println("##################");
            System.out.println();
            // Update the total collected with the balance.
            total = total + balance;
            // Set the new balance after buying ticket
            balance -= price;
        }else{
            System.out.println("You must insert " +(amountLeftToPay)+ " more cents");
        }
    }
}

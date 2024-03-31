/**
 * @author Joshua Panicker
 * CIS 36B, Lab 7
 */
import java.util.ArrayList;
import java.text.DecimalFormat;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String accountNum;
    private double cash;
    public static final int NEXT = 1;

    private ArrayList<MutualFundAccount> funds;

    /**CONSTRUCTORS*/

    /**
     * Creates a new account
     * @param firstName member first name
     * @param lastName member last name
     * @param email the member email
     * @param password the member password
     * @param cash the starting amount of cash
     * @param funds the member's current MutualFunds
     * Calls getAccountSeed and assigns accountNum to this value
     * after converting it to a String BY USING CONCATENATION <-required!
     * Hint: Make sure you get no warnings or you did not call getAccountSeed
     * correctly!
     * Also creates a new, empty ArrayList of funds
     */
    public Customer(String firstName, String lastName, String email, String password,
                    double cash, ArrayList<MutualFundAccount> funds, String accountNum)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.cash = cash;
        this.funds = funds;
        this.accountNum = accountNum;
    }

    /**ACCESORS*/

    /**
     * Accesses the customer first name
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Accesses the customer last name
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Accesses the user's account number
     * @return the account number
     */
    public String getAccountNum() {
        return accountNum;
    }

    /**
     * Accesses the email address
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Determines whether a given password matches the customer password
     * @param anotherPassword the password to compare
     * @return whether the two passwords match
     */
    public boolean passwordMatch(String anotherPassword)
    {
        return password.equals(anotherPassword);
    }


    /**
     * Accesses a specific fund
     * @param fund the chosen fund
     * @return the specified mutual fund
     */
    public MutualFund getFund(int fund)
    {
        return funds.get(fund).getMf();
    }

    /**
     * Accesses the amount of cash in your account
     * @return the amount of cash
     */
    public double getCash() {
        return cash;
    }

    /**
     * Accesses the list of mutual fund accounts
     * @return the mutual fund accounts
     */
    public ArrayList<MutualFundAccount> getFunds() {
        return funds;
    }

    /**MUTATORS*/

    /**
     * Updates the customer first name
     * @param firstName a new first name
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Updates the customer last name
     * @param lastName a new last name
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Updates the value of the email address
     * @param email the Customer's email address
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Sets the amount of cash in your account
     * @param accountNum the account number
     */
    public void setAccountNumber(String accountNum) {
        this.accountNum = accountNum;
    }

    /**
     * Updates the Account number
     * @param password the Customer password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Increases/Decreases the amount of cash
     * by adding to the existing cash
     * @param cash the amount of cash to add
     */
    public void updateCash(double cash)
    {
        this.cash += cash;
    }

    private MutualFundAccount findMutualFundAccount(MutualFund mf)
    {
        for (int i = 0; i < funds.size(); i++)
        {
            if (funds.get(i).getMf() == mf)
            {
                return funds.get(i);
            }
        }
        return null;
    }
    /**
     * Adds a new mutual fund to the user's
     * list of funds or updates the total shares
     * if the mutual fund is already owned
     * @param shares the desired number of shares
     * @param mf a new mutual fund
     * @return whether the fund was added
     * to the customer's account - i.e. the
     * customer had sufficient cash to add
     * the MutualFund
     * Decrements the amount of cash if purchase made
     */
    public boolean addFund(double shares, MutualFund mf)
    {
     //   double totalCost = (mf.getPricePerShare() * shares) + mf.getTradingFee();
        double totalCost = mf.getPricePerShare() * shares;
        if (cash >= totalCost)
        {
            MutualFundAccount mfAccount = findMutualFundAccount(mf);
            if (mfAccount == null)
            {
                mfAccount = new MutualFundAccount(shares, mf);
                String mfAccountNum = accountNum + "-" + Integer.toString(funds.size() + NEXT);
                mfAccount.setAccountNum(mfAccountNum);
                funds.add(mfAccount);
            }
            else
                mfAccount.updateShares(shares);

            cash = cash - totalCost;

            return true;
        }
        else
            return false;
    }

    /**
     * Sells a Mutual Fund and
     * returns the price per share
     * times the number of shares
     * to cash minus the fee
     * fee is % * price per share * number of shares
     * @param index the index of the mutual
     * fund in the funds ArrayList
     */
    public void sellFund(int index)
    {
        MutualFundAccount mfAccount = funds.get(index);
        double numShares = mfAccount.getNumShares();
        double pricePerShare = mfAccount.getMf().getPricePerShare();
       // double totalGains = numShares * pricePerShare - mfAccount.getMf().getTradingFee();
        double totalGains = numShares * pricePerShare;

        mfAccount.updateShares(-numShares);
        funds.remove(mfAccount);
        for (int i = 0; i < funds.size(); i++)
        {
            String mfAccountNum = accountNum + "-" + Integer.toString(i + NEXT);
            funds.get(i).setAccountNum(mfAccountNum);
        }
        cash = cash + totalGains;
    }

    /**ADDITIONAL OPERATIONS*/

    /**
     * Creates a String of customer information
     * in the form
     * Name: <firstName> <lastName>
     * Account Number: <accountNum>
     * Total Cash: $<cash>
     * Note that cash is formatted $XXX,XXX,XXX.XX
     *
     * CurrentFunds:
     * Mutual Funds
     * Account Number: <accountNum>-X
     * where x is a number from 1 to funds size
     */

    @Override public String toString()
    {
        String pattern = "###,###.00";
        DecimalFormat df = new DecimalFormat(pattern);

        return "Name: " + firstName + " "+ lastName + "\n" +
                "Email: " + email + "\n" +
                "Account Number: " + accountNum + "\n" +
                "Total Cash: $" + df.format(cash);
    }
}
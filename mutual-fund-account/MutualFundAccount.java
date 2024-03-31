/**
 * @author Joshua Panicker
 * CIS 36B, Lab 7
 */
public class MutualFundAccount {
    private double numShares;
    private MutualFund mf;
    private int mfIndex;
    private String accountNum;

    private static int accountSeed = 100000000;

    /**CONSTRUCTORS*/

    /**
     * One-argument constructor
     * @param mf the mutual fund for this account
     */
    public MutualFundAccount(MutualFund mf)
    {
        this.mf = mf;
    }

    /**
     * Two-argument constructor
     * @param numShares total shares of the mutual fund
     * @param mf the mutual fund
     */
    public MutualFundAccount(double numShares, MutualFund mf)
    {
        this.mf = mf;
        this.numShares = numShares;
    }

    /**ACCESSORS*/

    /**
     * Accesses the total number of shares
     * @return the total shares
     */
    public double getNumShares() {
        return numShares;
    }

    /**
     * Accesses the mutual fund
     * @return the mutual fund
     */
    public MutualFund getMf() {
        return mf;
    }

    /**
     * Increments the account seed and returns
     * the new value
     * @return the incremented account seed
     */
    public static int getAccountSeed() {
        accountSeed++;
        return accountSeed;
    }

    /**
     * gets the account number
     */
    public String getAccountNum()
    {
        return accountNum;
    }
    /**MUTATORS*/

    /**
     * Increases/Decreases the total shares
     * by the given amount
     * @param numShares the amount to increase
     * or decrease
     */
    public void updateShares(double numShares)
    {
        this.numShares += numShares;
    }

    public void setAccountNum(String accountNum)
    {
        this.accountNum = accountNum;
    }
    /**
     * Creates a String of the mutual fund
     * information along with the following:
     * Total Shares: <numShares>
     */
    @Override
    public String toString() {
        return mf.toString() + "\n" +
                "Total Shares: " + numShares + "\n" +
                "Account Number: " + accountNum;
    }
}
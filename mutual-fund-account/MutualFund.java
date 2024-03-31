/**
 * @author Joshua Panicker
 * CIS 36B, Lab 7
 */
import java.text.DecimalFormat;

public class MutualFund {
    private final String fundName;
    private final String ticker;
    private double pricePerShare;
    private double tradingFee;

    /**CONSTRUCTORS*/

    /**
     * Four-argument constructor for MutualFund
     * @param fundName the name of the MutualFund
     * @param ticker its ticker symbol
     * @param pricePerShare the price of the fund per share
     * @param tradingFee the trading fee as a percent
     */
    public MutualFund(String fundName, String ticker, double pricePerShare, double tradingFee)
    {
        this.fundName = fundName;
        this.ticker = ticker;
        this.pricePerShare = pricePerShare;
        this.tradingFee = tradingFee;
    }

    /**ACCESSORS*/

    /**
     * Accesses the name of the fund
     * @return the fund name
     */
    public String getFundName() {
        return fundName;
    }

    /**
     * Accesses the ticker symbol
     * @return the ticker symbol
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Accesses the price per share
     * @return the price per share
     */
    public double getPricePerShare() {
        return pricePerShare;
    }

    /**
     * Accesses the trading fee
     * @return the trading fee
     */
    public double getTradingFee() {
        return tradingFee;
    }

    /**MUTATORS*/

    /**
     * Updates the share price
     * @param pricePerShare the new share price
     */
    public void setPricePerShare(double pricePerShare)
    {
        this.pricePerShare = pricePerShare;
    }

    /**
     * Updates the trading fee
     * @param tradingFee the new trading fee
     */
    public void setTradingFee(double tradingFee)
    {
        this.tradingFee = tradingFee;
    }

    /**ADDITIONAL OPERATIONS*/

    @Override
    public String toString() {
        String pattern = "###,###.00";
        DecimalFormat df = new DecimalFormat(pattern);
        String priceStr = df.format(pricePerShare);

        return fundName + "\n" +
                ticker  + "\n" +
                "Share Price: $" + priceStr + "\n" +
                "Trading Fee: " + tradingFee + "%";
    }
}

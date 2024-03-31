/**
 * @author Joshua Panicker
 * CIS 36B, Lab 7
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.text.DecimalFormat;

public class CustomerInterface {
    public static Scanner input;
    public static final int NEXT = 1;

    public static void main(String[] args) throws IOException {

        CustomerInterface customerInterface = new CustomerInterface();
        Customer customer;
        int customerIndex;
        String first, last, email, password, mutualName, ticker, choice;
        double cash, sharePrice, numShares, fee;
        boolean repeats = true;

        ArrayList<MutualFund> funds = new ArrayList<MutualFund>();
        ArrayList<Customer> customers = new ArrayList<Customer>();

        input = new Scanner(System.in);
        customerInterface.readMutualFunds(funds);
        customerInterface.readCustomers(customers, funds);

        System.out.print("Welcome to Mutual Fund InvestorTrack!\n\nEnter your email address: ");
        email = input.nextLine();
        System.out.print("Enter your password: ");
        password = input.nextLine();
        System.out.println();
        customerIndex = customerInterface.linearSearch(email, password, customers);
        if (customerIndex == -1)
        {
            System.out.println("We don't have your account on file.");
            System.out.println("Let's create a new account for you...");
            System.out.println();
            customer = customerInterface.createAccount(email, password, input);
            customers.add(customer);
        }
        else
        {
            customer = customers.get(customerIndex);

            System.out.printf("Hi, %s %s!\n", customer.getFirstName(), customer.getLastName());
            System.out.println();
            System.out.println("We have the following account information on file for you:");
            System.out.println();
            System.out.printf("%s\n", customer.toString());
            System.out.println();
            System.out.print("Current Funds:\n");
            for (int i = 0; i < customer.getFunds().size(); i++) {
                System.out.printf("%s\n", customer.getFunds().get(i).toString());
                System.out.println();
            }
            System.out.println();
        }
        while (repeats)
        {
            repeats = customerInterface.menu(customer, funds, input);
        }
        System.out.println();
        System.out.println("Goodbye!");
    }

    /**
     * Displays menu
     * @param customer the customer
     * @param funds the funds of the customer
     * @return whether to continue menu or not
     */
    public boolean menu(Customer customer,  ArrayList<MutualFund> funds, Scanner input) {
        String choice;

        System.out.println("Please select from the following options:");
        System.out.println("A. Add Cash");
        System.out.println("B. Buy a New Fund");
        System.out.println("C. Sell a Fund");
        System.out.println("X. Exit");
        System.out.print("Enter your choice: ");
        choice = input.next();

        if (choice.equals("A")) {
             addCash(customer, input);
        }
        else if (choice.equals("B"))
        {
             buyNewFund(customer, funds, input);
        }
        else if (choice.equals("C"))
        {
            sellFund(customer, input);
        }
        else if (choice.equals("X"))
        {
            return false;
        }
        else
        {
            System.out.println("Invalid menu option. Please enter A-C or X to exit.");
        }
        return true;
    }

    /**
     *  Adds cash to the customer's account
     * @param customer the customer
     */
    public void addCash(Customer customer, Scanner input)
    {
        double dollarAmount;
        String pattern = "###,###.00";
        DecimalFormat df = new DecimalFormat(pattern);

        System.out.printf("You currently have the following cash balance: $%s\n", df.format(customer.getCash()));
        System.out.print("Enter the dollar amount you wish to add: $");
        dollarAmount = input.nextInt();
        customer.updateCash(dollarAmount);
        dollarAmount = customer.getCash();
        System.out.println();
        System.out.printf("New cash balance: $%s\n", df.format(dollarAmount));
    }

    /**
     * Customer buys fund
     * @param customer the customer
     * @param funds the funds of the customer
     */
    public void buyNewFund(Customer customer, ArrayList<MutualFund> funds, Scanner input)
    {
        String ticker;
        int mfIndex;
        double numSharesToBuy;
        MutualFund mf;
        String pattern = "###,###.00";
        String pattern2 = "#.##";
        DecimalFormat df = new DecimalFormat(pattern);
        DecimalFormat df2 = new DecimalFormat(pattern2);

        System.out.printf("Enter the ticker symbol for the fund to purchase: ");
        ticker = input.next();
        mfIndex = binarySearch(funds, ticker);
        mf = funds.get(mfIndex);
        System.out.printf("%s sells for $%1.2f per share and has a fee of %s%%\n",
                mf.getFundName(), mf.getPricePerShare(), df2.format(mf.getTradingFee()));
        System.out.println();
        System.out.print("Enter the number of shares to purchase: ");
        numSharesToBuy = input.nextDouble();
        if (customer.addFund(numSharesToBuy, mf))
        {
            System.out.println("This fund has been added!");
            System.out.println();
            System.out.printf("Your updated cash balance: $%s\n", df.format(customer.getCash()));
            System.out.println();
            System.out.println("Here are your current funds:");
            System.out.println();
            for (int i = 0; i < customer.getFunds().size(); i++)
            {
                System.out.printf("%s\n", customer.getFunds().get(i).toString());
                System.out.println();
            }
        }
        else
        {
            System.out.println("You don't have enough cash for this transaction.");
            System.out.println("Please make a cash deposit to try again.");
        }
    }

    /**
     *  Sells the fund
     * @param customer the customer
     */
    public void sellFund(Customer customer, Scanner input)
    {
        int accountNumDigit;
        double numShares;
        MutualFundAccount mfa;
        String pattern = "###,###.00";
        DecimalFormat df = new DecimalFormat(pattern);

        System.out.println();
        System.out.println("Here are your current funds:");
        System.out.println();
        for (int i = 0; i < customer.getFunds().size(); i++)
        {
            System.out.printf("%s\n", customer.getFunds().get(i).toString());
            System.out.println();
        }
        System.out.print("Enter the last digit of the account number for the fund to sell: ");
        accountNumDigit = input.nextInt();
        if (accountNumDigit <= 0)
        {
            System.out.println("Invalid account number. Please try again.");
            return;
        }
        if (accountNumDigit < customer.getFunds().size())
        {
            customer.sellFund(accountNumDigit - NEXT);
            System.out.println();
            System.out.printf("Your updated cash balance: $%s\n", df.format(customer.getCash()));
            System.out.println();
            System.out.println("Here are your current funds:");
            System.out.println();
            for (int i = 0; i < customer.getFunds().size(); i++)
            {
                System.out.printf("%s\n", customer.getFunds().get(i).toString());
                System.out.println();
            }
            System.out.println();
        }
        else
        {
            System.out.println("Invalid account number. Please try again.");
            System.out.println();
        }
    }

    /**
     *  Reads the mutual funds file
     * @param funds the funds of the customer
     */
    public void readMutualFunds(ArrayList<MutualFund> funds) throws IOException
    {
        File file1 = new File("mutual_funds.txt");
        MutualFund mutualFund;
        Scanner input = new Scanner(file1);

        while (input.hasNextLine())
        {
            String first, last, email, password, mutualName, ticker, sharePriceStr, feeStr;
            double cash, sharePrice, numShares, fee;

            mutualName = input.nextLine();
            ticker = input.nextLine();
            sharePriceStr = input.nextLine();
            sharePrice = Double.parseDouble(sharePriceStr);
            feeStr = input.nextLine();
            fee = Double.parseDouble(feeStr);
            mutualFund = new MutualFund(mutualName, ticker, sharePrice, fee);
            funds.add(mutualFund);
        }
    }

    /**
     *  Reads the customers file
     * @param customers the customers
     * @param mfs the arraylist of all mutual funds
     */
    public void readCustomers(ArrayList<Customer> customers, ArrayList<MutualFund> mfs) throws IOException
    {
        Customer customer;
        File file1 = new File("customers.txt");
        Scanner input = new Scanner(file1);

        while (input.hasNextLine())
        {
            String cashStr, name, first, last, email, password, mutualName, ticker, numFundsStr, accountNum;
            double cash, sharePrice, numShares, fee;
            int numFunds;
            ArrayList<MutualFundAccount> funds = new ArrayList<MutualFundAccount>();

            name = input.nextLine();
            String[] parts = name.split(" ");
            first = parts[0];
            last = parts[1];
            email = input.nextLine();
            password = input.nextLine();
            cashStr = input.nextLine();
            cash = Double.parseDouble(cashStr);
            numFundsStr = input.nextLine();
            numFunds = Integer.parseInt(numFundsStr);
            for (int i = 0; i < numFunds; i++)
            {
                MutualFundAccount mfAccount;
                MutualFund mf;
                int mfIndex;
                String numSharesStr;

                ticker = input.nextLine();
                numSharesStr = input.nextLine();
                numShares = Double.parseDouble(numSharesStr);
                mfIndex = binarySearch(mfs, ticker);
                mf = mfs.get(mfIndex);
                mfAccount = new MutualFundAccount(numShares, mf);
                funds.add(mfAccount);
            }

            accountNum = Integer.toString(MutualFundAccount.getAccountSeed());
            customer = new Customer(first, last, email, password, cash, funds, accountNum);
            customers.add(customer);
            for (int i = 0; i < funds.size(); i++)
            {
                String mfAccountNum = accountNum + "-" + Integer.toString(i + NEXT);
                funds.get(i).setAccountNum(mfAccountNum);
            }
        }
    }


    /**
     * Searches for a Customer whose email and password match those currently stored
     * in the users ArrayList
     * @param email    the given email address
     * @param password the given password
     * @param users    the ArrayList storing customers on file
     * @return the location of the Customer or -1 if not found
     */
    public int linearSearch(String email, String password, ArrayList<Customer> users)
    {
        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getEmail().equals(email) && (users.get(i).passwordMatch(password)))
                return i;
        }
        return -1;
    }

    /**
     * Searches for a specified ticker symbol in a list using the binary search algorithm
     * @param array       the ArrayList of mutual funds
     * @param ticker      the ticker symbol to search for
     * @return the index where value is located in the array
     */

    public int binarySearch(ArrayList<MutualFund> al, String ticker) {
        int mid, high = al.size() - 1, low = 0;

        while (low <= high)
        {
            mid = (low + high)/2;
            if (al.get(mid).getTicker().equals(ticker))
                return mid;
            else if (ticker.compareTo(al.get(mid).getTicker()) < 0)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }

    /**
     * Prints each account followed a new line character
     * @param mfas the list of accounts
     * @param account the customer account number
     */
    public void printAccounts(ArrayList<MutualFundAccount> mfas, String account) {

    }

    /**
     * Prompts user for first and last names, and starting cash
     * Then creates and returns a new Customer, with no mfas
     * @param email the customer email address
     * @param password the customer password
     * @param input a Scanner
     * @return the new Customer account
     */
    public Customer createAccount(String email, String password, Scanner input)
    {
        String first, last, accountNum, cashStr;
        double cash;
        Customer customer;

        System.out.print("Enter your first name: ");
        first = input.nextLine();
        System.out.print("Enter your last name: ");
        last = input.nextLine();
        System.out.print("Enter your starting cash deposit: ");
        cashStr = input.nextLine();
        cash = Double.parseDouble(cashStr);
        accountNum = Integer.toString(MutualFundAccount.getAccountSeed());

        customer = new Customer(first, last, email, password, cash, null, accountNum);
        return customer;
    }
}

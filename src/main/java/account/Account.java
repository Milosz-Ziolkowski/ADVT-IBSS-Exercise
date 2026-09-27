package account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String number;
    private final String name;

    // Running balance for the account
    private BigDecimal balance = BigDecimal.ZERO;

    // Transaction references used to build the statement output
    private final List<String> transactions = new ArrayList<>();

    public Account(String number, String name) {
        this.number = number;
        this.name = name;
        this.balance = BigDecimal.ZERO;
    }

    // Add money and record the transaction reference
    public void deposit(String reference, BigDecimal amount) {
        balance = balance.add(amount);
        transactions.add(reference);
    }

    // Remove money and record the transaction reference
    public void withdraw(String reference, BigDecimal amount) {
        balance = balance.subtract(amount);
        transactions.add(reference);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    // Build a simple statement including account details and all transactions
    public String getStatement() {
        return "Name: " + name + "\n" +
                "Account: " + number + "\n" +
                "Balance: " + balance + "\n" +
                "Transactions:\n" + 
                String.join("\n", transactions);
    }

}

package glue;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.assertTrue;

import account.Account;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class AccountSteps {

    Account account = null;
    private String statement;

    @Given("^Account exists for Acc No\\. \"([^\"]*)\" with Name \"([^\"]*)\"$")
    public void accountExistsForAccNoWithName(String number, String name) {
        account = new Account(number, name);
    }

    @Given("deposits are made")
    public void depositsAreMade(DataTable dataTable) {
        for (List<String> row : dataTable.asLists(String.class)) {
            String reference = row.get(0);
            BigDecimal amount = new BigDecimal(row.get(1));
            account.deposit(reference, amount);
        }
    }

    @Given("withdrawls are made") 
    public void withdrawlsAreMade(DataTable dataTable) {
        for (List<String> row : dataTable.asLists(String.class)) {
            String reference = row.get(0);
            BigDecimal amount = new BigDecimal(row.get(1));
            account.withdraw(reference, amount);
        }
    }

    @When("statement is produced")
    public void statementIsProduced() {
        statement = account.getStatement();
    }

    @Then("statement includes {string}")
    public void statementIncludes(String expectedStatement) {
        assertTrue(statement.contains(expectedStatement));
    }
}

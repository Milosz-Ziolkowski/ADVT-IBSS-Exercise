package glue;

import java.time.Duration;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSteps {

    // Open the given URL and dismiss Google’s cookie consent if it appears
    @Given("url {string} is launched")
    public void url(String url) {
        W.get().driver.get(url);
        acceptCookiesIfWarned();
    }

    // Accept Google’s cookie prompt when it is visible
    private static void acceptCookiesIfWarned() {
        try {
            W.get().driver.findElement(By.cssSelector("#L2AGLb")).click();
        } catch (NoSuchElementException ignored) {
        }
    }

    // Normalise apostrophes and any odd character encoding before text assertions
    private String normalizeText(String text) {
        return text.replace("\u2019", "\'").replace("Æ", "'");
    }

    // Check whether the current page contains the expected text
    private void assertPageContains(String expectedText) {
        WebDriverWait wait = new WebDriverWait(
                W.get().driver,
                Duration.ofSeconds(10)
        );

        wait.until(driver -> normalizeText(
                driver.findElement(By.tagName("body")).getText()
        ).contains(normalizeText(expectedText)));

        Assert.assertTrue(
                normalizeText(W.get().driver.findElement(By.tagName("body")).getText())
                        .contains(normalizeText(expectedText))
        );
    }

    // Navigate to the Google About page
    @When("About page is shown")
    public void aboutPageIsShown() {
        W.get().driver.findElement(
                By.cssSelector("a[href='https://about.google/?fg=1&utm_source=google-GB&utm_medium=referral&utm_campaign=hp-header']")
        ).click();

        new WebDriverWait(W.get().driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("https://about.google/?fg=1&utm_source=google-GB&utm_medium=referral&utm_campaign=hp-header"));
    }

    // Assert the current page includes the expected text
    @Then("page displays {string}")
    public void pageDisplays(String expectedText) {
        assertPageContains(expectedText);
    }

    // Search the Google homepage for the supplied term and wait for results area to load
    @When("searching for {string}")
    public void searchingFor(String searchTerm) {
        WebDriverWait wait = new WebDriverWait(W.get().driver, Duration.ofSeconds(10));

        var searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));

        searchBox.sendKeys(searchTerm);
        searchBox.submit();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));
    }

    // Check that a result page contains the supplied text
    @Then("results contain {string}")
    public void pageDisplaysNews(String expectedText) {
        assertPageContains(expectedText);
    }

    // Confirm the results summary is available on the page
    @Then("result stats are displayed")
    public void resultStatsAreDisplayed() {
        new WebDriverWait(W.get().driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));
    }

    // Parse the result count or search duration from the Google summary text
    @Then("number of {string} is more than {int}")
    public void numberOfIsmoreThan(String unit, int number) {
        WebDriverWait wait = new WebDriverWait(W.get().driver, Duration.ofSeconds(10));

        String stats = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result-stats"))).getText();

        if (unit.equals("results")) {
            String resultCount = stats.replaceAll(".*About ([0-9,]+) results.*", "$1")
                    .replace(",", "");

            Assert.assertTrue(Integer.parseInt(resultCount) > number);

        } else if (unit.equals("seconds")) {
            String seconds = stats.replaceAll(".*in ([0-9.]+) seconds.*", "$1");

            Assert.assertTrue(Double.parseDouble(seconds) > number);
        } else {
            Assert.fail("Unknown unit: " + unit);
        }
    }

}

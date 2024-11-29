package se.yrgo.integrations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import se.yrgo.integrations.pageobject.SearchPage;
import se.yrgo.integrations.pageobject.StartPage;
import se.yrgo.integrations.utils.Utils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static se.yrgo.integrations.GeneralStepDefinitions.driver;

public class SearchEmptyFormStepDefinition {

    @When("they click the search button without entering any data")
    public void they_click_the_button_without_entering_any_data() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        StartPage startPage = Utils.openStartPage(driver);
        SearchPage searchPage = startPage.navigateToSearchPage();
        searchPage.clickSearchButton();
    }

    @Then("they should see a message saying {string}")
    public void they_should_see_a_message_saying(String expectedMessage) {
        SearchPage searchPage = new SearchPage(driver);
        String actualMessage = searchPage.getErrorMessage();
        assertEquals(expectedMessage, actualMessage, "The error messages are not the same.");
    }
}

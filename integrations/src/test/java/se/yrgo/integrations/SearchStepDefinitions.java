package se.yrgo.integrations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import se.yrgo.integrations.pageobject.SearchPage;
import se.yrgo.integrations.pageobject.StartPage;
import se.yrgo.integrations.utils.Utils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static se.yrgo.integrations.GeneralStepDefinitions.driver;

public class SearchStepDefinitions {


    @When("the user navigates to the book search.")
    public void the_user_navigates_to_the_book_search() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        StartPage startPage = Utils.openStartPage(driver);
        SearchPage searchPage = startPage.navigateToSearchPage();
        assertTrue(searchPage.isSearchPageLoaded(), "User could not navigate to the search page");
    }

    @Then("they can see the search form.")
    public void they_can_see_the_search_form() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        StartPage startPage = Utils.openStartPage(driver);
        SearchPage searchPage = startPage.navigateToSearchPage();
        assertTrue(searchPage.isFormVisible(), "User could not see the search form");
    }
}



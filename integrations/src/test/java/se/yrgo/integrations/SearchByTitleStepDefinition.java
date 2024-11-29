package se.yrgo.integrations;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import se.yrgo.integrations.pageobject.SearchPage;
import se.yrgo.integrations.pageobject.StartPage;
import se.yrgo.integrations.utils.Utils;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static se.yrgo.integrations.GeneralStepDefinitions.driver;

public class SearchByTitleStepDefinition {


    @When("they enter {string} in the title field")
    public void they_enter_in_the_field(String title) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        StartPage startPage = Utils.openStartPage(driver);
        SearchPage searchPage = startPage.navigateToSearchPage();
        searchPage.enterTitle(title);
    }

    @When("they click the SEARCH button")
    public void they_click_the_button() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        StartPage startPage = Utils.openStartPage(driver);
        SearchPage searchPage = startPage.navigateToSearchPage();
        searchPage.clickSearchButton();
    }

    @Then("they should see a list of books with titles matching {string}")
    public void they_should_see_a_list_of_books_with_titles_matching(String string) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        StartPage startPage = Utils.openStartPage(driver);
        SearchPage searchPage = startPage.navigateToSearchPage();
        List<Book> books = searchPage.getBooks();
        for (Book book : books){
            assertEquals(string, book.getTitle(), "Titles are not the same");
        }

    }

}

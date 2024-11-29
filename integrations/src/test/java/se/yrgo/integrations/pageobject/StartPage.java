package se.yrgo.integrations.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import se.yrgo.integrations.utils.CustomConditions;
import se.yrgo.integrations.utils.Utils;

import java.time.Duration;

public class StartPage {
    private WebDriver driver;

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    public SearchPage navigateToSearchPage() {
        final var wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        final var edLink = Utils.find(driver, By.xpath("//a[contains(text(), 'Find a book')]"));

        wait.until(CustomConditions.elementHasBeenClicked(edLink));

        return new SearchPage(driver);
    }

}

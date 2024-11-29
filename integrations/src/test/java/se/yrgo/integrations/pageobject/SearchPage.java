package se.yrgo.integrations.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import se.yrgo.integrations.Book;
import se.yrgo.integrations.utils.CustomConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchPage {
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchPageLoaded() {
        try {
            return driver.getCurrentUrl().contains("search");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isFormVisible() {
        try {
            return driver.findElement(By.cssSelector("form.max-w-md")).isDisplayed();

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void enterTitle(String title) {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        final By textInput = By.cssSelector("form input[placeholder='Title']");
        WebElement textElem = wait.until(ExpectedConditions.presenceOfElementLocated(textInput));
        textElem.sendKeys(title);
    }

    public void clickSearchButton() {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        final By button = By.cssSelector("form input[value=\"Search\"]");
        WebElement buttonElem =
                wait.until(ExpectedConditions.presenceOfElementLocated(button));
        wait.until(CustomConditions.elementHasBeenClicked(buttonElem));
    }


    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();

        List<WebElement> bookRows = driver.findElements(By.cssSelector("tbody tr"));

        for (WebElement bookRow : bookRows) {
            String title = bookRow.findElement(By.cssSelector("td:nth-child(1)")).getText();
            books.add(new Book(title));
        }

        return books;
    }

//    public String getErrorMessage() {
//        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        By errorMessageLocator = By.cssSelector(".errors > div");
//        WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(errorMessageLocator));
//        return errorElement.getText();
//    }
public String getErrorMessage() {
    try {
        WebElement errorMessage = driver.findElement(By.cssSelector(".errors > div"));
        return errorMessage.getText();
    } catch (NoSuchElementException e) {
        return null;
    }
}


}

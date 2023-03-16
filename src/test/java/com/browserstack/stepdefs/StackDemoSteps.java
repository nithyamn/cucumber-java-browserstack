package com.browserstack.stepdefs;

import com.browserstack.pageobjects.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.percy.selenium.Percy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.Assert;

public class StackDemoSteps {
    private WebDriver driver;
    private HomePage homePage;
    private Percy percy;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        driver = new RemoteWebDriver(
                new URL("https://hub.browserstack.com/wd/hub"), capabilities);
//        percy = new Percy(driver);
        homePage = new HomePage(driver, percy);
    }

    @Given("^I am on the website '(.+)'$")
    public void I_am_on_the_website(String url) throws Throwable {
        driver.get(url);
//        percy.snapshot("Home Page");
        Thread.sleep(2000);
    }

    @When("^I select a product and click on 'Add to cart' button")
    public void I_select_a_product_and_add_to_cart() throws Throwable {
        homePage.selectFirstProductName();
        homePage.clickAddToCartButton();
        Thread.sleep(2000);
    }

    @Then("the product should be added to cart")
    public void product_should_be_added_to_cart() {
        homePage.waitForCartToOpen();
//        percy.snapshot("Final Page");
        Assert.assertEquals(homePage.getSelectedProductName(), homePage.getProductCartText());
    }

    @Then("the page should contain '(.+)'$")
    public void page_should_contain(String expectedTitle) {
        Assert.assertTrue(driver.getPageSource().contains(expectedTitle));
    }

    @After
    public void teardown(Scenario scenario) throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
    @When("I click on Offers link")
    public void iClickOnOffersLink() {
        driver.findElement(By.id("offers")).click();
    }

    @Then("the offers should be displayed")
    public void theOffersShouldBeDisplayed() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.cssSelector("#username input")).sendKeys("demouser", Keys.ENTER);
        driver.findElement(By.cssSelector("#password input")).sendKeys("testingisfun99", Keys.ENTER);
        driver.findElement(By.id("login-btn")).click();
    }


    @When("Search for {string}")
    public void searchForBrowserStack(String searchTerm) {
        driver.findElement(By.cssSelector("input[name='q']")).sendKeys(searchTerm, Keys.ENTER);
    }

    @Then("display search results")
    public void displaySearchResults() {
        System.out.printf(driver.getTitle());
    }
}

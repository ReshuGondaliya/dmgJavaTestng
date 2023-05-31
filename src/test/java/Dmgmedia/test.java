package Dmgmedia;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test {

	public static void main(String[] args) throws InterruptedException {

		// Set the path to the ChromeDriver executable
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// Maximize the browser window
		driver.manage().window().maximize();

		// Go to site
		driver.get("https://www.mailtravel.co.uk/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Confirm the page title
		String expectedTitle = "Home Page | Mail Travel";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle);

		Thread.sleep(3000);
		// Accept all cookies
		By locator = By.id("onetrust-accept-btn-handler");
		WebElement cookies = wait.until(ExpectedConditions.visibilityOfElementLocated((locator)));
		cookies.click();

		// Search for india in searchbar
		WebElement searchBar = driver.findElement(By.xpath("//input[@id='searchtext_freetext_search_form']"));
		// searchBar.sendKeys("India");

		String contry = "India";
		WebElement resultsSection = driver.findElement(By.className("nbf_tpl_quicksearch_searchtext"));
		resultsSection.sendKeys(contry);

		// Thread.sleep(3000);
		WebElement search = wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.id("as_searchtext_freetext_search_form"))));
		WebElement sResult = driver.findElement(By.id("as_ul"));
		WebElement firstLiElement = sResult.findElement(By.tagName("li"));

		// Click on the first li element
		firstLiElement.click();

		// Step 3
		driver.findElement(By.cssSelector("button[name='enterbookingflow']")).click();

		// step 4
		// Find the element for Date and Price to scroll
		WebElement element = driver.findElement(By.id("dates-and-prices"));

		// Scroll until the element is in the center of the screen
		scrollElementToCenter(driver, element);

		// Select Date
		driver.findElement(By.cssSelector(".nbf_tpl_pms_calendar_box.nbf_tpl_pms_calendar_day_limited")).click();

		// Specify the class name to look for
		String className = "fielderror";

		// Find elements with the specified class name
		int elementCount = driver.findElements(By.className(className)).size();

		// Assert if any element with the class name is present
		if (elementCount > 0) {
			System.out.println("Error message is present.");
		} else {
			System.out.println("Error message is not present.");
		}

		String price = driver.findElement(By.cssSelector("div[class='nbf_tpl_pms_calendar_price'] span"))
				.getAttribute("data-amt");
		System.out.println(price);

		// Convert the string to an integer
		double doublePrice = Double.parseDouble(price);

		// Multiply the integer by 2
		double multipliedValue = doublePrice * 2;

		System.out.println(multipliedValue);

		// Find the dropdown element
		WebElement dropdown = driver.findElement(By.id("numAdults-f0050aa159413059b0d39248658bdb50"));

		// Create a Select object
		Select select = new Select(dropdown);

		// Select the option with value equals to "2"
		String expectedPassenger = "2";
		select.selectByValue(expectedPassenger);

		// Make note of Departure Airport
		String departureAirport = driver.findElement(By.cssSelector("div[class='nbf_tpl_pms_departure_select'] span"))
				.getText();
		System.out.println(departureAirport);

		// Validate the Price for 2 Adults
		String actualPrice = driver.findElement(By.cssSelector("span[id='tour-price'] span")).getAttribute("data-amt");
		double actualValue = Double.parseDouble(actualPrice);
		System.out.println(actualValue);

		// Perform the assertion for price
		Assert.assertEquals(multipliedValue, actualValue);

		driver.findElement(By.id("book-button")).click();

		// Step 5
//		Thread.sleep(3000);
		WebElement tog = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("content"))));
		WebElement paxDepDateForm = wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#paxDepDateForm-container"))));
		paxDepDateForm.click();

		// Verify Details
		String actualPassenger = driver.findElement(By.className("nbf_tpl_pms_bf_passenger_number")).getText();
		Assert.assertEquals(expectedPassenger, actualPassenger);

		String actualdepartureAirport = driver.findElement(By.xpath("//span[@dir='ltr']")).getText();
		System.out.println(actualdepartureAirport);
//        Assert.assertEquals(departureAirport, actualdepartureAirport);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(departureAirport, actualdepartureAirport, "Airport name is failed");

		// In Accommodation section select the Number required as 1 for 2 people
		WebElement acc = driver.findElement(By.id("accommodationSelection"));
		// Scroll until the element is in the center of the screen
		scrollElementToCenter(driver, acc);

		// Find the dropdown element
		WebElement accommodationDropdown = driver.findElement(By.id("room-1859919-numselect"));

		// Create a Select object
		Select selectAccomodation = new Select(accommodationDropdown);

		// Select the option with value equals to "1"
		String num = "1";
		selectAccomodation.selectByValue(num);

		// Click on the ‘SELECT YOUR ROOMS AND CONTINUE’
		driver.findElement(By.cssSelector("div[id='accomForm-select'] button")).click();

		// Step 6: Fill the ‘Passenger details’ and select ‘where did you hear about
		// us?’ As ‘Email’ and click ‘CONTINUE button.
		driver.findElement(By.id("paxSelection")).click();
		// Form Data
		String a1Title = "Mr";
		String a1Fname = "firstname";
		String a1Lname = "lastname";
		String a1Date = "10";
		String a1Month = "10";
		String a1Year = "1989";

		String a2Title = "Mrs";
		String a2Fname = "firsts";
		String a2Lname = "lasts";
		String a2Date = "12";
		String a2Month = "12";
		String a2Year = "1989";

		String leadContact = "07474747474";
		String email = "testing@gmail.com";
		String addressL1 = "addr1";
		String addressL2 = "addr2";
		String city = "city";
		String postcode = "postcode";
		String country = "GB";
		String hearAboutUs = "2";

		// Find the dropdown element
		Thread.sleep(5000);
		WebElement formDropdown = driver.findElement(By.id("pax-a-title-1"));
		selectDropdownOptionByValue(formDropdown, a1Title);

		WebElement formA1Fname = driver.findElement(By.id("pax-a-first-1"));
		sendKeysToTextField(formA1Fname, a1Fname);

		WebElement formA1Lname = driver.findElement(By.id("pax-a-last-1"));
		sendKeysToTextField(formA1Lname, a1Lname);

		WebElement formDateDropdowna1 = driver.findElement(By.id("pax-a-dobd-1"));
		selectDropdownOptionByValue(formDateDropdowna1, a1Date);

		WebElement formMonthDropdowna1 = driver.findElement(By.id("pax-a-dobm-1"));
		selectDropdownOptionByValue(formMonthDropdowna1, a1Month);

		WebElement formYearDropdowna1 = driver.findElement(By.id("pax-a-doby-1"));
		selectDropdownOptionByValue(formYearDropdowna1, a1Year);

		WebElement formTitleDropdowna2 = driver.findElement(By.id("pax-a-title-2"));
		selectDropdownOptionByValue(formTitleDropdowna2, a2Title);

		WebElement formA2Fname = driver.findElement(By.id("pax-a-first-2"));
		sendKeysToTextField(formA2Fname, a2Fname);

		WebElement formA2Lname = driver.findElement(By.id("pax-a-last-2"));
		sendKeysToTextField(formA2Lname, a2Lname);

		WebElement formDateDropdowna2 = driver.findElement(By.id("pax-a-dobd-2"));
		selectDropdownOptionByValue(formDateDropdowna2, a2Date);

		WebElement formMonthDropdowna2 = driver.findElement(By.id("pax-a-dobm-2"));
		selectDropdownOptionByValue(formMonthDropdowna2, a2Month);

		WebElement formYearDropdowna2 = driver.findElement(By.id("pax-a-doby-2"));
		selectDropdownOptionByValue(formYearDropdowna2, a2Year);

		WebElement formContactname = driver.findElement(By.id("contact-name"));
		sendKeysToTextField(formContactname, a1Fname + " " + a1Lname);

		WebElement formContact = driver.findElement(By.id("contact-mobile"));
		sendKeysToTextField(formContact, leadContact);

		WebElement formEmail = driver.findElement(By.id("contact-email"));
		sendKeysToTextField(formEmail, email);

		WebElement formAdd1 = driver.findElement(By.id("contact-address1"));
		sendKeysToTextField(formAdd1, addressL1);

		WebElement formAdd2 = driver.findElement(By.id("contact-address2"));
		sendKeysToTextField(formAdd2, addressL2);

		WebElement formCity = driver.findElement(By.id("contact-city"));
		sendKeysToTextField(formCity, city);

		WebElement formPostCode = driver.findElement(By.id("contact-postcode"));
		sendKeysToTextField(formPostCode, postcode);

		WebElement formCountry = driver.findElement(By.id("contact-country"));
		selectDropdownOptionByValue(formCountry, country);

		WebElement formHearAbout = driver.findElement(By.id("contact-hearabout"));
		selectDropdownOptionByValue(formHearAbout, hearAboutUs);

		driver.findElement(By.cssSelector("div[id='paxform-select'] button")).click();

		// Verify the Confirm Details + Book page and verify the Book Now button is
		// enabled
		Thread.sleep(10000);
		String expectedconfirmMsg = "Confirm Details + Book";
		String actualconfirmMsg = driver.findElement(By.className("nbf_fancyimg_payment_pageheader")).getText();
		Assert.assertEquals(expectedconfirmMsg, actualconfirmMsg);

		System.out.println(driver.findElement(By.id("nbf_booknow_button")).isEnabled());

		driver.quit();

	}

	private static void scrollElementToCenter(WebDriver driver, WebElement element) {
		// Execute JavaScript to scroll the element to the center of the screen
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	}

	private static void selectDropdownOptionByValue(WebElement dropdownElement, String optionValue) {
		// Create a Select object
		Select select = new Select(dropdownElement);

		// Select the option by value
		select.selectByValue(optionValue);
	}

	private static void sendKeysToTextField(WebElement textFieldElement, String keysToSend) {
		// Send keys to the text field
		textFieldElement.sendKeys(keysToSend);
	}

}

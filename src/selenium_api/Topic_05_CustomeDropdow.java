package selenium_api;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_CustomeDropdow {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

//		 driver = new FirefoxDriver();
//		 //driver.get("http://demo.guru99.com/v4/");
//		 driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		 driver.manage().window().maximize();
	}

	@Test
	public void TC_01() throws InterruptedException {
		// driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();

		// Jquery
		// customeDropdowlist("//span[@id='speed-button']",
		// "//ul[@id='speed-menu']//li[@class='ui-menu-item']/div", "Fast");
		// Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text'
		// and text()='Fast']")).isDisplayed());
		//
		// customeDropdowlist("//span[@id='number-button']",
		// "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
		// Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text'
		// and text()='19']")).isDisplayed());

		// Agular
		// driver.get("https://material.angular.io/components/select/examples ");
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		//
		// customeDropdowlist("//mat-select[@placeholder='Favorite food']",
		// "//mat-option//span[@class='mat-option-text']", "Tacos");
		// Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Tacos']")).isDisplayed());
		//
		// customeDropdowlist("//mat-select[@placeholder='State']",
		// "//mat-option//span[@class='mat-option-text']", "Michigan");
		// Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Michigan']")).isDisplayed());

		// Jquery 1
//		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//
//		customeDropdowlist("//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']//li", "Grey");
//		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[text()='Grey']")).isDisplayed());
//
//		customeDropdowlist("//span[@aria-owns='size_listbox']", "//ul[@id='size_listbox']/li", "XL - 7 5/8\"");
//		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='size_listbox']//span[@class='k-input' and text()='XL - 7 5/8\"']")).isDisplayed());

		// Vue
//		driver.get("https://mikerodham.github.io/vue-dropdowns/");
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		
//		customeDropdowlist("//div[@class='btn-group']", "//ul[@class='dropdown-menu']//li", "Third Option");
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='btn-group']//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());
		
		// Jquery2
//		driver.get("http://indrimuska.github.io/jquery-editable-select/");
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		
//		customeDropdowlist("//div[@id='default-place']", "//ul[@class='es-list']//li", "Mini");
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='text' and contains(text(),'Christian')]")).isDisplayed());
		
		// Advance2
		driver.get("https://semantic-ui.com/modules/dropdown.html ");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		

//		customeDropdowlist("//div[@class='ui fluid selection dropdown']", "//div[@class='menu transition visible']//div[@class='item']", "Christian");
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui fluid selection dropdown']//div[@class='text' and contains(text(),'Christian')]")).isDisplayed());
		
//		customeDropdowlist("//div[@class='ui fluid search dropdown selection multiple']", "//div[@class='menu transition visible']//div[@class='item']", "Texas");
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui fluid search dropdown selection multiple']//a[@class='ui label transition visible' and contains(text(),'Texas')]")).isDisplayed());
		driver.findElement(By.xpath("//div[@class='ui fluid search dropdown selection multiple']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='ui fluid search dropdown selection multiple active visible']//input[@class='search']")).sendKeys("Texas");
		Thread.sleep(3000);
		
	}

	public void customeDropdowlist(String dropdow, String listitem, String valueitem) throws InterruptedException {
		// Click vao dropdow
		WebElement DropdowElement = driver.findElement(By.xpath(dropdow));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", DropdowElement);
		DropdowElement.click();

		// Dua tat cac vao mot danh sach
		List<WebElement> Allitem = driver.findElements(By.xpath(listitem));

		// Wait danh sach trong mot khoang thoi gian
		wait.until(ExpectedConditions.visibilityOfAllElements(Allitem));

		// Su dung vong lap de duyet
		for (WebElement item : Allitem) {
			if (item.getText().trim().equals(valueitem)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", DropdowElement);

				item.click();
				Thread.sleep(3000);
				break;
			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

package selenium_api;

import java.sql.Time;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_11_Wait {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// driver = new FirefoxDriver();
		// driver.get("http://demo.guru99.com/v4/");
		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
	}

	// @Test
	public void TC_01() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement start = driver.findElement(By.xpath("//div[@id='start']//button"));
		start.click();
		By imageStar = By.xpath("//div[@id='loading']/img");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(imageStar));

		WebElement hello = driver.findElement(By.xpath("//div[@id='finish']/h4"));
		Assert.assertTrue(hello.isDisplayed());

	}

	// @Test
	public void TC_02() {
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		// Step 02 - Wait cho "Date Time Picker" được hiển thị (sử dụng: presence or
		// visibility)
		WebElement showContains = driver.findElement(By.xpath("//div[@class='contentWrapper']"));
		wait.until(ExpectedConditions.visibilityOf(showContains));

		// Step 03 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in
		// ra = "No Selected Dates to display."
		WebElement displayStart = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(displayStart.getText(), "No Selected Dates to display.");

		// Step 04 - Chọn ngày hiện tại (VD: 23/09/2017) (hoặc 1 ngày bất kì tương ứng
		// trong tháng/ năm hiện tại)
		WebElement date = driver.findElement(By.xpath("//a[text()='16']"));
		date.click();

		// Step 05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng:
		// invisibility)
		By ajaxDisplay = By.xpath("//div[@class='raDiv']");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxDisplay));

		// Step 06 - Wait cho selected date = 23 được visible ((sử dụng: visibility)
		By selectDate = By.xpath("//td[@class='rcSelected']//a[text()='16']");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selectDate));

		// Step 07 - Verify ngày đã chọn bằng = Saturday, September 23, 2017
		WebElement displayEnd = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(displayEnd.getText(), "Thursday, August 16, 2018");

	}

	// @Test
	public void TC_03_Fluent() {
		driver.get("https://stuntcoders.com/snippets/javascript-countdown/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 02 - Wait cho đến khi countdown time được visible (visibility)
		WebElement count_time = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		wait.until(ExpectedConditions.visibilityOf(count_time));

		// Step 03 - Sử dụng Fluent wait để:
		// Mỗi 1s kiểm tra countdount= 00 được available trên page (giây đếm ngược về
		// 00)
		// Tức là trong vòng 15s, cứ mỗi 1 giây verify xem nó đã đếm ngược về giây 00
		// hay chưa

		// Khoi tao Fluentwait
		new FluentWait<WebElement>(count_time)
				// Tong thoi gian
				.withTimeout(15, TimeUnit.SECONDS)
				// tan so thoi gian
				.pollingEvery(1, TimeUnit.SECONDS)
				// gap exception thi bo qua
				.ignoring(NoSuchElementException.class)
				// Dieu kien
				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						boolean flag = element.getText().endsWith("00");
						System.out.println("Time = " + element.getText());
						return flag;
					}
				});

	}

	@Test
	public void TC_04_Fluent_2() {

		driver.get("http://toolsqa.wpengine.com/automation-practice-switch-windows/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 02 - Wait cho đến khi countdown time được visible (visibility)
		WebElement count_clock = driver.findElement(By.xpath("//span[@id='clock']"));
		wait.until(ExpectedConditions.visibilityOf(count_clock));

		// Step 03 - Sử dụng Fluent wait để:- Trong vòng 45s: 		
		// Mỗi 5s kiểm tra button Change Color đầu tiên được chuyển thành màu đỏ hay chua		
		// Mỗi 10s verify text được hiển thị = Buzz Buzz

		// Khoi tao Fluentwait
		new FluentWait<WebElement>(count_clock)
				// Tong thoi gian
				.withTimeout(45, TimeUnit.SECONDS)
				// tan so thoi gian
				.pollingEvery(5, TimeUnit.SECONDS)
				// gap exception thi bo qua
				.ignoring(NoSuchElementException.class)
				// Dieu kien
				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						WebElement color = driver.findElement(By.xpath("//button[@id='colorVar']"));
						String getAttributeColor = color.getAttribute("style");
						boolean colorred = getAttributeColor.contains("red");
						
						WebElement elementBuzz = driver.findElement(By.xpath("//span[@id='clock']"));
						String getTextBuzzz = elementBuzz.getText();
						boolean buzz = getTextBuzzz.contains("Buzz");
						
						//boolean flag = element.getText().endsWith("00");
						System.out.println("color = " + colorred);
						System.out.println("buzz = " + buzz);
						return colorred && buzz;
										
					}
				});
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

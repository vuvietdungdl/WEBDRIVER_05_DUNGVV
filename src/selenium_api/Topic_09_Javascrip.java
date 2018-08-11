package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Javascrip {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		// driver = new ChromeDriver();
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

		// driver = new FirefoxDriver();
		// driver.get("http://demo.guru99.com/v4/");
		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		
		//IE
		System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01() {
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Step 02 - Sử dụng JE để get domain của page			
		String domain = (String) executeForBrowserElement("return document.domain");
		Assert.assertEquals("live.guru99.com", domain);

		// Step 03 - Sử dụng JE để get URL của page		
		String URL = (String) executeForBrowserElement("return document.URL");
		Assert.assertEquals("http://live.guru99.com/", URL);

		// Step 04 - Open MOBILE page (Sử dụng JE)
		WebElement clickMobile = driver.findElement(By.xpath("//a[text()='Mobile']"));
		executeForWebElement(clickMobile);

		// Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button
		// bằng JE)
		WebElement addButon = driver.findElement(By.xpath("//h2[@class='product-name']//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button[@class='button btn-cart']"));
		executeForWebElement(addButon);

		// Verify message được hiển thị: Samsung Galaxy was added to your shopping cart.
		// (Sử dụng JE - Get innertext of the entire webpage)
		
		String sText = (String) executeForBrowserElement("return document.documentElement.innerText;");
		Assert.assertTrue(sText.contains("Samsung Galaxy was added to your shopping cart."));

		// Step 07 - Open PRIVACY POLICY page (Sử dụng JE)
		WebElement clickPolicy = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		executeForWebElement(clickPolicy);
		// Verify title của page = Privacy Policy (Sử dụng JE)		
		String titlePolicy = (String) executeForBrowserElement("return document.title;");
		
		Assert.assertEquals("Privacy Policy", titlePolicy);

		// Step 08 - Srcoll xuống cuối page
		scrollToBottomPage();

		// Step 09 - Verify dữ liệu có hiển thị với chỉ 1 xpath:
		WebElement getTable = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
		Assert.assertTrue(getTable.isDisplayed());

		// Step 10 - Navigate tới domain: http://demo.guru99.com/v4/ (Sử dụng JE)
		String domainGuru = "http://demo.guru99.com/v4/";
		String newDomain = "window.location = '" + domainGuru + "'";	
		executeForBrowserElement(newDomain);
		
		// Verify domain sau khi navigate = http://demo.guru99.com/v4/		
		
		String domain2 = (String) executeForBrowserElement("return document.domain;");
		Assert.assertEquals("demo.guru99.com", domain2);
	}

	@Test
	public void TC_02() {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//Step 02 - Remove thuộc tính disabled của field Last name --> Switch qua iframe nếu có
		WebElement iframeName = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(iframeName);
		WebElement lastname = driver.findElement(By.xpath("//input[@name='lname']"));
		removeAttributeInDOM(lastname,"disabled");
			
		//Step 03 - Sendkey vào field Last name
		lastname.sendKeys("Test Automation");
		
		//Step 04 - Click Submit button
		WebElement clickSubmit = driver.findElement(By.xpath("//input[@type='submit']"));
		executeForWebElement(clickSubmit);
		
		//Step 05 - Verify dữ liệu sau khi submit chứa đoạn text đã fill trong field Lastname (Automation Testing)
			// String textBeforSubmit = (String) executeForBrowserElement("return
			// document.documentElement.innerText;");
			// Assert.assertTrue(textBeforSubmit.contains("Test Automation"));
		WebElement resultSubmit = driver.findElement(By.xpath("//div[contains(text(),'Test Automation')]"));
		Assert.assertTrue(resultSubmit.isDisplayed());
	}
	// Su dung cac ham dung chung

	// Highlight an element:
	public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='6px groove red'", element);
	}

	// Execute for Browser (Thao tac voi Browser)
	public Object executeForBrowserElement(String javaSript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	// Execute for WebElement (Thao tac voi Element)
	public Object executeForWebElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	// Remove attribute in DOM (Xoa thuoc tinh trong Dom)
	public Object removeAttributeInDOM(WebElement element, String attribute) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	// Scroll to bottom page (Cuon xuong cuoi trang)
	public Object scrollToBottomPage() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

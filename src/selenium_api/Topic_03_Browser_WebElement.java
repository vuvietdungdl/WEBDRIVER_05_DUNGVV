package selenium_api;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Browser_WebElement {
    WebDriver driver;
    
	@BeforeClass
	public void beforeClass() {
		
		// Chay bang Chrome
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// Chay bang Frifox
//		driver = new FirefoxDriver();		
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		
		//Chay bang IE
//		System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();		
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		
	}

	// @Test
	public void TC_01() throws InterruptedException {
		//login vào trang		
		driver.get("http://daominhdam.890m.com/");		
		
		//kiem tra phan tu email
		WebElement CheckEmail = driver.findElement(By.xpath("//input[@id='mail']"));
		if(CheckEmail.isDisplayed()) {
			System.out.println("Text Box Display");
		}
		else {
			System.out.println("Text Box is Display");
		}
		
		//kiem tra phan tu radio
		WebElement radio = driver.findElement(By.xpath("//input[@id='under_18']"));
		if(radio.isDisplayed()) {
			System.out.println("Radio Button Display");
		}
		else {
			System.out.println("Radio Button Not Display");
		}
		
		//kiem tra phan tu Education
		WebElement CheckEdu = driver.findElement(By.xpath("//textarea[@id='edu']"));
		if(CheckEdu.isDisplayed()) {
			System.out.println("TextArea Box Display");
		}
		else {
			System.out.println("TextArea Box not Display");
		}
		
		//nhap du lieu vao email
		driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation Testing ");
		driver.findElement(By.xpath("//input[@id='under_18']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation Testing ");
	}
	
	//@Test
	public void TC_02() throws InterruptedException {
		driver.get("http://daominhdam.890m.com/");
		
		//check email enable
		WebElement CheckEnableEmail = driver.findElement(By.xpath("//input[@id='mail']"));
		if(CheckEnableEmail.isEnabled()) {
			System.out.println("Text Email Enable");
		}
		else {
			System.out.println("Text Email Not Enable");
		}
		
		//check age enable
		WebElement CheckEnableAge = driver.findElement(By.xpath("//input[@id='under_18']"));
		if(CheckEnableAge.isEnabled()) {
			System.out.println("Text Age Enable");
		}
		else {
			System.out.println("Text Age Not Enable");
		}
		
		//check Edu enable
		WebElement CheckEnableEdu = driver.findElement(By.xpath("//textarea[@id='edu']"));
		if(CheckEnableEdu.isEnabled()) {
			System.out.println("Text Edu Enable");
		}
		else {
			System.out.println("Text Edu Not Enable");
		}
		
		//check Job Role enable
		WebElement CheckEnableJob = driver.findElement(By.xpath("//select[@id='job1']"));
		if(CheckEnableJob.isEnabled()) {
			System.out.println("Text Job Enable");
		}
		else {
			System.out.println("Text Job Not Enable");
		}
		
		//check Job Interests enable
		WebElement CheckEnableInterests = driver.findElement(By.xpath("//input[@id='development']"));
		if(CheckEnableInterests.isEnabled()) {
			System.out.println("Text Interests Enable");
		}
		else {
			System.out.println("Text Interests Not Enable");
		}
		
		//check  Slider enable
		WebElement CheckEnableSlider = driver.findElement(By.xpath("//input[@id='slider-1']"));
		if(CheckEnableSlider.isEnabled()) {
			System.out.println("Text Slider Enable");
		}
		else {
			System.out.println("Text Slider Not Enable");
		}
		
		//check Button enable
		WebElement CheckEnableButton = driver.findElement(By.xpath("//button[@id='button-enabled']"));
		if(CheckEnableButton.isEnabled()) {
			System.out.println("Text Button Enable");
		}
		else {
			System.out.println("Text Button Not Enable");
		}
		
		//check Pass Disble
		WebElement CheckDisblePass = driver.findElement(By.xpath("//input[@id='password']"));
		if(CheckDisblePass.isEnabled()) {
			System.out.println("Text Pass Enable");
		}
		else {
			System.out.println("Text Pass Disble");
		}
		
		//check Pass Disble
		WebElement CheckDisPass = driver.findElement(By.xpath("//input[@id='password']"));
		if(CheckDisPass.isEnabled()) {
			System.out.println("Text Pass Enable");
		}
		else {
			System.out.println("Text Pass Disble");
		}
		
		//check Radio Disble
		WebElement CheckDisbleRadio = driver.findElement(By.xpath("//input[@id=\"radio-disabled\"]"));
		if(CheckDisbleRadio.isEnabled()) {
			System.out.println("Text Radio Enable");
		}
		else {
			System.out.println("Text Radio Disble");
		}
		
		//check Biography Disble
		WebElement CheckDisbleBiography = driver.findElement(By.xpath("//textarea[@id=\"bio\"]"));
		if(CheckDisbleBiography.isEnabled()) {
			System.out.println("Text Biography Enable");
		}
		else {
			System.out.println("Text Biography Disble");
		}
		
		//check Job Role 02 Disble
		WebElement CheckDisbleJobRole02 = driver.findElement(By.xpath("//select[@id=\"job2\"]"));
		if(CheckDisbleJobRole02.isEnabled()) {
			System.out.println("Text Job Role 02 Enable");
		}
		else {
			System.out.println("Text Job Role 02 Disble");
		}
		
		//check Job Role 02 Interests
		WebElement CheckDisbleInterests = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
		if(CheckDisbleInterests.isEnabled()) {
			System.out.println("Text Interests Enable");
		}
		else {
			System.out.println("Text Interests Disble");
		}
		
		//check Job Slider 02 Disble
		WebElement CheckDisbleSlider02 = driver.findElement(By.xpath("//input[@id=\"slider-2\"]"));
		if(CheckDisbleSlider02.isEnabled()) {
			System.out.println("Text Slider 02 Enable");
		}
		else {
			System.out.println("Text Slider 02 Disble");
		}
		
		
		WebElement CheckDisbleButton2 = driver.findElement(By.xpath("//button[@id='button-disabled']"));
		if(CheckDisbleButton2.isEnabled()) {
			System.out.println("Text Button 02 Enable");
		}
		else {
			System.out.println("Text Button 02 Disble");
		}
		
	}

	@Test
	public void TC_03() throws InterruptedException {
		driver.get("http://daominhdam.890m.com/");
		
		WebElement CheckAge = driver.findElement(By.xpath("//input[@id='under_18']"));
		CheckAge.click();
		Thread.sleep(3000);
		
		WebElement Interests  = driver.findElement(By.xpath("//input[@id='development']"));
		Interests.click();
		Thread.sleep(3000);
		
		if(CheckAge.isSelected()) {
			System.out.println("Check Box Is Check");
		}
		else {
			driver.findElement(By.xpath("\"//input[@id='under_18']\"")).click();
			Thread.sleep(3000);
		}
		
		if(Interests.isSelected()) {
			System.out.println("Check Box Is Check");
		}
		else {
			driver.findElement(By.xpath("//input[@id='development']")).click();
			
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

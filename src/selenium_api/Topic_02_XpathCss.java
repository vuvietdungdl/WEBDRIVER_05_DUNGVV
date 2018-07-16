package selenium_api;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_XpathCss {
    WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		//driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}    
    
	@Test
	
	public void TC_01(){
		//kiem tra title
		String CheckTitle = driver.getTitle();
		String expected ="Home page";
		
		//ket qua sau khi kiem tra title
		Assert.assertEquals(CheckTitle,expected);
				
		// toi trang dang nhap
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		
		// toi trang dang ky
		driver.findElement(By.xpath("//span[contains(text(),\"Create an Account\")]")).click();
		
		//back lai trang dang nhap
		driver.navigate().back();
		
		//chuyen tiep toi trang dang ky
		driver.navigate().forward();
		
		// kiem tra url cua trang dang ky
		String URL = driver.getCurrentUrl();
		Assert.assertEquals("http://live.guru99.com/index.php/customer/account/create/", URL);
	}	
	
	@Test
	public void TC_02_Login_Empty() {		
		
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		
		//driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.name("login[username]")).sendKeys("");
		
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailErroMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals("This is a required field.", emailErroMsg);
		
		String passErroMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals("This is a required field.", passErroMsg);
		
	}	

	@Test
	public void TC_03(){		
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[contains(text(),\"My Account\")]")).click();
		
		//nhap gia tri email sai
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		
		//click vao button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailErroMsg1 = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", emailErroMsg1);
				
	}	

	@Test
	public void TC_04(){		
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[contains(text(),\"My Account\")]")).click();
		
		//nhap gia tri email sai
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		
		//nhap pass sai
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		
		//click vao button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//kiem tra ket qua
		String passlErroMsg4 = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", passlErroMsg4);
				
	}	

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

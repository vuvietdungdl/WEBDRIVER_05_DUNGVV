package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Dropdow_Textbox_P1 {
	WebDriver driver;
	String name,dob,address,city,state,pin,mobile,email,pass,customerID=null,newAddres,newCity;
	
	// By Element Varible
	By NameTextbox = By.xpath("//input[@name=\"name\"]");	
	By GenderTextbox = By.xpath("//input[@name=\"gender\"]");
	By DobTextbox = By.xpath("//input[@name=\"dob\"]");
	By AddressTextbox = By.xpath("//textarea[@name=\"addr\"]");
	By CityTextbox = By.xpath("//input[@name=\"city\"]");	
	By StateTextbox = By.xpath("//input[@name='state']");
	By PinTextbox = By.xpath("//input[@name=\"pinno\"]");
	By MobileTextbox = By.xpath("//input[@name=\"telephoneno\"]");
	By EmailTextbox = By.xpath("//input[@name=\"emailid\"]");
	By PassTextbox = By.xpath("//input[@name=\"password\"]");	
	

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		//Data Test
		name = "Ha Xuan Dung";
		dob="1989-02-01";
		address = "66 Đinh Bộ Lĩnh";		
		city="HCM";
		state="Thanh";
		pin="123456";
		mobile="0987564352";
		email="haxuandung"+RandomNumber()+"@gmail.com";
		pass="crm123";
		newAddres="55 Bach Dang";
		newCity ="Ha Noi";
	
	}

	//@Test
	public void TC_01() throws InterruptedException {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		Select JobRole=new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		//kiem tra khong ho tro thuoc tinh multi
		Assert.assertFalse(JobRole.isMultiple());
		
		JobRole.selectByVisibleText("Automation Tester");
		Assert.assertEquals("Automation Tester", JobRole.getFirstSelectedOption().getText());
		Thread.sleep(3000);
		
		JobRole.selectByValue("manual");
		Assert.assertEquals("Manual Tester", JobRole.getFirstSelectedOption().getText());
		Thread.sleep(3000);
		
		JobRole.selectByIndex(3);
		Assert.assertEquals("Mobile Tester", JobRole.getFirstSelectedOption().getText());
		Thread.sleep(3000);
	}

	//@Test
	public void TC_02() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_03() throws InterruptedException {
		driver.get("http://demo.guru99.com/v4");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//input[@name=\"uid\"]")).sendKeys("mngr145524");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("Unybeqe");
		driver.findElement(By.xpath("//input[@name=\"btnLogin\"]")).click();		
		
		//Veryfy homepage
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()=\"New Customer\"]")).click();
		
		//Input Data vao
		driver.findElement(NameTextbox).sendKeys(name);
		driver.findElement(DobTextbox).sendKeys(dob);
		driver.findElement(AddressTextbox).sendKeys(address);
		driver.findElement(CityTextbox).sendKeys(city);
		driver.findElement(StateTextbox).sendKeys(state);
		driver.findElement(PinTextbox).sendKeys(pin);
		driver.findElement(MobileTextbox).sendKeys(mobile);
		driver.findElement(EmailTextbox).sendKeys(email);		
		driver.findElement(PassTextbox).sendKeys(pass);
		
		driver.findElement(By.xpath("//input[@name=\"sub\"]")).click();
		
		//get customerID
		customerID=driver.findElement(By.xpath("//td[text()=\"Customer ID\"]/following-sibling::td")).getText();
		
		//Verify du lieu tai man hinh detail
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Customer Name\"]/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Birthdate\"]/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Address\"]/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"City\"]/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"State\"]/following-sibling::td")).getText(), state );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Pin\"]/following-sibling::td")).getText(), pin );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Mobile No.\"]/following-sibling::td")).getText(), mobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Email\"]/following-sibling::td")).getText(), email);
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[text()=\"Edit Customer\"]")).click();
		
		//truyen ma customer
		driver.findElement(By.xpath("//input[@name=\"cusid\"]")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name=\"AccSubmit\"]")).click();
		
		//Check 3 Field Customename, Gender,Date co disble khong
		System.out.println("Attribute = " + driver.findElement(NameTextbox).getAttribute("disabled"));
		
		Assert.assertFalse(driver.findElement(NameTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(GenderTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(DobTextbox).isEnabled());
		
		//Verify du lieu 
		Assert.assertEquals(driver.findElement(NameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(AddressTextbox).getText(), address);
		
		//Input new data Address and city
		driver.findElement(AddressTextbox).clear();
		driver.findElement(AddressTextbox).sendKeys(newAddres);
		driver.findElement(CityTextbox).clear();
		driver.findElement(CityTextbox).sendKeys(newCity);
		
		driver.findElement(By.xpath("//input[@name=\"sub\"]")).click();
		
		//Veryfy edit
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Address\"]/following-sibling::td")).getText(), newAddres);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"City\"]/following-sibling::td")).getText(), newCity);
	}
	
	// ham random email
	public int RandomNumber() {
		Random randemail = new Random();
		int  n = randemail.nextInt(999999) + 1;
		return n;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

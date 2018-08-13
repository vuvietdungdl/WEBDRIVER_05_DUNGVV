package selenium_api;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Uploadfile {
	WebDriver driver;
	String userDirectory = System.getProperty("user.dir");
	String pathName = userDirectory + "\\image\\nu.jpg";
	String imageName = "nu.jpg";
	String nameforder = "dung vu " + randomNumber();
	String emailAddress = "testauto" + randomNumber() + "@gmail.com";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// driver = new FirefoxDriver();
		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

		// System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_sendKey() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement addFile = driver.findElement(By.xpath("//input[@name='files[]']"));
		addFile.sendKeys(pathName);

		// WebElement start = driver.findElement(By.xpath("//button[@class='btn
		// btn-primary start']//span"));
		// start.click();
		Thread.sleep(3000);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and contains(text(),'" + imageName + "')]")).isDisplayed());
	}

	// @Test
	public void TC_02_autoIT() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Derectory = " + pathName);

		// WebElement addFile =
		// driver.findElement(By.xpath("//input[@name='files[]']"));
		// String add = "//span[text()='Add files...']";
		// clickElementByJavascrip(add);

		WebElement addFile = driver.findElement(By.cssSelector(".fileinput-button"));
		addFile.click();

		Thread.sleep(3000);
		// Chrome
		// Runtime.getRuntime().exec(new String[] {".\\autoIT\\chrome.exe",pathName});

		// IE
		// Runtime.getRuntime().exec(new String[] {".\\autoIT\\ie.exe",pathName});

		// Firefox
		Runtime.getRuntime().exec(new String[] { ".\\autoIT\\firefox.exe", pathName });
		Thread.sleep(3000);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and contains(text(),'" + imageName + "')]")).isDisplayed());
	}

	// @Test
	public void TC_03_robot() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Specify the file location with extension
		StringSelection select = new StringSelection(pathName);

		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		// Click
		WebElement addFile = driver.findElement(By.cssSelector(".fileinput-button"));
		addFile.click();

		Thread.sleep(3000);

		Robot robot = new Robot();
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and contains(text(),'" + imageName + "')]")).isDisplayed());
	}

	@Test
	public void TC_04() throws Exception {
		driver.get("https://encodable.com/uploaddemo/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement file_upload = driver.findElement(By.xpath("//input[@id='uploadname1']"));
		file_upload.sendKeys(pathName);

		// Step 3
		Select upload_to = new Select(driver.findElement(By.xpath("//select[@name='subdir1']")));
		upload_to.selectByVisibleText("/uploaddemo/files/");

		// Step 4
		WebElement forder_name = driver.findElement(By.xpath("//input[@id='newsubdir1']"));
		forder_name.sendKeys(nameforder);

		// Step 5: Input email and firstname
		WebElement email_address = driver.findElement(By.xpath("//input[@id='formfield-email_address']"));
		email_address.sendKeys(emailAddress);

		WebElement firt_name = driver.findElement(By.xpath("//input[@id='formfield-first_name']"));
		firt_name.sendKeys("Auto");
		
		// Step 06 - Click Begin Upload (Note: Wait for page load successfully)
		WebElement begin_upload = driver.findElement(By.xpath("//input[@id='uploadbutton']"));
		begin_upload.click();			
		
		// Step 07 - Verify information
			// + Email Address: dam@gmail.com/ First Name: DAM DAO
		Assert.assertTrue(driver.findElement(By.xpath("//dd[text()='Email Address: "+emailAddress+"']")).isDisplayed());
			// + File name: UploadFile.jpg
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ imageName +"']")).isDisplayed());
				
		// Step 08 - Click 'View Uploaded Files' link
		WebElement view_upload = driver.findElement(By.xpath("//a[text()='View Uploaded Files']"));
		view_upload.click();
		
		// Step 09 - Click to random folder (Ex: dam1254353)
		WebElement forder_view = driver.findElement(By.xpath("//a[text()='"+nameforder+"']"));
		forder_view.click();
		// Step 09 - Verify file name exist in folder (UploadFile.jpg)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+imageName+"']")).isDisplayed());
		
	}

	// Ham thao tac voi Element cua javascrip
	public Object executeForWebElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	// Ham Click
	public void clickElementByJavascrip(String localtion) {
		WebElement element = driver.findElement(By.xpath(localtion));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random rd = new Random();
		int random = rd.nextInt(999999);
		return random;
	}

}

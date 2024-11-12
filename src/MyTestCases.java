import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class MyTestCases {
	
	
	WebDriver driver = new ChromeDriver();
	String websiteURL = "https://codenboxautomationlab.com/practice/";
	
	Random rand = new Random();
	
	@BeforeTest
	public void mySetup()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // da2eman baktobha

		driver.manage().window().maximize();
		driver.get(websiteURL);
	}
	
	@Test(priority = 1,enabled = false)
	public void CheckHoverButton()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0 , 2000)");
		
		Actions action = new Actions(driver);
		WebElement button = driver.findElement(By.id("mousehover"));
		
		action.moveToElement(button).perform();
		
		driver.findElement(By.linkText("Top")).click();
		
		driver.switchTo().frame("courses-iframe");
	}
	
	@Test(priority = 2,enabled = false)
	public void CheckRadioButton() throws InterruptedException
	{
		WebElement divForRadio = driver.findElement(By.id("radio-btn-example"));
		
		int randomRadio = rand.nextInt(divForRadio.findElements(By.tagName("input")).size()) ;
		WebElement selectedRadio = divForRadio.findElements(By.tagName("input")).get(randomRadio);
		Thread.sleep(2000);
		selectedRadio.click();
		
		boolean actualResult = selectedRadio.isSelected();
		boolean expectedResult = true;
		Assert.assertEquals(actualResult, expectedResult);
	}
	@Test(priority = 3,enabled = false)
	public void DynamicDropDown() throws InterruptedException
	{
		WebElement autoCompleteInput = driver.findElement(By.id("autocomplete"));
		
		String[] randomChar = {"ai", "jo", "ef", "Gh", "ij", "Kl", "Mn", "op", "Qa", "St"};
		int listlength = randomChar.length;
		int randomIndex = rand.nextInt(listlength);
		String text = randomChar[randomIndex];
		autoCompleteInput.sendKeys(text);
		Thread.sleep(1000);
		autoCompleteInput.sendKeys(Keys.chord(Keys.ARROW_DOWN,Keys.ENTER));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String actualResult =(String) js.executeScript("return arguments[0].value" , autoCompleteInput);
		
		System.out.println(actualResult);
		
		boolean actual = actualResult.contains(text);
		boolean expected = true;
		
		assertEquals(actual, expected);
	}
	
	@Test(priority = 4,enabled = false)
	public void StaticDropDown() throws InterruptedException
	{
		WebElement selectButton = driver.findElement(By.id("dropdown-class-example"));
		Select selector = new Select(selectButton);
		int randomIndex = rand.nextInt(1 , 4);
		Thread.sleep(2000);
		selector.selectByIndex(randomIndex);
	}
	
	@Test(priority = 5,enabled = false)
	public void CheckBox() throws InterruptedException
	{
		WebElement CheckBoxDiv = driver.findElement(By.id("checkbox-example"));
		List<WebElement> myCheckBox = CheckBoxDiv.findElements(By.xpath("//input[@type='checkbox']"));
		
		for (int i=0 ; i<= myCheckBox.size() ; i++)
		{
			myCheckBox.get(i).click();
		}
	}
	@Test(priority = 6, enabled = false)
	public void SwitchWiindow() throws InterruptedException
	{
		driver.findElement(By.id("openwindow")).click();
		Thread.sleep(2000);
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles()); /// get all open windows
		
		driver.switchTo().window(windowHandles.get(1));
		System.out.println(driver.getTitle());
		
		driver.findElement(By.xpath("//li[@id='menu-item-9675']//a")).click();
		// back to the first window
		driver.switchTo().window(windowHandles.get(0));
		
		WebElement CheckBoxDiv = driver.findElement(By.id("checkbox-example"));
		List<WebElement> myCheckBox = CheckBoxDiv.findElements(By.xpath("//input[@type='checkbox']"));
		
		for (int i=0 ; i<= myCheckBox.size() ; i++)
		{
			myCheckBox.get(i).click();
		}
		
	}
	
	@Test(priority = 7,enabled = false)
	public void switchTab() throws InterruptedException
	{
		driver.findElement(By.id("opentab")).click();
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles()); /// get all open windows
		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(1000);
		WebElement test =  driver.findElement(By.xpath("//span[@role='text'][normalize-space()='Codenbox AutomationLab']"));
	System.out.println(test.getText());
	}
	@Test(priority = 8,enabled = false)
	public void alert() throws InterruptedException
	{
		driver.findElement(By.id("alertbtn")).click();
		driver.switchTo().alert().accept();
		
		driver.findElement(By.id("name")).sendKeys("lujain");
		
		driver.findElement(By.id("confirmbtn")).click();
		String actual = driver.switchTo().alert().getText();
		String expected = "Hello lujain, Are you sure you want to confirm?";
		
		Assert.assertEquals(actual, expected);
		Thread.sleep(2000);
		driver.switchTo().alert().dismiss();
		
		
	}
	
	@Test(priority = 9,enabled = false)
	public void tableExample() throws InterruptedException
	{
		WebElement table = driver.findElement(By.id("product"));
		System.out.println(table.findElements(By.tagName("td")).size());
		
		List<WebElement> dataList = table.findElements(By.tagName("td"));
 		
		int listSize = table.findElements(By.tagName("td")).size();
		for (int i =1 ; i <=listSize ; i+=3 )
		{
			System.out.println(dataList.get(i).getText());
		}
		
		
	}
	
	@Test(priority = 10,enabled = false)
	public void elementDisplayed() throws InterruptedException
	{
		driver.findElement(By.id("hide-textbox")).click();

		boolean actual = driver.findElement(By.id("displayed-text")).isDisplayed();
		boolean expected = false;
		
		Assert.assertEquals(actual, expected);
		
	}
	
	@Test(priority = 11,enabled = false)
	public void EnabledDisabledExample () throws InterruptedException
	{
		driver.findElement(By.id("disabled-button")).click();
		boolean actual = driver.findElement(By.id("enabled-example-input")).isEnabled();
		boolean expected = false;
		Assert.assertEquals(actual, expected);
		
		driver.findElement(By.id("enabled-button")).click();
		boolean actualEnable = driver.findElement(By.id("enabled-example-input")).isEnabled();
		boolean expectedEnable = true;
		Assert.assertEquals(actualEnable, expectedEnable);
		
	}
	
	@Test(priority = 12,enabled=false)
	public void CalendarExample () throws InterruptedException
	{
		driver.findElement(By.linkText("Booking Calendar")).click();
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles()); /// get all open windows
		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(2000);
		List<WebElement> availabelDate = driver.findElements(By.xpath("//a[@href='javascript:void(0)']"));
		int randomDate = rand.nextInt(1 , availabelDate.size());
		availabelDate.get(randomDate).click();
	
	
	}
	@Test(priority = 13)
	public void IframeExample () throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0 , 2400)");
		
		WebElement iFrame = driver.findElement(By.id("courses-iframe"));
		
		driver.switchTo().frame(iFrame);
		js.executeScript("window.scrollTo(0 , 2400)");
		
	}

}

import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
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
	@Test(priority = 2,enabled = false)
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
	
	@Test(priority = 2)
	public void StaticDropDown() throws InterruptedException
	{
		WebElement selectButton = driver.findElement(By.id("dropdown-class-example"));
		Select selector = new Select(selectButton);
		int randomIndex = rand.nextInt(1 , 4);
		Thread.sleep(2000);
		selector.selectByIndex(randomIndex);
	}
	
	

}

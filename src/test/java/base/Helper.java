package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Helper 
{
	public static WebDriver driver;
	public static Properties prop;
	static 
	{
		try {
				FileInputStream file= new FileInputStream("TestData/TestData.properties"); // Code that must execute
			    prop= new Properties();
				prop.load(file);
	    	}
		catch (FileNotFoundException e) 
			{
				e.printStackTrace();  // exception here
			}
		catch(IOException e)
			{
				e.printStackTrace();
			}
	
	}

	public static void setup() 
	{
		String browserNameString = prop.getProperty("browser");
		if (browserNameString.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		}
		else if (browserNameString.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions(); 
			options.addArguments("--incognito");
			driver= new FirefoxDriver(options);		
		}
		else if (browserNameString.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--incognito");
			driver = new EdgeDriver(options);
		}
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10)); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
	}

	public void getScreenShot() 
	{
		TakesScreenshot ts= (TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		try 
		{
			FileUtils.copyFile(srcFile, new File("Screenshots"+System.currentTimeMillis()+".png"));	
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	 public void SuperClick(WebElement element) 
	    {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", element);
	    }	
	public void clickIamAbv18()
	{
		WebElement acceptCookiesButton= driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']"));
		acceptCookiesButton.click();
		WebElement iam18Button= driver.findElement(By.xpath("(//button[@class='button-root_highPriority-3u6 button-root-16x clickable-root-2gB'])[17]"));
  		SuperClick(iam18Button);
	}
	public void clickIambelow18() 
	{
		WebElement acceptCookiesButton= driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']"));
		acceptCookiesButton.click();
		WebElement iamunder18Button= driver.findElement(By.xpath("(//button[@class='button-root_normalPriority-LB0 button-root-16x clickable-root-2gB'])[6]"));
  		SuperClick(iamunder18Button);
	}
	
	public static void SerarhingItem(String itemname) 
	{
		WebElement SearchInput = driver.findElement(By.xpath("//input[@name='search_query']"));
		SearchInput.sendKeys(itemname);
		SearchEnterAction();
	}
    public  void MyClick(String element)
    {	
		WebElement signinElement = driver.findElement(By.xpath(element));
		signinElement.click();
    }
	public void UserDetails(String username,String pass) 
	{
		WebElement EnterEmail = driver.findElement(By.xpath("//input[@autocomplete='email']"));
			EnterEmail.sendKeys(username); 			 
		WebElement EnterPassword = driver.findElement(By.xpath("//input[@autocomplete='current-password']"));
			EnterPassword.sendKeys(pass); 					
			MyClick("//button[@type='submit']");   			// Clicking on submit Button
	}
	public  static void SearchEnterAction()
	{
		Actions SearchEnterAction =  new Actions(driver);
		SearchEnterAction.sendKeys(Keys.ENTER).build().perform(); 	
	}
	public void waitbyVisibility(String element, int timeout) 
	{
		WebElement WaitEleVisible= new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
			WaitEleVisible.click();
	}
	public void waitbyEleClickable (String element, int timeout) 
	{
		WebElement WaitEleClick= new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
			WaitEleClick.click();
	}
	public void deletingkartItems() 
	{
		driver.findElement(By.xpath("//header[@class='header-rootCheckout-OmQ header-root-2P7']/div/a/span")).click();
		List<WebElement> cancelelements = driver.findElements(By.xpath("//button[@class='product-delete-1dM']"));
			int i = cancelelements.size();
		first:
		while(i>0)
		{		
			WebElement Proceedtocheckout= new WebDriverWait(driver, Duration.ofSeconds(120)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='button-root_highPriority-3u6 button-root-16x clickable-root-2gB'])[8]/span[@class='button-content-3ns']")));
				System.out.println(Proceedtocheckout);
			driver.findElement(By.xpath("//button[@class='product-delete-1dM']")).click();
				System.out.println(i);
			List<WebElement> a = driver.findElements(By.xpath("//button[@class='product-delete-1dM']"));
				i=a.size();
			if(i==1)
			{
				driver.findElement(By.xpath("//button[@class='product-delete-1dM']")).click();
				driver.navigate().refresh();
				break first;
			}
		}
		System.out.println("All items deleted suceessfully ;) ");
	}
}

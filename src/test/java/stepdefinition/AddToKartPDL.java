package stepdefinition;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Helper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddToKartPDL extends Helper
{
	@When("user login application {string} and {string}")
	public void user_login_application_and(String username, String pass)
	{	
		setup();
		clickIamAbv18();
	//clicking on SignIn Button
		MyClick("//button[@aria-label='Toggle My Account Menu']");
	//entering username and pass
		UserDetails(username, pass);
	}

	@And("delete items in cart")
	public void delete_items_in_cart() 
	{	//waiting for counter to visible
		waitbyVisibility("//div[@class='cartTrigger-triggerContainer-1Sn cartTrigger-triggerContainer-F0h']", 120);
		//waiting checkout to be clickable
		waitbyEleClickable("//button[@class='miniCart-checkoutButton-3bi button-root_highPriority-3u6 button-root-16x clickable-root-2gB']", 120);
		//clicking on Arrow
		 MyClick("//header[@class='header-rootCheckout-OmQ header-root-2P7']/div/a/span");
		// deleting Items
		 List<WebElement> cancelelements = driver.findElements(By.xpath("//button[@class='product-delete-1dM']"));
			int i = cancelelements.size();
			first:
			while(i>0)
			{		
				WebElement Proceedtocheckout= new WebDriverWait(driver, Duration.ofSeconds(120)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='button-root_highPriority-3u6 button-root-16x clickable-root-2gB'])[8]/span[@class='button-content-3ns']")));
				driver.findElement(By.xpath("//button[@class='product-delete-1dM']")).click();
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

	@And("Search product")
	public void search_product()
	{
		SerarhingItem("Vuse ePen USB Charging Cable");
		SearchEnterAction();
	}

	@And("Add to cart")
	public void add_to_cart() 
	{
		waitbyEleClickable("(//button[@class='button-root_highPriority-3u6 button-root-16x clickable-root-2gB'])[8]", 30);
	}

	@Then("validate item added successfully")
	public void validate_item_added_successfully() 
	{
		Boolean addkartNotification= driver.findElement(By.xpath("//div[@class='toast-infoToast-3FE toast-root-2IN']")).isDisplayed();
		if(addkartNotification)
		{
			System.out.println("Item added to the kart message Visible");
		}
		else
		{
			System.out.println("Issue with product addition");
		}
	}
}

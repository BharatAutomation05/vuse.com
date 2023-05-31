package stepdefinition;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;

import base.Helper;
import io.cucumber.java.en.Given;
public class LoginF extends Helper
{
	@Given("user enter url for Sign In")
	public void user_enter_url_for_sign_in() 
	{
		setup();
	}
	@When("enter login details {string} and {string}")
	public void enter_login_details_and(String username, String pass)
	{
		clickIamAbv18();
	//clicking on SignIn Button
		MyClick("//button[@aria-label='Toggle My Account Menu']");
	//entering username and pass
		UserDetails(username, pass);	
	}

	@Then("login status")
	public void login_status() 
	{	
		try 
		{
			if(driver.findElement(By.xpath("(//*[@class='errorMessage-root-1cS'])[1]/span[@class='errorMessage-errorMessage-3sE']")).isDisplayed())
			{
				System.out.println("login Failed");
				getScreenShot();
			}
		} 
		catch (Exception e) 
			{
				System.out.println("login Successfull");
			}
		driver.quit();
	
	}

}

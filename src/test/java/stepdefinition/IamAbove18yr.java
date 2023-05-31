package stepdefinition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.Helper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class IamAbove18yr extends Helper
{

	@Given("user click on the url")
	public void user_click_on_the_url() 
	{
		setup();
	}

	@When("user click on IamAbove18yr button")
	public void user_click_on_iam_above18yr_button() 
	{
		clickIamAbv18();
	}

	@Then("direct to main website home page")
	public void direct_to_main_website_home_page() 
	{	
		WebElement SignIn= driver.findElement(By.xpath("//span[@class='accountChip-root-2kC']"));
			if(SignIn.getText().contains("Sign In"))
			{
				System.out.println("We are at the login page ");
			}
			else
			{
				System.out.println(" Test Case failed ");	
			}
			driver.quit();
		
	}
}

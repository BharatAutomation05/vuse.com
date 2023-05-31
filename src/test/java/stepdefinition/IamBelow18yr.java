package stepdefinition;


import base.Helper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
public class IamBelow18yr extends Helper
{
	@Given("user enter url")
	public void user_enter_url() 
	{
		setup();
	}

	@When("click on I am below 18yr")
	public void click_on_i_am_below_18yr() 
	{
		clickIambelow18();
	}

	@Then("redirect to google search engine")
	public void redirect_to_google_search_engine() throws InterruptedException 
	{
  		Thread.sleep(30000);
  		String titleString= driver.getTitle();	
		if(titleString.contains("Google")) 
		{
			System.out.println("Now we are at the google website after clicking I am under 18yr Button :)");
		}
		else 
		{
			System.out.println("end of the Code");
		}
		driver.quit();
	}
	
	
	
}

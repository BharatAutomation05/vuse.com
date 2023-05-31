package stepdefinition;

import base.Helper;
import io.cucumber.java.en.And;

public class AddToKartViaPDP extends Helper
{
	
	@And("clicking on product image")
	public void clicking_on_product_image() 
	{	//Clicking on product image
		MyClick("//div[@class='productCompactDetail-imageContainer-1I3 image-container-t_c']");
		//System.out.println("image clicked");
	}
	
}

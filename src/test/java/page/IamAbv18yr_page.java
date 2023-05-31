package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.Helper;

public class IamAbv18yr_page extends Helper {

public void IamAbv18yr_page_elements() 
{
	WebElement acceptCookiesButton= driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']"));
}

}

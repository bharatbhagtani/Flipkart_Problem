package devon;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import testbase.PageBase;

public class Flipkart extends PageBase {
	public static RemoteWebDriver remoteWebDriver;

	public Flipkart() {
		super(remoteWebDriver);
	}

	String xpath;
	By object;

	public By closeLogin=By.xpath("(//button)[2]");
	
	public By searchGlobal=By.xpath("//input[@title='Search for products, brands and more']");
	
	public By searchBrand=By.xpath("//input[@placeholder='Search Brand']");

	public By firstProductName=By.xpath("(//div[@class='_4rR01T'])[1]");
			
	public By firstRAM=By.xpath("((//ul[@class='_1xgFaf'])[1]/li)[1]");
	
	public By closeFilter=By.xpath("((//*[@class='_3ztiZO'])//div)[1]");

	public By getDevice(String deviceName) {
		xpath = "//input[@title='" + deviceName + "']";
		object = By.xpath(xpath);
		return object;
	}

	public By getCheckbox(String name) {
		xpath = "(//*[@title='" + name + "']//input/..//div)[1]";
		object = By.xpath(xpath);
		return object;
	}

	public void globalSearch(String searchTerm) throws Exception {
		enterText(searchGlobal, searchTerm, "Global search");

	}

	public void launchFlipkartURL(String url) throws Exception {

		launchUrl(url, "Flipkart Web App");
		click(closeLogin, "Login close");
		
	}

	public void launchBrowser() throws Exception {

		initiateBrowser();
	}
	
	public void closeBrowser() throws Exception {

		closeDriver();
	}

	public void searchMobileDevice(String globalSearch, String brand, String RAM) throws Exception {
		enterText(searchGlobal, globalSearch, "Global search");
		keyBoardEnter(searchGlobal);
		enterText(searchBrand, brand, "Brand filter");
		click(getCheckbox(brand.toUpperCase()), "Brand checkbox");

	}
	
	public void verifyMobileDevice(String brand, String RAM) throws Exception {

		
		String firstProduct=getText(firstProductName);
		String firstProductRAM=getText(firstRAM);
		Assert.assertTrue(firstProduct.contains(brand.toUpperCase())&&firstProductRAM.contains(RAM));
		System.out.println("Verification is successfull");
	}

	public void removeFilter() throws Exception {

		click(closeFilter, "Close filter");
	}


}

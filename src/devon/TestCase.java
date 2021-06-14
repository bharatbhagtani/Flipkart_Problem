package devon;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestCase {
	
	Flipkart flpkt=new Flipkart();
	
	
	@BeforeTest
	public void startDriver() throws Exception {
		flpkt.launchBrowser();
	}
	
	@AfterTest
	public void closeDriver() throws Exception {
		flpkt.closeBrowser();
	}
	
	
	@Test
	public void SearchDevice() throws Exception{
		// TODO Auto-generated method stub
		
		//---------Input values---------//
		
		String url="https://flipkart.com";
		String searchParameter="Mobiles";
		String brand = "Gionee";
		String RAM="2 GB";
		
		//-----------Test Flow------------//
		
		flpkt.launchFlipkartURL(url);
		flpkt.searchMobileDevice(searchParameter, brand, RAM);
		flpkt.verifyMobileDevice(brand, RAM);
		flpkt.removeFilter();
		

	}

}

package stepDefination;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

public class testBase {

	public static WebDriver driver;

	@Given("^Enter username and password$")
	public void Enter_username_and_password(DataTable dealData) throws Throwable {
//		System.out.println("hi");

	for (Map<String, String> data:dealData.asMaps(String.class, String.class)) {
//		for (Map<String, String>data:dealData.asMaps(String.class, String.class)) {	
//		for (Map<String,String>data:dealData.asMaps(String.class, String.class)) {
			
		
		try {						
			
			Properties pop = new Properties();

			FileInputStream file = new FileInputStream(
					"C:\\Users\\GANGULA\\Desktop\\Automation\\Projects\\BDDparameterization\\src\\test\\resources\\resource\\data.properties");
			pop.load(file);

			String browserVal = pop.getProperty("browser");
			if (browserVal.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\GANGULA\\Desktop\\Automation\\Projects\\BDDparameterization\\src\\test\\resources\\resource\\chromedriver.exe");
				System.out.println("PRASAD REACHED");
				
				driver = new ChromeDriver();
				driver.get(pop.getProperty("url"));
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//*[@id='email']")).sendKeys(data.get("username"));
				driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(data.get("password"));
				Thread.sleep(500);
				driver.findElement(By.xpath("//input[@value='Log In']")).click();
				Thread.sleep(3000);
				driver.close();
				driver.quit();

				}
			else{
				System.out.println("No a browser is selected");
			}
		}

		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		finally {
			driver = null;
		}
		
		
		
	}
		
}	
		
}	


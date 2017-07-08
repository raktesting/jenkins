

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearch {
	
	// String exePath = "/home/devops/ws/seleniumGrid/chromedriver";
	
    public static void main(String[] args) throws MalformedURLException {

        //WebDriver driver = new ChromeDriver();
        //ChromeOptions options = new ChromeOptions();
        //options.setBinary("/home/jagadeesh/devops/ws/chromedriver");
        //System.setProperty("webdriver.chrome.driver", "/home/jagadeesh/devops/ws/chromedriver");
        
        //WebDriver driver = new RemoteWebDriver(DesiredCapabilities.chrome());
        DesiredCapabilities dcap = DesiredCapabilities.chrome();
        dcap.setPlatform(Platform.LINUX);
		//driver = new RemoteWebDriver(new URL("http://172.17.0.3:5555"),dcap);
		WebDriver driver = new RemoteWebDriver(new URL("http://172.17.0.4:5555/wd/hub"), dcap);
        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        System.out.println("Page title 1 is: " + driver.getTitle());
        
        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
        
        //Close the browser
        driver.quit();
    }
}

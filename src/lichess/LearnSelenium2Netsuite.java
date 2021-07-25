package lichess;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LearnSelenium2Netsuite {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\stirumar\\workspacejava\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://7341500.app.netsuite.com/app/accounting/transactions/billingworkcenter/billrun.nl?whence=");
		driver.manage().window().maximize();
	
	
	}

}

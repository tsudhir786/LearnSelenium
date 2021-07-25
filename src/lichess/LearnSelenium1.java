package lichess;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class LearnSelenium1 {

	WebDriver driver = new ChromeDriver();
	int count = 0;

	public static void main(String[] args) throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\stirumar\\workspacejava\\chromedriver.exe");
		LearnSelenium1 ls = new LearnSelenium1();

		ls.navigateAndLogin();
//		ls.clickPuzzles();
//		ls.clickPuzzlesStreak();
//		ls.mouseAction();
//		ls.pageScroll();
//		ls.verifyDonate();
//		ls.patronsList();
//		ls.rapidMatch();
		ls.playWithFriend();

	}

	public void navigateAndLogin() throws InterruptedException {
		driver.navigate().to("https://lichess.org/");
		driver.manage().window().maximize();
//		driver.findElement(By.xpath("//*[contains(text(),'Sign in')]")).click();
//		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("LearningChess786");
//		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Tmalleswari100!");
//		driver.findElement(By.xpath("//div[@class='one-factor']/child::button")).click();
		Thread.sleep(3000);

	}

	public void clickPuzzles() {
		driver.navigate().to("https://lichess.org/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		List<WebElement> webElementList = driver.findElements(By.xpath("//a[@href='/training']"));
		Iterator<WebElement> itr = webElementList.iterator();
		while (itr.hasNext()) {

			String clickText = itr.next().getText();
			System.out.println(clickText);
			if (clickText.equals("PUZZLES")) {
				driver.findElement(By.xpath("//a[@href='/training']")).click();
				driver.quit();
			}
		}

	}

	public WebElement clickPuzzlesStreak() {
		driver.navigate().to("https://lichess.org/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		List<WebElement> webElementList = driver
				.findElements(By.xpath("//a[@href='/training']/following-sibling::div/a"));
		Iterator<WebElement> itr = webElementList.iterator();
		while (itr.hasNext()) {
			WebElement webElement = itr.next();
			if (webElement.getText().equals("Puzzle Streak")) {

				return webElement;

			}

		}
		return null;

	}

	public void pageScroll() {
		driver.navigate().to("https://lichess.org/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scrollBy(0, 1000)");
	}

	public void mouseAction() {

		driver.navigate().to("https://lichess.org/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		/*
		 * List<WebElement> webElementList = driver
		 * .findElements(By.xpath("//a[@href='/training']/following-sibling::div/a"));
		 * Iterator<WebElement> itr = webElementList.iterator(); while (itr.hasNext()) {
		 * WebElement webElement = itr.next(); String clickElement =
		 * webElement.getAttribute("innerHTML"); if
		 * (clickElement.equals("Puzzle Streak")) {
		 * System.out.println("Required WebElelement Found : " + webElement);
		 */
		Actions actions = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.xpath("//a[@href='/training']/parent::section"));
		actions.moveToElement(mainMenu);

		WebElement subMenu = driver.findElement(By.xpath(" //a[@href='/training']/parent::div/a[3]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();

		driver.quit();
	}

	public void verifyDonate() throws InterruptedException {

		driver.navigate().to("https://lichess.org/");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		Thread.sleep(5000);

		Actions actions = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.xpath("//a[@href='/player']/parent::section"));
		actions.moveToElement(mainMenu);

		WebElement subMenu = driver
				.findElement(By.xpath("//a[@href='/player']/following-sibling::a[contains(text(),'Donate')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		String finalText = driver.findElement(By.xpath("//*[contains(text(),'Thank')]")).getText();
		System.out.println(finalText);
		driver.quit();

	}

	public void patronsList() throws InterruptedException {

		Actions actions = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.xpath("//a[@href='/player']/parent::section"));
		actions.moveToElement(mainMenu);

		WebElement subMenu = driver
				.findElement(By.xpath("//a[@href='/player']/following-sibling::a[contains(text(),'Donate')]"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		List<WebElement> myList = driver.findElements(By.xpath("//div[@class='best_patrons']/child::div/div"));
		Iterator<WebElement> itr = myList.iterator();
		while (itr.hasNext()) {

			String clickText = itr.next().getText();
//			System.out.println(clickText);
			if (clickText.contains("IM")) {
//				System.out.println(clickText);
				count++;
				System.out.println("Total count of IM is " + count);
			}

		}

	}

	public void rapidMatch() throws InterruptedException {

		driver.findElement(By.xpath("//div[text()='Rapid']/preceding-sibling::div[text()='10+0']")).click();

		List<WebElement> chatList = driver.findElements(By.xpath("//div[@class='mchat__presets']/child::span"));

		for (WebElement webElement : chatList) {
			webElement.click();

			driver.quit();

		}
	}

	public void playWithFriend() {
		driver.findElement(By.xpath("//a[text()='Play with a friend']")).click();
		WebElement testDropDown = driver.findElement(By.xpath("//select[@id='sf_variant']"));
		Select dropDown = new Select(testDropDown);
		dropDown.selectByVisibleText("Chess960");
//		driver.findElement(By.xpath("//*[text()='Casual']")).click();
		driver.findElement(By.xpath("//button[@type='submit' and @title='White']/i")).submit();
		driver.findElement(By.xpath("//button[@title='Copy URL']")).click();
		
		
	}
	
	public void copy() throws UnsupportedFlavorException, IOException {
		String result = "google.com";
		String copy = Keys.chord(Keys.CONTROL,Keys.chord("c"));
		driver.findElement(By.xpath("//*[@id=\"lst-ib\"]")).sendKeys("google.com");
		driver.findElement(By.xpath("//*[@id=\"lst-ib\"]")).sendKeys(Keys.CONTROL+"a");
		driver.findElement(By.xpath("//*[@id=\"lst-ib\"]")).sendKeys(copy);

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		String x = (String) contents.getTransferData(DataFlavor.stringFlavor);
		System.out.println(x);
		int a= result.length();
		int b = x.length();
		System.out.println(a);
		System.out.println(b);
		if(a<=b) 
		{
		System.out.println("Matched Character length");
		}else 
		{
		 System.out.println("Issue In Character length");
		}
		 }

		    
}
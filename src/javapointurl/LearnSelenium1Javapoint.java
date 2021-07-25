package javapointurl;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class LearnSelenium1Javapoint {

	WebDriver driver = new ChromeDriver();

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\stirumar\\workspacejava\\chromedriver.exe");
		LearnSelenium1Javapoint ls = new LearnSelenium1Javapoint();
//		ls.seleniumOperations();
		ls.dragAnddrop1();

	}

	public void seleniumOperations() {
		driver.navigate().to("https://www.testandquiz.com/selenium/testing.html");
		driver.manage().window().maximize();
		String text = driver.findElement(By.xpath("//b[text()='This is sample text.']")).getText();
		System.out.println(text);
		String text1 = driver.findElement(By.xpath("//a[@href='https://www.javatpoint.com/']"))
				.getAttribute("innerHTML");
		System.out.println(text1);
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("Vipassana Meditation");
		driver.findElement(By.xpath("//button[@id='idOfButton']")).click();
		driver.findElement(By.xpath("//input[@id='male']")).click();

		WebElement automationCheckBox = driver.findElement(By.xpath("//input[@type='checkbox'][1]"));
		WebElement performanceCheckBox = driver.findElement(By.xpath("//input[@type='checkbox'][2]"));
		System.out.println(automationCheckBox.getAttribute("value"));
		System.out.println(automationCheckBox.getAttribute("outerHTML"));

		if (!automationCheckBox.isSelected() && !performanceCheckBox.isSelected()) {
			automationCheckBox.click();
			performanceCheckBox.click();
		}

		WebElement dropDown = driver.findElement(By.xpath("//select[@id='testingDropdown']"));
		Select selectDrop = new Select(dropDown);
		selectDrop.selectByVisibleText("Manual Testing");

		WebElement dblClickButton = driver.findElement(By.xpath("//button[@id='dblClkBtn']"));
		Actions action = new Actions(driver);
		action.doubleClick(dblClickButton).build().perform();

		String alertText = driver.switchTo().alert().getText();
		System.out.println(alertText);
		driver.switchTo().alert().accept();

		WebElement dblClickConfirmAlert = driver.findElement(By.xpath("//button[@onclick='generateConfirmBox()']"));
		Actions actiondblClick = new Actions(driver);
		actiondblClick.doubleClick(dblClickConfirmAlert).build().perform();
		driver.switchTo().alert().dismiss();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");

		WebElement source = driver.findElement(By.xpath("//img[@id='sourceImage']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='targetDiv']"));

		Actions dragImage = new Actions(driver);
		/*
		 * dragImage.clickAndHold(source).moveToElement(target).release(target).build();
		 * dragImage.perform();
		 * System.out.println("Drag and drop performed successfully");
		 */

		dragImage.dragAndDrop(source, target).build().perform();

	}

	public void dragAnddrop1() {
		driver.navigate().to("https://the-internet.herokuapp.com/drag_and_drop");
		driver.manage().window().maximize();

		// Actions class method to drag and drop
		Actions builder = new Actions(driver);
		WebElement from = driver.findElement(By.xpath("//div[@id='column-a']"));
		WebElement to = driver.findElement(By.xpath("//div[@id='column-b']"));
		// Perform drag and drop
		builder.dragAndDrop(from, to).build().perform();
		// verify text changed in to 'Drop here' box
		String textTo = to.getText();
		if (textTo.equals("A")) {
			System.out.println("PASS: File is dropped to target as expected");
		} else {
			System.out.println("FAIL: File couldn't be dropped to target as expected");
		}
		driver.close();

	}
}

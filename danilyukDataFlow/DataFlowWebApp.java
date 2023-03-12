import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public class DataFlowWebApp {

	public WebDriver driver;

	public DataFlowWebApp() {
		WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
		this.driver = new ChromeDriver();
		this.driver.get("https://cs.gmu.edu:8443/offutt/coverage/DFGraphCoverage");
	}

	public void inputAndSubmitData(String edgelist, String initial_n, String final_n, String defs, String uses) throws InterruptedException {
		Thread.sleep(2000);
		WebElement edgeBox = this.driver.findElement(By.name("edges"));
		edgeBox.sendKeys(edgelist);
		Thread.sleep(1200);
		this.driver.findElement(By.name("initialNode")).sendKeys(initial_n);
		Thread.sleep(1200);
		this.driver.findElement(By.name("endNode")).sendKeys(final_n);
		Thread.sleep(1200);
        // added code for defs ans uses
		this.driver.findElement(By.name("defs")).sendKeys(defs);
		Thread.sleep(1200);
		this.driver.findElement(By.name("uses")).sendKeys(uses);
		Thread.sleep(1200);
	}

    public ArrayList<String> getDUpaths() throws InterruptedException {
        this.driver.findElement(By.cssSelector("input[type='submit'][value = 'DU Paths']")).click();
        Thread.sleep(1200);
        ArrayList<String> actual = new ArrayList<String>();
        WebElement table = this.driver.findElement(By.xpath("//*[@id='tableResult']/tbody/tr/td/table/tbody/tr"));
		List<WebElement> t_rows = table.findElements(By.tagName("tr"));
        String[] DUpaths = t_rows.get(0).findElements(By.tagName("td")).get(0).getText().replaceAll("[\\[\\]]", "")
                .split("\n");
        for (int i = 1; i < DUpaths.length; i++) {
			actual.add(DUpaths[i]);
		}
		return actual;
    }

	public void clearAll() {
		this.driver.findElement(By.name("edges")).clear();
		this.driver.findElement(By.name("initialNode")).clear();
		this.driver.findElement(By.name("endNode")).clear();
		this.driver.findElement(By.name("defs")).clear();
		this.driver.findElement(By.name("uses")).clear();
	}

	public void shutDown() throws InterruptedException {
		Thread.sleep(2000);
		this.driver.quit();
	}
}
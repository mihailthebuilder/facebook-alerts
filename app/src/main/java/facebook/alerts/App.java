/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package facebook.alerts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new App().getGreeting());

        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/main/resources/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get("youtube.com");
        driver.close();
    }
}

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;
    public static String browserUrl = "https://www.lcwaikiki.com/tr-TR/TR";

    @BeforeScenario
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(browserUrl);
    }

    @AfterScenario
    public static void tearDown() {
        driver.close();
        driver.quit();
    }
}

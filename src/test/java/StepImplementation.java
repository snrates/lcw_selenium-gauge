import com.thoughtworks.gauge.Logger;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StepImplementation extends BaseTest {

    @Step("<number> saniye bekle")
    public void wait(int number) throws InterruptedException {
        Thread.sleep(number*1000);
    }

    @Step("<key> css li elemente tıkla")
    public void clickCss(String key){
        driver.findElement(By.cssSelector(key)).click();
    }

    @Step("<key> xPath li elemente tıkla")
    public void clickXpath(String key){
        driver.findElement(By.xpath(key)).click();
    }
    @Step("<key> xPath li listenin sıralamasını kontrol et")
    public void checkPrice(String key){
        String sortBy="En Yüksek Fiyat";
        String target=driver.findElement(By.xpath(key)).getText();
        Assert.assertEquals("İçermeme", sortBy,target);
        Logger.info("Sıralama, En Yüksek Fiyattan olarak seçilmiştir");
    }

    @Step("<url> li ve <name> isimli sayfa kontrolü yapılır")
    public void pageControl(String url, String name) {
        assertEquals(name + " isimli sayfada değilsiniz", driver.getCurrentUrl(), url);
        Logger.info(name + " isimli sayfadasınız");
    }

    @Step("<key> li listede 3 eleman var mı?")
    public void listControl(String key) {
        boolean x = false;
        List<WebElement> li = driver.findElements(By.xpath(key));
        if (li.size() == 3) {
            x = true;
        }
        Assert.assertTrue("Favoriye eklenen 3 ürün görünmüyor", x);
        Logger.info("Favoriye eklenen 3 ürün görünüyor");
    }

    @Step("<key1> xPath li elementin üzerine gelip menüden <key2> li elemente tıklama")
    public void hover(String key1, String key2) throws InterruptedException {
        Thread.sleep(1000);
        WebElement mainMenu = driver.findElement(By.xpath(key1));
        Actions actions = new Actions(driver);
        actions.moveToElement(mainMenu).perform();
        Thread.sleep(1000);
        WebElement subMenu = driver.findElement(By.xpath(key2));
        actions.moveToElement(subMenu);
        actions.click().build().perform();
    }

    @Step("<key> xPath li listeden ilk 3 ürünü favorilere ekle")
    public void addToFav(String key) throws InterruptedException {
        List<WebElement> li = driver.findElements(By.xpath(key));
        li.get(0).click();
        Thread.sleep(500);
        li.get(1).click();
        Thread.sleep(500);
        li.get(2).click();

    }

    @Step("<key> xPath li listede eleman kontrolü")
    public void listCheckControl(String key) {
        boolean flag = false;
        List<WebElement> li = driver.findElements(By.xpath(key));
        if (li.size() == 3) {
            flag = true;
        }
        Assert.assertTrue("Favoriye eklenen 3 ürün de seçilmedi", flag);
        Logger.info("Favoriye eklenen 3 ürün de seçilmiştir");
    }

    @Step("<key> css elementinin içeriğini kontrol et")
    public void checkProduct(String key) throws InterruptedException {
        Thread.sleep(1000);
        String title = driver.findElement(By.cssSelector(key)).getText();
        String knowTitle = "Favori Ürününüz Yok";
        Assert.assertEquals("İçermeme", knowTitle, title);
        Logger.info("Favori Ürününüz Yok metni sayfadadır");
    }

}
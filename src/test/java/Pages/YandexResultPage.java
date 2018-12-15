package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.Set;

public class YandexResultPage extends BasePage{

    public YandexResultPage(WebDriver webDriver) {
        super(webDriver);
        this.waitForLoadPage();
    }

    @FindBys(@FindBy(xpath="//*[@class='main__content']//ul//li[@class='serp-item']"))
    private List<WebElement> resultElements;


    public WebElement lookForURL( String URL){
        String URLName = URL.split("://")[1];
        String xPath =  "//div[@class='path organic__path']//a[contains(@href,'"+URLName+"')]";

        WebElement wel2ret = null ;
        wel2ret = resultElements.stream().filter(wel ->
                wel.findElements(By.xpath(xPath)).size()>0)
                        .findFirst().get();

        if( wel2ret!=null )
            // why we can dot that ? - cause we've already find it
            return wel2ret.findElement(By.xpath(xPath));
        else
            return null;
    }

    public OpenedPage clickToURl( String URL){
        Set<String> whsBefor = webDriver.getWindowHandles();

        try {
            lookForURL(URL).click();
        }catch ( NullPointerException npe){
            return null;
        }
        // get Window Handler of opened Tab
        String  newTabWH = webDriver.getWindowHandles().stream().filter(
                                                            wh -> !( whsBefor.contains(wh))
                                                            ).findFirst().get();

        // check case of IE - when link opens in the same tab
        if( newTabWH !=null )
            webDriver.switchTo().window( newTabWH );

        return new OpenedPage(this.webDriver);
    }

}

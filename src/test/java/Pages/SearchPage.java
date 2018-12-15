package Pages;

import org.openqa.selenium.WebDriver;

public abstract class  SearchPage extends BasePage {


    public SearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract public YandexResultPage doSearch();

    abstract  public void inputText(String lookingFor);


}

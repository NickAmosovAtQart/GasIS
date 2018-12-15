package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YaPage extends SearchPage {

    @FindBy(css = "form>div.search2__input input#text.input__control.input__input")
    protected WebElement searchInput;

    @FindBy(css = "form>div.search2__button>button")
    protected WebElement searchButton;

    public YaPage(WebDriver webDriver) {
        super(webDriver);
        waitForLoadPage();
    }

    @Override
    public YandexResultPage doSearch() {
        searchButton.click();
        return new YandexResultPage( this.webDriver );
    }

    @Override
    public void inputText(String lookingFor) {
        searchInput.sendKeys(lookingFor);
    }



}

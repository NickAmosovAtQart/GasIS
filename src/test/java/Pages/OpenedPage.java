package Pages;

import org.openqa.selenium.WebDriver;

public class OpenedPage  extends BasePage{
    public OpenedPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    void waitForLoadPage(){
        super.waitForLoadPage();
//TODO here might be described additional rules to define of Gaz-IS page loading completed.
    }

}

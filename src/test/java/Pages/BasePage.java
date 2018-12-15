package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected final int TIME_FOR_PAGE_WAITING=5000;
    protected WebDriver webDriver;
    protected boolean pageIsLoaded = false;

    public BasePage( WebDriver webDriver){
        this.webDriver = webDriver;
        waitForLoadPage();
        PageFactory.initElements( this.webDriver, this);
    }

    public boolean isLoaded(){
        return pageIsLoaded;
    }

    public String getURL(){
        return webDriver.getCurrentUrl();
    }

    // TODO  how to avoid webDriver
    void waitForLoadPage(){

        new WebDriverWait(webDriver, TIME_FOR_PAGE_WAITING).until( (wbdr) -> {

            String docReadyState = ((JavascriptExecutor) wbdr).executeScript("return document.readyState").toString();
            //System.out.println("document.readyState is: "+ docReadyState);
            pageIsLoaded = docReadyState.equals("complete");
            return pageIsLoaded;
        } );

    }
}

package GazIsTest;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.*;

@RunWith(value = Parameterized.class )
public class TestBase {
    protected WebDriver webDriver;
    protected String browser;

    static Properties properties;
    static protected String searchEngineURL;

    @BeforeClass
    public static void  globSetup() {
        File f1 = new File("config.properties");
        FileReader fin = null;
        try {
            fin = new FileReader(f1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        properties = new Properties();
        try {
            properties.load(fin);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (System.getProperty("os.name").equals("Mac OS X")) {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver.osx"));
        } else {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver.win"));
            System.setProperty("webdriver.ie.driver", properties.getProperty("webdriver.ie.driver"));
        }
        searchEngineURL = properties.getProperty("searchEngineURL");

// TODO should be refactored
        if (!searchEngineURL.contains("ya.ru")) {
            System.out.println("Now is supported only 'ya.ru' search engine");
            throw new NotImplementedException();
        }

    }


    public TestBase( String browser ){
        this.browser=browser;
        switch (browser){
            case "IE11":
                webDriver = new InternetExplorerDriver();
                break;
            case "Chrome":
            default:
                webDriver = new ChromeDriver();
                break;
        }
    }

    @Parameterized.Parameters
    public static Collection<Object> browsers() {
        return Arrays.asList(new Object[]{
                "Chrome",
                "IE11"
        });
    }
}

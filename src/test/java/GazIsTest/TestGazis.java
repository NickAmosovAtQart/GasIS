package GazIsTest;

import Pages.*;
import org.junit.*;
import java.io.UnsupportedEncodingException;


public class TestGazis extends TestBase{

    protected String looking4String;
    protected String looking4URL;

    public TestGazis(String browser) {
        super(browser);
    }

    @Before
    public void setUp() {
        try {
            looking4String = new String (properties.getProperty("looking4String").getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        looking4URL = properties.getProperty("looking4URL");

        webDriver.navigate().to(searchEngineURL);
    }

    @Test()
    public void gazisTest1(){
        YaPage yaPage = new YaPage( webDriver );
        Assert.assertTrue("Search engine did not loaded", yaPage.isLoaded() );
        yaPage.inputText( looking4String );

        YandexResultPage yaRes = yaPage.doSearch();
        Assert.assertTrue("Search result page doesn't load", yaRes.isLoaded() );
        OpenedPage openedPage = yaRes.clickToURl( looking4URL );

        Assert.assertTrue( openedPage.getURL().contains(looking4URL.toLowerCase()) );
        Assert.assertTrue( "Error occurred during loading of "+openedPage.getURL() , openedPage.isLoaded() );
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}

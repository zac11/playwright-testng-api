package playwright.restfulbooker.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import playwright.manager.RequestManager;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected RequestManager requestManager;

    /**
     *  Setting up the tests - Setup
     */
    @BeforeTest
    public void setupBaseTest(){
        requestManager  = new RequestManager();
        requestManager.createPlaywright();

        // set up the base url of the project
        final String baseUrl = "http://localhost:3001";

        // set the headers
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        headers.put("Accept", "application/json");

        // create the API context
        requestManager.setApiRequestContext(baseUrl, headers);


    }

    /**
     * Closing the tests  - Teardown
     */

    @AfterTest
    public void tearDownTests(){
        requestManager.disposeAPIRequestContext();
        requestManager.closePlaywrightConnection();
    }
}

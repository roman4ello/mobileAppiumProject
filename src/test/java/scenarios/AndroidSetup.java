package scenarios;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.io.File;
import java.net.URL;


public class AndroidSetup {
    protected AndroidDriver driver;
    protected Fields fields;

    protected void prepareAndroidForAppium() throws MalformedURLException {
        File appDir = new File("/myAppiumProject/apps");
        File app = new File(appDir, "linkedin-4-0-93.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");
        capabilities.setCapability("deviceName", "Nexus4");

        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        fields = new Fields();
    }

    protected boolean login(String userPar, String passwPar) {

        waitForVisibilityOf(fields.firstSignIn);
        driver.findElement(fields.firstSignIn).click();
        driver.findElement(fields.userEmail).sendKeys(userPar);
        driver.findElement(fields.userPassword).sendKeys(passwPar);
        driver.findElement(fields.showButton).click();

        //enter app
        driver.findElement(fields.sign_inButton).click();

        //Checking view feedbuton
        boolean displayed = waitForVisibilityOf(fields.feedButton);
        return displayed;
    }

    protected boolean people() {

        //click butt Conections
        driver.findElement(fields.myNetworkButton).click();

        //Checking view feedbuton
        //если хотя бы одна из них покажется
        boolean displayed = false;
        if (waitForVisibilityOf(fields.connectionsButton)) {
            displayed = true;
            driver.findElement(fields.connectionsButton).click();

        } else if (waitForVisibilityOf(fields.top_cardConnectionsButton)) {
            driver.findElement(fields.top_cardConnectionsButton).click();
            displayed = true;

        }

        return displayed;
    }

    protected boolean contacts() {
        return waitForVisibilityOf(fields.connecionsTextInConnectionsMenu);

    }

    protected boolean writeTheMessage(String message_to_userPar) {

        driver.findElement(fields.profile_view_top_card_primary_Button).click();
        waitForVisibilityOf(fields.text_input_container_field);
        driver.findElement(fields.text_input_container_field).click();
        driver.findElement(fields.text_input_container_field).clear();
        driver.findElement(fields.text_input_container_field).sendKeys(message_to_userPar);
        driver.findElement(fields.keyboard_send_button).click();
        return waitForVisibilityOf(fields.icon_message_to_user);

    }

    protected boolean openTheContact() {
        driver.findElement(fields.my_goal_contact).click();
        return waitForVisibilityOf(fields.profile_view_top_card_primary_Button);
    }

    protected boolean waitForVisibilityOf(By locator) {
        boolean flag = false;
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (driver.findElement(locator) != null) {
            flag = true;
        }
        return flag;
    }

}
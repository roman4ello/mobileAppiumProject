package scenarios;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class AppiumTest extends AndroidSetup {


    @BeforeClass
    public void setUp() throws Exception {
        prepareAndroidForAppium();
    }

    //    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void showTest() {

        String app_package_name = "com.linkedin.android:id/";

        By firstSignIn = By.id(app_package_name + "growth_prereg_fragment_sign_in_button");
        By userEmail = By.id(app_package_name + "growth_login_join_fragment_email_address");
        By userPassword = By.id(app_package_name + "growth_login_join_fragment_password");
        By showButton = By.id(app_package_name + "growth_login_join_show_hide_password");

        waitForVisibilityOf(firstSignIn);

        driver.findElement(firstSignIn).click();
        driver.findElement(userEmail).sendKeys("br777roman@gmail.com");
        driver.findElement(userPassword).sendKeys("1478965roman");

        driver.findElement(showButton).click();

        //Checking if the "SHOW" button works right
        String typedPass = driver.findElement(userPassword).getText();
        Assert.assertEquals(typedPass, "1478965roman");

    }


    @Test
    public void writeMessageTest() {


        boolean flag = login(fields.user, fields.password);
        Assert.assertEquals(flag, true);

          flag = people();
        //проверка того что отработала иконка главного меню "контакты", но еще мы не в вконтактах
        Assert.assertEquals(flag, true);

          flag = contacts();
        //проверили что мы уже в контактах
        Assert.assertEquals(flag, true);

          flag = openTheContact();
        //проверили что мы уже в искомом контакте
        Assert.assertEquals(flag, true);

          flag = writeTheMessage(fields.message_to_user);
        // icon_message_to_user = message
        Assert.assertEquals(flag, true);

    }


}
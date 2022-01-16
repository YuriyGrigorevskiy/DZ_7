package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/** Страница авторизации */
public class LoginPage extends HelpdeskBasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement InputName;
    @FindBy (xpath = "//input[@id='password']")
    private WebElement InputPassword;
    @FindBy (xpath = "//*[@id='content-wrapper']/div/div/div/div[2]/form/div[3]/div/label/input")
    private WebElement CheckboxRememberPassword;
    @FindBy (xpath = "//*[@id='content-wrapper']/div/div/div/div[2]/form/input[1]")
    private WebElement LoginButton;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Авторизация пользователя
     *
     * @param user     логин пользователя
     * @param password пароль пользователя
     */

    @Step("Вводим логин и пароль")
    public void login(String user, String password) {
        InputName.sendKeys(user);
        InputPassword.sendKeys(password);
        LoginButton.click();
    }
}

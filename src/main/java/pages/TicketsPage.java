package pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import models.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/** Страница со списком тикетов */
public class TicketsPage extends HelpdeskBasePage {

    @FindBy (xpath = "//div[@class='tickettitle']/a")
    private WebElement TitleSearch;

    public TicketsPage() {
        PageFactory.initElements(driver, this);
    }

    /** Ищем строку с тикетом и нажимаем на нее */
    @Step("Open ticket")
    public void openTicket() {

        TitleSearch.click();
        makeScreenshot();
    }

    @Attachment(value = "Attachment Screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

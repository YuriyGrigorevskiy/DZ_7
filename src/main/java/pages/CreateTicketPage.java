package pages;

import models.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/** Страница создания тикета */
public class CreateTicketPage extends HelpdeskBasePage {

    // Способ объявления элементы страницы, через аннотацию

    @FindBy(xpath = "//*[@id='id_queue']")
    private WebElement TicketQueue;

    @FindBy(xpath = "//*[@id='id_title']")
    private WebElement TicketTitle;

    @FindBy(xpath = "//*[@id='id_body']")
    private WebElement TicketDescription;

    @FindBy(xpath = "//*[@id='id_priority']")
    private WebElement TicketPriority;

    @FindBy(xpath = "//*[@id='id_due_date']")
    private WebElement TicketDue_date;

    @FindBy(xpath = "//*[@id='id_attachment']")
    private WebElement TicketAttachmentFile;

    @FindBy(xpath = "//*[@id='id_submitter_email']")
    private WebElement TicketSubmitEmail;

    @FindBy(xpath = "//*[@id='id_assigned_to']")
    private WebElement TicketAssigned_to;

    @FindBy(xpath = "//*[@id='content-wrapper']/div/div/div/div[2]/form/button")
    private WebElement SubmitTicket;


    // todo: остальные элементы


    public CreateTicketPage(WebDriver driver) {
        this.driver = driver;

        // Необходимо инициализировать элементы класса, лучше всего это делать в конструкторе
        PageFactory.initElements(driver, this);
    }

    /** Создание тикета */
    public void createTicket(Ticket ticket) {

        setProblemTitle(ticket.getTitle());
        setDescriptionIssue(ticket.getDescription());
        setDue_on(ticket.getDue_date());
        setQueue(ticket.getQueue());
        setPriority(ticket.getPriority());
        setEmail(ticket.getSubmitter_email());

        createTicketBtn();
    }

    /** Заполнение поля "Summary of the problem" */
    public void setProblemTitle(String text) {
        TicketTitle.sendKeys(text);
    }

    public void setDescriptionIssue(String text) {
        TicketDescription.sendKeys(text);
    }

    public void setDue_on(String text) {
        TicketDue_date.click();
        List <WebElement> call = driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr/td/a"));
        for( WebElement a : call){
            if(a.getText().equals(text)){
                a.click();
            }
        }
    }

    public void setQueue(Integer number) {
        Select s = new Select(TicketQueue);
        s.selectByValue(String.valueOf(number));
    }

    public void setPriority(int number) {
        Select s = new Select(TicketPriority);
        s.selectByValue(String.valueOf(number));
    }

    public void setEmail(String text) {
        TicketSubmitEmail.sendKeys(text);
    }

    public void setKbitem(int number) {
        TicketAttachmentFile.sendKeys(String.valueOf(number));
    }

    public void setAssigned_to(String text){
        TicketAssigned_to.sendKeys(text);
    }

    /** Зажатие кнопки "Submit Ticket" */
    public void createTicketBtn() {
        SubmitTicket.click();
    }
}

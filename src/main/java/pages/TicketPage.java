package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/** Страница отдельного тикета */
public class TicketPage extends HelpdeskBasePage {

    @FindBy(xpath = "//h3")
     WebElement ticketTitle;

    @FindBy(xpath = "//h3/following-sibling::text()")
     WebElement queue;

    @FindBy(xpath = "//th[text()='Submitter E-Mail']/following-sibling::td[1]")
     WebElement email;

    @FindBy(xpath = "//th[text()='Assigned To']/following-sibling::td[1]")
     WebElement assigned_to;

    @FindBy(xpath = "//th[text()='Due Date']/following-sibling::td[1]")
     WebElement due_date;

    @FindBy(xpath = "//th[text()='Priority']/following-sibling::td[1]")
     WebElement priority;

    @FindBy(xpath = "//th[text()='Dependencies']/following-sibling::td[1]")
     WebElement dependencies;

    @FindBy(xpath = "//th[text()='Submitted On']/following-sibling::td[1]")
     WebElement submitted_on;

    @FindBy(xpath = "//th[text()='Copies To']/following-sibling::td[1]")
     WebElement copies_to;

    @FindBy(xpath = "//th[text()='Total time spent']/following-sibling::td[1]")
     WebElement total_time_spend;

    @FindBy(xpath = "//*[@id='ticket-description']/p")
    WebElement description;

    public TicketPage() {
        PageFactory.initElements(driver, this);
    }

    /** Получить имя тикета */
    public String getTicketTitle() {
        return ticketTitle.getText();
    }

    /** Получить адрес почты */
    public String getEmail() {
        // Получаем значение адреса почты
        return email.getText();
    }

    public String getQueue(){
        return queue.getText();
    }

    public String getDescription(){ return description.getText();
    }

    public String getAssigned_to(){
        return assigned_to.getText();
    }

    public String getDue_date(){ return due_date.getAttribute("days");
    }

    public Integer getPriority(){
        return Integer.parseInt(priority.getText().replaceAll("\\D+",""));
    }

    public String getDependencies(){
        return dependencies.getText();
    }

    public Integer getCopies_to(){
        return Integer.parseInt(copies_to.getText());
    }

    public String getSubmitted_on(){ return submitted_on.getText();
    }

    public String getTotal_time_spend(){
        return total_time_spend.getText();
    }

    /**
     * Получить значение элемента таблицы
     *
     * @param columnElem элемент ячейки для которой нужно вернуть значение
     * @return текстовое значение ячейки рядом
     */
    private String getValue(WebElement columnElem) {
        return columnElem
                // Находи следующий элемент находящийся в том же теге
                .findElement(By.xpath("./following-sibling::td[1]"))
                // Получаем текст
                .getText()
                // Обрезаем лишние пробелы
                .trim();
    }
}

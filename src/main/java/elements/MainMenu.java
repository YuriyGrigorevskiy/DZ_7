package elements;

import io.qameta.allure.Step;
import models.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/** Элемент главного меню */
public class MainMenu {

    private WebDriver driver;

    // Поиск элементов без аннотации
            By newTicketBtn = (By.xpath("//*[@id='wrapper']/ul/li[2]/a"));
            By loginBtn = (By.xpath("//*[@id='userDropdown']"));
            By knowledgeBtn = (By.xpath("//*[@id='wrapper']/ul/li[3]/a"));
            By searchTitleColumn = (By.xpath("//*[@id='search_query']"));
            By searchBtn = (By.xpath("//*[@class='btn btn-primary']"));
            By loginButtonAfterCreateTicket = (By.xpath("//*[@id='bg-dark']/nav/ul"));

    public MainMenu(WebDriver driver) {
        this.driver = driver;
    }

    public void newTicket() {
        driver.findElement(newTicketBtn).click();
    }

    public void logIn(){
        driver.findElement(loginBtn).click();
    }
    @Step("Ищем тикет")
    public void searchTicket(Ticket ticket) {
        setSearch(ticket.getTitle());
        search();
    }

    /* Если после вызова void метода, может потребоваться вызов другого метода этого же класса,
        то можно вернуть сам класс и вызвать следующий метод через точку. */
    public MainMenu setSearch(String text) {
        driver.findElement(searchTitleColumn).sendKeys(text);
        return this;
    }

    public void search() {
        driver.findElement(searchBtn).click();
    }
    @Step("Нажимаем на кнопку регистрации")
    public void jumpOnLoginPage() {
        driver.findElement(loginButtonAfterCreateTicket).click();
    }
}

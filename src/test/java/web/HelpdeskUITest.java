package web;


import io.qameta.allure.Epic;
import models.Ticket;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Epic("Тестирование создания тикета")
public class HelpdeskUITest {

    private WebDriver driver;
    private Ticket ticket;

    @BeforeClass
    public void setup() throws IOException {
        // Читаем конфигурационные файлы в System.properties
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
        // Создание экземпляра драйвера
        driver = new ChromeDriver();
        // Устанавливаем размер окна браузера, как максимально возможный
        driver.manage().window().maximize();
        // Установим время ожидания для поиска элементов
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Установить созданный драйвер для поиска в веб-страницах
        AbstractPage.setDriver(driver);
    }

    @Test
    public void createTicketTest() throws InterruptedException {
        // Заполняем объект класс Ticket необходимыми тестовыми данными

        MainPage main = new MainPage();

        driver.get("https://at-sandbox.workbench.lanit.ru/");
        CreateTicketPage newTicket = PageFactory.initElements(driver,CreateTicketPage.class);

        main.mainMenu().newTicket();

        Ticket ticket = new Ticket();
        ticket.setTitle("title");
        ticket.setDescription("this is ring");
        ticket.setDue_date("7");
        ticket.setPriority(5);
        ticket.setQueue(1);
        ticket.setSubmitter_email("aaa@mail.ru");

        newTicket.createTicket(ticket);

        LoginPage login = new LoginPage();
        main.mainMenu().jumpOnLoginPage();

        login.login(System.getProperty("user"),System.getProperty("password"));

        main.mainMenu().searchTicket(ticket);
        TicketsPage findTicket = new TicketsPage();
        findTicket.openTicket();

        TicketPage findColumn = new TicketPage();

      Assert.assertEquals(findColumn.getPriority(),ticket.getPriority(),"не равны");
      Assert.assertEquals(findColumn.getEmail(),ticket.getSubmitter_email(),"не равны");
      Assert.assertEquals(findColumn.getDescription(),ticket.getDescription(),"не равны");
        driver.close();
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}


package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.time.Duration;

// Главная страница
public class MainPage {
    private final By cookieButton = By.xpath(".//button[text()='да все привыкли']");
    // Кнопка Заказать в шапке страницы
    private final By orderButtonHead = By.xpath(".//button[@class='Button_Button__ra12g']");
    // Кнопка Заказать внизу страницы
    private final By orderButtonBody = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");
    // Локатор для списка вопросов
    private final By listOfQuestions = By.className("accordion");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Закрытие плашки с куками
    public void acceptCookie() {
        driver.findElement(cookieButton).click();
    }

    // Нажатие на кнопку оформления заказа в шапке сайта
    public void clickOnOrderButtonHead() {
        driver.findElement(orderButtonHead).click();
    }

    // Нажатие на кнопку оформления заказа в теле сайта
    public void clickOnOrderButtonBody() {
        WebElement element = driver.findElement(orderButtonBody);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(orderButtonBody).click();
    }

    // Массив с ожидаемыми ответами на вопросы
    public String[] expectedAnswersArray = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    // Скролл до блока вопросов, раскрытие вопроса, получение текста ответа
    public String getAnswerText(int questionsNumber) {
        WebElement element = driver.findElement(listOfQuestions);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        driver.findElement(By.xpath(".//div[@class='accordion']/div["+ questionsNumber + "]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // при других значения < 20 тесты периодически завершаются не успешно
        String idOfElementWithAnswerText = String.format("accordion__panel-%d", questionsNumber-1);

        return driver.findElement(By.id(idOfElementWithAnswerText)).getText();
    }

}

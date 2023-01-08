package ru.yandex.praktikum;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//страница заказа
public class OrderPage {
    private final By firstNameField = By.xpath(".//input[@placeholder='* Имя']"); // поле Имя
    private final By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']"); // поле Фамилия
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']"); // поле Адрес
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']"); // поле Станция метро
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']"); // поле Телефон
    private final By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[text()='Далее']"); // кнопка Далее
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']"); // поле Когда привезти самокат
    private final By rentalPeriodField = By.xpath(".//span[@class='Dropdown-arrow']"); // поле Срок Аренды
    private final By blackColorCheckBox = By.xpath(".//input[@id='black']"); // чекбокс Цвет самоката - черный жемчуг
    private final By greyColorCheckBox = By.xpath(".//input[@id='grey']"); // чекбокс Цвет самоката - серая безысходность
    private final By commentField = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']"); // поле Комментарий для курьера
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']"); // кнопка Заказать
    private final By yesButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']"); // Кнопка Да
    private final By orderConfirm = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']"); // Подтверждение создания заказа
    private final By firstNameError = By.xpath(".//div[text()='Введите корректное имя']"); //ошибка "Введите корректное имя"
    private final By lastNameError = By.xpath(".//div[text()='Введите корректную фамилию']"); //ошибка "Введите корректную фамилию"
    private final By addressError = By.xpath(".//div[text()='Введите корректный адрес']"); //ошибка "Введите корректный адрес"
    private final By metroError = By.xpath(".//div[text()='Выберите станцию']"); //ошибка "Выберите станцию"
    private final By phoneError = By.xpath(".//div[text()='Введите корректный номер']"); //ошибка "Введите корректный номер"

    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Ввод имени
    public void setFirstName(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    // Ввод фамилии
    public void setLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    // Ввод адреса
    public void setAddress(String address){
        driver.findElement(addressField).sendKeys(address);
    }

    // Метод ввода станции метро
    public void setMetroStation(String metroStation) {
        driver.findElement(metroField).click();
        driver.findElement(metroField).sendKeys(metroStation);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[text()='" + metroStation + "']")));
        driver.findElement(By.xpath(".//div[text()='" + metroStation + "']")).click();
    }

    // Метод для ввода станции метро (негативные тесты)
    public void setWrongMetroStation(String metroStation) {
        driver.findElement(metroField).click();
        driver.findElement(metroField).sendKeys(metroStation);
        driver.findElement(metroField).click();
    }

    // Ввод номера телефона
    public void setPhoneNumber(String phoneNumber){
        driver.findElement(phoneField).sendKeys(phoneNumber);
    }

    // Клик на кнопку Далее
    public void clickOnNextButton(){
        driver.findElement(nextButton).click();
    }

    // Ввод даты
    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date);
    }

    // Метод для выбора срока аренды на двое суток
    public void setRentalPeriod(String period){
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-option' and text()='" + period + "']")).click();
    }

    // Метод выбора цвета самоката в зависимости от того, какой цвет выбран
    public void setScooterColor(String color) {
        if(color.equals("black")){
            driver.findElement(blackColorCheckBox).click(); // если черный жемчуг
        } else if(color.equals("grey")) {
            driver.findElement(greyColorCheckBox).click(); // если серая безысходность
        }
    }

    // Ввод комментария
    public void setComment(String comment){
        driver.findElement(commentField).sendKeys(comment);
    }

    // Клик на кнопку Заказать
    public void clickOnOrderButton() {
        driver.findElement(orderButton).click();
    }

    // Клик на кнопку Да
    public void clickOnYes() {
        driver.findElement(yesButton).click();
    }

    // Метод для проверки, что заказ создан (проверка окна с фразой Заказ оформлен)
    public boolean checkOrderConfirm() {
        return driver.findElement(orderConfirm).isDisplayed();
    }

    // Метод оформления заказа
    public void createOrder(String firstName, String lastName, String address, String metroStation, String phoneNumber,
                            String date, String period, String color, String comment) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setMetroStation(metroStation);
        setPhoneNumber(phoneNumber);
        clickOnNextButton();
        setDate(date);
        setRentalPeriod(period);
        setScooterColor(color);
        setComment(comment);
        clickOnOrderButton();
        clickOnYes();
    }

    // Проверка высвечивания ошибки "Введите корректное имя"
    public boolean isFirstNameErrorVisible() {
        return driver.findElement(firstNameError).isDisplayed();
    }
    // Проверка высвечивания "Введите корректную фамилию"
    public boolean isLastNameErrorVisible() {
        return driver.findElement(lastNameError).isDisplayed();
    }
    // Проверка высвечивания "Введите корректный адрес"
    public boolean isAddressErrorVisible() {
        return driver.findElement(addressError).isDisplayed();
    }
    // Проверка высвечивания "Выберите станцию"
    public boolean isMetroStationErrorVisible() {
        return driver.findElement(metroError).isDisplayed();
    }
    // Проверка высвечивания "Введите корректный номер"
    public boolean isPhoneErrorVisible() {
        return driver.findElement(phoneError).isDisplayed();
    }
}

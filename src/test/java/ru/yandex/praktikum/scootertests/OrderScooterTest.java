package ru.yandex.praktikum.scootertests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.MainPage;
import ru.yandex.praktikum.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderScooterTest extends TestBase {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;

    public OrderScooterTest(String firstName, String lastName, String address,
                            String metroStation, String phoneNumber, String date, String period, String color,
                            String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getValue() {
        return new  Object[][] {
                {"Иван", "Макиенко", "Мой дом", "Котельники", "+79250001313", "02.01.2023", "сутки", "black", "Комментарий для теста номер один"},
                {"Петр", "Иванов", "Новый дом", "Полежаевская", "+79160654367", "05.01.2023", "двое суток", "grey", "Комментарий для теста номер два"},
        };
    }

    @Test
    public void checkCreateOrderHeadButtonPositive() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.acceptCookie();
        objMainPage.clickOnOrderButtonHead();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.createOrder(firstName, lastName, address, metroStation, phoneNumber, date, period, color, comment);

        boolean actualResult = objOrderPage.checkOrderConfirm();

        assertTrue("Должно отобразиться окно: 'Заказ оформлен'", actualResult);
    }

    @Test
    public void checkCreateOrderBodyButtonPositive() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.acceptCookie();
        objMainPage.clickOnOrderButtonBody();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.createOrder(firstName, lastName, address, metroStation, phoneNumber, date, period, color, comment);

        boolean actualResult = objOrderPage.checkOrderConfirm();

        assertTrue("Должно отобразиться окно 'Заказ оформлен'", actualResult);
    }

}
package ru.yandex.praktikum.scootertests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.MainPage;
import ru.yandex.praktikum.OrderStatusPage;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CheckOrderTest extends TestBase {
    private final int orderNumber;


    public CheckOrderTest(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Parameterized.Parameters
    public static Object[][] setOrderNumbers() {
        return new Object[][] {
                {1},
                {0},
                {1765434},
        };
    }

    @Test
    public void checkWrongOrderNumber() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.acceptCookie();

        OrderStatusPage objTrackPage = new OrderStatusPage(driver);
        boolean actual = objTrackPage.isOrderNotFound(orderNumber);

        assertTrue("Должно отображаться сообщение: 'Такого заказа нет, точно верный номер?'", actual);
    }
}
package ru.yandex.praktikum.scootertests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.MainPage;
import ru.yandex.praktikum.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MetroStationFieldErrorsTest extends TestBase {
    private final String metroStationWithErrors;

    public MetroStationFieldErrorsTest(String metroStationWithErrors) {
        this.metroStationWithErrors = metroStationWithErrors;
    }

    @Parameterized.Parameters
    public static Object[][] getValue() {
        return new  Object[][] {
                {""},
                {"Kotelniki"},
        };
    }

    @Test
    public void checkMetroStationFieldNegative() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.acceptCookie();
        objMainPage.clickOnOrderButtonHead();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.setWrongMetroStation(metroStationWithErrors);
        objOrderPage.clickOnNextButton();

        assertTrue("Должно отображаться сообщение: 'Выберите станцию'", objOrderPage.isMetroStationErrorVisible());
    }
}

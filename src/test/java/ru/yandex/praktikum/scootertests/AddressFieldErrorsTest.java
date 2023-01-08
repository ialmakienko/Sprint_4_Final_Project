package ru.yandex.praktikum.scootertests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.MainPage;
import ru.yandex.praktikum.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class AddressFieldErrorsTest extends TestBase {
    private final String addressWithErrors;

    public AddressFieldErrorsTest(String addressWithErrors) {
        this.addressWithErrors = addressWithErrors;
    }

    @Parameterized.Parameters
    public static Object[][] getValue() {
        return new  Object[][] {
                {"Дом"},
                {""},
                {"Home sweet home"},
                {"23456"},
                {"!@@(*!@%@$)"},
        };
    }

    @Test
    public void checkAddressFieldNegative() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickOnOrderButtonHead();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.setAddress(addressWithErrors);
        objOrderPage.clickOnNextButton();

        assertTrue("Должно отображаться сообщение: 'Введите корректный адрес'", objOrderPage.isAddressErrorVisible());
    }

}

package ru.yandex.praktikum.scootertests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.MainPage;
import ru.yandex.praktikum.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PhoneFieldsErrorsTest extends TestBase {
    private final String phoneWithErrors;

    public PhoneFieldsErrorsTest(String phoneWithErrors) {
        this.phoneWithErrors = phoneWithErrors;
    }

    @Parameterized.Parameters
    public static Object[][] getValue() {
        return new  Object[][] {
                {"my phone number"},
                {""},
                {"Мой номер телефона"},
                {"9250001313"},
                {"+7(925)000-13-13"}
        };
    }

    @Test
    public void checkPhoneFieldNegative() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.acceptCookie();
        objMainPage.clickOnOrderButtonHead();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.setPhoneNumber(phoneWithErrors);
        objOrderPage.clickOnNextButton();

        assertTrue("Должно отображаться сообщение: 'Введите корректный номер'", objOrderPage.isPhoneErrorVisible());

    }
}

package ru.yandex.praktikum.scootertests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.MainPage;
import ru.yandex.praktikum.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FirstNameFieldErrorsTest extends TestBase {
    private final String firstNameWithErrors;

    public FirstNameFieldErrorsTest(String firstNameWithErrors) {
        this.firstNameWithErrors = firstNameWithErrors;
    }

    @Parameterized.Parameters
    public static Object[][] getValue() {
        return new  Object[][] {
                {"М"},
                {""},
                {"V"},
                {"9"},
                {"?*%№"},
        };
    }

    @Test
    public void checkFirstNameFieldNegative() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.acceptCookie();
        objMainPage.clickOnOrderButtonHead();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.setFirstName(firstNameWithErrors);
        objOrderPage.clickOnNextButton();

        assertTrue("Должно отображаться сообщение: 'Введите корректное имя'", objOrderPage.isFirstNameErrorVisible());
    }
}

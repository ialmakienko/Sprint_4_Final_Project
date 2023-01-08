package ru.yandex.praktikum.scootertests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.MainPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuestionsPageTest extends TestBase {
    private final int questionsNumber;

    public QuestionsPageTest(int questionsNumber) {
        this.questionsNumber = questionsNumber;
    }

    @Parameterized.Parameters
    public static Object[][] getNumberOfQuestions() {
        return new Object[][] {
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
                {8},
        };
    }

    @Test
    public void compareListOfQuestions() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.acceptCookie();
        String answerText = objMainPage.getAnswerText(questionsNumber);

        assertEquals(objMainPage.expectedAnswersArray[questionsNumber-1], answerText);

    }

}
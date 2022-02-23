package hh.request;

import hh.WebDriverSettings;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class TestRequest extends WebDriverSettings {

    /**
     * Открываем сайт hh.ru и вводим запрос "Android разработчик"
     * Проверить заголовки первых 5 вакансий. Заголовки содержат "Android разработчик" или "Android developer"
     */
    @Test
    public void testRequest() {
        Request request = PageFactory.initElements(driver, Request.class);
        request.open();
        request.requestIntroduction();

        CompareRequest compareRequest = PageFactory.initElements(driver, CompareRequest.class);
        int flag = compareRequest.getAndCompareRequest();

        if (flag == 1) {
            Assert.assertTrue("Первые 5 вакансий содержат в заголовке Android developer или Android разработчик", true);
        } else {
            Assert.assertTrue("Первые 5 вакансий не содержат в заголовке Android developer или Android разработчик", false);
        }


    }
}
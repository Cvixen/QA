package com.example.selenid;

import org.junit.Assert;
import org.junit.Test;

public class AppleTest extends SetUp {

    private static final String BASE_URL = "https://appleinsider.ru/";
    private static final String SEARCH_STRING = "Чем IPhone 13 отличается от iPhone 12";
    private static final String EXPECTED_WORD = "iphone-13";
    @Test
    public void checkHref(){
        Assert.assertTrue(new MainPage(BASE_URL).
                search(SEARCH_STRING).
                getHrefFromFirstArticle().
                contains(EXPECTED_WORD));
    }
}

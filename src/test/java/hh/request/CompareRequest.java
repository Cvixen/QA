package hh.request;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CompareRequest {
    private WebDriver driver;
    private List<String> findJobNamedOne;
    private List<String> findJobNamedTwo;
    private List<String> stringsSplit;

    public CompareRequest(WebDriver driver) {
        this.driver = driver;
    }

    private By jobTitles = By.xpath("//a[@data-qa='vacancy-serp__vacancy-title']");

    private void createComparableRequest() {
        findJobNamedOne = new ArrayList<>();
        findJobNamedTwo = new ArrayList<>();
        String[] addString = {"android", "разработчик", "developer"};
        findJobNamedOne.add(addString[0]);
        findJobNamedOne.add(addString[1]);
        findJobNamedTwo.add(addString[0]);
        findJobNamedTwo.add(addString[2]);
    }
    public int getAndCompareRequest() {
        int flag = 1;
        WebElement job;
        List<WebElement> jobs = driver.findElements(jobTitles);
        createComparableRequest();

        for (int i = 0; i < 5; i++) {
            job = jobs.get(i);
            stringsSplit = List.of(job.getText().toLowerCase(Locale.ROOT).split(" "));
            if (!stringsSplit.containsAll(findJobNamedOne) || !stringsSplit.containsAll(findJobNamedTwo)) {
                flag = 0;
            }

        }
        return flag;
    }
}

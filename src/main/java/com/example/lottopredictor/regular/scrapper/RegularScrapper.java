package com.example.lottopredictor.regular.scrapper;

import com.example.lottopredictor.regular.model.Regular;

import com.example.lottopredictor.regular.service.RegularService;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RegularScrapper {

    private final RegularService regularService;
    private static final String url = "https://danskespil.dk/lotto/statistik/favorittal";

    public RegularScrapper(RegularService regularService) {
        this.regularService = regularService;
    }

    public List<Regular> fetchRegularData() {
        ChromeOptions opts = new ChromeOptions();
        // headless + lidt “realistiske” flag
        opts.addArguments("--headless=new", "--no-sandbox", "--disable-gpu",
                "--window-size=1280,1600", "--disable-dev-shm-usage");
        // (valgfrit) UA:
        // opts.addArguments("--user-agent=Mozilla/5.0 ...");

        WebDriver driver = new ChromeDriver(opts); // Selenium Manager henter driveren

        try {
            driver.get(url);

            // Vent til kortene er rendret i DOM’en
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            List<WebElement> cards = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.cssSelector("div[data-number]"))
            );

            List<Regular> out = new ArrayList<>();
            for (WebElement card : cards) {
                // tallet (i “kuglen”) – vælg en robust locator
                String numberTxt = "";
                try {
                    // prøv nogle almindelige class’er
                    numberTxt = card.findElement(By.cssSelector(".text-2xl, .font-semibold"))
                            .getText().trim();
                } catch (NoSuchElementException e) {
                    // fallback: første tal i cardets tekst
                    numberTxt = card.getText().replaceAll(".*?(\\d+).*", "$1");
                }

                // “Trukket X gange” (rens kun tal ud)
                String countTxt;
                try {
                    countTxt = card.findElement(By.cssSelector(".font-bold"))
                            .getText();
                } catch (NoSuchElementException e) {
                    countTxt = card.getText();
                }
                countTxt = countTxt.replaceAll("\\D+", "").trim();

                if (!numberTxt.isEmpty() && !countTxt.isEmpty()) {
                    int number = Integer.parseInt(numberTxt);
                    int count  = Integer.parseInt(countTxt);
                    Regular r = new Regular(number, count);
                    regularService.save(r);
                    out.add(r);
                }
            }

            out.sort(Comparator.comparing(Regular::getNumber));
            return out;

        } finally {
            driver.quit();
        }
    }
}

LottoPredictor (learning Selenium)

This project is a small Spring Boot app built to learn and experiment with Selenium by scraping public “favorite numbers” statistics from Danske Spil’s Lotto/Vikinglotto pages.
It’s not meant for production; the goal is to practice headless browser automation, waits, and robust locators.

⚠️ Educational use only. Website structure may change without notice, and scraping can break.

What it does

Starts a Spring Boot REST API.

Uses Selenium (headless Chrome) to render JavaScript-heavy pages.

Clicks UI elements (tabs/buttons), waits for DOM state changes, and extracts data.

Persists results via Spring Data JPA (H2/MySQL).

Exposes endpoints to fetch/scrape and to read stored data.

Tech stack

Java 21

Spring Boot 3.5.x (Web, Data JPA)

Selenium 4.x (using Selenium Manager – no WebDriverManager needed)

Jsoup (optional, for parsing rendered HTML if desired)

H2/MySQL (choose one via config)

Requirements

Java 21

Maven

Google Chrome or Chromium installed (Selenium Manager downloads a matching driver automatically)

Internet access

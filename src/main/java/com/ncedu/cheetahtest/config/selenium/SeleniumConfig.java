package com.ncedu.cheetahtest.config.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SeleniumConfig {

    @Value("${webdriver.chrome.bin}")
    private String googleChromeBin;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public WebDriver getWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary(googleChromeBin);
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox");

        return new ChromeDriver(options);
    }
}

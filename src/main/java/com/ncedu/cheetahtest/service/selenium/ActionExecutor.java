package com.ncedu.cheetahtest.service.selenium;

import com.ncedu.cheetahtest.entity.selenium.ActionResult;
import com.ncedu.cheetahtest.entity.selenium.SeleniumAction;
import org.openqa.selenium.WebDriver;

@FunctionalInterface
public interface ActionExecutor {
    ActionResult execute(SeleniumAction action, WebDriver webDriver);
}

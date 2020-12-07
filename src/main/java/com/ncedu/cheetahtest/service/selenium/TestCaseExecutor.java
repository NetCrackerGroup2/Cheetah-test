package com.ncedu.cheetahtest.service.selenium;

import com.ncedu.cheetahtest.entity.selenium.ActionResult;
import com.ncedu.cheetahtest.entity.selenium.SeleniumAction;

public interface TestCaseExecutor {
    ActionResult executeAction(SeleniumAction action);
    void close();
}

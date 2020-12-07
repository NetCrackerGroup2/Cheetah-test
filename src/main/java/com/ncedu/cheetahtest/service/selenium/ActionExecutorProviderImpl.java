package com.ncedu.cheetahtest.service.selenium;

import com.ncedu.cheetahtest.entity.selenium.ActionResult;
import com.ncedu.cheetahtest.entity.selenium.ActionResultStatus;
import com.ncedu.cheetahtest.entity.selenium.SeleniumAction;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActionExecutorProviderImpl implements ActionExecutorProvider {

    private Map<String, ActionExecutor> actionMap;

    public ActionExecutorProviderImpl() {
        actionMap = new HashMap<>();

        actionMap.put("getPage", this::getPage);
        actionMap.put("checkTitle", this::checkTitle);
        actionMap.put("checkUrl", this::checkUrl);
        actionMap.put("goBack", this::goBack);
        actionMap.put("goForward", this::goForward);

        actionMap.put("hoverElementById", this::hoverElementById);
        actionMap.put("hoverElementByClassName", this::hoverElementByClassName);
        actionMap.put("hoverElementByCssSelector", this::hoverElementByCssSelector);

        actionMap.put("checkElementPresenceById", this::checkElementPresenceById);
        actionMap.put("checkElementPresenceClassName", this::checkElementPresenceClassName);
        actionMap.put("checkElementPresenceByCssSelector", this::checkElementPresenceByCssSelector);

        actionMap.put("clickElementById", this::clickElementById);
        actionMap.put("clickElementByClassName", this::clickElementByClassName);
        actionMap.put("clickElementByCssSelector", this::clickElementByCssSelector);

        actionMap.put("fillById", this::fillById);
        actionMap.put("fillByClassName", this::fillByClassName);
        actionMap.put("fillByCssSelector", this::fillByCssSelector);

        actionMap.put("checkElementTextById", this::checkElementTextById);
        actionMap.put("checkElementTextClassName", this::checkElementTextClassName);
        actionMap.put("checkElementTextCssSelector", this::checkElementTextCssSelector);

        actionMap.put("pressEnter", this::pressEnter);
    }

    @Override
    public ActionExecutor getActionExecutor(String actionType) {
        return actionMap.get(actionType);
    }

    private ActionResult getPage(SeleniumAction action, WebDriver webDriver) {

        if (action.getArgument() == null) {
            return getFailResult(action, "Missing argument.");
        }

        try {
            webDriver.get(action.getArgument());
        } catch (WebDriverException e) {
            return getFailResult(action, "This ULR can’t be reached.");
        }

        return getSuccessResult(action, "Action was successfully executed.");
    }

    private ActionResult checkTitle(SeleniumAction action, WebDriver webDriver) {

        if (action.getArgument() == null) {
            return getFailResult(action, "Missing argument.");
        }

        if (webDriver.getTitle().equals(action.getArgument())) {
            return getSuccessResult(action, "The titles match.");
        } else {
            return getFailResult(action, "The titles do not match.");
        }
    }

    private ActionResult checkUrl(SeleniumAction action, WebDriver webDriver) {
        if (action.getArgument() == null) {
            return getFailResult(action, "Missing argument.");
        }

        if (webDriver.getCurrentUrl().equals(action.getArgument())) {
            return getSuccessResult(action, "The titles match.");
        } else {
            return getFailResult(action, "The titles do not match.");
        }
    }

    private ActionResult goBack(SeleniumAction action, WebDriver webDriver) {

        try {
            webDriver.navigate().back();
        } catch (WebDriverException e) {
            return getFailResult(action, "It is not possible to go back");
        }

        return getSuccessResult(action, "The action was successfully executed");
    }

    private ActionResult goForward(SeleniumAction action, WebDriver webDriver) {
        try {
            webDriver.navigate().forward();
        } catch (WebDriverException e) {
            return getFailResult(action, "It is not possible to go forward");
        }

        return getSuccessResult(action, "The action was successfully executed");
    }

    private ActionResult hoverElementById(SeleniumAction action, WebDriver webDriver) {

        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        try {
            WebElement webElement = webDriver.findElement(By.id(action.getElement()));

            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).build().perform();

            return getSuccessResult(action, "Action was successfully executed.");
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult hoverElementByClassName(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        try {
            WebElement webElement = webDriver.findElement(By.className(action.getElement()));

            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).build().perform();

            return getSuccessResult(action, "Action was successfully executed.");
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult hoverElementByCssSelector(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        try {
            WebElement webElement = webDriver.findElement(By.cssSelector(action.getElement()));

            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).build().perform();

            return getSuccessResult(action, "Action was successfully executed.");
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult checkElementPresenceById(SeleniumAction action, WebDriver webDriver) {

        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        try {
            webDriver.findElement(By.id(action.getElement()));

            return getSuccessResult(action, "Action was successfully executed.");
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult checkElementPresenceClassName(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        try {
            webDriver.findElement(By.className(action.getElement()));

            return getSuccessResult(action, "Action was successfully executed.");
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult checkElementPresenceByCssSelector(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        try {
            webDriver.findElement(By.cssSelector(action.getElement()));

            return getSuccessResult(action, "Action was successfully executed.");
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult clickElementById(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        try {
            WebElement webElement = webDriver.findElement(By.id(action.getElement()));

            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).click().build().perform();

            return getSuccessResult(action, "Action was successfully executed.");
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult clickElementByClassName(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        try {
            WebElement webElement = webDriver.findElement(By.className(action.getElement()));

            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).click().build().perform();

            return getSuccessResult(action, "Action was successfully executed.");
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult clickElementByCssSelector(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        try {
            WebElement webElement = webDriver.findElement(By.cssSelector(action.getElement()));

            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).click().build().perform();

            return getSuccessResult(action, "Action was successfully executed.");
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult fillById(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        if (action.getArgument() == null) {
            return getFailResult(action, "Missing argument.");
        }

        try {
            WebElement webElement = webDriver.findElement(By.id(action.getElement()));
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).sendKeys(action.getArgument()).build().perform();

            return getSuccessResult(action, "Action was successfully executed.");
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult fillByClassName(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        if (action.getArgument() == null) {
            return getFailResult(action, "Missing argument.");
        }

        try {
            WebElement webElement = webDriver.findElement(By.className(action.getElement()));
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).sendKeys(action.getArgument()).build().perform();

            return getSuccessResult(action, "Action was successfully executed.");
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult fillByCssSelector(SeleniumAction action, WebDriver webDriver) {

        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        if (action.getArgument() == null) {
            return getFailResult(action, "Missing argument.");
        }

        try {
            WebElement webElement = webDriver.findElement(By.cssSelector(action.getElement()));
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).sendKeys(action.getArgument()).build().perform();

            return getSuccessResult(action, "Action was successfully executed.");
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult checkElementTextById(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        if (action.getArgument() == null) {
            return getFailResult(action, "Missing argument.");
        }

        try {
            WebElement webElement = webDriver.findElement(By.id(action.getElement()));

            if (webElement.getText().equals(action.getArgument())) {
                return getSuccessResult(action, "Text of the element matches the argument value");
            } else {
                return getFailResult(action, "Text of the element doesn't match the argument value");
            }
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult checkElementTextClassName(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        if (action.getArgument() == null) {
            return getFailResult(action, "Missing argument.");
        }

        try {
            WebElement webElement = webDriver.findElement(By.className(action.getElement()));

            if (webElement.getText().equals(action.getArgument())) {
                return getSuccessResult(action, "Text of the element matches the argument value");
            } else {
                return getFailResult(action, "Text of the element doesn't match the argument value");
            }
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }
    }

    private ActionResult checkElementTextCssSelector(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, "Missing element identifier.");
        }

        if (action.getArgument() == null) {
            return getFailResult(action, "Missing argument.");
        }

        try {
            WebElement webElement = webDriver.findElement(By.cssSelector(action.getElement()));

            if (webElement.getText().equals(action.getArgument())) {
                return getSuccessResult(action, "Text of the element matches the argument value");
            } else {
                return getFailResult(action, "Text of the element doesn't match the argument value");
            }
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed. Can't find such element.");
        }      }

    private ActionResult pressEnter(SeleniumAction action, WebDriver webDriver) {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(Keys.ENTER).build().perform();

            return getSuccessResult(action, "Action was successfully executed.");
        } catch (WebDriverException e) {
            return getFailResult(action, "Action execution failed.");
        }
    }

    private ActionResult getFailResult(SeleniumAction action, String message) {
        ActionResult actionResult = new ActionResult();

        actionResult.setAction(action);
        actionResult.setStatus(ActionResultStatus.FAIL);
        actionResult.setResultDescription(message);

        return actionResult;
    }

    private ActionResult getSuccessResult(SeleniumAction action, String message) {
        ActionResult actionResult = new ActionResult();

        actionResult.setAction(action);
        actionResult.setStatus(ActionResultStatus.SUCCESS);
        actionResult.setResultDescription(message);

        return actionResult;
    }
}
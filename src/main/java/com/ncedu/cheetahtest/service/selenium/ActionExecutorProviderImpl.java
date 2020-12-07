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

    private final Map<String, ActionExecutor> actionMap;

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
            return getFailResult(action, ResultMessagesConstants.MISSING_ARGUMENT);
        }

        try {
            webDriver.get(action.getArgument());
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.URL_CANNOT_BE_REACHED);
        }

        return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
    }

    private ActionResult checkTitle(SeleniumAction action, WebDriver webDriver) {

        if (action.getArgument() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ARGUMENT);
        }

        if (webDriver.getTitle().equals(action.getArgument())) {
            return getSuccessResult(action, "The titles match.");
        } else {
            return getFailResult(action, "The titles do not match.");
        }
    }

    private ActionResult checkUrl(SeleniumAction action, WebDriver webDriver) {
        if (action.getArgument() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ARGUMENT);
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
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        try {
            WebElement webElement = webDriver.findElement(By.id(action.getElement()));

            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).build().perform();

            return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult hoverElementByClassName(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        try {
            WebElement webElement = webDriver.findElement(By.className(action.getElement()));

            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).build().perform();

            return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult hoverElementByCssSelector(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        try {
            WebElement webElement = webDriver.findElement(By.cssSelector(action.getElement()));

            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).build().perform();

            return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult checkElementPresenceById(SeleniumAction action, WebDriver webDriver) {

        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        try {
            webDriver.findElement(By.id(action.getElement()));

            return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult checkElementPresenceClassName(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        try {
            webDriver.findElement(By.className(action.getElement()));

            return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult checkElementPresenceByCssSelector(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        try {
            webDriver.findElement(By.cssSelector(action.getElement()));

            return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult clickElementById(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        try {
            WebElement webElement = webDriver.findElement(By.id(action.getElement()));

            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).click().build().perform();

            return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult clickElementByClassName(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        try {
            WebElement webElement = webDriver.findElement(By.className(action.getElement()));

            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).click().build().perform();

            return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult clickElementByCssSelector(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        try {
            WebElement webElement = webDriver.findElement(By.cssSelector(action.getElement()));

            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).click().build().perform();

            return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult fillById(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        if (action.getArgument() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ARGUMENT);
        }

        try {
            WebElement webElement = webDriver.findElement(By.id(action.getElement()));
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).sendKeys(action.getArgument()).build().perform();

            return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult fillByClassName(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        if (action.getArgument() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ARGUMENT);
        }

        try {
            WebElement webElement = webDriver.findElement(By.className(action.getElement()));
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).sendKeys(action.getArgument()).build().perform();

            return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult fillByCssSelector(SeleniumAction action, WebDriver webDriver) {

        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        if (action.getArgument() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ARGUMENT);
        }

        try {
            WebElement webElement = webDriver.findElement(By.cssSelector(action.getElement()));
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).sendKeys(action.getArgument()).build().perform();

            return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult checkElementTextById(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        if (action.getArgument() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ARGUMENT);
        }

        try {
            WebElement webElement = webDriver.findElement(By.id(action.getElement()));

            if (webElement.getText().equals(action.getArgument())) {
                return getSuccessResult(action, ResultMessagesConstants.TEXT_MATCHES);
            } else {
                return getFailResult(action, ResultMessagesConstants.TEXT_DOES_NOT_MATCH);
            }
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult checkElementTextClassName(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        if (action.getArgument() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ARGUMENT);
        }

        try {
            WebElement webElement = webDriver.findElement(By.className(action.getElement()));

            if (webElement.getText().equals(action.getArgument())) {
                return getSuccessResult(action, ResultMessagesConstants.TEXT_MATCHES);
            } else {
                return getFailResult(action, ResultMessagesConstants.TEXT_DOES_NOT_MATCH);
            }
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }
    }

    private ActionResult checkElementTextCssSelector(SeleniumAction action, WebDriver webDriver) {
        if (action.getElement() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ELEMENT_IDENTIFIER);
        }

        if (action.getArgument() == null) {
            return getFailResult(action, ResultMessagesConstants.MISSING_ARGUMENT);
        }

        try {
            WebElement webElement = webDriver.findElement(By.cssSelector(action.getElement()));

            if (webElement.getText().equals(action.getArgument())) {
                return getSuccessResult(action, ResultMessagesConstants.TEXT_MATCHES);
            } else {
                return getFailResult(action, ResultMessagesConstants.TEXT_DOES_NOT_MATCH);
            }
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.CANNOT_FIND_SUCH_ELEMENT);
        }      }

    private ActionResult pressEnter(SeleniumAction action, WebDriver webDriver) {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(Keys.ENTER).build().perform();

            return getSuccessResult(action, ResultMessagesConstants.SUCCESSFULLY_EXECUTED);
        } catch (WebDriverException e) {
            return getFailResult(action, ResultMessagesConstants.SIMPLE_FAIL);
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

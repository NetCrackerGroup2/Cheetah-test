package com.ncedu.cheetahtest.service.selenium;

public interface ActionExecutorProvider {
    ActionExecutor getActionExecutor(String actionType);
}

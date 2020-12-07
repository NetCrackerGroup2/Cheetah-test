package com.ncedu.cheetahtest.entity.selenium;

import lombok.Data;

@Data
public class ActionResult {
    private SeleniumAction action;

    private ActionResultStatus status;
    private String resultDescription;
    private String screenshotUrl;
}

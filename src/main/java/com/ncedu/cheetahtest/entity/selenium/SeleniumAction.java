package com.ncedu.cheetahtest.entity.selenium;

import lombok.Data;

@Data
public class SeleniumAction {
    private Integer actionId;
    private Integer compoundId;
    private String actionType;
    private String element;
    private String argument;
}

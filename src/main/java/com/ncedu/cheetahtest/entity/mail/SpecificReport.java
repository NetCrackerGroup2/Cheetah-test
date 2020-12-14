package com.ncedu.cheetahtest.entity.mail;

import lombok.Data;

import java.util.List;

@Data
public class SpecificReport {

    private List<String> emails;
    private List<Integer> sendSelectTestCaseId;
    private Boolean sendTestCaseAllDone;
    private Boolean sendTestCaseUse;
    private Boolean sendProjectUse;
}

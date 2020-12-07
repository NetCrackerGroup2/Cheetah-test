package com.ncedu.cheetahtest.entity.testcase;

import lombok.Data;

@Data
public class TestCase {
    private int id;
    private int projectId;
    private String title;
    private TestCaseStatus status;
    private TestCaseResult result;
}

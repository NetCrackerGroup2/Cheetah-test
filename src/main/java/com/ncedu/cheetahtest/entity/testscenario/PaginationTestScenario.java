package com.ncedu.cheetahtest.entity.testscenario;

import lombok.Data;

import java.util.List;

@Data
public class PaginationTestScenario {
    private List<TestScenario> testScenarios;
    private int totalElements;
}

package com.ncedu.cheetahtest.entity.testscenario;

import lombok.Data;

@Data
public class TestScenario {
    private int id;
    private String title;
    private String description;
    private StatusTestScenario status;
    private int idTestCase;
}

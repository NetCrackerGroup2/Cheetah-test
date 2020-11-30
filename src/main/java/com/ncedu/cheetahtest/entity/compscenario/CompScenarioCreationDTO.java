package com.ncedu.cheetahtest.entity.compscenario;

import lombok.Data;

import java.util.List;

@Data
public class CompScenarioCreationDTO {
    private CompScenario compScenario;
    private List<Integer> idParams;
}

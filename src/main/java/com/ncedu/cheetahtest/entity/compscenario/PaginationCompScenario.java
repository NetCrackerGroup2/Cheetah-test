package com.ncedu.cheetahtest.entity.compscenario;

import lombok.Data;

import java.util.List;

@Data
public class PaginationCompScenario {
    private List<CompScenario> compScenarios;
    private int totalElements;
}

package com.ncedu.cheetahtest.entity.actscenario;

import lombok.Data;

import java.util.List;
@Data
public class PaginationActScenario {
    List<ActScenario> actScenarios;
    int totalElements;
}

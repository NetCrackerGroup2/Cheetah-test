package com.ncedu.cheetahtest.entity.parameter;

import lombok.Data;

import java.util.List;

@Data
public class PaginationParameter {
    private int totalParameters;
    private List<Parameter> parameters;

}

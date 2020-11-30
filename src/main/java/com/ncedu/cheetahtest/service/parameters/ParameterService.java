package com.ncedu.cheetahtest.service.parameters;

import com.ncedu.cheetahtest.entity.parameter.PaginationParameter;
import com.ncedu.cheetahtest.entity.parameter.Parameter;

public interface ParameterService {
    PaginationParameter findByType(String type,int idDataSet,int page,int size);
    Parameter createParameter(Parameter parameter);
    Parameter editParameter(Parameter parameter,int id);
    void deleteParameter(int id);
}

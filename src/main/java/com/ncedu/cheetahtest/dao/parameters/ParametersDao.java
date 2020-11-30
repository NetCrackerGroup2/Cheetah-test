package com.ncedu.cheetahtest.dao.parameters;

import com.ncedu.cheetahtest.entity.parameter.Parameter;

import java.util.List;

public interface ParametersDao {
    Parameter findById(int id);
    List<Parameter> findByTypeLike(String type , int idDataSet, int limit, int offset);
    Parameter createParameter(Parameter parameter);
    Parameter editParameter(Parameter parameter, int id);
    void deleteParameter(int id);
    void deleteByIdDataSet(int idDataSet);
    int getTotalElements(int idDataSet,String type);
    Parameter findByTypeAndIdDataSet(String type,int idDataSet);

}

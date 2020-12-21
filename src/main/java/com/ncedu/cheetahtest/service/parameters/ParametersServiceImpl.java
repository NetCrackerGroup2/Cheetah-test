package com.ncedu.cheetahtest.service.parameters;

import com.ncedu.cheetahtest.dao.parameters.ParametersDao;
import com.ncedu.cheetahtest.entity.parameter.PaginationParameter;
import com.ncedu.cheetahtest.entity.parameter.Parameter;
import com.ncedu.cheetahtest.exception.helpers.EntityAlreadyExistException;
import com.ncedu.cheetahtest.exception.helpers.ForeignKeyConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParametersServiceImpl implements ParameterService {
    private final ParametersDao parametersDao;

    @Autowired
    public ParametersServiceImpl(ParametersDao parametersDao) {
        this.parametersDao = parametersDao;
    }

    @Override
    public PaginationParameter findByType(String type, int idDataSet, int page, int size) {
        int totalParameters = parametersDao.getTotalElements(idDataSet, type);
        PaginationParameter paginationParameter = new PaginationParameter();
        paginationParameter.setTotalParameters(totalParameters);
        if (size * (page - 1) < totalParameters) {
            paginationParameter.setParameters(
                    parametersDao.findByTypeLike(type, idDataSet, size, size * (page - 1))
            );
        }
        return paginationParameter;
    }

    @Override
    public PaginationParameter findAllByType(String type, int page, int size) {
        int totalParameters = parametersDao.getTotalAllElements(type);
        PaginationParameter paginationParameter = new PaginationParameter();
        paginationParameter.setTotalParameters(totalParameters);
        if (size * (page - 1) < totalParameters) {
            paginationParameter.setParameters(
                    parametersDao.findAllByType(type, size, size * (page - 1))
            );
        }
        return paginationParameter;
    }

    @Override
    public List<Parameter> findAllByValue(String value, int idTestCase) {
        return parametersDao.findAllByValue(value, idTestCase);
    }

    @Override
    public List<Parameter> findAllByIdDataSet(int idDataSet) {
        return parametersDao.findAllByIdDataSet(idDataSet);
    }

    @Override
    public List<Parameter> findAllByIdTestCase(int idTestCase) {
        return parametersDao.findAllByIdTestCase(idTestCase);
    }

    @Override
    public Parameter createParameter(Parameter parameter) {

        try {
            return parametersDao.createParameter(parameter);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityAlreadyExistException();
        }

    }

    @Override
    public Parameter editParameter(Parameter parameter, int id) {
        return parametersDao.editParameter(parameter, id);
    }

    @Override
    public void deleteParameter(int id) {
        try {
            parametersDao.deleteParameter(id);
        }catch (DataIntegrityViolationException e){
            throw new ForeignKeyConstraintViolation();
        }

    }
}

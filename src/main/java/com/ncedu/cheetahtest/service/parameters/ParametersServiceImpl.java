package com.ncedu.cheetahtest.service.parameters;

import com.ncedu.cheetahtest.dao.parameters.ParametersDao;
import com.ncedu.cheetahtest.entity.parameter.PaginationParameter;
import com.ncedu.cheetahtest.entity.parameter.Parameter;
import com.ncedu.cheetahtest.exception.helpers.EntityAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
        parametersDao.deleteParameter(id);
    }
}

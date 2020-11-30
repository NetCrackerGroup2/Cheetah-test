package com.ncedu.cheetahtest.service.actscenario;


import com.ncedu.cheetahtest.dao.actscenario.ActScenarioDao;
import com.ncedu.cheetahtest.entity.actscenario.ActScenario;
import com.ncedu.cheetahtest.entity.actscenario.ActStatus;
import com.ncedu.cheetahtest.entity.actscenario.PaginationActScenario;
import com.ncedu.cheetahtest.exception.helpers.EntityAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActScenarioServiceImpl implements ActScenarioService {
    private final ActScenarioDao actScenarioDao;

    @Autowired
    public ActScenarioServiceImpl(ActScenarioDao actScenarioDao) {
        this.actScenarioDao = actScenarioDao;
    }

    @Override
    public ActScenario createActScenario(ActScenario actScenario) {
        try {
            return actScenarioDao.createActScenario(actScenario);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityAlreadyExistException();
        }

    }

    @Override
    public ActScenario editActScenario(ActScenario actScenario, int id) {
        return actScenarioDao.editActScenario(actScenario, id);
    }

    @Override
    public PaginationActScenario findByTitleLike(String title, int size, int page) {
        PaginationActScenario paginationActScenario = new PaginationActScenario();
        int totalElements = actScenarioDao.totalFindByTitleLike(title);
        paginationActScenario.setTotalElements(totalElements);
        if (size * (page - 1) < totalElements) {
            paginationActScenario.setActScenarios(actScenarioDao.findByTitleLike(title, size, size * (page - 1)));
        }
        return paginationActScenario;
    }

    @Override
    public PaginationActScenario findByTitleInTestScenario(String title, int idTestScenario, int size, int page) {
        PaginationActScenario paginationActScenario = new PaginationActScenario();
        int totalElements = actScenarioDao.totalFindByTitleInTestScenario(title,idTestScenario);
        paginationActScenario.setTotalElements(totalElements);
        if (size * (page - 1) < totalElements) {
            paginationActScenario.setActScenarios(actScenarioDao.findByTitleInTestScenario(title,idTestScenario, size, size * (page - 1)));
        }
        return paginationActScenario;
    }

    @Override
    public List<ActScenario> findAllByTitleLike(String title) {
        return actScenarioDao.findAllByTitleLike(title);
    }

    @Override
    public List<ActScenario> findAllByTitleInTestScenario(String title, int idTestScenario) {
        return actScenarioDao.findAllByTitleInTestScenario(title, idTestScenario);
    }

    @Override
    public void deleteActScenario(int id) {
        actScenarioDao.deleteActScenario(id);
    }

    @Override
    public ActScenario setStatus(ActStatus actStatus, int id) {
        return actScenarioDao.setStatus(actStatus,id);
    }

    @Override
    public void deleteAllByIdTestScenario(int idTestScenario) {
        actScenarioDao.deleteAllByIdTestScenario(idTestScenario);
    }
}

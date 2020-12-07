package com.ncedu.cheetahtest.service.action;

import com.ncedu.cheetahtest.dao.action.ActionDao;
import com.ncedu.cheetahtest.entity.action.Action;
import com.ncedu.cheetahtest.entity.action.PaginationAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {
    private final ActionDao actionDao;

    @Autowired
    public ActionServiceImpl(ActionDao actionDao) {
        this.actionDao = actionDao;
    }


    @Override
    public PaginationAction getActionsByTitle(String title, int size, int page) {
        int totalElements = actionDao.getTotalElements(title);
        PaginationAction paginationAction = new PaginationAction();
        paginationAction.setTotalElements(totalElements);
        if (size * (page - 1) < totalElements) {
            paginationAction.setList(actionDao.selectActionsByTitleLike(title, size, size * (page - 1)));
        }
        return paginationAction;

    }

    @Override
    public PaginationAction getActionsInCompound(int idCompound, int size, int page) {
        int totalActions = actionDao.getTotalActionsInComp(idCompound);
        PaginationAction paginationAction = new PaginationAction();
        paginationAction.setTotalElements(totalActions);
        if (size * (page - 1) < totalActions) {
            paginationAction.setList(actionDao.getActionsInCompound(idCompound, size, size * (page - 1)));
        }
        return paginationAction;
    }


    @Override
    public Action editActionDescription(String description, int id) {
        return actionDao.editActionDesc(description, id);

    }

    @Override
    public List<Action> getAllByTitleLike(String title) {
        return actionDao.selectAllActionsByTitleLike(title);
    }

    @Override
    public PaginationAction geActionsByType(String type, int size, int page) {
        int totalElements = actionDao.getTotalActionsByType(type);
        PaginationAction paginationAction = new PaginationAction();
        paginationAction.setTotalElements(totalElements);
        if (size * (page - 1) < totalElements) {
            paginationAction.setList(actionDao.getActionsByType(type, size, size * (page - 1)));
        }
        return paginationAction;
    }

    @Override
    @Transactional
    public Action getActionById(int id) {
        return actionDao.getActionById(id);
    }

    @Override
    @Transactional
    public List<Action> getActionsInCompoundNotPaginated(int idCompound) {
        return actionDao.getActionsInCompoundNotPaginated(idCompound);
    }
}

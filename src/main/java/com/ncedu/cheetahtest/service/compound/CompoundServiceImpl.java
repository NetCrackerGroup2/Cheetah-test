package com.ncedu.cheetahtest.service.compound;

import com.ncedu.cheetahtest.dao.compactprior.CompActPriorDao;
import com.ncedu.cheetahtest.dao.compound.CompoundDao;
import com.ncedu.cheetahtest.entity.action.Action;
import com.ncedu.cheetahtest.entity.compactprior.CompActPrior;
import com.ncedu.cheetahtest.entity.compound.Compound;
import com.ncedu.cheetahtest.entity.compound.PaginationCompound;
import com.ncedu.cheetahtest.exception.helpers.EntityAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompoundServiceImpl implements CompoundService {
    private final CompoundDao compoundDao;
    private final CompActPriorDao compActPriorDao;


    @Autowired
    public CompoundServiceImpl(CompoundDao compoundDao, CompActPriorDao compActPriorDao) {
        this.compoundDao = compoundDao;
        this.compActPriorDao = compActPriorDao;
    }

    @Override
    public Compound createCompound(Compound compound, List<Action> actions) {
        Compound createdComp;
        try {
            createdComp = compoundDao.createCompound(compound);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityAlreadyExistException();
        }
        CompActPrior compActPrior = new CompActPrior();
        int priority = 1;
        for (Action action : actions) {
            compActPrior.setCompId(createdComp.getId());
            compActPrior.setActId(action.getId());
            compActPrior.setPriority(priority);
            compActPriorDao.createCompActPrior(compActPrior);
            priority++;
        }
        return createdComp;

    }

    private void createPriorityInActions(List<Action> actions, Compound compound) {
        int priority = 1;
        CompActPrior compActPrior = new CompActPrior();
        for (Action action : actions) {
            compActPrior.setCompId(compound.getId());
            compActPrior.setActId(action.getId());
            compActPrior.setPriority(priority);
            compActPriorDao.createCompActPrior(compActPrior);
            priority++;
        }
    }

    @Override
    public Compound editCompound(Compound compound, int id, List<Action> actions) {
        Compound editedComp = compoundDao.editCompound(compound, id);
        compActPriorDao.deleteByIdCompound(editedComp.getId());
        createPriorityInActions(actions, editedComp);
        return editedComp;
    }

    @Override
    public PaginationCompound getCompoundsByTitleLike(String title, int size, int page) {
        PaginationCompound paginationCompound = new PaginationCompound();
        int totalElements = compoundDao.getTotalCompByTitle(title);
        paginationCompound.setTotalCompounds(totalElements);
        if (size * (page - 1) < totalElements) {
            paginationCompound.setCompounds(compoundDao.selectCompoundsByTitleLike(title, size, size * (page - 1)));
        }
        return paginationCompound;
    }


    @Override
    public Compound editCompoundProperties(Compound compound, int id) {
        return compoundDao.editCompound(compound, id);
    }


    @Override
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public void deleteCompound(int idCompound) {
        compActPriorDao.deleteByIdCompound(idCompound);
        compoundDao.removeCompoundById(idCompound);

    }


}

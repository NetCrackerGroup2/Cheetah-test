package com.ncedu.cheetahtest.service.copactprior;

import com.ncedu.cheetahtest.dao.compactprior.CompActPriorDao;
import com.ncedu.cheetahtest.entity.compactprior.CompActPrior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompActPriorServiceImpl implements CompActPriorService{
    private final CompActPriorDao compActPriorDao;
    @Autowired

    public CompActPriorServiceImpl(CompActPriorDao compActPriorDao) {
        this.compActPriorDao = compActPriorDao;
    }

    @Override
    public void createCompActPrior(CompActPrior compActPrior) {
        compActPriorDao.createCompActPrior(compActPrior);
    }

    @Override
    public void deleteByIdCompound(int idCompound) {
        compActPriorDao.deleteByIdCompound(idCompound);

    }

    @Override
    public void deleteById(int id) {
        compActPriorDao.deleteById(id);
    }
}

package com.ncedu.cheetahtest.dao.compactprior;


import com.ncedu.cheetahtest.entity.compactprior.CompActPrior;

public interface CompActPriorDao {
    void createCompActPrior(CompActPrior compActPrior);
    void deleteByIdCompound(int idCompound);
    void deleteById(int id);


}

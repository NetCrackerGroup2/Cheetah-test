package com.ncedu.cheetahtest.service.copactprior;

import com.ncedu.cheetahtest.entity.compactprior.CompActPrior;

public interface CompActPriorService {
    void createCompActPrior(CompActPrior compActPrior);

    void deleteByIdCompound(int idCompound);

    void deleteById(int id);

}

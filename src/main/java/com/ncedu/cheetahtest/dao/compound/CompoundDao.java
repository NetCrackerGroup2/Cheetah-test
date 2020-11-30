package com.ncedu.cheetahtest.dao.compound;

import com.ncedu.cheetahtest.entity.compound.Compound;

import java.util.List;

public interface CompoundDao {
    Compound createCompound(Compound compound);

    List<Compound> selectCompoundsByTitleLike(String title, int limit, int size);

    Compound editCompound(Compound compoundDTO, int id);

    Compound findByTitle(String title);

    void removeCompoundById(int id);

    int getTotalCompByTitle( String title);
}

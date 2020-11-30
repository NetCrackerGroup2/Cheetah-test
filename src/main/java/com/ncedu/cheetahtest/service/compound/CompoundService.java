package com.ncedu.cheetahtest.service.compound;

import com.ncedu.cheetahtest.entity.action.Action;
import com.ncedu.cheetahtest.entity.compound.Compound;
import com.ncedu.cheetahtest.entity.compound.PaginationCompound;

import java.util.List;

public interface CompoundService {

    Compound createCompound(Compound compound, List<Action> actions);
    PaginationCompound getCompoundsByTitleLike(String title,int size, int page);
    Compound editCompoundProperties(Compound compound, int id);
    void deleteCompound(int idCompound);
    Compound editCompound(Compound compound,int id, List<Action> actions);
}

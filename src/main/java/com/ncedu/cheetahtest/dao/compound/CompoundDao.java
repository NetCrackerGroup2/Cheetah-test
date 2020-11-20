package com.ncedu.cheetahtest.dao.compound;

import com.ncedu.cheetahtest.entity.compound.Compound;

import java.util.List;

public interface CompoundDao {
    void createCompound(Compound compound);

    Compound findCompoundById(int id);

    Compound findCompoundByTitle(String title);

    List<Compound> findCompoundByIdTestScenario(int idTestScenario);

    void setTitle(String title, int id);

    void setDescription(String description, int id);

    void setTestScenarioId(String testScenarioId, int id);

    void setStatus(String status, int id);

    void removeCompound(int id);
}

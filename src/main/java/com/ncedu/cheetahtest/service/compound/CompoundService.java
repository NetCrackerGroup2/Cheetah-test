package com.ncedu.cheetahtest.service.compound;

import com.ncedu.cheetahtest.entity.compound.Compound;
import com.ncedu.cheetahtest.entity.compound.DeleteCompoundDTO;

import java.util.List;

public interface CompoundService {

    void createCompound(int idLibrary, Compound compoundDTO);
    List<Compound> selectAllCompound();
    Compound getCompoundId(int id);
    List<Compound> getCompoundByTitle(int idLibrary, String title);
    void editCompound(Compound compoundDTO);
    void changeStatus(String status, int id);
    boolean isAdmin(String jwtToken);
    void deleteCompound(DeleteCompoundDTO deleteCompoundDTO);
}
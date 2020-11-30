package com.ncedu.cheetahtest.dao.compscenario;

import com.ncedu.cheetahtest.entity.actscenario.ActScenario;
import com.ncedu.cheetahtest.entity.actscenario.ActStatus;
import com.ncedu.cheetahtest.entity.compscenario.CompScenario;

import java.util.List;

public interface CompScenarioDao {
    CompScenario createCompScenario(CompScenario compScenario);
    CompScenario editCompScenario(CompScenario compScenario, int id);
    void deleteCompScenario(int id);
    List<CompScenario> findByTitleLike(String title, int limit, int offset);
    List<CompScenario> findByTitleInTestScenario(String title, int idTestScenario,int limit, int offset);
    List<CompScenario> findAllByTitleLike(String title);
    List<CompScenario> findAllByTitleInTestScenario(String title, int idTestScenario);
    int totalFindByTitleLike(String title);
    int totalFindByTitleInTestScenario(String title,int idTestScenario);
    void setStatusForAllByIdTestScenario(ActStatus actStatus, int idTestScenario);
    List<ActScenario> getAllActionScenarioInComp(int id);
    CompScenario findById(int id);
    CompScenario findBySignature(CompScenario compScenario);
    void deleteAllByIdTestScenario(int idTestScenario);
}

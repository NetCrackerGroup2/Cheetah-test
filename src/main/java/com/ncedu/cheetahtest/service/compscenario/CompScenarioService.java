package com.ncedu.cheetahtest.service.compscenario;

import com.ncedu.cheetahtest.entity.actscenario.ActScenario;
import com.ncedu.cheetahtest.entity.actscenario.ActStatus;
import com.ncedu.cheetahtest.entity.compscenario.CompScenario;
import com.ncedu.cheetahtest.entity.compscenario.CompScenarioCreationDTO;
import com.ncedu.cheetahtest.entity.compscenario.PaginationCompScenario;

import java.util.List;

public interface CompScenarioService {
    CompScenario createCompScenario(CompScenarioCreationDTO compScenarioCreationDTO);
    PaginationCompScenario findByTitleLike(String title, int size, int page);
    PaginationCompScenario findByTitleInTestScenario(String title, int idTestScenario,int size, int page);
    List<CompScenario> findAllByTitleLike(String title);
    List<CompScenario> findAllByTitleInTestScenario(String title, int idTestScenario);
    CompScenario editCompScenarioProps(CompScenario compScenario, int id);
    CompScenario editCompScenario(CompScenarioCreationDTO compScenarioCreationDTO, int id);
    void setStatusForAllByIdTestScenario(ActStatus actStatus, int idTestScenario);
    List<ActScenario> getAllActionScenarioInComp(int id);
    void deleteCompScenario(int id);
}

package com.ncedu.cheetahtest.service.actscenario;

import com.ncedu.cheetahtest.entity.actscenario.ActScenario;
import com.ncedu.cheetahtest.entity.actscenario.ActStatus;
import com.ncedu.cheetahtest.entity.actscenario.PaginationActScenario;

import java.util.List;

public interface ActScenarioService {
    ActScenario createActScenario(ActScenario actScenario);
    ActScenario editActScenario(ActScenario actScenario, int id);
    PaginationActScenario findByTitleLike(String title, int size, int page);
    PaginationActScenario findByTitleInTestScenario(String title, int idTestScenario,int size, int page);
    List<ActScenario> findAllByTitleLike(String title);
    List<ActScenario> findAllByTitleInTestScenario(String title, int idTestScenario);
    void deleteActScenario(int id);
    ActScenario setStatus(ActStatus actStatus, int id);
    void deleteAllByIdTestScenario(int idTestScenario);
}

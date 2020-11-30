package com.ncedu.cheetahtest.dao.actscenario;

import com.ncedu.cheetahtest.entity.actscenario.ActScenario;
import com.ncedu.cheetahtest.entity.actscenario.ActStatus;

import java.util.List;

public interface ActScenarioDao {
    ActScenario createActScenario(ActScenario actScenario);
    ActScenario editActScenario(ActScenario actScenario, int id);
    List<ActScenario> findByTitleLike(String title, int limit,int offset);
    List<ActScenario> findByTitleInTestScenario(String title, int idTestScenario,int limit, int offset);
    List<ActScenario> findAllByTitleLike(String title);
    List<ActScenario> findAllByTitleInTestScenario(String title, int idTestScenario);
    void deleteActScenario(int id);
    ActScenario setStatus(ActStatus actStatus, int id);
    ActScenario findById(int id);
    ActScenario findBySignature(ActScenario actScenario);
    int totalFindByTitleLike(String title);
    int totalFindByTitleInTestScenario(String title,int idTestScenario);
    void setStatusForAllByIdTestScenario(ActStatus actStatus, int idTestScenario);
    void deleteAllByIdTestScenario(int idTestScenario);
    void deleteAllByIdCompScenario(int idCompScenario);

}

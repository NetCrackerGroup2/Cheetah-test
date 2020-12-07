package com.ncedu.cheetahtest.service.testscenario;

import com.ncedu.cheetahtest.entity.testscenario.*;

public interface TestScenarioService {
    PaginationTestScenario findByTitle(String title, int idTestCase, int size, int page);

    TestScenario createTestScenario(TestScenario testScenario);

    PaginationItems getItemsFromScenario(int idTestScenario, int size, int page);

    PaginationTestScenario getAllTestScenarios(int size, int page);

    PaginationTestScenario getAllTestScenariosFromTestCase(int idTestCase, int size, int page);

    TestScenario editTestScenario(TestScenario testScenario, int id);

    void deactivateTestScenario(int id);

    TestScenario findById(int id);
}

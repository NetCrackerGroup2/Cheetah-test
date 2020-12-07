package com.ncedu.cheetahtest.controller.testscenario;

import com.ncedu.cheetahtest.entity.testscenario.*;
import com.ncedu.cheetahtest.service.testscenario.TestScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${frontend.ulr}")
@RequestMapping("/api/test-scenarios")
public class TestScenarioController {
    private final TestScenarioService testScenarioService;

    @Autowired
    public TestScenarioController(TestScenarioService testScenarioService) {
        this.testScenarioService = testScenarioService;
    }

    @PostMapping
    public TestScenario createTestScenario(@RequestBody TestScenario testScenario) {
        return testScenarioService.createTestScenario(testScenario);
    }

    @GetMapping
    public PaginationTestScenario findTestScenarioByTitle(
            @RequestParam("title") String title,
            @RequestParam("size") int size,
            @RequestParam("page") int page,
            @RequestParam("idTestCase") int idTestCase) {
        return testScenarioService.findByTitle(title, idTestCase, size, page);
    }

    @GetMapping("/{id}")
    public PaginationItems getItemsFromScenario(
                                                 @RequestParam("size") int size,
                                                 @RequestParam("page") int page,
                                                 @PathVariable("id") int idTestScenario) {
        return testScenarioService.getItemsFromScenario(idTestScenario, size, page);
    }

    @GetMapping("/{id}/all")
    public PaginationTestScenario getAllTestScenariosFromTestCase(
            @RequestParam("size") int size,
            @RequestParam("page") int page,
            @PathVariable("id") int idTestCase) {
        return testScenarioService.getAllTestScenariosFromTestCase(idTestCase, size, page);
    }

    @PutMapping
    public TestScenario editTestScenario(@RequestBody TestScenario testScenario,
                                         @RequestParam("id") int id) {
        return testScenarioService.editTestScenario(testScenario, id);
    }

    @GetMapping("/all")
    public PaginationTestScenario getAllTestScenarios(
            @RequestParam("size") int size,
            @RequestParam("page") int page) {
        return testScenarioService.getAllTestScenarios(size, page);
    }

    @DeleteMapping
    public ResponseTestScenario deleteTestScenario(@RequestParam("id") int id) {
        testScenarioService.deactivateTestScenario(id);
        return new ResponseTestScenario("Success");
    }
}

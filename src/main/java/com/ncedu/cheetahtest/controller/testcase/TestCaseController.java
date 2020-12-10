package com.ncedu.cheetahtest.controller.testcase;

import com.ncedu.cheetahtest.entity.testcase.TestCase;
import com.ncedu.cheetahtest.entity.testcase.TestCaseIdsDto;
import com.ncedu.cheetahtest.entity.testcase.TestCasePaginated;
import com.ncedu.cheetahtest.service.testcase.crud.TestCaseService;
import com.ncedu.cheetahtest.service.testcase.runwrapper.TestCaseLauncher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${frontend.ulr}")
@RequiredArgsConstructor
public class TestCaseController {

    private final TestCaseService testCaseService;
    private final TestCaseLauncher testCaseLauncher;


    @GetMapping("/test-cases")
    public TestCasePaginated getActiveTestCases(@RequestParam int page,
                                                @RequestParam int size) {
        return testCaseService.getTestCases(page, size);
    }

    @GetMapping("/projects/{projectId}/test-cases")
    public TestCasePaginated getActiveTestCasesPaginatedByProjectId(
            @RequestParam int page,
            @RequestParam int size,
            @PathVariable int projectId) {
        return testCaseService.getActiveTestCasesPaginatedByProjectId(page, size, projectId);
    }

    @GetMapping("/projects/{projectId}/test-cases/{id}")
    public ResponseEntity<TestCase> getTestCaseById(@PathVariable int projectId,
                                                    @PathVariable int id) {
        return ResponseEntity.ok(testCaseService.findTestCaseByProjectIdAndTestCaseId(projectId, id));
    }

    @GetMapping("/test-cases/search/findByTitle")
    public TestCasePaginated findTestCasesByTitlePaginated(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String keyword) {
        return testCaseService.findTestCasesByTitlePaginated(page, size, keyword);
    }

    @GetMapping("/projects/{projectId}/test-cases/search/findByTitle")
    public TestCasePaginated findTestCasesByTitlePaginatedAndByProjectId(
            @PathVariable int projectId,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String keyword) {
        return testCaseService.findTestCasesByTitlePaginatedAndByProjectId(page, size, keyword, projectId);
    }


    // Active and Inactive test cases
    @GetMapping("/all-test-cases")
    public TestCasePaginated getAllTestCases(@RequestParam int page,
                                             @RequestParam int size) {
        return testCaseService.getAllTestCases(page, size);
    }

    // Active and Inactive test cases
    @GetMapping("/all-test-cases/search/findByTitle")
    public TestCasePaginated findAllTestCasesByTitlePaginated(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String keyword) {
        return testCaseService.findAllTestCasesByTitlePaginated(page, size, keyword);
    }

    @PutMapping("/test-cases/{id}")
    public TestCase updateTestCase(@PathVariable int id,
                                   @RequestBody TestCase testCase) {
        testCase.setId(id);
        testCaseService.save(testCase);

        return testCase;
    }

    @DeleteMapping("/test-cases/{id}")
    public ResponseEntity<Object> deactivateTestCase(@PathVariable int id) {

        testCaseService.deactivateTestCase(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/test-cases")
    public TestCase createTestCase(@RequestBody TestCase testCase){
        int newId = testCaseService.createTestCase(testCase);
        testCase.setId(newId);

        return testCase;
    }

    @GetMapping("/runTestCase/{id}")
    public ResponseEntity<String> runTestCase(@PathVariable int id)   {
        testCaseLauncher.formActionForSelenium(id);
        return ResponseEntity.ok("Test case is being executed.");
    }

    @GetMapping("/runTestCases")
    public ResponseEntity<String> runTestCases(@RequestBody TestCaseIdsDto testCaseIdsDto)   {
        int[] ids = testCaseIdsDto.getIds();
        for (int testCaseId : ids) {
            testCaseLauncher.formActionForSelenium(testCaseId);
        }
        return ResponseEntity.ok("Test cases are being executed.");
    }
}

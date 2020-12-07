package com.ncedu.cheetahtest.controller.testcase;

import com.ncedu.cheetahtest.entity.testcase.TestCase;
import com.ncedu.cheetahtest.entity.testcase.TestCasePaginated;
import com.ncedu.cheetahtest.service.testcase.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${frontend.ulr}")
@RequiredArgsConstructor
public class TestCaseController {

    private final TestCaseService testCaseService;

    @GetMapping("/test-cases")
    public TestCasePaginated getActiveTestCases(@RequestParam int page,
                                                @RequestParam int size) {
        return testCaseService.getTestCases(page, size);
    }

    @GetMapping("/test-cases/search/findByTitle")
    public TestCasePaginated findTestCasesByTitlePaginated(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String keyword) {
        return testCaseService.findTestCasesByTitlePaginated(page, size, keyword);
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

}

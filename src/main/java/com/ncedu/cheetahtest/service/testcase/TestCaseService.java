package com.ncedu.cheetahtest.service.testcase;

import com.ncedu.cheetahtest.entity.testcase.TestCase;
import com.ncedu.cheetahtest.entity.testcase.TestCasePaginated;

public interface TestCaseService {

    TestCasePaginated getTestCases(int page, int size);

    TestCasePaginated getAllTestCases(int page, int size);

    void save(TestCase testCase);

    TestCase findTestCaseById(int id);

    void deactivateTestCase(int id);

    TestCasePaginated findTestCasesByTitlePaginated(int page, int size, String title);

    TestCasePaginated findAllTestCasesByTitlePaginated(int page, int size, String title);

    int createTestCase(TestCase testCase);
}

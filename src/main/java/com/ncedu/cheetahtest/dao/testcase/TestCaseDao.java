package com.ncedu.cheetahtest.dao.testcase;

import com.ncedu.cheetahtest.entity.testcase.TestCase;

import java.util.List;

public interface TestCaseDao {

    void save(TestCase testCase);

    TestCase findTestCaseByTitleExceptCurrent(String title, int id);

    int createTestCase(TestCase testCase);

    void deactivate(int id);

    List<TestCase> getActivePaginated(int offset, int size);

    List<TestCase> getAllPaginated(int offset, int size);

    int getAmountActiveElements();

    int getAmountAllElements();

    TestCase findById(int id);

    int getSearchedActiveTotalElements(String title);

    int getSearchedAllTotalElements(String title);

    List<TestCase> findActiveByTitlePaginated(int offset, int size, String title);

    List<TestCase> findAllByTitlePaginated(int offset, int size, String title);

    int getSingleIntElement(String title, String getAmountOfAllSearchedTestCases);
}

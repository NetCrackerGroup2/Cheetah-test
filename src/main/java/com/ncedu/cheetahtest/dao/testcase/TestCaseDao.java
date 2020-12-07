package com.ncedu.cheetahtest.dao.testcase;

import com.ncedu.cheetahtest.entity.testcase.TestCase;

public interface TestCaseDao {

    void save(TestCase testCase);

    TestCase findTestCaseByTitleExceptCurrent(String title, int id);

    int createTestCase(TestCase testCase);

    void deactivate(int id);

}

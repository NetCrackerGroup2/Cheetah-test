package com.ncedu.cheetahtest.dao.historytestcase;

import com.ncedu.cheetahtest.entity.historytestcase.HistoryTestCase;

import java.util.Date;
import java.util.List;

public interface HistoryTestCaseDao {

    int addTestCase(String result, Date dateCompleted, int testCaseId);

    void editTestCaseResultById(int testCaseId, String result);

    Integer getCountTestCaseFailedCompleted();

    List<HistoryTestCase> getPage(int size, int page);
}
package com.ncedu.cheetahtest.service.testcase;

import com.ncedu.cheetahtest.dao.genericdao.AbstractActiveDao;
import com.ncedu.cheetahtest.dao.project.ProjectDao;
import com.ncedu.cheetahtest.dao.testcase.TestCaseDao;
import com.ncedu.cheetahtest.entity.project.Project;
import com.ncedu.cheetahtest.entity.testcase.TestCase;
import com.ncedu.cheetahtest.entity.testcase.TestCasePaginated;
import com.ncedu.cheetahtest.exception.project.ProjectNotFoundException;
import com.ncedu.cheetahtest.exception.testcase.TestCaseAlreadyExistsException;
import com.ncedu.cheetahtest.exception.testcase.TestCaseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestCaseServiceImpl implements TestCaseService {

    private final TestCaseDao testCaseDao;
    private final AbstractActiveDao<TestCase> testCaseGenDao;
    private final ProjectDao projectDao;

    @Override
    @Transactional
    public TestCasePaginated getTestCases(int page, int size) {
        List<TestCase> testCaseList = testCaseGenDao.getActivePaginated(page, size);
        int totalElements = testCaseGenDao.getAmountActiveElements();

        return new TestCasePaginated(testCaseList, totalElements);
    }

    @Override
    @Transactional
    public TestCasePaginated getAllTestCases(int page, int size) {

        List<TestCase> testCaseList = testCaseGenDao.getAllPaginated(page, size);
        int totalElements = testCaseGenDao.getAmountAllElements();

        return new TestCasePaginated(testCaseList, totalElements);
    }

    @Override
    @Transactional
    public void save(TestCase testCase) {
        TestCase testCaseWithSameTitle = testCaseDao
                .findTestCaseByTitleExceptCurrent(
                        testCase.getTitle(),
                        testCase.getId());

        if (testCaseWithSameTitle == null) {
            testCaseDao.save(testCase);
        } else {
            throw new TestCaseAlreadyExistsException();
        }
    }

    @Override
    @Transactional
    public TestCase findTestCaseById(int id) {
        TestCase testCase = testCaseGenDao.findById(id);

        if (testCase == null) {
            throw new TestCaseNotFoundException();
        }

        return testCase;
    }

    @Override
    @Transactional
    public void deactivateTestCase(int id) {
        testCaseDao.deactivate(id);
    }

    @Override
    public TestCasePaginated findTestCasesByTitlePaginated(int page, int size, String title) {
        List<TestCase> testCaseList = testCaseGenDao
                .findActiveByTitlePaginated(page, size, title);

        int totalElements = testCaseGenDao.getSearchedActiveTotalElements(title);

        return new TestCasePaginated(testCaseList, totalElements);
    }

    @Override
    @Transactional
    public TestCasePaginated findAllTestCasesByTitlePaginated(
            int page, int size, String title) {

        List<TestCase> testCaseList = testCaseGenDao
                .findAllByTitlePaginated(page, size, title);

        int totalElements = testCaseGenDao.getSearchedAllTotalElements(title);

        return new TestCasePaginated(testCaseList, totalElements);
    }

    @Override
    @Transactional
    public int createTestCase(TestCase testCase) {
        TestCase testCaseWithSameTitle = testCaseDao
                .findTestCaseByTitleExceptCurrent(
                        testCase.getTitle(),
                        testCase.getId());

        Project project = projectDao.findByProjectId(testCase.getProjectId());

        if (project == null) {
            throw new ProjectNotFoundException();
        }
        if (testCaseWithSameTitle != null) {
            throw new TestCaseAlreadyExistsException();
        }

        return testCaseDao.createTestCase(testCase);
    }

}


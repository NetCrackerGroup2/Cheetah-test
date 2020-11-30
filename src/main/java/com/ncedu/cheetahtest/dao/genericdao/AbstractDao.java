package com.ncedu.cheetahtest.dao.genericdao;

import java.util.List;

public interface AbstractDao<T> {
    List<T> getActivePaginated(int page, int size);

    List<T> getAllPaginated(int page, int size);

    int getAmountActiveElements();

    int getAmountAllElements();

    T findById(int id);

    int getSearchedActiveTotalElements(String title);

    int getSearchedAllTotalElements(String title);

    List<T> findActiveByTitlePaginated(int page, int size, String title);

    List<T> findAllByTitlePaginated(int page, int size, String title);

    int getSingleIntElement(String title, String getAmountOfAllSearchedTestCases);
}

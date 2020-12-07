package com.ncedu.cheetahtest.dao.genericdao;

import java.util.List;

public interface AbstractDao<T> {

    List<T> getAllPaginated(int page, int size);

    int getAmountAllElements();

    T findById(int id);

    int getSearchedAllTotalElements(String title);

    List<T> findAllByTitlePaginated(int page, int size, String title);

    int getSingleIntElement(String title, String getAmountOfAllSearchedTestCases);
}

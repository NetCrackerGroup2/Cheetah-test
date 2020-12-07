package com.ncedu.cheetahtest.dao.genericdao;

import java.util.List;

public interface AbstractActiveDao<T> extends AbstractDao<T> {
    List<T> getActivePaginated(int page, int size);

    int getAmountActiveElements();

    int getSearchedActiveTotalElements(String title);

    List<T> findActiveByTitlePaginated(int page, int size, String title);
}

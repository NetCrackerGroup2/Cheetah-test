package com.ncedu.cheetahtest.service.dataset;

import com.ncedu.cheetahtest.entity.dataset.DataSet;
import com.ncedu.cheetahtest.entity.dataset.PaginationDataset;

public interface DataSetService {
    PaginationDataset findByTitleLike(String title, int idTestCase, int size, int page);
    DataSet createDataSet(DataSet dataSet);
    DataSet editDataSet(DataSet dataSet, int id);
    void deleteDataSet(int id);
}

package com.ncedu.cheetahtest.dao.dataset;


import com.ncedu.cheetahtest.entity.dataset.DataSet;

import java.util.List;

public interface DataSetDao {
    DataSet findById(int id);
    List<DataSet> findByTitleLike(String title,int idTestCase,int limit, int offset);
    DataSet createDataSet(DataSet dataSet);
    DataSet editDataSet(DataSet dataSet, int id);
    void deleteDataSet(int id);
    int getTotalElements(int idTestcase,String title);
    DataSet findByTitle(String title);
}

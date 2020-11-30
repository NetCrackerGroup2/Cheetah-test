package com.ncedu.cheetahtest.service.dataset;

import com.ncedu.cheetahtest.dao.dataset.DataSetDao;
import com.ncedu.cheetahtest.dao.parameters.ParametersDao;
import com.ncedu.cheetahtest.entity.dataset.DataSet;
import com.ncedu.cheetahtest.entity.dataset.PaginationDataset;
import com.ncedu.cheetahtest.exception.helpers.EntityAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DataSetServiceImpl implements DataSetService {
    private final DataSetDao dataSetDao;
    @Autowired
    public DataSetServiceImpl(DataSetDao dataSetDao) {
        this.dataSetDao = dataSetDao;
    }




    @Override
    public PaginationDataset findByTitleLike(String title, int idTestCase, int size, int page) {
        int totalDataSets = dataSetDao.getTotalElements(idTestCase, title);
        PaginationDataset paginationDataset = new PaginationDataset();
        paginationDataset.setTotalElements(totalDataSets);
        if (size * (page - 1) < totalDataSets) {
            paginationDataset.setDataSets(dataSetDao.findByTitleLike(title, idTestCase, size, size * (page - 1)));
        }
        return paginationDataset;
    }

    @Override
    public DataSet createDataSet(DataSet dataSet) {
        try {
            return dataSetDao.createDataSet(dataSet);
        }catch (DataIntegrityViolationException ex){
            throw new EntityAlreadyExistException();
        }


    }

    @Override
    public DataSet editDataSet(DataSet dataSet, int id) {
        return dataSetDao.editDataSet(dataSet, id);
    }

    @Override
    public void deleteDataSet(int id) {
        dataSetDao.deleteDataSet(id);
    }
}

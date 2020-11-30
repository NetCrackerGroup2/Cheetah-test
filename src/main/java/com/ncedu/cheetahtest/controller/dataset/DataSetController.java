package com.ncedu.cheetahtest.controller.dataset;

import com.ncedu.cheetahtest.entity.dataset.DataSet;
import com.ncedu.cheetahtest.entity.dataset.PaginationDataset;
import com.ncedu.cheetahtest.entity.dataset.ResponseDataSet;
import com.ncedu.cheetahtest.service.dataset.DataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${frontend.ulr}")
@RequestMapping("/api/data-set")
public class DataSetController {
    private final DataSetService dataSetService;

    @Autowired
    public DataSetController(DataSetService dataSetService) {
        this.dataSetService = dataSetService;
    }

    @PostMapping
    public DataSet createDataSet( @RequestBody DataSet dataSet) {
        return dataSetService.createDataSet(dataSet);
    }
    @GetMapping
    public PaginationDataset findDataSetByTitle(
            @RequestParam("title") String title,
            @RequestParam("size") int size,
            @RequestParam("page") int page,
            @RequestParam("idTestCase") int idTestCase){
        return dataSetService.findByTitleLike(title,idTestCase,size,page);
    }
    @PutMapping
    public DataSet editDataSet(@RequestBody DataSet dataSet,
                               @RequestParam("id") int id){
        return dataSetService.editDataSet(dataSet,id);
    }
    @DeleteMapping
    public ResponseDataSet deleteDataSet(@RequestParam("id") int id){
        dataSetService.deleteDataSet(id);
        return new ResponseDataSet("Success");
    }
}

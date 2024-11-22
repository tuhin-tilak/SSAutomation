package com.cucumbercraft.Models;


import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import java.io.File;
import java.util.List;

public class Excelutils {

    public PurchaseModel getTestData(String filepath,String sheetName,String testcase) {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName(sheetName).build();
        List<PurchaseModel> allRows = Poiji.fromExcel(new File(filepath), PurchaseModel.class, options);
        return allRows
                .stream()
                .filter(testData -> testData.getTestcase().equals(testcase))
                .findFirst()
                .get();

    }
}

package com.cucumbercraft.Models;


import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import java.io.File;
import java.util.List;

public class Excelutils {

    public PurchaseModel getTestData(String filepath,String sheetName,String testcase) {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("Buy Now").build();
        List<PurchaseModel> allRows = Poiji.fromExcel(new File("src/test/resources/Test_Data/Automation Data.xlsx"), PurchaseModel.class, options);
        return allRows
                .stream()
                .filter(testData -> testData.getTestcase().equals(testcase))
                .findFirst()
                .get();

    }
}

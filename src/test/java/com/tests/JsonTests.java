package com.tests;

import com.base.BaseClass;
import com.pojo.TestDataBatter;
import com.pojo.TestDataBatterAndToppings;
import com.pojo.TestDataObjects;
import com.utils.ExtentReportUtils;
import com.utils.JSONUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JsonTests extends BaseClass {

    private String jsonfilepath = System.getProperty("user.dir")+"/src/test/resources/testdata/testdata.json";

    @Test
    public void TC001_readJsonFromFile(){
        ExtentReportUtils.info(JSONUtils.jsonPrettyPrint(JSONUtils.readJsonFromFile(jsonfilepath)));
    }

    @Test
    public void TC002_readJsonFromString(){
        String json = JSONUtils.jsonPrettyPrint(JSONUtils.readJsonFromFile(jsonfilepath));
        ExtentReportUtils.info(JSONUtils.readJson(json).toPrettyString());
    }

    @Test
    public void TC003_extractValueForGivenPath(){
        ArrayList<String> topping_type = JSONUtils.readValueFromJsonPath(JSONUtils.readJsonFromFile(jsonfilepath),"$[*].topping[*].type");
        ExtentReportUtils.info(topping_type.toString());
        List<String> filtered_items = topping_type.stream().filter(e -> !e.equalsIgnoreCase("None")).filter(e -> e.startsWith("C")).collect(Collectors.toList());
        ExtentReportUtils.info("Filtered items with starts only C: "+filtered_items);
        ExtentReportUtils.info("Filtered items with starts only C with distinct: "+filtered_items.stream().distinct().collect(Collectors.toList()));
    }

    @Test
    public void TC004_setValueOfKey(){
        ExtentReportUtils.info((JSONUtils.setJsonValue(JSONUtils.readJsonFromFile(jsonfilepath),"$[0].topping[0].type","Honey")).toPrettyString());
        ExtentReportUtils.info((JSONUtils.setJsonValue(JSONUtils.readJsonFromFile(jsonfilepath),"$[*].topping[0].type","Honey")).toPrettyString());
        ExtentReportUtils.info((JSONUtils.setJsonValue(JSONUtils.readJsonFromFile(jsonfilepath),"$[*].topping[?(@.type=='None')].type","Honey")).toPrettyString());
        ExtentReportUtils.info((JSONUtils.setJsonValue(JSONUtils.readJsonFromFile(jsonfilepath),"$[*].topping[?(@.type=='None')].type","Honey")).toPrettyString());
        ExtentReportUtils.info((JSONUtils.setJsonValue(JSONUtils.readJsonFromFile(jsonfilepath),"$[?(@.ppu==0.55)].topping[?(@.type=='None')].type","Honey")).toPrettyString());
    }

    @Test
    public void TC005_convertJsonToPojo(){
        List<TestDataObjects> list_obj = JSONUtils.jsonToListPojo(JSONUtils.readJsonFromFile(jsonfilepath), TestDataObjects.class);
        for(TestDataObjects obj : list_obj){
            TestDataBatter tdb = obj.getBatters();
            List<TestDataBatterAndToppings> batters = tdb.getBatter();
            for(TestDataBatterAndToppings batter : batters){
                ExtentReportUtils.info("Print batter : "+batter.toString());
            }
            List<TestDataBatterAndToppings> toppings_list = obj.getTopping();
            for(TestDataBatterAndToppings topping : toppings_list){
                ExtentReportUtils.info("Print topping : "+topping.toString());
            }
            ExtentReportUtils.info("Print whole object : "+obj.toString());
        }
    }

    @Test
    public void TC006_convertPojoToJson(){
        List<TestDataObjects> list_obj = JSONUtils.jsonToListPojo(JSONUtils.readJsonFromFile(jsonfilepath), TestDataObjects.class);
        ExtentReportUtils.info(JSONUtils.jsonPrettyPrint(JSONUtils.pojoToJson(list_obj)));
    }

    @Test
    public void TC007_usageOfStreams(){
        List<TestDataObjects> list_obj = JSONUtils.jsonToListPojo(JSONUtils.readJsonFromFile(jsonfilepath), TestDataObjects.class);
        ExtentReportUtils.info("Iterate all objects");
        list_obj.forEach(e->ExtentReportUtils.info(e.toString()));
        ExtentReportUtils.info("Filter based on condition");
        List<TestDataObjects> list_obj_filtered = list_obj.stream().filter(e->Double.parseDouble(e.getPpu())>0.55).toList();
        list_obj_filtered.forEach(e->ExtentReportUtils.info(e.toString()));
        ExtentReportUtils.info("compare and sort");
        List<TestDataObjects> list_obj_sorted = list_obj.stream().sorted(Comparator.comparingDouble(pp -> Double.parseDouble(pp.getPpu()))).collect(Collectors.toList());
        list_obj_sorted.forEach(e->ExtentReportUtils.info(e.toString()));
    }

}

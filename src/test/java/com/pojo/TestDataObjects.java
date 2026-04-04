package com.pojo;

import java.util.ArrayList;
import java.util.List;

public class TestDataObjects {

    private String id;
    private String type;
    private String name;
    private String ppu;
    private TestDataBatter batters;
    private List<TestDataBatterAndToppings> topping;

    public TestDataObjects() {
    }

    public TestDataObjects(String id, String type, String name, String ppu, TestDataBatter batters, ArrayList<TestDataBatterAndToppings> topping) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.ppu = ppu;
        this.batters = batters;
        this.topping = topping;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPpu() {
        return ppu;
    }

    public void setPpu(String ppu) {
        this.ppu = ppu;
    }

    public TestDataBatter getBatters() {
        return batters;
    }

    public void setBatters(TestDataBatter batters) {
        this.batters = batters;
    }

    public List<TestDataBatterAndToppings> getTopping() {
        return topping;
    }

    public void setTopping(ArrayList<TestDataBatterAndToppings> topping) {
        this.topping = topping;
    }

    @Override
    public String toString() {
        return "TestDataObjects{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", ppu='" + ppu + '\'' +
                ", batters=" + batters +
                ", topping=" + topping +
                '}';
    }
}

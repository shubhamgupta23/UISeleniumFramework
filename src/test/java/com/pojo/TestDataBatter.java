package com.pojo;

import java.util.ArrayList;
import java.util.List;

public class TestDataBatter {

    private List<TestDataBatterAndToppings> batter;

    public TestDataBatter() {

    }

    public TestDataBatter(List<TestDataBatterAndToppings> batter) {
        this.batter = batter;
    }

    public List<TestDataBatterAndToppings> getBatter() {
        return batter;
    }

    public void setBatter(ArrayList<TestDataBatterAndToppings> batter) {
        this.batter = batter;
    }

    @Override
    public String toString() {
        return "TestDataBatter{" +
                "batter=" + batter +
                '}';
    }
}

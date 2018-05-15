package com.world.cup.responses;

import com.world.cup.entities.Forecast;

import java.util.List;

/**
 * Created by loiccol on 04/05/18.
 */

public class ForecastResponse {
    List<Forecast> data;

    public List<Forecast> getData() {
        return data;
    }
}

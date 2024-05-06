package ru.lightapps.telemetry.util.exception;

import ru.lightapps.telemetry.model.Measure;
import ru.lightapps.telemetry.to.MeasureTo;

public class MeasureUtil {

    public static MeasureTo asTo (Measure measure) {
        return new MeasureTo(measure.getTemperature(), measure.getCo2(), measure.getHumidity(), measure.getDateTime());
    }
}

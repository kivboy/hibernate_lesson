package by.vadarod.javaee.repository;

import by.vadarod.javaee.entity.Activity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ActivityRepository {
    Long addActivity(Activity activity);
    List<Activity> getAll();
    Activity findActivityById(Long activityId);
    Map<String, BigDecimal> getRoomsPricePerClient(Long activityId);
    Activity getMinPriceActivity();
}

package by.vadarod.javaee.repository;

import by.vadarod.javaee.entity.Activity;

import java.util.List;

public interface ActivityRepository {
    Long addActivity(Activity activity);
    List<Activity> getAll();
    Activity findActivityById(Long activityId);
}

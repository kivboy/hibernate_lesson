package by.vadarod.javaee.service;

import by.vadarod.javaee.entity.Activity;
import by.vadarod.javaee.repository.ActivityRepository;
import lombok.NonNull;

import java.util.List;

public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Long addActivity(@NonNull Activity activity) {
        return activityRepository.addActivity(activity);
    }

    public Activity findActivityById(Long activityId) {
        return activityRepository.findActivityById(activityId);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.getAll();
    }
}

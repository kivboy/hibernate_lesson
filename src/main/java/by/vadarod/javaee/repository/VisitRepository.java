package by.vadarod.javaee.repository;

import by.vadarod.javaee.entity.Visit;

import java.util.List;

public interface VisitRepository {
    Long addVisit(Visit visit);
    List<Visit> getAll();
}

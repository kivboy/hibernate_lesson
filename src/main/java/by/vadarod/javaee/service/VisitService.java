package by.vadarod.javaee.service;

import by.vadarod.javaee.entity.Visit;
import by.vadarod.javaee.repository.VisitRepository;
import lombok.NonNull;

import java.util.List;

public class VisitService {
    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Long addVisit(@NonNull Visit visit) {
        return visitRepository.addVisit(visit);
    }

    public List<Visit> getAllVisits() {
        return visitRepository.getAll();
    }
}

package com.taskmanagement.repository.task;

import com.taskmanagement.dto.task.TaskFilterDTO;
import com.taskmanagement.model.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Task> findTasksByFilters(TaskFilterDTO filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> query = cb.createQuery(Task.class);
        Root<Task> task = query.from(Task.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getTitle() != null) {
            predicates.add(cb.like(cb.lower(task.get("title")), "%" + filter.getTitle().toLowerCase() + "%"));
        }
        if (filter.getStatus() != null) {
            predicates.add(cb.equal(task.get("status"), filter.getStatus()));
        }
        if (filter.getPriority() != null) {
            predicates.add(cb.equal(task.get("priority"), filter.getPriority()));
        }
        if (filter.getCreatedFrom() != null && filter.getCreatedTo() != null) {
            predicates.add(cb.between(task.get("createdAt"), filter.getCreatedFrom(), filter.getCreatedTo()));
        }
        if (filter.getUpdatedFrom() != null && filter.getUpdatedTo() != null) {
            predicates.add(cb.between(task.get("updatedAt"), filter.getUpdatedFrom(), filter.getUpdatedTo()));
        }

        query.select(task).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
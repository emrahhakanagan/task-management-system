package com.taskmanagement.repository.user;

import com.taskmanagement.dto.user.UserFilterDTO;
import com.taskmanagement.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserCustomRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findUsersByFilters(UserFilterDTO filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getIsActive() != null) {
            predicates.add(cb.equal(user.get("isActive"), filter.getIsActive()));
        }
        if (filter.getIsEmailConfirmed() != null) {
            predicates.add(cb.equal(user.get("isEmailConfirmed"), filter.getIsEmailConfirmed()));
        }
        if (filter.getIsDeleted() != null) {
            predicates.add(cb.equal(user.get("isDeleted"), filter.getIsDeleted()));
        }
        if (filter.getRole() != null) {
            predicates.add(cb.equal(user.get("role"), filter.getRole()));
        }

        query.select(user).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
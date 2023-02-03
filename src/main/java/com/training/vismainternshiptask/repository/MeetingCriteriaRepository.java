package com.training.vismainternshiptask.repository;

import com.training.vismainternshiptask.dto.MeetingSearchCriteria;
import com.training.vismainternshiptask.enums.PersonNumFilterType;
import com.training.vismainternshiptask.model.Meeting;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MeetingCriteriaRepository {

  public static final String PERSONS = "persons";
  private final EntityManager entityManager;
  private final CriteriaBuilder criteriaBuilder;


  public MeetingCriteriaRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
    this.criteriaBuilder = entityManager.getCriteriaBuilder();
  }

  public List<Meeting> findAllWithFilter(MeetingSearchCriteria searchCriteria) {

    CriteriaQuery<Meeting> cq = criteriaBuilder.createQuery(Meeting.class);
    Root<Meeting> root = cq.from(Meeting.class);
    Predicate predicate = getFilterPredicate(searchCriteria, root);
    cq.where(predicate);
    TypedQuery<Meeting> query = entityManager.createQuery(cq);
    return query.getResultList();
  }

  private Predicate getFilterPredicate(MeetingSearchCriteria searchCriteria, Root<Meeting> root) {

    List<Predicate> predicates = new ArrayList<>();

    String description = searchCriteria.getDescription();
    if (description != null) {
      predicates.add(
        criteriaBuilder
          .equal(criteriaBuilder.lower(root.get("description")), description.toLowerCase()));
    }
    String responsiblePerson = searchCriteria.getResponsiblePerson();
    if (responsiblePerson != null) {
      predicates.add(
        criteriaBuilder
          .equal(criteriaBuilder.lower(root.get("responsiblePerson")),
            responsiblePerson.toLowerCase()));
    }
    String type = searchCriteria.getType();
    if (type != null) {
      predicates.add(
        criteriaBuilder
          .equal(root.get("type").as(String.class), type));
    }
    String category = searchCriteria.getCategory();
    if (category != null) {
      predicates.add(
        criteriaBuilder
          .equal(root.get("category").as(String.class), category));
    }
    String startDate = searchCriteria.getStartDate();
    if (startDate != null) {
      predicates.add(
        criteriaBuilder
          .greaterThanOrEqualTo(root.get("startDate").as(String.class), startDate));
    }
    String endDate = searchCriteria.getEndDate();
    if (endDate != null) {
      predicates.add(
        criteriaBuilder
          .lessThanOrEqualTo(root.get("endDate").as(String.class), endDate));
    }

    Integer numPersons = searchCriteria.getNumPersons();

    PersonNumFilterType personNumFilterType = searchCriteria.getPersonNumFilterType();

    if (numPersons != null && personNumFilterType != null) {
      predicates.add(switch (personNumFilterType) {
        case GREATER -> criteriaBuilder.greaterThan(criteriaBuilder.size(root.get(PERSONS)), numPersons);
        case EQUAL -> criteriaBuilder.equal(criteriaBuilder.size(root.get(PERSONS)), numPersons);
        case LESS -> criteriaBuilder.lessThan(criteriaBuilder.size(root.get(PERSONS)), numPersons);
      });
    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }
}
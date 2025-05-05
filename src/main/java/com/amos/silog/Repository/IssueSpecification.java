package com.amos.silog.Repository;

import com.amos.silog.Dto.IssueDto.IssueFilterRequestDto;
import com.amos.silog.Entity.Issue;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class IssueSpecification {

    public static Specification<Issue> withFilters(IssueFilterRequestDto filters) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filters.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filters.getStatus().name()));
            }
            if (filters.getSeverityLevel() != null) {
                predicates.add(cb.equal(root.get("severityLevel"), filters.getSeverityLevel().name()));
            }
            if (filters.getProject() != null) {
                predicates.add(cb.equal(root.get("project"), filters.getProject()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

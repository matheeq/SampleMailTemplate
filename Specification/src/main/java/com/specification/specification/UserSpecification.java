package com.specification.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.specification.model.User;
import com.specification.model.UserRequest;

@Component
public class UserSpecification {
	
	public static Specification<User> getUsers(User request) {
		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();

				if (request.getEmail() != null && !request.getEmail().isEmpty()) {
					predicate.add(criteriaBuilder.equal(root.get("email"), request.getEmail()));
				}

				if(request.getFirstName() !=null && !request.getLastName().isEmpty()) {
					predicate.add(criteriaBuilder.equal(root.get("firstName"), request.getFirstName()));
					
				}
				if(request.getLastName() !=null && !request.getLastName().isEmpty()) {
					predicate.add(criteriaBuilder.equal(root.get("lastName"), request.getLastName()));
					
				}
				query.orderBy(criteriaBuilder.asc(root.get("userId")));

				return criteriaBuilder.and(predicate.toArray(new Predicate[] {}));

			}
		};

}
}
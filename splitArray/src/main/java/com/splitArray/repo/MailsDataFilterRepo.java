package com.splitArray.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.splitArray.model.MailsDataFilter;

public interface MailsDataFilterRepo extends JpaRepository<MailsDataFilter, Integer> {

		@Query(value = "SELECT u FROM MailsDataFilter u WHERE u.formatId=:formatId and u.receiversEmail IN :emails")
	    List<MailsDataFilter> getExistingRecords(@Param("formatId")Integer formatId, @Param("emails") List<String> emails);

	}


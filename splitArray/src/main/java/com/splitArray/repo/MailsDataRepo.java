/**
 * 
 */
package com.splitArray.repo;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.splitArray.model.MailsData;

/**
 * @author SSoumya
 *
 */
public interface MailsDataRepo extends JpaRepository<MailsData, Integer>,JpaSpecificationExecutor<MailsData>{

	//List<MailsData> findAll(Specification<MailsData> byColumnNameAndValue);

	
	
}

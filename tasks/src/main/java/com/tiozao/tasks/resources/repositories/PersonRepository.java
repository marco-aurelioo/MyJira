package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity,Integer> {

    Optional<PersonEntity> findByUserId(String userId);
}

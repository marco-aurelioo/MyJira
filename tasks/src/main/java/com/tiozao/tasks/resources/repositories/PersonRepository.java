package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.domain.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity,Integer> {

    Optional<PersonEntity> findByUserId(String userId);

    @Query("SELECT p.projectMember FROM PersonEntity p WHERE p.userId = :userId")
    Page<ProjectEntity> findProjectsByPersonId(@Param("userId") String userId, Pageable pageable);

}

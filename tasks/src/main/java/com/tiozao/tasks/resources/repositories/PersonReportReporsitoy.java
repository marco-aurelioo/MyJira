package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.domain.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PersonReportReporsitoy  extends PagingAndSortingRepository<PersonEntity,Integer> {

    Page<PersonEntity> findByNameLike(String nameLike, Pageable pageable);

    @Query("SELECT p FROM PersonEntity p " +
            "WHERE p.name LIKE %:name% " +
            "AND NOT EXISTS (" +
            "    SELECT 1 FROM p.projectMember pm WHERE pm.externalId = :externalId" +
            ")")
    Page<PersonEntity> findByNameLikeAndProjectMemberNot(@Param("name") String name,
                                                         @Param("externalId") String projectId,
                                                         Pageable pageable);
}

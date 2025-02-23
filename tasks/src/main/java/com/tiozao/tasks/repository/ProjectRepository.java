package com.tiozao.tasks.repository;

import com.tiozao.tasks.domain.entity.Project;
import com.tiozao.tasks.domain.entity.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {

    @Query(value = "SELECT COUNT(*) FROM projects WHERE unic_name ~ :pattern", nativeQuery = true)
    long countByRegExpUnicName(@Param("pattern") String pattern);

    Optional<Project> findByExternalIdAndOwner(String externalId, UserProfile myUserProfile);

    Optional<Project> findByUnicNameAndOwner(String unicName,  UserProfile myUserProfile);
}

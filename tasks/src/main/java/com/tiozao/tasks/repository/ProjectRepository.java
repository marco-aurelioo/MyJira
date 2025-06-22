package com.tiozao.tasks.repository;

import com.tiozao.tasks.domain.entity.Project;
import com.tiozao.tasks.domain.entity.UserProfile;
import com.tiozao.tasks.domain.views.ProjectRequestParticipation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    Optional<Project> findByUnicNameAndOwner(String unicName, UserProfile myUserProfile);


    Optional<Project> findByExternalId(String externalId);

    Optional<Project> findByUnicName(String unicName);


    Page<Project> findByOwner(UserProfile myUserProfile, PageRequest pageable);

    Page<Project> findByIsPublic(Boolean isPublic, PageRequest pageable);

    Page<Project> findByIsPublicAndNameLike(boolean isPublicb, String nameLikes, PageRequest pageable);


    @Query(value =
            " SELECT p.external_id, " +
                    "p.unic_name, " +
                    "p.name," +
                    "p.description, " +
                    "(SELECT COUNT(*) " +
                    "    FROM request_participation r " +
                    "    WHERE r.project_id = p.id AND r.status = 'PENDING' " +
                    ") AS qtdPendingRequests, " +
                    "( " +
                    "    SELECT COUNT(*) " +
                    "    FROM invitation_projects i " +
                    "    WHERE i.project_id = p.id AND i.status = 'PENDING' " +
                    ") AS qtdPendingInvites " +
                    "FROM projects p " +
                    "WHERE p.owner_id = :ownerId ",

            countQuery =
                    "SELECT COUNT(*)  " +
                            "FROM projects p  " +
                            "WHERE p.owner_id = :ownerId ",
            nativeQuery = true)
    Page<Object[]> findMyProjectsWithRequestParticipation(@Param("ownerId") Long ownerId, Pageable pageable);


}

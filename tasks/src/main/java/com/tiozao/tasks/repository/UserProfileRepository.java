package com.tiozao.tasks.repository;

import com.tiozao.tasks.domain.entity.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {
    Optional<UserProfile> findByExternalId(String externalId);

    Page<UserProfile> findByNameLike(String name, Pageable pageable);

}

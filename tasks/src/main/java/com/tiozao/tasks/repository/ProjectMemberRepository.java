package com.tiozao.tasks.repository;

import com.tiozao.tasks.domain.entity.Project;
import com.tiozao.tasks.domain.entity.ProjectMember;
import com.tiozao.tasks.domain.entity.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepository extends CrudRepository<ProjectMember,Long> {

    Page<ProjectMember> findByUser(UserProfile myUserProfile, PageRequest pageable);
}

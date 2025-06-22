package com.tiozao.tasks.repository;

import com.tiozao.tasks.domain.entity.ParticipationStatus;
import com.tiozao.tasks.domain.entity.Project;
import com.tiozao.tasks.domain.entity.RequestParticipation;
import com.tiozao.tasks.domain.entity.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface RequestParticipationRepository extends CrudRepository<RequestParticipation,Long> {

    Page<RequestParticipation> findByRequestingUserAndMessageLike( UserProfile myUserProfile, String filtroMsg, Pageable pageable);

    Page<RequestParticipation> findByProjectAndStatus(Project project, ParticipationStatus status, Pageable pageable);


}

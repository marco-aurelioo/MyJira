package com.tiozao.tasks.repository;

import com.tiozao.tasks.domain.entity.ProjectRequest;
import com.tiozao.tasks.domain.entity.RequestStatus;
import com.tiozao.tasks.domain.entity.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRequestRepository extends CrudRepository<ProjectRequest,Long> {


    Page<ProjectRequest> findBySenderAndStatusIn(UserProfile myProfile, List<RequestStatus> statuses, Pageable pageable);

    Page<ProjectRequest> findByReceiverAndStatusIn(UserProfile myProfile, List<RequestStatus> status, Pageable pageable);

}

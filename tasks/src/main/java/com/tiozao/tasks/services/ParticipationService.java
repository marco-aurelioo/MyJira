package com.tiozao.tasks.services;

import com.tiozao.tasks.domain.entity.ParticipationStatus;
import com.tiozao.tasks.domain.entity.RequestParticipation;
import com.tiozao.tasks.repository.RequestParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ParticipationService {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RequestParticipationRepository repository;

    public RequestParticipation createRequestToProject(RequestParticipation requestParticipation) {
        requestParticipation.setStatus(ParticipationStatus.PENDING);
        return repository.save(requestParticipation);
    }


    public Page<RequestParticipation> findMyRequests(String messageLike, Pageable pageable) {
        return repository.findByRequestingUserAndMessageLike(
                userProfileService.findMyUserProfile(), "%"+messageLike+"%",  pageable);
    }

    public boolean cancelRequest(Long participarId) {
        RequestParticipation requestParticipation = repository.findById(participarId).orElseThrow();
        if(!requestParticipation.getRequestingUser().equals(userProfileService.findMyUserProfile())){
            throw new IllegalArgumentException("usuario invalido");
        }
        repository.delete(requestParticipation);
        return true;
    }
}

package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonReportReporsitoy  extends PagingAndSortingRepository<PersonEntity,Integer> {

    Page<PersonEntity> findByNameLike(Integer nameLike, Pageable pageable);
}

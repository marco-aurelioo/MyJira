package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.PersonPlansEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonPlansRepository extends JpaRepository<PersonPlansEntity,Integer> {
}

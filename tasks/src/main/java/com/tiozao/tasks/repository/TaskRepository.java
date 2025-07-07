package com.tiozao.tasks.repository;

import com.tiozao.tasks.domain.entity.Project;
import com.tiozao.tasks.domain.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task,Integer> {

    @Query("SELECT MAX(t.sequencia) FROM Task t WHERE t.project = :project")
    Integer findMaxSequenciaByProject(@Param("project") Project project);

}

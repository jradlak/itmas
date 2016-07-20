package com.jrd.itmas_server.repository;

import com.jrd.itmas_server.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kuba on 2016-07-18.
 */
public interface TaskRepository extends JpaRepository<Task, Long>{

}

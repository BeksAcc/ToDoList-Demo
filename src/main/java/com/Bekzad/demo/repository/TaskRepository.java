package com.Bekzad.demo.repository;

import com.Bekzad.demo.models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long>{
}

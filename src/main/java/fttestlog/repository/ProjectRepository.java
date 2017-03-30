package fttestlog.repository;

import fttestlog.model.Project;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface ProjectRepository extends CrudRepository<Project, Long> {

  String Q_GET_PROJECT_BY_PROJECT_ID_NAME = "select p from Project p inner join p.customer where p.idName = :project_id_name";

  @Query(Q_GET_PROJECT_BY_PROJECT_ID_NAME)
  Collection<Project> findByProjectIdName(@Param("project_id_name") String project_id_name);
}

package fttestlog.repository;

import fttestlog.model.TestLog;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TestLogRepository extends CrudRepository<TestLog, Long> {

  String Q_GET_TESTLOG_BY_PROJECT_ID = "select t from TestLog t inner join t.project p where p.id = :project_id";

  String Q_GET_TESTLOG_BY_PROJECT_ID_NAME = "select t from TestLog t inner join t.project p where p.idName = :project_id_name";

  @Query(Q_GET_TESTLOG_BY_PROJECT_ID)
  Collection<TestLog> findByProjectId(@Param("project_id") long project_id);

  @Query(Q_GET_TESTLOG_BY_PROJECT_ID_NAME)
  Collection<TestLog> findByProjectIdName(@Param("project_id_name") String project_id_name);
}

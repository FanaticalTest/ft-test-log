package fttestlog.repository;

import fttestlog.model.TestLog;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by skacem on 29.03.2017.
 */
public interface TestLogRepository extends CrudRepository<TestLog, Long> {
}

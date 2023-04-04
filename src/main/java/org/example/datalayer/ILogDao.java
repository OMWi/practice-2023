package org.example.datalayer;

import org.example.models.Log;

import java.util.List;

public interface ILogDao {
    Log getLog(int id);

    List<Log> getAllLogs();

    boolean insertLog(Log log);

    boolean updateLog(Log log);

    boolean deleteLog(int id);
}

package service;

import model.HealthRecord;
import java.util.List;

public interface HealthRecordService {
    void add(HealthRecord record);
    List<HealthRecord> getAll();
    void update(HealthRecord record);
    void delete(int id);
}

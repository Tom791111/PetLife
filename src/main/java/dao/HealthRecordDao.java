package dao;

import model.HealthRecord;
import java.util.List;

public interface HealthRecordDao {
    void create(HealthRecord record);
    List<HealthRecord> readAll();
    void update(HealthRecord record);
    void delete(int id);
}

package dao;

import model.LostReport;
import java.util.List;

public interface LostReportDao {
    void create(LostReport report);
    List<LostReport> readAll();
    void update(LostReport report);
    void delete(int id);
}

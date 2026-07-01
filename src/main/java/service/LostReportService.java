package service;

import model.LostReport;
import java.util.List;

public interface LostReportService {
    void add(LostReport report);
    List<LostReport> getAll();
    void update(LostReport report);
    void delete(int id);
}

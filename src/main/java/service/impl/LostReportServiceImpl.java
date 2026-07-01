package service.impl;

import dao.LostReportDao;
import dao.impl.LostReportDaoImpl;
import exception.AppException;
import model.LostReport;
import service.LostReportService;
import java.util.List;

public class LostReportServiceImpl implements LostReportService {
    private final LostReportDao dao = new LostReportDaoImpl();
    public void add(LostReport report) { validate(report, false); dao.create(report); }
    public List<LostReport> getAll() { return dao.readAll(); }
    public void update(LostReport report) { validate(report, true); dao.update(report); }
    public void delete(int id) { if (id <= 0) throw new AppException("請先選擇要刪除的走失通報"); dao.delete(id); }
    private void validate(LostReport report, boolean needId) {
        if (needId && report.getId() <= 0) throw new AppException("請先選擇要修改的走失通報");
        if (report.getPetId() <= 0) throw new AppException("請輸入寵物ID");
    }
}

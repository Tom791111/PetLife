package service.impl;

import dao.HealthRecordDao;
import dao.impl.HealthRecordDaoImpl;
import exception.AppException;
import model.HealthRecord;
import service.HealthRecordService;
import java.util.List;

public class HealthRecordServiceImpl implements HealthRecordService {
    private final HealthRecordDao dao = new HealthRecordDaoImpl();
    public void add(HealthRecord record) { validate(record, false); dao.create(record); }
    public List<HealthRecord> getAll() { return dao.readAll(); }
    public void update(HealthRecord record) { validate(record, true); dao.update(record); }
    public void delete(int id) { if (id <= 0) throw new AppException("請先選擇要刪除的健康紀錄"); dao.delete(id); }
    private void validate(HealthRecord record, boolean needId) {
        if (needId && record.getId() <= 0) throw new AppException("請先選擇要修改的健康紀錄");
        if (record.getPetId() <= 0) throw new AppException("請輸入寵物ID");
    }
}

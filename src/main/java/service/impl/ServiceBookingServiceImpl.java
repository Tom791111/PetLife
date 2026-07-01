package service.impl;

import dao.ServiceBookingDao;
import dao.impl.ServiceBookingDaoImpl;
import exception.AppException;
import model.ServiceBooking;
import service.ServiceBookingService;
import java.util.List;

public class ServiceBookingServiceImpl implements ServiceBookingService {
    private final ServiceBookingDao dao = new ServiceBookingDaoImpl();
    public void add(ServiceBooking booking) { validate(booking, false); dao.create(booking); }
    public List<ServiceBooking> getAll() { return dao.readAll(); }
    public void update(ServiceBooking booking) { validate(booking, true); dao.update(booking); }
    public void delete(int id) { if (id <= 0) throw new AppException("請先選擇要刪除的服務預約"); dao.delete(id); }
    private void validate(ServiceBooking booking, boolean needId) {
        if (needId && booking.getId() <= 0) throw new AppException("請先選擇要修改的服務預約");
        if (booking.getPetId() <= 0) throw new AppException("請輸入寵物ID");
    }
}

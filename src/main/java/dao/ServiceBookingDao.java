package dao;

import model.ServiceBooking;
import java.util.List;

public interface ServiceBookingDao {
    void create(ServiceBooking booking);
    List<ServiceBooking> readAll();
    void update(ServiceBooking booking);
    void delete(int id);
}

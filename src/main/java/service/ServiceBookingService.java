package service;

import model.ServiceBooking;
import java.util.List;

public interface ServiceBookingService {
    void add(ServiceBooking booking);
    List<ServiceBooking> getAll();
    void update(ServiceBooking booking);
    void delete(int id);
}

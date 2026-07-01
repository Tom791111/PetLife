package dao.impl;

import dao.ServiceBookingDao;
import exception.AppException;
import model.ServiceBooking;
import util.DbUtil;
import java.sql.*;
import java.util.*;

public class ServiceBookingDaoImpl implements ServiceBookingDao {
    public void create(ServiceBooking b){
        String sql="INSERT INTO service_bookings (pet_id, service_type, provider_name, booking_date, status, note) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection c=DbUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){ fill(ps,b); ps.executeUpdate(); }
        catch(SQLException e){ throw new AppException("新增服務預約失敗", e); }
    }
    public List<ServiceBooking> readAll(){
        List<ServiceBooking> list=new ArrayList<>();
        String sql="SELECT s.*, p.name pet_name FROM service_bookings s JOIN pets p ON s.pet_id=p.id ORDER BY s.id DESC";
        try(Connection c=DbUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql); ResultSet rs=ps.executeQuery()){
            while(rs.next()) list.add(map(rs));
        } catch(SQLException e){ throw new AppException("查詢服務預約失敗", e); }
        return list;
    }
    public void update(ServiceBooking b){
        String sql="UPDATE service_bookings SET pet_id=?, service_type=?, provider_name=?, booking_date=?, status=?, note=? WHERE id=?";
        try(Connection c=DbUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){ fill(ps,b); ps.setInt(7,b.getId()); ps.executeUpdate(); }
        catch(SQLException e){ throw new AppException("修改服務預約失敗", e); }
    }
    public void delete(int id){
        try(Connection c=DbUtil.getConnection(); PreparedStatement ps=c.prepareStatement("DELETE FROM service_bookings WHERE id=?")){ ps.setInt(1,id); ps.executeUpdate(); }
        catch(SQLException e){ throw new AppException("刪除服務預約失敗", e); }
    }
    private void fill(PreparedStatement ps, ServiceBooking b) throws SQLException { ps.setInt(1,b.getPetId()); ps.setString(2,b.getServiceType()); ps.setString(3,b.getProviderName()); ps.setString(4,b.getBookingDate()); ps.setString(5,b.getStatus()); ps.setString(6,b.getNote()); }
    private ServiceBooking map(ResultSet rs) throws SQLException { return new ServiceBooking(rs.getInt("id"),rs.getInt("pet_id"),rs.getString("pet_name"),rs.getString("service_type"),rs.getString("provider_name"),rs.getString("booking_date"),rs.getString("status"),rs.getString("note")); }
}

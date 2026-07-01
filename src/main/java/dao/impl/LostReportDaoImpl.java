package dao.impl;

import dao.LostReportDao;
import exception.AppException;
import model.LostReport;
import util.DbUtil;
import java.sql.*;
import java.util.*;

public class LostReportDaoImpl implements LostReportDao {
    public void create(LostReport r){
        String sql="INSERT INTO lost_reports (pet_id, lost_date, lost_location, contact_phone, status, description) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection c=DbUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){ fill(ps,r); ps.executeUpdate(); }
        catch(SQLException e){ throw new AppException("新增走失通報失敗", e); }
    }
    public List<LostReport> readAll(){
        List<LostReport> list=new ArrayList<>();
        String sql="SELECT l.*, p.name pet_name FROM lost_reports l JOIN pets p ON l.pet_id=p.id ORDER BY l.id DESC";
        try(Connection c=DbUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql); ResultSet rs=ps.executeQuery()){
            while(rs.next()) list.add(map(rs));
        } catch(SQLException e){ throw new AppException("查詢走失通報失敗", e); }
        return list;
    }
    public void update(LostReport r){
        String sql="UPDATE lost_reports SET pet_id=?, lost_date=?, lost_location=?, contact_phone=?, status=?, description=? WHERE id=?";
        try(Connection c=DbUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){ fill(ps,r); ps.setInt(7,r.getId()); ps.executeUpdate(); }
        catch(SQLException e){ throw new AppException("修改走失通報失敗", e); }
    }
    public void delete(int id){
        try(Connection c=DbUtil.getConnection(); PreparedStatement ps=c.prepareStatement("DELETE FROM lost_reports WHERE id=?")){ ps.setInt(1,id); ps.executeUpdate(); }
        catch(SQLException e){ throw new AppException("刪除走失通報失敗", e); }
    }
    private void fill(PreparedStatement ps, LostReport r) throws SQLException { ps.setInt(1,r.getPetId()); ps.setString(2,r.getLostDate()); ps.setString(3,r.getLostLocation()); ps.setString(4,r.getContactPhone()); ps.setString(5,r.getStatus()); ps.setString(6,r.getDescription()); }
    private LostReport map(ResultSet rs) throws SQLException { return new LostReport(rs.getInt("id"),rs.getInt("pet_id"),rs.getString("pet_name"),rs.getString("lost_date"),rs.getString("lost_location"),rs.getString("contact_phone"),rs.getString("status"),rs.getString("description")); }
}

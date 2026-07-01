package dao.impl;

import dao.HealthRecordDao;
import exception.AppException;
import model.HealthRecord;
import util.DbUtil;
import java.sql.*;
import java.util.*;

public class HealthRecordDaoImpl implements HealthRecordDao {
    public void create(HealthRecord r) {
        String sql = "INSERT INTO health_records (pet_id, record_date, weight, appetite, stool_status, symptom, note) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection c=DbUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql)) { fill(ps,r); ps.executeUpdate(); }
        catch(SQLException e){ throw new AppException("新增健康紀錄失敗", e); }
    }
    public List<HealthRecord> readAll() {
        List<HealthRecord> list=new ArrayList<>();
        String sql="SELECT h.*, p.name pet_name FROM health_records h JOIN pets p ON h.pet_id=p.id ORDER BY h.id DESC";
        try(Connection c=DbUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql); ResultSet rs=ps.executeQuery()){
            while(rs.next()) list.add(map(rs));
        } catch(SQLException e){ throw new AppException("查詢健康紀錄失敗", e); }
        return list;
    }
    public void update(HealthRecord r) {
        String sql="UPDATE health_records SET pet_id=?, record_date=?, weight=?, appetite=?, stool_status=?, symptom=?, note=? WHERE id=?";
        try(Connection c=DbUtil.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){ fill(ps,r); ps.setInt(8,r.getId()); ps.executeUpdate(); }
        catch(SQLException e){ throw new AppException("修改健康紀錄失敗", e); }
    }
    public void delete(int id) {
        try(Connection c=DbUtil.getConnection(); PreparedStatement ps=c.prepareStatement("DELETE FROM health_records WHERE id=?")){ ps.setInt(1,id); ps.executeUpdate(); }
        catch(SQLException e){ throw new AppException("刪除健康紀錄失敗", e); }
    }
    private void fill(PreparedStatement ps, HealthRecord r) throws SQLException { ps.setInt(1,r.getPetId()); ps.setString(2,r.getRecordDate()); ps.setDouble(3,r.getWeight()); ps.setString(4,r.getAppetite()); ps.setString(5,r.getStoolStatus()); ps.setString(6,r.getSymptom()); ps.setString(7,r.getNote()); }
    private HealthRecord map(ResultSet rs) throws SQLException { return new HealthRecord(rs.getInt("id"),rs.getInt("pet_id"),rs.getString("pet_name"),rs.getString("record_date"),rs.getDouble("weight"),rs.getString("appetite"),rs.getString("stool_status"),rs.getString("symptom"),rs.getString("note")); }
}

package dao.impl;

import dao.PetDao;
import exception.AppException;
import model.Pet;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDaoImpl implements PetDao {

    @Override
    public void create(Pet pet) {
        String sql = "INSERT INTO pets (name, species, breed, gender, age, weight, chip_number, health_note) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            fillStatement(ps, pet);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AppException("新增寵物資料失敗", e);
        }
    }

    @Override
    public List<Pet> readAll() {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT id, name, species, breed, gender, age, weight, chip_number, health_note FROM pets ORDER BY id DESC";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pets.add(mapPet(rs));
            }
        } catch (SQLException e) {
            throw new AppException("查詢寵物資料失敗", e);
        }
        return pets;
    }

    @Override
    public Pet findById(int id) {
        String sql = "SELECT id, name, species, breed, gender, age, weight, chip_number, health_note FROM pets WHERE id = ?";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapPet(rs);
                }
            }
        } catch (SQLException e) {
            throw new AppException("查詢單筆寵物資料失敗", e);
        }
        return null;
    }

    @Override
    public void update(Pet pet) {
        String sql = "UPDATE pets SET name=?, species=?, breed=?, gender=?, age=?, weight=?, chip_number=?, health_note=? WHERE id=?";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            fillStatement(ps, pet);
            ps.setInt(9, pet.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AppException("修改寵物資料失敗", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM pets WHERE id = ?";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AppException("刪除寵物資料失敗", e);
        }
    }

    private void fillStatement(PreparedStatement ps, Pet pet) throws SQLException {
        ps.setString(1, pet.getName());
        ps.setString(2, pet.getSpecies());
        ps.setString(3, pet.getBreed());
        ps.setString(4, pet.getGender());
        ps.setInt(5, pet.getAge());
        ps.setDouble(6, pet.getWeight());
        ps.setString(7, pet.getChipNumber());
        ps.setString(8, pet.getHealthNote());
    }

    private Pet mapPet(ResultSet rs) throws SQLException {
        return new Pet(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("species"),
                rs.getString("breed"),
                rs.getString("gender"),
                rs.getInt("age"),
                rs.getDouble("weight"),
                rs.getString("chip_number"),
                rs.getString("health_note")
        );
    }
}

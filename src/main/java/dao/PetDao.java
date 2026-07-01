package dao;

import java.util.List;
import model.Pet;

public interface PetDao {
    void create(Pet pet);
    List<Pet> readAll();
    Pet findById(int id);
    void update(Pet pet);
    void delete(int id);
}

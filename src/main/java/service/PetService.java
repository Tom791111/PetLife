package service;

import java.util.List;
import model.Pet;

public interface PetService {
    void addPet(Pet pet);
    List<Pet> getAllPets();
    Pet getPetById(int id);
    void updatePet(Pet pet);
    void deletePet(int id);
}

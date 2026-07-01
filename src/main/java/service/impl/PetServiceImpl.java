package service.impl;

import dao.PetDao;
import dao.impl.PetDaoImpl;
import exception.AppException;
import model.Pet;
import service.PetService;

import java.util.List;

public class PetServiceImpl implements PetService {
    private final PetDao petDao = new PetDaoImpl();

    @Override
    public void addPet(Pet pet) {
        validatePet(pet);
        petDao.create(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petDao.readAll();
    }

    @Override
    public Pet getPetById(int id) {
        return petDao.findById(id);
    }

    @Override
    public void updatePet(Pet pet) {
        if (pet.getId() <= 0) {
            throw new AppException("請先選擇要修改的寵物資料");
        }
        validatePet(pet);
        petDao.update(pet);
    }

    @Override
    public void deletePet(int id) {
        if (id <= 0) {
            throw new AppException("請先選擇要刪除的寵物資料");
        }
        petDao.delete(id);
    }

    private void validatePet(Pet pet) {
        if (pet.getName() == null || pet.getName().trim().isEmpty()) {
            throw new AppException("請輸入寵物名稱");
        }
        if (pet.getSpecies() == null || pet.getSpecies().trim().isEmpty()) {
            throw new AppException("請選擇寵物種類");
        }
        if (pet.getAge() < 0) {
            throw new AppException("年齡不可小於 0");
        }
        if (pet.getWeight() < 0) {
            throw new AppException("體重不可小於 0");
        }
    }
}

package model;

public class Pet {
    private int id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private int age;
    private double weight;
    private String chipNumber;
    private String healthNote;

    public Pet() {
    }

    public Pet(String name, String species, String breed, String gender, int age, double weight, String chipNumber, String healthNote) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.chipNumber = chipNumber;
        this.healthNote = healthNote;
    }

    public Pet(int id, String name, String species, String breed, String gender, int age, double weight, String chipNumber, String healthNote) {
        this(name, species, breed, gender, age, weight, chipNumber, healthNote);
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getChipNumber() { return chipNumber; }
    public void setChipNumber(String chipNumber) { this.chipNumber = chipNumber; }
    public String getHealthNote() { return healthNote; }
    public void setHealthNote(String healthNote) { this.healthNote = healthNote; }
}

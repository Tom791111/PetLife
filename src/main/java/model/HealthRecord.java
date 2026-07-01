package model;

public class HealthRecord {
    private int id;
    private int petId;
    private String petName;
    private String recordDate;
    private double weight;
    private String appetite;
    private String stoolStatus;
    private String symptom;
    private String note;

    public HealthRecord() {}
    public HealthRecord(int id, int petId, String petName, String recordDate, double weight, String appetite, String stoolStatus, String symptom, String note) {
        this.id = id; this.petId = petId; this.petName = petName; this.recordDate = recordDate; this.weight = weight;
        this.appetite = appetite; this.stoolStatus = stoolStatus; this.symptom = symptom; this.note = note;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }
    public String getRecordDate() { return recordDate; }
    public void setRecordDate(String recordDate) { this.recordDate = recordDate; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getAppetite() { return appetite; }
    public void setAppetite(String appetite) { this.appetite = appetite; }
    public String getStoolStatus() { return stoolStatus; }
    public void setStoolStatus(String stoolStatus) { this.stoolStatus = stoolStatus; }
    public String getSymptom() { return symptom; }
    public void setSymptom(String symptom) { this.symptom = symptom; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}

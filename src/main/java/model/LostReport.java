package model;

public class LostReport {
    private int id;
    private int petId;
    private String petName;
    private String lostDate;
    private String lostLocation;
    private String contactPhone;
    private String status;
    private String description;

    public LostReport() {}
    public LostReport(int id, int petId, String petName, String lostDate, String lostLocation, String contactPhone, String status, String description) {
        this.id = id; this.petId = petId; this.petName = petName; this.lostDate = lostDate; this.lostLocation = lostLocation;
        this.contactPhone = contactPhone; this.status = status; this.description = description;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }
    public String getLostDate() { return lostDate; }
    public void setLostDate(String lostDate) { this.lostDate = lostDate; }
    public String getLostLocation() { return lostLocation; }
    public void setLostLocation(String lostLocation) { this.lostLocation = lostLocation; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

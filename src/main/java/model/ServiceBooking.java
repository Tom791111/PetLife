package model;

public class ServiceBooking {
    private int id;
    private int petId;
    private String petName;
    private String serviceType;
    private String providerName;
    private String bookingDate;
    private String status;
    private String note;

    public ServiceBooking() {}
    public ServiceBooking(int id, int petId, String petName, String serviceType, String providerName, String bookingDate, String status, String note) {
        this.id = id; this.petId = petId; this.petName = petName; this.serviceType = serviceType; this.providerName = providerName;
        this.bookingDate = bookingDate; this.status = status; this.note = note;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public String getProviderName() { return providerName; }
    public void setProviderName(String providerName) { this.providerName = providerName; }
    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}

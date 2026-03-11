package lk.ijse.gem_management_layered.dto;

public class SuppliersDTO {
    private int id;
    private String name;
    private String address;
    private int contact;

    public SuppliersDTO() {}

    public SuppliersDTO(String name, String address, int contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public SuppliersDTO(int id, String name, String address, int contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getContact() { return contact; }
    public void setContact(int contact) { this.contact = contact; }
}
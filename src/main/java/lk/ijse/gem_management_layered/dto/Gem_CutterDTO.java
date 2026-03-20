package lk.ijse.gem_management_layered.dto;

public class Gem_CutterDTO {

    private int cutterId;
    private String cutterName;
    private String contact;
    private String address;

    public Gem_CutterDTO(){}

    public Gem_CutterDTO(String cutterName, String contact, String address) {
        this.cutterName = cutterName;
        this.contact = contact;
        this.address = address;
    }

    public Gem_CutterDTO(int cutterId, String cutterName, String contact, String address) {
        this.cutterId = cutterId;
        this.cutterName = cutterName;
        this.contact = contact;
        this.address = address;
    }

    public int getCutterId() { return cutterId; }
    public void setCutterId(int cutterId) { this.cutterId = cutterId; }

    public String getCutterName() { return cutterName; }
    public void setCutterName(String cutterName) { this.cutterName = cutterName; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
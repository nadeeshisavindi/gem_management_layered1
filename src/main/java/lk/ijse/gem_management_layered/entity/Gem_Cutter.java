package lk.ijse.gem_management_layered.entity;

public class Gem_Cutter {

    private int cutterId;
    private String cutterName;
    private int contact;
    private String address;

    public Gem_Cutter(){}

    public Gem_Cutter(int cutterId, String cutterName, int contact, String address) {
        this.cutterId = cutterId;
        this.cutterName = cutterName;
        this.contact = contact;
        this.address = address;
    }

    public Gem_Cutter(String cutterName, int contact, String address) {
        this.cutterName = cutterName;
        this.contact = contact;
        this.address = address;
    }

    public int getCutterId() { return cutterId; }
    public void setCutterId(int cutterId) { this.cutterId = cutterId; }

    public String getCutterName() { return cutterName; }
    public void setCutterName(String cutterName) { this.cutterName = cutterName; }

    public int getContact() { return contact; }
    public void setContact(int contact) { this.contact = contact; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
package lk.ijse.gem_management_layered.dto;

public class SuppliersDTO {

        private int supplierId;
        private String name;
        private String address;
        private int contact;

        public SuppliersDTO() {}

        public SuppliersDTO(String name, String address, int contact) {
            this.name = name;
            this.address = address;
            this.contact = contact;
        }

        public SuppliersDTO(int supplierId, String name, String address, int contact) {
            this.supplierId = supplierId;
            this.name = name;
            this.address = address;
            this.contact = contact;
        }

        public int getSupplierId() { return supplierId; }
        public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }

        public int getContact() { return contact; }
        public void setContact(int contact) { this.contact = contact; }

}

package lk.ijse.gem_management_layered.dto;

public class SalesDTO {


        private int saleId;
        private String cancel;
        private String updateInfo;
        private String returnInfo;

        public SalesDTO() {}

        public SalesDTO(String cancel, String updateInfo, String returnInfo) {
            this.cancel = cancel;
            this.updateInfo = updateInfo;
            this.returnInfo = returnInfo;
        }

        public SalesDTO(int saleId, String cancel, String updateInfo, String returnInfo) {
            this.saleId = saleId;
            this.cancel = cancel;
            this.updateInfo = updateInfo;
            this.returnInfo = returnInfo;
        }

        public int getSaleId() { return saleId; }
        public void setSaleId(int saleId) { this.saleId = saleId; }

        public String getCancel() { return cancel; }
        public void setCancel(String cancel) { this.cancel = cancel; }

        public String getUpdateInfo() { return updateInfo; }
        public void setUpdateInfo(String updateInfo) { this.updateInfo = updateInfo; }

        public String getReturnInfo() { return returnInfo; }
        public void setReturnInfo(String returnInfo) { this.returnInfo = returnInfo; }

}

package lk.ijse.gem_management_layered.entity;

public class Gem {


        private int gemId;
        private String gemName;
        private String type;

        public Gem() {}

        public Gem(int gemId, String gemName, String type) {
            this.gemId = gemId;
            this.gemName = gemName;
            this.type = type;
        }

        public int getGemId() {
            return gemId;
        }

        public void setGemId(int gemId) {
            this.gemId = gemId;
        }

        public String getGemName() {
            return gemName;
        }

        public void setGemName(String gemName) {
            this.gemName = gemName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

}

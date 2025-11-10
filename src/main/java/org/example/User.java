package org.example;

public class User {
        private String naam;
        private String email;
        private String postcode;
        private int huisnummer;

        // Getters en setters
        public String getNaam() {
            return naam;
        }

        public void setNaam(String naam) {
            this.naam = naam;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public int getHuisnummer() {
            return huisnummer;
        }

        public void setHuisnummer(int huisnummer) {
            this.huisnummer = huisnummer;
        }

    @Override
    public String toString() {
        return "User{" +
                "naam='" + naam + '\'' +
                ", email='" + email + '\'' +
                ", postcode='" + postcode + '\'' +
                ", huisnummer=" + huisnummer +
                '}';
    }
}

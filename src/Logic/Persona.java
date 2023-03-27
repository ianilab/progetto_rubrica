package Logic;

import java.util.Objects;

public class Persona {
        private String nome, cognome, indirizzo, telefono;
        private int eta;

        public Persona(){
            nome = cognome = indirizzo = telefono = "";
            eta = -1;
        }
        public Persona(Object[] data){
            nome = (String) data[0];
            cognome = (String) data[1];
            indirizzo = (String) data[2];
            telefono = (String) data[3];
            eta = Integer.parseInt((String) data[4]);
        }
        public Persona(String nome, String cognome, String indirizzo, String telefono, int eta) {
            this.nome = nome;
            this.cognome = cognome;
            this.indirizzo = indirizzo;
            this.telefono = telefono;
            this.eta = eta;
        }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return nome.equals(persona.nome) && cognome.equals(persona.cognome) && Objects.equals(indirizzo, persona.indirizzo) && telefono.equals(persona.telefono) && Objects.equals(eta, persona.eta);
    }


}

package database.model;

import java.util.Dictionary;
import java.util.UUID;

public class ObiektUmieszczalny {
    UUID idObiektu;
    String nazwa;
    Dictionary<String ,String> zestawParametrow;

    public ObiektUmieszczalny(UUID idObiektu, String nazwa, Dictionary<String, String> zestawParametrow) {
        this.idObiektu = idObiektu;
        this.nazwa = nazwa;
        this.zestawParametrow = zestawParametrow;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setZestawParametrow(Dictionary<String, String> zestawParametrow) {
        this.zestawParametrow = zestawParametrow;
    }

    public String getNazwa() {
        return nazwa;
    }

    public Dictionary<String, String> getZestawParametrow() {
        return zestawParametrow;
    }
}

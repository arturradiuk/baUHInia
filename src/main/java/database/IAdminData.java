package database;

import database.model.ObiektUmieszczalny;

import java.util.List;

public interface IDaneAdministratora {
    List<ObiektUmieszczalny> pobierzObiekty();

    void dodajObiekt(ObiektUmieszczalny obj);
}

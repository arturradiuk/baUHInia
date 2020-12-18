package logic.service;

import database.IAdminData;
import database.model.ObiektUmieszczalny;

import java.util.Dictionary;
import java.util.List;
import java.util.UUID;

public class ManagerObiektow implements IObslugaObiektow{
    private List<ObiektUmieszczalny> obiekty;
    private IAdminData adminData;

    public ManagerObiektow() {
        // todo adminData = x
        aktualizujObiekty();
    }

    @Override
    public void addObject(String name, Dictionary<String, String> parameterSet) throws RepositoryException {
        ObiektUmieszczalny obj = new ObiektUmieszczalny(UUID.randomUUID(), name, parameterSet);
        if(obiekty.contains(obj)){
            throw new RepositoryException(RepositoryException.EXIST);
        }
        adminData.addObject(obj);
        aktualizujObiekty();
    }

    @Override
    public void removeObject(UUID objectID) {

    }

    @Override
    public void updateParameters(UUID objectID, Dictionary<String, String> newParametersSet) {

    }

    @Override
    public List<Dictionary<String, String>> getInfo() {
        return null;
    }
    void aktualizujObiekty(){
        obiekty = adminData.pobierzObiekty();
    }
}

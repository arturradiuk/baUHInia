package simulation_logic;

import java.util.ArrayList;

import java.util.UUID;

public class Simulation implements ISimulation {
    private final UUID ID;
    private String algorithm;

    public Simulation(UUID ID, String algorithm) {
        this.ID = ID;
        this.algorithm = algorithm;
    }

    public UUID getIDSimulation() {
        return ID;
    }

    public String getAlgorithmSimulation() {
        return algorithm;
    }

    public void setAlgorithmSimulation(String algorithm) {
        this.algorithm = algorithm;
    }

    public ArrayList<ArrayList<Integer>> runSimulation() {
        //tworzenie listy ktora bedzie pobierana (lista do danych o mapie) usunac poniżesz potem
        ArrayList<ArrayList<String>> mapList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            mapList.add(new ArrayList<String>());
        }
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (j <= 33) {
                    mapList.get(i).add(j, "asfalt");
                } else if (j <= 66) {
                    mapList.get(i).add(j, "kostka");
                } else {
                    mapList.get(i).add(j, "trawa");
                }
            }
        }
        //do tad usunac
        ArrayList<ArrayList<Integer>> listM = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            listM.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (mapList.get(i).get(j) == "asfalt") {
                    listM.get(i).add(j, 40);
                } else if (mapList.get(i).get(j) == "kostka") {
                    listM.get(i).add(j, 37);
                } else if (mapList.get(i).get(j) == "trawa") {
                    listM.get(i).add(j, 25);
                } else {
                    listM.get(i).add(j, 0);
                }
            }
        }
        return listM;
    }
}

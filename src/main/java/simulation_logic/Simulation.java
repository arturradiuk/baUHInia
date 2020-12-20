package simulation_logic;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import logic.service.IObjectsService;
import logic.service.IMapService;

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
    
    public void runSimulation(String algorithm, Map<String, String> map,List<Map<String,String>> List ) {
        /**
         * Place for simulation algorithm
         * 
         */
    }
}

package simulation_logic;

import java.util.ArrayList;
import java.util.UUID;


public interface ISimulation {
    public UUID getIDSimulation();

    public void updateHeatSimulationFactor(double newValue);

    public ArrayList<ArrayList<Double>> runSimulation(UUID Map_UUID);
    /*public ArrayList<ArrayList<Double>> runSimulation(Map map);*/
}

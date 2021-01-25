package simulation_logic;

import maps.api.Map;

import java.util.ArrayList;
import java.util.UUID;


public interface ISimulation {
public UUID getIDSimulation();
public void updateHeatSimulationFactor(double newValue);
public ArrayList<ArrayList<Integer>> runSimulation(UUID Map_UUID);
}

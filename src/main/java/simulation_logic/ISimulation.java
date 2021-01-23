package simulation_logic;

import java.util.ArrayList;
import java.util.UUID;


public interface ISimulation {
public UUID getIDSimulation();
public String getAlgorithmSimulation();
public void setAlgorithmSimulation(String algorithm);
public ArrayList<ArrayList<Integer>> runSimulation();
}

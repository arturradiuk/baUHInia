package simulation_logic;

import java.util.List;
import java.util.Map;
import java.util.UUID;


public interface ISimulation {
public UUID getIDSimulation();
public String getAlgorithmSimulation();
public void setAlgorithmSimulation(String algorithm);
public void runSimulation(String algorithm, Map<String, String> map,List<Map<String,String>> List );
}

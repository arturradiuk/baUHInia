package simulation_logic;


import maps.api.Cell;
import maps.api.Map;
import maps.api.MapObject;
import maps.api.services.FilesystemMapsProvider;
import maps.api.services.IMapsService;
import maps.api.services.MapsService;
import java.lang.Math;
import java.util.ArrayList;

import java.util.UUID;

import static java.lang.Math.*;

public class Simulation implements ISimulation {
    private final UUID ID;
    private double T_asphalt=40.04;
    private double T_concrete=33.62;
    private double T_grass=25.37;
    private double T_default= 21.0;
    IMapsService mapService;

    public Simulation(UUID ID) {
        this.ID = ID;
        mapService= new MapsService(new FilesystemMapsProvider("src/main/resources"));
    }

    public UUID getIDSimulation() {
        return ID;
    }

    @Override
    public void updateHeatSimulationFactor(double newValue) {
        T_asphalt+= newValue-T_default*2;
        T_concrete+=newValue-T_default*0.75;
        T_grass+= newValue-T_default*0.25;
        T_default= newValue;
    }


    public double simulate(String type, Cell cell){
        double value=0,R_ni=0,H_i=0,LE_i=0.5,C=0,U_s=1.0,SW_in=0,SW_out=0,LW_in=0,LW_out=0,albedo=0,S_d=5,S_q=4,F=0.746,cos_L_shadow=0.8751,L_shadow=0,sin_L_shadow=0
                ,L_d=304.23,emisity=0.97;
        C=6.15+4.18*U_s;
        sin_L_shadow=sqrt(1-cos_L_shadow*cos_L_shadow);
        switch (type){
            case "None":
                value = 37;
                return value;
            case "Road":
                if(cell.getPlacedObjectMetadata()!=null){
                    albedo=0.2;
                    LE_i=0.7;
                    L_shadow= cell.getPlacedObjectMetadata().getMapObject().getHeight()*(sin_L_shadow/cos_L_shadow)*sin(165-90);
                    SW_in=S_d*((cell.getPlacedObjectMetadata().getMapObject().getWidth()-L_shadow)/cell.getPlacedObjectMetadata().getMapObject().getWidth())*(1-albedo)+S_q*F*(1-albedo);
                    LW_in=emisity*(L_d*F+emisity*5.67*pow(10,-8)*pow(T_asphalt,4)*F-5.67*pow(10,-8)*pow(T_asphalt,4));
                    LW_out=(1-emisity)*L_d*F*F+(1-emisity)*emisity*5.67*pow(10,-8)*pow(T_asphalt,4)*F*F+emisity*(1-emisity)*5.67*pow(10,-8)*pow(T_asphalt,4)*F*F;
                    R_ni=SW_in*(1-albedo)+LW_in-LW_out;
                    H_i=C*(T_asphalt-T_default);
                   // value=T_asphalt+ (H_i/(1.225*pow(10,-0.4*0.1)*1))/LE_i;
                    value = (R_ni -H_i)/LE_i;
                    int m=0;
                }else value = T_asphalt;
                return value;
            case "Green":
                if(cell.getPlacedObjectMetadata()!=null) {
                    albedo = 0.3;
                    emisity = 0.95;
                    LE_i = 1.4;
                    L_shadow = cell.getPlacedObjectMetadata().getMapObject().getHeight() * (sin_L_shadow / cos_L_shadow) * sin(165 - 90);
                    SW_in = S_d * ((cell.getPlacedObjectMetadata().getMapObject().getWidth() - L_shadow) / cell.getPlacedObjectMetadata().getMapObject().getWidth()) * (1 - albedo) + S_q * F * (1 - albedo);
                    LW_in = (1-emisity) * (L_d * F + emisity * 5.67*pow(10,-8) * pow(T_grass, 4) * F - 5.67*pow(10,-8) * pow(T_grass, 4));
                    LW_out = (1 - emisity) * L_d * F * F + (1 - emisity) * emisity * 5.67*pow(10,-8) * pow(T_grass, 4) * F * F + emisity * (1 - emisity) * 5.67*pow(10,-8)* pow(T_grass, 4) * F * F;
                    R_ni = SW_in * (1 - albedo) + LW_in - LW_out;
                    H_i = C * (T_grass - T_default);
                    value = ((H_i-R_ni-cell.getPlacedObjectMetadata().getMapObject().getHeatFactor())/LE_i) ;
                    int m=0;
                }else value = T_grass;
                return value;
            case "Concrete":
                if(cell.getPlacedObjectMetadata()!=null) {
                    albedo=0.2;

                    L_shadow= cell.getPlacedObjectMetadata().getMapObject().getHeight()*(sin_L_shadow/cos_L_shadow)*sin(165-90);
                    SW_in=S_d*(L_shadow/2*cell.getPlacedObjectMetadata().getMapObject().getHeight())*(1-albedo)+S_q*F*(1-albedo);
                    LW_in=emisity*(L_d*F+emisity*5.67*pow(10,-8)*pow(T_concrete,4)*F)+emisity*5.67*pow(10,-8)*pow(T_concrete,4)*F-5.67*pow(10,-8)*pow(T_concrete,4);
                    LW_out=emisity*((1-emisity)*L_d*F*F+(1-emisity)*emisity*5.67*pow(10,-8)*pow(T_concrete,4)*F*F+(1-emisity)*L_d*F*F-(1-emisity)*emisity*5.67*pow(10,-8)*pow(T_concrete,4)*F*F);
                    LW_in+=emisity*(L_d-5.67*pow(10,-8)*pow(T_concrete,4));
                    LW_out+=emisity*(L_d-5.67*pow(10,-8)*pow(T_concrete,4));
                    R_ni=SW_in*(1-albedo)+LW_in-LW_out;
                    H_i=C*(T_concrete-T_default);
                    value = (R_ni -H_i)/LE_i;
                    int m=0;
                 }else value = T_concrete;

                return value;
            default:
                return 28;
        }

    }

    public ArrayList<ArrayList<Double>> runSimulation(UUID map_uuid) {
        ArrayList<ArrayList<Double>> heat_val = new ArrayList<>();
        for(int i=0;i<100;i++)
        {
            heat_val.add(new ArrayList<Double>());
            for(int j=0;j<100;j++)
            {
                if(mapService.getMap(map_uuid).get(i,j).getType().toString()=="None") heat_val.get(i).add( simulate("None",mapService.getMap(map_uuid).get(i,j)));
                else if(mapService.getMap(map_uuid).get(i,j).getType().toString()=="Road") heat_val.get(i).add( simulate("Road",mapService.getMap(map_uuid).get(i,j)));
                else if(mapService.getMap(map_uuid).get(i,j).getType().toString()=="Green") heat_val.get(i).add( simulate("Green",mapService.getMap(map_uuid).get(i,j)));
                else if(mapService.getMap(map_uuid).get(i,j).getType().toString()=="Concrete") heat_val.get(i).add( simulate("Building",mapService.getMap(map_uuid).get(i,j)));
                else if(mapService.getMap(map_uuid).get(i,j).getType().toString()=="Building") heat_val.get(i).add( simulate("Building",mapService.getMap(map_uuid).get(i,j)));
                else heat_val.get(i).add(32.0);
            }
        }
        return heat_val;
    }
    public ArrayList<ArrayList<Double>> runSimulation(Map map) {
        ArrayList<ArrayList<Double>> heat_val = new ArrayList<>();
        for(int i=0;i<50;i++)
        {
            heat_val.add(new ArrayList<Double>());
            for(int j=0;j<50;j++)
            {

                if(map.get(i,j).getType().toString()=="Road") heat_val.get(i).add( simulate("Road",map.get(i,j)));
                else if(map.get(i,j).getType().toString()=="Green") heat_val.get(i).add( simulate("Green",map.get(i,j)));
                else if(map.get(i,j).getType().toString()=="Building") heat_val.get(i).add( simulate("Building",map.get(i,j)));
                else if(map.get(i,j).getType().toString()=="Concrete") heat_val.get(i).add( simulate("Building",map.get(i,j)));
                else if(map.get(i,j).getType().toString()=="None") heat_val.get(i).add(( simulate("None",map.get(i,j))));
                else heat_val.get(i).add(32.0);
            }
        }
        return heat_val;
    }
}

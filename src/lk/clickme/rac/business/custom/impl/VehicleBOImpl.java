/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.clickme.rac.business.custom.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.clickme.rac.business.custom.VehicleBO;
import lk.clickme.rac.controller.RentCarFormController;
import lk.clickme.rac.dao.DAOFactory;
import static lk.clickme.rac.dao.DAOFactory.DAOType.Rentaldetail;
import lk.clickme.rac.dao.custom.RentaldetailDAO;
import lk.clickme.rac.dao.custom.VehicleDAO;
import lk.clickme.rac.dto.RentaldetailDTO;
import lk.clickme.rac.dto.VehicleDTO;
import lk.clickme.rac.entity.Rentaldetail;
import lk.clickme.rac.entity.Vehicle;

/**
 *
 * @author Harsha madushan
 */
public class VehicleBOImpl implements VehicleBO{

    VehicleDAO vehicleDAO;
    RentaldetailDAO rentaldetailDAO;

    public VehicleBOImpl() {
        this.rentaldetailDAO = (RentaldetailDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Rentaldetail);
        this.vehicleDAO = (VehicleDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Vehicle);
    }
    
    @Override
    public boolean saveVehicle(VehicleDTO vehicle) throws Exception {
        Vehicle Evehicle = new Vehicle(vehicle.getvID(), vehicle.getvNO(), vehicle.getCategory(), vehicle.getBrand(), vehicle.getvRate());
        return vehicleDAO.save(Evehicle);
    }

    @Override
    public boolean updateVehicle(VehicleDTO vehicle) throws Exception {
        Vehicle Evehicle = new Vehicle(vehicle.getvID(), vehicle.getvNO(), vehicle.getCategory(), vehicle.getBrand(), vehicle.getvRate());
        return vehicleDAO.update(Evehicle);
    }

    @Override
    public boolean deleteVehicle(int id) throws Exception {
        return vehicleDAO.delete(id);
    }

    @Override
    public VehicleDTO findByID(int id) throws Exception {
        Vehicle eVehicle = vehicleDAO.findByID(id);
        VehicleDTO vehicleDTO = new VehicleDTO(eVehicle.getvID(), eVehicle.getvNO(), eVehicle.getCategory(), eVehicle.getBrand(), eVehicle.getvRate());
        return vehicleDTO;
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehicle() throws Exception {
        ArrayList<Vehicle> vehicles = vehicleDAO.getAll();
        ArrayList<VehicleDTO> vehicleDTOs = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            VehicleDTO vehicleDTO = new VehicleDTO(vehicle.getvID(), vehicle.getvNO(), vehicle.getCategory(), vehicle.getBrand(), vehicle.getvRate());
            vehicleDTOs.add(vehicleDTO);
        }
        return vehicleDTOs;
    }

    /*@Override
    public ArrayList<VehicleDTO> getAvailableVehicles() throws Exception{
       
            ArrayList<Rentaldetail> Rentals = rentaldetailDAO.getAll();
            ArrayList<Integer> rentedvehicleIDs = new ArrayList<>();
            for (Rentaldetail ERental : Rentals) {
                if(ERental.getRentalState() == "continue"){
                    int vehileNumber = ERental.getRentaldetail_PK().getVehicleID();
                    rentdvehicleIDs.add(vehileNumber);
                }
            }
            ArrayList<RentaldetailDAO> rentaldetailDAO = new ArrayList<>();
            ArrayList<VehicleDTO> vehiclesDTO = new ArrayList<>();
            ArrayList<Vehicle> allvehicles = vehicleDAO.getAll();
            for (Vehicle vehicleID : allvehicles) {
                
                VehicleDTO vehicleDTO = new VehicleDTO(vehicle.getvID(), vehicle.getvNO(), vehicle.getCategory(), vehicle.getBrand(), vehicle.getvRate());
                vehiclesDTO.add(vehicleDTO);
            }
            return vehiclesDTO;
       
    }*/
    
}

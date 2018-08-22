/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.clickme.rac.business.custom.impl;

import java.util.ArrayList;
import lk.clickme.rac.business.BOFactory;
import lk.clickme.rac.business.custom.CustomerBO;
import lk.clickme.rac.business.custom.RentalDetailBO;
import lk.clickme.rac.dao.DAOFactory;
import lk.clickme.rac.dao.custom.RentaldetailDAO;
import lk.clickme.rac.dto.RentaldetailDTO;
import lk.clickme.rac.entity.Rentaldetail;

/**
 *
 * @author Harsha madushan
 */
public class RentaldetailBOImpl implements RentalDetailBO{

    RentaldetailDAO rentaldetailDAO;
    CustomerBO customerBO;

    public RentaldetailBOImpl() {
        this.customerBO = (CustomerBO)BOFactory.getInstance().getBO(BOFactory.BOType.CustomerBO);
        this.rentaldetailDAO = (RentaldetailDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Rentaldetail);
    }
    
    @Override
    public boolean saveRentaldetail(RentaldetailDTO rentaldetailDTO) throws Exception {
        Rentaldetail rentaldetail = new Rentaldetail(rentaldetailDTO.getRentalID(),rentaldetailDTO.getCustomerID(),rentaldetailDTO.getVehicleID(),rentaldetailDTO.getRentalState(),rentaldetailDTO.getRentFrom(),rentaldetailDTO.getRentTo());
        return rentaldetailDAO.save(rentaldetail);
    }

    @Override
    public boolean updateRentaldetail(RentaldetailDTO rentaldetailDTO) throws Exception {
        Rentaldetail rentaldetail = new Rentaldetail(rentaldetailDTO.getRentalID(),rentaldetailDTO.getCustomerID(), rentaldetailDTO.getVehicleID(), rentaldetailDTO.getRentalState(), rentaldetailDTO.getRentFrom(), rentaldetailDTO.getRentTo());
        return rentaldetailDAO.update(rentaldetail);
    }

    @Override
    public boolean deleteRentaldetail(int id) throws Exception {
        
        return rentaldetailDAO.delete(id);
        
    }

    @Override
    public RentaldetailDTO findByID(int id) throws Exception {
        
        Rentaldetail rentaldetail = rentaldetailDAO.findByID(id);
        RentaldetailDTO rentaldetailDTO = new RentaldetailDTO(rentaldetail.getRentalID(),rentaldetail.getCustomerID(),rentaldetail.getVehicleID(), rentaldetail.getRentalState(), rentaldetail.getRentFrom(), rentaldetail.getRentTo());
        return rentaldetailDTO;
    }

    @Override
    public ArrayList<RentaldetailDTO> getAllRentaldetail() throws Exception {
       ArrayList<Rentaldetail> rentaldetails = rentaldetailDAO.getAll();
       ArrayList<RentaldetailDTO> rentadetailDTOs = new ArrayList<RentaldetailDTO>();
        for (Rentaldetail rentaldetail : rentaldetails) {
            RentaldetailDTO rentaldetailDTO = new RentaldetailDTO(rentaldetail.getRentalID(),rentaldetail.getCustomerID(),rentaldetail.getVehicleID(), rentaldetail.getRentalState(), rentaldetail.getRentFrom(), rentaldetail.getRentTo());
            rentadetailDTOs.add(rentaldetailDTO);
        }
        return rentadetailDTOs;
    }

   /*@Override
    public ArrayList<CustomerDTO> getAllFinalRentalDetails() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String newDate = dateFormat.format(date);
        
        
        ArrayList<CustomerDTO> Allrentaldetails = new ArrayList<>();
        ArrayList<RentaldetailDTO> rentaldetails = getAllRentaldetail();
        for (RentaldetailDTO rentaldetail : rentaldetails) {
            
                    
        }
        ArrayList<CustomerDTO> customerdetails = customerBO.getAllCustomer();
        
        
    }*/
    
}

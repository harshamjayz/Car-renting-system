/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.clickme.rac.business.custom.impl;

import java.sql.Connection;
import java.util.ArrayList;
import lk.clickme.rac.business.custom.PaymentBO;
import lk.clickme.rac.dao.DAOFactory;
import lk.clickme.rac.dao.custom.PaymentDAO;
import lk.clickme.rac.dao.custom.RentaldetailDAO;
import lk.clickme.rac.db.DBConnection;
import lk.clickme.rac.dto.PaymentDTO;
import lk.clickme.rac.dto.RentaldetailDTO;
import lk.clickme.rac.entity.Payment;
import lk.clickme.rac.entity.Rentaldetail;

/**
 *
 * @author Harsha madushan
 */
public class PaymentBOImpl implements PaymentBO{

    PaymentDAO paymentDAO;
    RentaldetailDAO rentaldetailDAO;

    public PaymentBOImpl() {
        this.rentaldetailDAO = (RentaldetailDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Rentaldetail);
        this.paymentDAO = (PaymentDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Payment);
    }
    
    @Override
    public boolean savePayment(PaymentDTO payment) throws Exception {
        Payment Epayment = new Payment(payment.getpID(), payment.getcID(),payment.getRentID(), payment.getpMethod(), payment.getPaneltyFee(), payment.getAmount());
        return paymentDAO.save(Epayment);
    }

    @Override
    public boolean updatePayment(PaymentDTO payment) throws Exception {
        Payment Epayment = new Payment(payment.getpID(), payment.getcID(),payment.getRentID(), payment.getpMethod(), payment.getPaneltyFee(), payment.getAmount());
        return paymentDAO.update(Epayment);
    }

    @Override
    public boolean deletePayment(int id) throws Exception {
        return paymentDAO.delete(id);
    }

    @Override
    public PaymentDTO findByID(int id) throws Exception {
        Payment payment = paymentDAO.findByID(id);
        PaymentDTO paymentDTO = new PaymentDTO(payment.getpID(), payment.getcID(),payment.getRentID(), payment.getpMethod(), payment.getPaneltyFee(), payment.getAmount());
        return paymentDTO;
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayment() throws Exception {
        ArrayList<Payment> payments = paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDTOs = new ArrayList<>();
        for (Payment payment : payments) {
            PaymentDTO paymentDTO = new PaymentDTO(payment.getpID(), payment.getcID(),payment.getRentID(), payment.getpMethod(), payment.getPaneltyFee(), payment.getAmount());
            paymentDTOs.add(paymentDTO);
        }
        return paymentDTOs;
    }

    @Override
    public boolean FinishPayment(PaymentDTO payment, RentaldetailDTO rentaldetail)throws Exception{
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            Payment Epayment = new Payment(0,payment.getcID(), payment.getRentID(), payment.getpMethod(), payment.getPaneltyFee(), payment.getAmount());
            Boolean result1 = paymentDAO.save(Epayment);
            if(result1 == true){
                Rentaldetail Erentaldetail = new Rentaldetail(rentaldetail.getRentalID(), rentaldetail.getCustomerID(), rentaldetail.getVehicleID(), rentaldetail.getRentalState(), rentaldetail.getRentFrom(), rentaldetail.getRentTo());
                Boolean result2 = rentaldetailDAO.update(Erentaldetail);
                if (!result2) {
                    connection.rollback();
                    return false;
                }
            }else{
                connection.rollback();
                return false;
            }
        } catch (Exception ex) {
            connection.rollback();
            throw ex;
        }finally{
            connection.setAutoCommit(true);
        }
        return true;
        
    }
    
    
    
}

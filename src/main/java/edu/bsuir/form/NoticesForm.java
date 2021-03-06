package edu.bsuir.form;

import edu.bsuir.model.Drivers;
import edu.bsuir.model.Users;

import java.sql.Date;

public class NoticesForm {
    private int id;
    private Date dateOfLoading;
    private String typeOfRollingStock;
    private String placeOfCustomsClearance;
    private String orderOfLoading;
    private String transportDocument;
    private String status;

    private Drivers driver;
    private Users forwarder;

    public NoticesForm(){}

    public NoticesForm(String typeOfRollingStock, Date dateOfLoading, String placeOfCustomsClearance, String orderOfLoading, String transportDocument, String status) {
        this.dateOfLoading = dateOfLoading;
        this.typeOfRollingStock = typeOfRollingStock;
        this.placeOfCustomsClearance = placeOfCustomsClearance;
        this.orderOfLoading = orderOfLoading;
        this.transportDocument = transportDocument;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOfLoading() {
        return dateOfLoading;
    }

    public void setDateOfLoading(Date dateOfLoading) {
        this.dateOfLoading = dateOfLoading;
    }

    public String getTypeOfRollingStock() {
        return typeOfRollingStock;
    }

    public void setTypeOfRollingStock(String typeOfRollingStock) {
        this.typeOfRollingStock = typeOfRollingStock;
    }

    public String getPlaceOfCustomsClearance() {
        return placeOfCustomsClearance;
    }

    public void setPlaceOfCustomsClearance(String placeOfCustomsClearance) { this.placeOfCustomsClearance = placeOfCustomsClearance; }

    public String getOrderOfLoading() {
        return orderOfLoading;
    }

    public void setOrderOfLoading(String orderOfLoading) {
        this.orderOfLoading = orderOfLoading;
    }

    public String getTransportDocument() {
        return transportDocument;
    }

    public void setTransportDocument(String transportDocument) {
        this.transportDocument = transportDocument;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Drivers getDriver() {
        return driver;
    }

    public void setDriver(Drivers driver) {
        this.driver = driver;
    }

    public Users getForwarder() {
        return forwarder;
    }

    public void setForwarder(Users forwarder) {
        this.forwarder = forwarder;
    }
}

package com.app.personalhealthmonitor.model;

import java.util.Date;

public class RDV {
    private int noRDV;
    private Date dateRDV;
    private Date datePriseRDV;
    private String nomMedicine;

    public RDV(int noRDV, Date dateRDV, Date datePriseRDV, String nomMedicine) {
        this.noRDV = noRDV;
        this.dateRDV = dateRDV;
        this.datePriseRDV = datePriseRDV;
        this.nomMedicine = nomMedicine;
    }

    public int getNoRDV() {
        return noRDV;
    }

    public void setNoRDV(int noRDV) {
        this.noRDV = noRDV;
    }

    public Date getDateRDV() {
        return dateRDV;
    }

    public void setDateRDV(Date dateRDV) {
        this.dateRDV = dateRDV;
    }

    public Date getDatePriseRDV() {
        return datePriseRDV;
    }

    public void setDatePriseRDV(Date datePriseRDV) {
        this.datePriseRDV = datePriseRDV;
    }

    public String getNomMedicine() {
        return nomMedicine;
    }

    public void setNomMedicine(String nomMedicine) {
        this.nomMedicine = nomMedicine;
    }


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.models;

/**
 *
 * @author mhdja
 */
public class HasilTukModel {
    private int id;
    private int idTuk;
    private double nilai;
    private String remarks;
    private String namaTuk;

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTuk() {
        return idTuk;
    }

    public void setIdTuk(int idTuk) {
        this.idTuk = idTuk;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
       /**
     * @return the namaTuk
     */
    public String getNamaTuk() {
        return namaTuk;
    }

    /**
     * @param namaTuk the namaTuk to set
     */
    public void setNamaTuk(String namaTuk) {
        this.namaTuk = namaTuk;
    }
}

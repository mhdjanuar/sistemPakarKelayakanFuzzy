/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.models;

/**
 *
 * @author mhdja
 */
public class KaryawanModel {
    private int id;
    private String name;
    private int usia;
    private String kontak;
    private String email;
    private String gender;
    private String alamat;
    private String jabatan;
    

    /**
     * @return the usia
     */
    public int getUsia() {
        return usia;
    }

    /**
     * @param usia the usia to set
     */
    public void setUsia(int usia) {
        this.usia = usia;
    }

    /**
     * @return the kontak
     */
    public String getKontak() {
        return kontak;
    }

    /**
     * @param kontak the kontak to set
     */
    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    /**
     * @return the jabatan
     */
    public String getJabatan() {
        return jabatan;
    }

    /**
     * @param jabatan the jabatan to set
     */
    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
}

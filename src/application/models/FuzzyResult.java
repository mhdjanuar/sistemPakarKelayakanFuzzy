/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.models;

/**
 *
 * @author mhdja
 */
public class FuzzyResult {
    private String variabel;
    private double input;
    private String label;
    private double nilaiMembership;
    private String rumus;

    public FuzzyResult(String variabel, double input, String label, double nilaiMembership, String rumus) {
        this.variabel = variabel;
        this.input = input;
        this.label = label;
        this.nilaiMembership = nilaiMembership;
        this.rumus = rumus;
    }
    
    
    /**
     * @return the variabel
     */
    public String getVariabel() {
        return variabel;
    }

    /**
     * @param variabel the variabel to set
     */
    public void setVariabel(String variabel) {
        this.variabel = variabel;
    }

    /**
     * @return the input
     */
    public double getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(double input) {
        this.input = input;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the nilaiMembership
     */
    public double getNilaiMembership() {
        return nilaiMembership;
    }

    /**
     * @param nilaiMembership the nilaiMembership to set
     */
    public void setNilaiMembership(double nilaiMembership) {
        this.nilaiMembership = nilaiMembership;
    }

    /**
     * @return the rumus
     */
    public String getRumus() {
        return rumus;
    }

    /**
     * @param rumus the rumus to set
     */
    public void setRumus(String rumus) {
        this.rumus = rumus;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f) → [%s] μ=%.2f, Rumus: %s", getVariabel(), getInput(), getLabel(), getNilaiMembership(), getRumus());
    }
}

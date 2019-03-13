package br.com.infisctest.service;

public class ClientTotalSale {

    private int Id;
    private String Name;
    private double Value;

    public ClientTotalSale() {

    }

    public ClientTotalSale(int id, String name, double value) {
        Id = id;
        Name = name;
        Value = value;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }
    
    
}

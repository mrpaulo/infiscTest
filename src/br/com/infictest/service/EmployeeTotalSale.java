package br.com.infictest.service;

public class EmployeeTotalSale {

    private int Id;
    private String Name;
    private double Value;

    public EmployeeTotalSale() {

    }

    public EmployeeTotalSale(int id, String name, double value) {
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

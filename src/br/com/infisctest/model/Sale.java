package br.com.infisctest.model;

public class Sale {

    private int Id;
    private int EmployeeId;
    private int ClientId;
    private double Value;
    private int SaleDateDay;
    private int SaleDateMonth;
    private int SaleDateYear;

    public Sale() {
    }

    public Sale(String id, String employeeId, String clientId, String value, String[] saleDate) {
        Id = Integer.parseInt(id);
        EmployeeId = Integer.parseInt(employeeId);
        ClientId = Integer.parseInt(clientId);
        Value = Double.parseDouble(value.replace(",", "."));
        SaleDateDay = Integer.parseInt(saleDate[2]);
        SaleDateMonth = Integer.parseInt(saleDate[1]);
        SaleDateYear = Integer.parseInt(saleDate[0]);
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int ClientId) {
        this.ClientId = ClientId;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public int getSaleDateDay() {
        return SaleDateDay;
    }

    public void setSaleDateDay(int SaleDateDay) {
        this.SaleDateDay = SaleDateDay;
    }

    public int getSaleDateMonth() {
        return SaleDateMonth;
    }

    public void setSaleDateMonth(int SaleDateMonth) {
        this.SaleDateMonth = SaleDateMonth;
    }

    public int getSaleDateYear() {
        return SaleDateYear;
    }

    public void setSaleDateYear(int SaleDateYear) {
        this.SaleDateYear = SaleDateYear;
    }
}

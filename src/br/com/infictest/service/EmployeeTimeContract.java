package br.com.infictest.service;

public class EmployeeTimeContract {

    private int EmployeeId;
    private String Name;
    private int YearQuantity;
    private int MonthQuantity;
    private int DayQuantity;

    public EmployeeTimeContract() {

    }

    public EmployeeTimeContract(int employeeId, String name, int yearQuantity, int monthQuantity, int dayQuantity) {

        EmployeeId = employeeId;
        Name = name;
        YearQuantity = yearQuantity;
        MonthQuantity = monthQuantity;
        DayQuantity = dayQuantity;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getYearQuantity() {
        return YearQuantity;
    }

    public void setYearQuantity(int YearQuantity) {
        this.YearQuantity = YearQuantity;
    }

    public int getMonthQuantity() {
        return MonthQuantity;
    }

    public void setMonthQuantity(int MonthQuantity) {
        this.MonthQuantity = MonthQuantity;
    }

    public int getDayQuantity() {
        return DayQuantity;
    }

    public void setDayQuantity(int DayQuantity) {
        this.DayQuantity = DayQuantity;
    }
    
    
}

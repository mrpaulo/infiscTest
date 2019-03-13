package br.com.infictest.service;

public class EmployeeTime {

    private int EmployeeId;
    private String Name;
    private Long TotalTime;
    private int Day;
    private int Month;
    private int Year;

    public EmployeeTime() {

    }

    public EmployeeTime(int employeeId, String name, long totalTime, int day, int month, int year) {

        EmployeeId = employeeId;
        Name = name;
        TotalTime = totalTime;
        Day = day;
        Month = month;
        Year = year;
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

    public Long getTotalTime() {
        return TotalTime;
    }

    public void setTotalTime(Long TotalTime) {
        this.TotalTime = TotalTime;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int Day) {
        this.Day = Day;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int Month) {
        this.Month = Month;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }
    
    
}

package br.com.infisctest.model;

public class Employee extends Person {

    private int ContractDateDay;
    private int ContractDateMonth;
    private int ContractDateYear;

    public Employee() {
    }

    public Employee(String id, String name, String[] contractDate) {
        super(Integer.parseInt(id), name);        
        this.ContractDateDay = Integer.parseInt(contractDate[2]);
        this.ContractDateMonth = Integer.parseInt(contractDate[1]);
        this.ContractDateYear = Integer.parseInt(contractDate[0]);
    }

    public int getContractDateDay() {
        return ContractDateDay;
    }

    public void setContractDateDay(int ContractDateDay) {
        this.ContractDateDay = ContractDateDay;
    }

    public int getContractDateMonth() {
        return ContractDateMonth;
    }

    public void setContractDateMonth(int ContractDateMonth) {
        this.ContractDateMonth = ContractDateMonth;
    }

    public int getContractDateYear() {
        return ContractDateYear;
    }

    public void setContractDateYear(int ContractDateYear) {
        this.ContractDateYear = ContractDateYear;
    }

}

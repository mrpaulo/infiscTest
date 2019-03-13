package br.com.infisctest.model;

public class Client extends Person {

    private int BirthDateDay;
    private int BirthDateMonth;
    private int BirthDateYear;

    public Client() {
    }

    public Client(String id, String name, String[] birthDate) {
        super(Integer.parseInt(id), name); 
        BirthDateDay = Integer.parseInt(birthDate[2]);
        BirthDateMonth = Integer.parseInt(birthDate[1]);
        BirthDateYear = Integer.parseInt(birthDate[0]);
    }

    public int getBirthDateDay() {
        return BirthDateDay;
    }

    public void setBirthDateDay(int BirthDateDay) {
        this.BirthDateDay = BirthDateDay;
    }

    public int getBirthDateMonth() {
        return BirthDateMonth;
    }

    public void setBirthDateMonth(int BirthDateMonth) {
        this.BirthDateMonth = BirthDateMonth;
    }

    public int getBirthDateYear() {
        return BirthDateYear;
    }

    public void setBirthDateYear(int BirthDateYear) {
        this.BirthDateYear = BirthDateYear;
    }
    
}

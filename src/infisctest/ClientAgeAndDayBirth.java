package infisctest;

public class ClientAgeAndDayBirth {

    private String Name;
    private int Age;
    private int DayBirth;

    public ClientAgeAndDayBirth(String name, int age, int day) {
        Name = name;
        Age = age;
        DayBirth = day;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public int getDayBirth() {
        return DayBirth;
    }

    public void setDayBirth(int DayBirth) {
        this.DayBirth = DayBirth;
    }
    
    
}

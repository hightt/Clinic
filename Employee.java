import java.time.LocalDate;

public abstract class Employee extends Person {
    protected String profession;
    protected double salary;
    protected LocalDate contractTerm;

    public Employee(String name, String birthdayDate, String pesel, String nationality, String specialiation) throws Exception
    {
        super(name, birthdayDate, pesel, nationality);
        LocalDate now = LocalDate.now(); 
        this.contractTerm = now.plusYears(1);

        this.profession = this.getClass().getName();
        if(profession == "Nurse") this.salary = 4000;
        else if(profession == "Doctor") this.salary = 7500;
    }

    public String toString()
    {
        return super.toString() + "\nZawód: " + this.profession + "\nPensja: " + this.salary + " zl\nUmowa o prace do: " + this.contractTerm;
    }

    public String getProfession()
    {
        return this.profession;
    }
    
    public double getSalary()
    {
        return this.salary;
    }

    public LocalDate getcontractTerm()
    {
        return this.contractTerm;
    }

}

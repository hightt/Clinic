import java.time.LocalDate;
import java.time.Period;

public abstract class Person extends Validator{

    protected String name;
    protected String birthdayDate;
    protected String pesel;
    protected String nationality;

    public Person(String name, String birthdayDate, String pesel, String nationality) throws Exception
    {
        if(validate(name, birthdayDate, pesel, nationality)) {
            this.name = name;
            this.birthdayDate = birthdayDate;
            this.pesel = pesel;
            this.nationality = nationality;
        }
        else throw new Exception();
    }

    public int getAge()
    {
        String[] arrSplit = this.birthdayDate.split("-");
        LocalDate birthdayDate = LocalDate.of(Integer.parseInt(arrSplit[2]), Integer.parseInt(arrSplit[1]), Integer.parseInt(arrSplit[0]));
        LocalDate now = LocalDate.now(); 
        Period diff = Period.between(birthdayDate, now); 
        return diff.getYears();
    }

    public String getName()
    {
        return this.name;
    }

    public String getBirthdayDate()
    {
        return this.birthdayDate;
    }

    public String getPesel()
    {
        return this.pesel;
    }

    public String getNationality()
    {
        return this.nationality;
    }

    public String toString()
    {
        return "Imie i nazwisko: " + this.name + "\n" + "Data urodzenia: " + this.birthdayDate + "\n" + "PESEL: " + this.pesel + "\nNarodowosc: " + this.nationality;
    }
    
    public void editData(String[] inputVal)
    {
        this.name = inputVal[0];
        this.birthdayDate = inputVal[1];
        this.pesel = inputVal[2];
        this.nationality = inputVal[3];
    }

    public String line()
    {
        return this.name + ',' + this.birthdayDate + ',' + this.pesel + ',' + this.nationality;
    }

    public int[] birthdayDateToArray(String bD)
    {
        String[] date = bD.split("-");
        int[] birthdayDate = {Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])};
        return birthdayDate;
    }

    public String compareTo(Person o) {
        int[] birthdayDateThis = this.birthdayDateToArray(this.getBirthdayDate());
        int[] birthdayDateObject = this.birthdayDateToArray(o.getBirthdayDate());
        int result = birthdayDateThis[2] - birthdayDateObject[2];
        if(result == 0){
            result = birthdayDateThis[1] - birthdayDateObject[1];
            if(result == 0){
                result = birthdayDateThis[0] - birthdayDateObject[0];
            }
        }
        if(result > 0)
            return this.getBirthdayDate() + " > " + o.getBirthdayDate();
        if(result < 0)
            return this.getBirthdayDate() + " < " + o.getBirthdayDate();
        else
           return this.getBirthdayDate() + " == " + o.getBirthdayDate();
    }

    public boolean equals(Person o)
    {
        int[] birthdayDateThis = this.birthdayDateToArray(this.getBirthdayDate());
        int[] birthdayDateObject = this.birthdayDateToArray(o.getBirthdayDate());
        if((birthdayDateThis[0] == birthdayDateObject[0]) && (birthdayDateThis[1] == birthdayDateObject[1]) && 
            (birthdayDateThis[2] == birthdayDateObject[2]))
            return true;
        else
            return false;
    }

}

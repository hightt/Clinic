public class Nurse extends Employee{
    protected String specialization;

    public Nurse(String name, String birthdayDate, String pesel, String nationality, String specialization) throws Exception
    {
        super(name, birthdayDate, pesel, nationality, specialization);
        this.specialization = specialization;
	}
   
    public String toString(){
        return super.toString() + "\nSpecjalizacja: " + this.specialization + "\n";
    }
    public void editNurseData(String[] inputVal)
    {
        this.editData(inputVal);
        this.specialization = inputVal[4];
    }
    public String generateLine()
    {
        return "N:" + this.line() + ',' + this.specialization;
    }
}

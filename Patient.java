public class Patient extends Person{
    protected String disease;
   
    public Patient(String name, String pesel, String birthdayDate, String nationality, String disease) throws Exception{
        super(name, pesel, birthdayDate, nationality);
		this.disease = disease;
	}

    public String toString()
    {
        return super.toString() + "\nChoroba: " + this.disease + "\n";
    }
    public void editPatientData(String[] inputVal)
    {
        this.editData(inputVal);
        this.disease = inputVal[4];
    }
    public String generateLine()
    {
        return "P:" + this.line() + ',' + this.disease;
    }

    
}

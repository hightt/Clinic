import java.util.ArrayList;

public class Clinic extends Validator{
    private String name;
    private String address;
    private String opening_hours;
    private ArrayList<Doctor> Doctors = new ArrayList<Doctor>();
    private ArrayList<Nurse> Nurses = new ArrayList<Nurse>();
    private ArrayList<Patient> Patients = new ArrayList<Patient>();
    
    public Clinic(String name, String address, String opening_hours){
        this.name = name;
		this.address = address;
		this.opening_hours = opening_hours;
	}
    public Clinic(){
        this.name = "";
		this.address = "";
		this.opening_hours = "";
	}

    public String toString(){
        return "Nazwa: " + this.name + "\n" + "Adres: " + this.address + "\n" + "Godziny otwarcia: " + this.opening_hours + "\n";
    }

    public ArrayList<Doctor> doctors()
    {
        return this.Doctors;
    }

    public ArrayList<Nurse> nurses()
    {
        return this.Nurses;
    }

    public ArrayList<Patient> patients()
    {
        return this.Patients;
    }

    public void showDoctors(ArrayList<Doctor> Objects)
    {
        int i = 0;
        System.out.println("Lekarze dostepni w przychodni ,," + this.name + "``: \n");
        for(Doctor obj: Objects) {
            System.out.println(++i + ". " + obj.name + " - " + obj.specialization);
        }
    }

    public void showNurses(ArrayList<Nurse> Objects)
    {
        int i = 0;
        System.out.println("Pielegniarki dostepne w przychodni ,," + this.name + "``: \n");
        for(Nurse obj : Objects) {
            System.out.println(++i + ". " + obj.name + " - " + obj.specialization);
        }
    }

    public void showPatients(ArrayList<Patient> Objects)
    {
        int i = 0;
        System.out.println("Pacjenci dostepni w przychodni ,," + this.name + "``: \n");
        for(Patient obj : Objects) {
            System.out.println(++i + ". " + obj.name + " - " + obj.disease);
        }
    }

    public void uploadPerson(String type, String name, String birthdayDate, String pesel, String nationality, String uniqueField)
    {
        try {
            if(type == "doctor") {
                this.Doctors.add(new Doctor(name, birthdayDate, pesel, nationality, uniqueField));
            }
            else if(type == "nurse") {
                this.Nurses.add(new Nurse(name, birthdayDate, pesel, nationality, uniqueField));
            }
            else {
                this.Patients.add(new Patient(name, birthdayDate, pesel, nationality, uniqueField));
            }
        }
        catch(Exception e) { e.printStackTrace(); }
    }

    public void addNewPerson(String type, String[] inputVal)
    {
        if(this.validate(inputVal[0], inputVal[1], inputVal[2], inputVal[3])) {
            this.uploadPerson(type, inputVal[0], inputVal[1], inputVal[2], inputVal[3], inputVal[4]);
            File newFile = new File("data.csv");
            newFile.addData(inputVal);
            System.out.println("\nPoprawnie dodano nowy rekord do bazy.");
            this.wait(2500);
        }
    }

    public String showDetails(int type, int id)
    {   
        if(type == 1) {
            return Doctors.get(id).toString();
        } 
        else if(type == 2) {
            return Nurses.get(id).toString();
        }
        else {
            return Patients.get(id).toString();
        } 
    }

    public void editDataInArrayList(int type, int id, String[] inputVal)
    {
        if(type == 1) {
            Doctors.get(id).editDoctorData(inputVal);
        }
        else if(type == 2) {
            Nurses.get(id).editNurseData(inputVal);
        } 
        else {
            Patients.get(id).editPatientData(inputVal);
        } 
    }

    public void editData(int type, int id, String[] inputVal)
    {
        if(this.validate(inputVal[0], inputVal[1], inputVal[2], inputVal[3])) {
            File newFile = new File("data.csv");
            String newLine = "";
            String oldLine = "";
            if(type == 1) {
                newLine = "D:";
                oldLine = Doctors.get(id).generateLine();
                Doctors.get(id).editDoctorData(inputVal);
            }
            else if(type == 2) {
                newLine = "N:";
                oldLine = Nurses.get(id).generateLine();
                Nurses.get(id).editNurseData(inputVal);
            } 
            else {
                newLine = "P:";
                oldLine = Patients.get(id).generateLine();
                Patients.get(id).editPatientData(inputVal);
            } 
            newLine += inputVal[0] + ',' + inputVal[1] + ',' + inputVal[2] + ',' + inputVal[3] + ',' + inputVal[4];
            newFile.changeData("", oldLine, newLine);
            this.editDataInArrayList(type, id, inputVal);
        }
    }

    private void deletePersonFromArrayList(int type, int id)
    {
        if(type == 1) {
            this.Doctors.remove(id);
        }
        else if(type == 2) {
            this.Nurses.remove(id);
        } 
        else {
            this.Patients.remove(id);
        }
    }

    public void deletePerson(int type, int id)
    {
        File newFile = new File("data.csv");
        String pesel = "";
        if(type == 1) {
            pesel = Doctors.get(id).getPesel();
        } 
        else if(type == 2) {
            pesel = Nurses.get(id).getPesel();
        }
        else {
            pesel = Patients.get(id).getPesel();
        }
        this.deletePersonFromArrayList(type, id);
        newFile.changeData(pesel, "", "");
    }

    public Person[] fetch(int type, int id, int id2)
    {
        Person[] objects = new Person[2];
        if(type == 1) {
            objects[0] = (Doctor)Doctors.get(id);
            objects[1] = (Doctor)Doctors.get(id2);
        }
        else if(type == 2) {
            objects[0] = (Nurse)Nurses.get(id);
            objects[1] = (Nurse)Nurses.get(id2);
        }
        else {
            objects[0] = (Patient)Patients.get(id);
            objects[1] = (Patient)Patients.get(id2);
        }
        return objects;
    
    }

    public String compare(int type, int id, int id2)
    {
        Person[] objects = fetch(type, id, id2);
        return objects[0].compareTo(objects[1]);
    }

    public boolean equals(int type, int id, int id2)
    {
        Person[] objects = fetch(type, id, id2);
        return objects[0].equals(objects[1]);
    }

   
}

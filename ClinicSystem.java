import java.io.*;
import java.io.IOException;
import java.util.Scanner;
public class ClinicSystem {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("data.csv"));
        Clinic Clinic = new Clinic();
        String data;
		while((data = in.readLine())!= null) {
            String[] getData = data.substring(2).split(",");
            if(data.charAt(0) == 'C'){
                Clinic = new Clinic(getData[0], getData[1], getData[2]);
            } 
            else if(data.charAt(0) == 'D'){
                Clinic.uploadPerson("doctor", getData[0], getData[1], getData[2], getData[3], getData[4]);
            }
             else if(data.charAt(0) == 'N'){
                Clinic.uploadPerson("nurse",getData[0], getData[1], getData[2], getData[3], getData[4]);
            } 
            else if(data.charAt(0) == 'P'){
                Clinic.uploadPerson("patient",getData[0], getData[1], getData[2], getData[3], getData[4]);
            }
		}
		in.close();
      
        BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
        Scanner read = new Scanner(System.in);
        String line = "";
        while (line.equalsIgnoreCase("q") == false) {
            showMenu(Clinic);
            line = in1.readLine();
            if(line.equals("1") || line.equals("2") || line.equals("3")){
                clearConsole();
                if(line.equals("1")){ // Doctors
                    Clinic.showDoctors(Clinic.doctors());
                } 
                else if(line.equals("2")){ // Nurses
                    Clinic.showNurses(Clinic.nurses());
                } 
                else if(line.equals("3")){ // Patients
                    Clinic.showPatients(Clinic.patients());
                }
                String choicedObject = line;
                System.out.println("\n[1] Pokaz szczegoly dotyczace osoby");
                System.out.println("[2] Edytuj dane");
                System.out.println("[3] Usun osobe");
                System.out.println("[4] Porownaj daty urodzin");
                System.out.println("[5] Sprawdz czy daty urodzin sa rowne");
                System.out.println("[0] Powrot");
                System.out.print('>');
                line = in1.readLine();
               
                if(!line.equals("0")) {
                    System.out.print("\nPodaj numer osoby: ");
                    int id = read.nextInt();
                    if(line.equals("1")) { //show
                        clearConsole();
                        System.out.println(Clinic.showDetails(Integer.parseInt(choicedObject), id-1));
                        System.out.println("[0] Powrot");
                        read.nextInt();
                    }
                    else if(line.equals("2")){ // edit
                        String[] inputVal = readData(read);
                        Clinic.editData(Integer.parseInt(choicedObject), id-1, inputVal);
                    }
                    else if(line.equals("3")){ // delete
                        Clinic.deletePerson(Integer.parseInt(choicedObject), id-1);
                    }
                    else if(line.equals("4") || line.equals("5")){
                        System.out.print("\nPodaj numer drugiej osoby: ");
                        int otherPersonId = read.nextInt();
                        if(line.equals("4")) { //compare 
                            System.out.println(Clinic.compare(Integer.parseInt(choicedObject), id-1, otherPersonId-1));
                        }
                        else { //equls
                            System.out.println(Clinic.equals(Integer.parseInt(choicedObject), id-1, otherPersonId-1));
                        }
                        Clinic.wait(3000);
                    }
                }
            }
            else if(line.equals("4") || line.equals("5") || line.equals("6")){
                String[] inputVal = readData(read);
                if(line.equals("4")) { // add doctor
                    inputVal[5] = "D:";
                    Clinic.addNewPerson("doctor", inputVal);
                }
                else if(line.equals("5")) { // add nurse
                    inputVal[5] = "N:";
                    Clinic.addNewPerson("nurse", inputVal);
                }
                else { // add patient
                    inputVal[5] = "P:";
                    Clinic.addNewPerson("patient", inputVal);
                }
            }
        }
        read.close();
        in1.close();
	}

    public static void showMenu(Clinic Clinic)
    {
            clearConsole();
            System.out.println(Clinic.toString());
            System.out.println("---------------------------------------------");
            System.out.println("[1] Pokaz dostepnych lekarzy");
            System.out.println("[2] Pokaz dostepne pielegniarki");
            System.out.println("[3] Pokaz dostepnych pacjentow");
            System.out.println("---------------------------------------------");
            System.out.println("[4] Dodaj lekarza");
            System.out.println("[5] Dodaj pielegniarke");
            System.out.println("[6] Dodaj pacjenta");
            System.out.println("---------------------------------------------");
            System.out.println("[q] Wyjdz");
            System.out.println("\nWybierz jedna z dostepnych opcji:");
            System.out.print('>');
    }

    public static void clearConsole()
    {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {}
    }

    public static String[] readData(Scanner read)
    { 
        System.out.print("Nacisnij dowolny przycisk aby kontynuowac...\n");
        read.nextLine();
        System.out.print("Wprowadz imie i nazwisko: ");
        String name = read.nextLine();
        System.out.print("Wprowadz date urodzenia (xx-xx-xxxx): ");
        String birthdayDate = read.nextLine();
        System.out.print("Wprowadz PESEL: ");
        String pesel = read.nextLine();
        System.out.print("Wprowadz narodowosc: ");
        String nationality = read.nextLine();
        System.out.print("Wprowadz specjalizacje / chorobe: ");
        String uniqueField = read.nextLine();
        String[] inputVal = {name, birthdayDate, pesel, nationality, uniqueField, ""};
        return inputVal;
    }
} 
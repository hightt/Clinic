
public abstract class Validator {

    public boolean validate(String name, String birthdayDate, String pesel, String nationality)
    {
        if(name.matches("[a-zA-Z ]+") && validateDate(birthdayDate) && validatePesel(pesel) && name.matches("[a-zA-Z ]+")) {
            return true;
        }
        else
            return false;
    }

    private boolean validatePesel(String Pesel)
    {
		int[] checksumWeight = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
		int checksum = 0;
        try {
            for(int i = 0; i < checksumWeight.length; i++) {
                checksum += Character.getNumericValue(Pesel.charAt(i)) * checksumWeight[i];
            }
            int checksumResult = 10 - (checksum % 10);
            if(checksumResult == 10) checksumResult = 0;
            if(Pesel.length() == 11 && Character.getNumericValue(Pesel.charAt(10)) == checksumResult)
                return true;
            else {
                System.out.println("\nNiepoprawny numer pesel");
                this.wait(2500);
                return false;
            }
        }
        catch(Exception  e) {
            System.out.println("\nZla dlugosc numeru PESEL");
            this.wait(2500);
            return false;
        }
			
	}

    private boolean validateDate(String date)
    {
        String[] getDate = date.split("-");
        try {
            if ((Integer.parseInt(getDate[0]) < 1 || Integer.parseInt(getDate[0]) > 30) || 
                (Integer.parseInt(getDate[1]) < 1 || Integer.parseInt(getDate[1]) > 12) || 
                (Integer.parseInt(getDate[2]) < 1900)){
                getDate[0] = "1";  getDate[1] = "1"; getDate[2] = "2000";
                System.out.println("Zly format daty");
                this.wait(2500);
                return false;
            }
        }
        catch(ArrayIndexOutOfBoundsException  e) {
            System.out.println("Niepoprawna data");
        }
		return true; 	
	}
    public void wait(int ms)
    {
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}

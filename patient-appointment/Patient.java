/**
 * @author Jennifer Parrish
 * CIS 36B, Lab 7
 */


public class Patient {

    /**Variable Declarations*/

    private int id;
    private String firstName;
    private String lastName;
    private String assignedHygienist;
    private String nextAppointment;
    private String[][] dentalChart;

    /**Constructors*/

    /**
     * Assigns values to id, firstName and lastName
     * Assigns default values of
     * "No hygienist assigned" to assignedHygienist
     * and "No appointment scheduled" to nextAppointment
     * Calls initializeDentalChart() method
     * @param theId the patient id
     * @param fName the patient first name
     * @param lName the patient last name
     */
    public Patient(int theId, String fName, String lName)
    {
        this.id = theId;
        this.firstName = fName;
        this.lastName = lName;
        this.initializeDentalChart();
    }

    /**
     * Assigns values to id, firstName, lastName,
     * assignedHygienist, and nextAppointment
     * Calls initializeDentalChart() method
     * @param theID the patient id
     * @param fName the patient first name
     * @param lName the patient last name
     * @param hygienist the hygienist assigned to the patient
     * @param appointment the next appointment
     */
    public Patient(int theID, String fName, String lName,
                   String hygienist, String appointment)
    {
        this.id = theID;
        this.firstName = fName;
        this.lastName = lName;
        this.assignedHygienist = hygienist;
        this.nextAppointment = appointment;
        this.initializeDentalChart();
    }

    /**Accessors*/

    /**
     * Returns the Patient id
     * @return the patient id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Returns the patient first name
     * @return the first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Returns the patient last name
     * @return the last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Returns the patient's assigned hygienist
     * @return the hygienist
     */
    public String getAssignedHygienist()
    {
        if (assignedHygienist != null)
        {
            return assignedHygienist;
        }
        else
        {
            return "No hygienist assigned";
        }
    }

    /**
     * Returns the date of the patient's
     * next appointment
     * @return the next appointment
     */
    public String getNextAppointment()
    {
        if (nextAppointment != null)
        {
            return nextAppointment;
        }
        else
        {
            return "No appointment scheduled";
        }
    }

    /**
     * Returns the patient's teeth chart
     * @return the dental chart
     */
    public String[][] getDentalChart()
    {
        return dentalChart;
    }

    /**Setters*/

    /**
     * Assigns a new id to the patient
     * @param newID the new id
     */
    public void setId(int newID)
    {
        id = newID;
    }

    /**
     * Assigns a new last name to the patient
     * @param newLastName the updated last name
     */
    public void setLastName(String newLastName)
    {
        lastName = newLastName;
    }

    /**
     * Assigns a new hygienist to the patient
     * @param newAssignedHygienist the updated hygienist
     */
    public void setAssignedHygienist(String newAssignedHygienist)
    {
        assignedHygienist = newAssignedHygienist;
    }

    /**
     * Updates the patient's next appointment
     * @param newAppointment the new appointment
     */
    public void setNextAppointment(String newAppointment)
    {
        nextAppointment = newAppointment;
    }

    /**
     * Updates a specific cavity in the dental chart
     * to be a cavity (represented as a "X")
     * @param row the row where the tooth is located
     * @param col the column where the tooth is located
     */
    public void updateCavity(int row, int col)
    {
        dentalChart[row - 1][col - 1] = "X";
    }
    /**
     * Updates a specific tooth in the dental chart
     * to be a crown (represented as a "C")
     * @param row the row where the tooth is located
     * @param col the column where the tooth is located
     */
    public void updateCrown(int row, int col)
    {
        dentalChart[row - 1][col - 1] = "C";
    }

    /**
     * Updates a specific tooth in the dental chart
     * to be a filling (represented as an "F")
     * @param row the row where the tooth is located
     * @param col the column where the tooth is located
     */
    public void updateFilling(int row, int col)
    {
        dentalChart[row - 1][col - 1] = "F";
    }


    /**Additional Methods*/

    /**
     * Returns a String containing the patient id, first name,
     * last name, hygienist and next appointment in the format
     * Patient ID: <id>
     * Patient Name: <firstName> <lastName>
     * Hygienist: <assignedHygienist>
     * Upcoming Appointment: <nextAppointment>
     * Note that there are no <> around the information.
     * The <> mean fill in the blank
     */
    @Override
    public String toString()
    {
        return "Patient ID: " + id + "\n" +
                "Patient Name: " + firstName + " " + lastName + "\n" +
                "Hygienist: " + getAssignedHygienist() + "\n" +
                "Upcoming appointment: " + getNextAppointment();
    }

    /**
     * Displays the dental chart, including the
     * name of the patient to the console
     */
    public void printDentalChart()
    {
        final int SIZE = 8; //avoiding magic numbers
        final int SUBTRACTOR = 2;

        System.out.printf("%10s\n", "12345678");
        for(int i = 0; i < dentalChart.length; i++) {
            System.out.printf("%d ", (i + 1));
            for(int j = 0; j < dentalChart[i].length; j++) {

                if(i == 0 && (j >= SUBTRACTOR && j < SIZE - SUBTRACTOR)) { //front teeth
                    System.out.printf("%s", dentalChart[i][j]);
                } else if ((i == 1 && j == 1) || (i == 1 && j == SIZE - SUBTRACTOR)) { //side teeth
                    System.out.printf("%s", dentalChart[i][j]);
                } else if ((j == 0 || j == SIZE - 1) && (SUBTRACTOR <= i && i <= SIZE - SUBTRACTOR)) { //back teeth
                    System.out.printf("%s", dentalChart[i][j]);
                } else if ((j == SIZE - 1) && (SUBTRACTOR <= i && i <= SIZE - SUBTRACTOR)) {
                    System.out.printf("%s", dentalChart[i][j]);
                } else {
                    System.out.printf("%s", dentalChart[i][j]);
                }
            }
            System.out.println();
        }
    }
    /**
     * Initializes the dental chart, including the
     * name of the patient.
     * This method only called by the constructor
     */
    private void initializeDentalChart() {
        final int SIZE = 8; //avoiding magic numbers
        final int SUBTRACTOR = 2;
        dentalChart = new String[SIZE-1][SIZE];

        for(int i = 0; i < dentalChart.length; i++) {
            for(int j = 0; j < dentalChart[i].length; j++) {
                if(i == 0 && (j >= SUBTRACTOR && j < SIZE - SUBTRACTOR)) { //front teeth
                    dentalChart[i][j] = "O";
                } else if ((i == 1 && j == 1) || (i == 1 && j == SIZE - SUBTRACTOR)) { //side teeth
                    dentalChart[i][j] = "O";
                } else if ((j == 0 || j == SIZE - 1) && (SUBTRACTOR <= i && i <= SIZE - SUBTRACTOR)) { //back teeth
                    dentalChart[i][j] = "O";
                } else if ((j == SIZE - 1) && (SUBTRACTOR <= i && i <= SIZE - SUBTRACTOR)) {
                    dentalChart[i][j] = "O";
                } else {
                    dentalChart[i][j] = " ";
                }
            }
        }
    }
}

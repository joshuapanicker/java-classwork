/**
 * @author Joshua Panicker
 * CIS 36B, Lab 7
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class AppointmentApp {
    public static final int NEXT = 1;
    public static Scanner input;
    public static int nextId = 0;
    public static final int ROW_MAX = 7;
    public static final int COLUMN_MAX = 8;

    /**
     * Searches for the patient by their id
     * @param patients the ArrayList of Patients
     * @return the patient according to the id
     */
    public static Patient searchForPatientById(ArrayList<Patient> patients)
    {
        int id = -1;
        int patientIndex;

        System.out.print("Enter the patient id: ");
        id = input.nextInt();
        bubbleSortById(patients);
        patientIndex = binarySearchById(patients, id);
        if (patientIndex != -1)
            return patients.get(patientIndex);
        return createAccount(patients, false, null, null, input);
    }

    /**
     * Searches for the patient by their name
     * @param patients the ArrayList of Patients
     * @return the patient according to the name
     */
    public static Patient searchForPatientByName(ArrayList<Patient> patients)
    {
        String firstName;
        String lastName;
        int patientIndex;

        System.out.println("Enter the patient information below:");
        System.out.println();
        System.out.print("First Name: ");
        firstName = input.next();
        System.out.print("Last Name: ");
        lastName = input.next();
        bubbleSortByName(patients);
        patientIndex = binarySearchByName(patients, firstName, lastName);
        if (patientIndex != -1)
            return patients.get(patientIndex);

        return createAccount(patients, true, firstName, lastName, input);
    }


    /**
     * Assigns a hygienist to the patient
     * @param patient the patient who is getting a hygienist assigned
     * @param hygienists the array of hygienists
     */
    public static void assignHygienist(Patient patient, String[] hygienists)
    {
        int hygienistIndex;

        System.out.println();
        System.out.println("Please select from one of the following hygienists:");
        System.out.println();
        printHygienists(hygienists);
        System.out.println();
        System.out.print("Enter the number of your choice: ");
        hygienistIndex = input.nextInt();
        System.out.println();
        patient.setAssignedHygienist(hygienists[hygienistIndex]);
        System.out.println("Here is the updated patient information:");
        System.out.println();
        System.out.printf("%s\n", patient.toString());
        System.out.println();
    }

    /**
     * Schedules an appointment for the patient
     * @param patient the patient who is getting a hygienist assigned
     */
    public static void scheduleAppointment(Patient patient)
    {
        String appointmentOption;
        String newAppointmentDate;

        System.out.println();
        System.out.printf("Currently scheduled appointment: %s\n", patient.getNextAppointment());
        System.out.println();
        System.out.println("Reschedule this appointment?");
        System.out.println("Enter 1 for yes or any other key + enter to return to main menu: ");
        input.useDelimiter("\n");
        appointmentOption = input.next();
        if (appointmentOption.equals("1"))
        {
            System.out.print("Enter the new appointment date: ");
            newAppointmentDate = input.next();
            patient.setNextAppointment(newAppointmentDate);
            System.out.println();
            System.out.printf("%s %s's next appointment is now scheduled for %s\n",
                    patient.getFirstName(), patient.getLastName(), patient.getNextAppointment());
            System.out.println();
        }
    }

    /**
     * Allows you to view and update the patient dental chart
     * @param patient the patient who is getting a hygienist assigned
     */
    public static void viewAndUpdateDentalChart(Patient patient)
    {
        boolean repeats = true;
        String dentalChartOption;
        int toothRow;
        int toothColumn;

        System.out.println();
        System.out.printf("%s %s:\n", patient.getFirstName(), patient.getLastName());
        System.out.println();
        patient.printDentalChart();
        System.out.println();
        System.out.println("Update this chart?");
        System.out.println("Enter 1 for yes or any other key + enter to return to main menu");
        System.out.print("Enter your choice: ");
        dentalChartOption = input.next();
        if (!dentalChartOption.equals("1"))
            return;
        while (repeats)
        {
            System.out.println();
            System.out.println("Please select from one of the following options: ");
            System.out.println();
            System.out.println("1. Cavity");
            System.out.println("2. Filling");
            System.out.println("3. Crown");
            System.out.println();
            System.out.println("Enter your choice or 0 to return to main menu: ");

            dentalChartOption = input.next();
            if (dentalChartOption.equals("0"))
            {
                System.out.println("Returning to main menu");
                return;
            }
            if (!dentalChartOption.equals("1") && !dentalChartOption.equals("2") && !dentalChartOption.equals("3"))
                return;

            System.out.print("Next, enter the row of the tooth to update: ");
            toothRow = input.nextInt();
            if (toothRow < 1 || toothRow > ROW_MAX)
                return;

            System.out.print("Enter the column of the tooth to update: ");
            toothColumn = input.nextInt();
            if (toothColumn < 1 || toothColumn > COLUMN_MAX)
                return;

            System.out.println("Updating Record...");
            System.out.println();
            System.out.printf("%s %s:\n", patient.getFirstName(), patient.getLastName());
            System.out.println();
            if (dentalChartOption.equals("1"))
            {
                patient.updateCavity(toothRow, toothColumn);
            }
            else if (dentalChartOption.equals("2"))
            {
                patient.updateFilling(toothRow, toothColumn);
            }
            else
            {
                patient.updateCrown(toothRow, toothColumn);
            }
            patient.printDentalChart();
        }
    }

    /**
     * Displays the current information of a patient
     * @param patient the patient to view info of
     */
    public static void currentPatientInfo(Patient patient)
    {
        System.out.printf("%s\n", patient.toString());
    }

    public static void main(String[] args) throws IOException {
        String[] hygienists = {"Dan Felipe", "Jasmine Brown", "Jin Yong"};

        ArrayList<Patient> patients = new ArrayList<Patient>();
        Patient patient = null;
        String choice;
        final String FILE_NAME = "patients.txt";
        File file = new File(FILE_NAME);

        input = new Scanner(System.in);
        readFile(patients, hygienists, file);

        boolean repeat = true;
        System.out.println("Welcome to the Dental Appointment and Record App!\n");
        while (repeat) {
            System.out.println("Please select from one of the following options:\n");
            System.out.println("1. Search for patient by id.");
            System.out.println("2. Search for patient by first and last name.");
            System.out.println();
            System.out.print("Enter the number of your choice: ");
            choice = input.next();
            if(choice.equals("1")) {
                patient = searchForPatientById(patients);
                repeat = false;
            } else if (choice.equals("2")) {
                patient = searchForPatientByName(patients);
                repeat = false;
            } else {
                System.out.println("Invalid option. Please try again\n");
            }
        }

        System.out.println("\nHere is the information we have on file: ");
        System.out.printf("%s\n", patient.toString());
        repeat = true;
        while(repeat) {
            System.out.println("\nPlease select from one of the following options:\n");
            System.out.println("A. Assign new hygenist");
            System.out.println("C. Current patient information");
            System.out.println("S. Schedule a patient appointment");
            System.out.println("V. View and update patient dental chart");
            System.out.println("Q. Quit");
            System.out.print("\nEnter your choice: ");
            choice = input.next();
            if(choice.equalsIgnoreCase("A"))
            {
                assignHygienist(patient, hygienists);
            }
            else if (choice.equalsIgnoreCase("C"))
            {
                currentPatientInfo(patient);
            }
            else if (choice.equalsIgnoreCase("S"))
            {
                scheduleAppointment(patient);
            }
            else if(choice.equalsIgnoreCase("V"))
            {
                viewAndUpdateDentalChart(patient);
            }
            else if (choice.equalsIgnoreCase("Q"))
            {
                repeat = false;
            }
            else
            {
                System.out.println("Invalid menu option. Enter A, S, V, or Q only");
            }
        }
        input.close();
        System.out.println("\nGoodbye!");
    }

    /**
     * Prints the ArrayList of hygienists
     * @param hygienists the array of hygienist options
     */
    public static void printHygienists(String[] hygienists)
    {
        for (int i = 0; i < hygienists.length; i++)
        {
            System.out.printf("%d. %s\n", i, hygienists[i]);
        }
    }

    /**
     * Reads data from the given file and populates the ArrayList of Patients
     * @param patients the ArrayList of Patients
     * @param hygienists the array of hygienist options
     * @param file a File object connected to a String file name
     * @throws IOException when file cannot be found
     */
    public static void readFile(ArrayList<Patient> patients, String[] hygienists, File file) throws IOException
    {
        File infile = new File("patients.txt");
        Scanner input = new Scanner(infile);

        while (input.hasNext())
        {
            int hygienistIndex, id;
            String firstName, lastName;
            String nextAppointment;
            String assignedHygienist;

            id = input.nextInt();
            firstName = input.next();
            firstName = firstName.trim();
            lastName = input.nextLine();
            lastName = lastName.trim();
            nextAppointment = input.nextLine();
            hygienistIndex = input.nextInt();
            assignedHygienist = hygienists[hygienistIndex];
            Patient p = new Patient(id, firstName, lastName, assignedHygienist, nextAppointment);
            patients.add(p);
            nextId = id + 1;
        }
    }

    /**
     * Sorts the given ArrayList of patients in ascending
     * numerical order by id
     * @param patients the ArrayList of Patients
     * Hint: You will need to call get and set methods
     * from the ArrayList class to write this method!
     */
    public static void bubbleSortById(ArrayList<Patient> patients)
    {
        for(int i = 0; i < patients.size() - NEXT; i++) {
            for(int j = 0; j < patients.size() - i - NEXT; j++) {
                if(patients.get(j).getId() > patients.get(j + NEXT).getId()) {
                    Patient temp = patients.get(j);
                    patients.set(j, patients.get(j + NEXT));
                    patients.set(j + NEXT, temp);
                }
            }
        }
    }

    /**
     * Sorts the given ArrayList of patients in ascending
     * order by last name and then by first name
     * @param patients the ArrayList of Patients
     * Hint: What happens if the last names are the same
     * but the first names are different?
     */
    public static void bubbleSortByName(ArrayList<Patient> patients)
    {
        for(int i = 0; i < patients.size() - NEXT; i++) {
            for(int j = 0; j < patients.size() - i - NEXT; j++) {
                if(patients.get(j).getLastName().compareTo(patients.get(j + NEXT).getLastName()) > 0) {
                    Patient temp = patients.get(j);
                    patients.set(j, patients.get(j + NEXT));
                    patients.set(j + NEXT, temp);
                }
                else if(patients.get(j).getLastName().equals(patients.get(j + NEXT).getLastName()))
                {
                    if(patients.get(j).getFirstName().compareTo(patients.get(j + NEXT).getFirstName()) > 0)
                    {
                        Patient temp = patients.get(j);
                        patients.set(j, patients.get(j + NEXT));
                        patients.set(j + NEXT, temp);
                    }
                }
            }
        }
    }

    /**
     * Searches for a Patient with a matching id within the patients ArrayList
     * @param patients the ArrayList of patients
     * @param id the id to search for
     * @return the location in the patients ArrayList where the matching
     * Patient with the given id is stored or -1 if not found
     */
    public static int binarySearchById(ArrayList<Patient> patients, int id)
    {
        int low = 0;
        int high = patients.size() - 1;
        int mid;

        while (low <= high)
        {
            mid = (low + high)/2;
            if (patients.get(mid).getId() == id)
            {
                return mid;
            }
            else if (id < patients.get(mid).getId())
            {
                high = mid - 1;
            }
            else
            {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Searches for a Patient with a matching first and last name
     *  within the patients ArrayList
     * @param patients the ArrayList of patients
     * @param first the patient first name to search for
     * @param last the patient last name to search for
     * @return the location in the patients ArrayList where the matching
     * Patient with the given first and last is stored or -1 if not found
     */
    public static int binarySearchByName(ArrayList<Patient> patients, String first, String last)
    {
        int low = 0;
        int high = patients.size() - 1;
        int mid;

        while (low <= high)
        {
            mid = (low + high)/2;
            if (patients.get(mid).getLastName().equals(last))
            {
                if (patients.get(mid).getFirstName().equals(first))
                {
                    return mid;
                }
                else if (first.compareTo(patients.get(mid).getFirstName()) < 0)
                {
                    high = mid - 1;
                }
                else
                {
                    low = mid + 1;
                }
            }
            else if (last.compareTo(patients.get(mid).getLastName()) < 0)
            {
                high = mid - 1;
            }
            else
            {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Prompts for new patient information and then
     * Creates a new Patient and insert it into ArrayList
     * The patient should be assigned a first and last name
     * and the dentalChart should be initialized
     * The assignedHygienist and nextAppointments
     * for this patient are to be assigned default values of
     * "No hygienist assigned"
     * and "No appointment scheduled"
     * The id should be the next available ArrayList index + 1000
     * @param patients the ArrayList of patients
     * @param nameKnown whether the Patient name is known
     * @param first the patient first name, if known, an empty
     * String otherwise
     * @param last the patient first name, if known, an empty
     * String otherwise
     * @param input a Scanner connected to System.in
     * @return the patient that was created
     */
    public static Patient createAccount(ArrayList<Patient> patients, boolean nameKnown,
                                        String first, String last, Scanner input) {
        System.out.println();
        System.out.println("This patient is not on file.");
        if(!nameKnown) {
            System.out.println("Please create a new account.");
            System.out.println();
            System.out.print("Enter the patient first name: ");
            first = input.next();
            System.out.print("Enter the patient last name: ");
            last = input.next();
        }
        else
        {
            System.out.printf("Creating a new account for %s %s...\n", first, last);
        }

        Patient patient = new Patient(nextId, first, last, null, null);
        nextId++;
        return patient;
    }
}

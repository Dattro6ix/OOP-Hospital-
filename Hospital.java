import java.util.*;

public class Hospital {
	Director director;
	SalariedEmployee salariedEmployee = new SalariedEmployee();
	ArrayList admins = new ArrayList<>();
    ArrayList physician = new ArrayList<>();
    ArrayList volunteer = new ArrayList<>();
    ArrayList patient = new ArrayList<>();
	private static int countAdmins = 0;
	private static int countPhysicians = 0;
	private static int countVolunteers = 0;
	private static int countPatients = 0;
	
	public Hospital(Director director) {
		this.director = director;
	}
	
	public Director getHospDirector() {
		return this.director;
	}
	
	public boolean addAdministrator(Administrator admin) {
		this.countAdmins += 1;
		if(!(this.countAdmins > 3)) {
			this.salariedEmployee.admins.add(admin);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean hirePhysician(Physician physician) {
		if(this.countPhysicians < 70 && !(this.salariedEmployee.physician.contains(physician))) {
			this.salariedEmployee.physician.add(physician);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean resignPhysician(Physician physician) throws NoSpecialtyException{
		if (this.salariedEmployee.physician.contains(physician)) {
			int index = this.salariedEmployee.physician.indexOf(physician);
			this.salariedEmployee.physician.remove(index);
			return true;
		}
		else {
			return false;
		}
	}
	
    public boolean hireVolunteer(Volunteer volunteer) {
		if(this.countVolunteers < 150 && !(this.volunteer.contains(volunteer))) {
			this.volunteer.add(volunteer);
			return true;
		}
		else {
			return false;
		}
	}
    
    public void resignVolunteer(Volunteer volunteer) throws NoVolunteersException {
		if (this.volunteer.contains(volunteer)) {
			int index = this.volunteer.indexOf(volunteer);
			this.volunteer.remove(index);
		}
		else {
			throw new NoVolunteersException();	 
		}
    }
    
    public ArrayList extractAllPhysicianDetails() {
    	return this.salariedEmployee.physician;
    }
    
    public boolean admitPatient(Patient patient) throws NoSpaceException {
    	if(this.countPatients < 500 && !(this.patient.contains(patient))) {
			this.patient.add(patient);
			return true;
		}
		else {
			return false;
		}
    }
    
    public boolean dischargePatient(Patient patient) {
    	if (this.patient.contains(patient)) {
			int index = this.patient.indexOf(patient);
			this.patient.remove(index);
			return true;
		}
    	else {
    		return false;
    	}
    }
    
    public ArrayList extractAllVolunteerDetails() {
    	return this.volunteer;
    }
    
    public ArrayList extractAllPatientDetails() {
    	return this.patient;
    }
}

class Director extends Employee {
    public Director(String firstName, String lastName, int age, String gender, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.employeeID += 1;
    }
    
    @Override
    public String toString() {
        return "Director [[" + this.employeeID + ",[" + this.firstName + ", " + this.lastName + ", " + this.age + ", " + this.gender + ", " + this.address + "]]]";
    }
}

class Person {
	String firstName;
	String lastName;
	int age;
	String gender;
	String address;
}

class Patient extends Person implements Comparable {
	Patient patient;
	String firstName;
	String lastName;
	int age;
	String gender;
	String address;
    static int patientID = 999;
    Physician physician;
    ArrayList<Patient> patientList = new ArrayList<Patient>();
    
	public Patient(String firstName, String lastName, int age, String gender, String address) {
		this.patientID += 1;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.patientList.add(this);
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public String getName() {
		return this.firstName + ", " + this.lastName;
	}
	
	public int getPatientID() {
		return this.patientID;
	}
	
	public void setAssignedPhysician(Physician physician) {
		this.physician = physician;
	}
	
	public Physician getAssignedPhysician() {
		return this.physician;
	}
	
	public boolean clearPatientRecord() {
		this.patientList.clear();
		if(this.physician == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Patient [" + this.patientID + ", [" + this.firstName + ", " + this.lastName + ", " + this.age + ", " + this.gender + ", " + this.address + "]]";
	}
	
	@Override
	public int compareTo(Object o) {
		return 0;
	}
}

abstract interface comparable {
}

class Employee extends Person {
	double salary;
	static int employeeID = 99;
}

class Volunteer extends Employee {
	String firstName;
	String lastName;
	int age;
	String gender;
	String address;
	
	public Volunteer(String firstName, String lastName, int age, String gender, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.address = address;	
		this.employeeID += 1;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getName() {
		return this.firstName + ", " + this.lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Volunteer [[" + this.employeeID + ",[" + this.firstName + ", " + this.lastName + ", " + this.age + ", " + this.gender + ", " + this.address + "]]]";
	}
}

class SalariedEmployee extends Employee {
	ArrayList physician = new ArrayList<>();
	ArrayList admins = new ArrayList<>();
}

class Administrator extends SalariedEmployee {
	Administrator admin;
	public static int countAdmins = 0;
	ArrayList physicianAdmins = new ArrayList<>();
}

abstract class Physician extends SalariedEmployee implements Comparable {
	
	String specialty;
	static int patientCount;
	int volunteerCount;
	Volunteer volunteer;
	ArrayList volunteerList = new ArrayList<>(5);
	ArrayList patientList = new ArrayList<>();
	
    public Physician(String firstName, String lastName, int age, String gender, String address) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.age = age;
	    this.gender = gender;
	    this.address = address;
	    this.employeeID += 1;
	}
    
    public String getAddress() {
    	return this.address;
    }
    
    public void setSalary(double salary) {
    	this.salary = salary;
    }
    
    public void setFirstName(String name) {
    	this.firstName = name;
    }
    
    public String getName() {
    	String name = this.firstName + ", " + this.lastName;
    	return name;
    }
    
    public void setSpecialty(String specialty) {
    	if(specialty == "Immunology" || specialty == "Dermatology" || specialty == "Neurology") {
			this.specialty = specialty;
		}
		else {
			throw new IllegalArgumentException();
		}
    }
    
    public String getSpecialty() {
    	return this.specialty;
    }
    
    public void setLastName(String name) {
    	this.lastName = name;
    }
    
    public int getEmployeeID() {
    	return this.employeeID;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
    
    public void setAge(int age) {
    	this.age = age;
    }
    
    public void setGender(String gender) {
    	this.gender = gender;
    }
    
    public boolean hasMaximumpatient() {
    	if(this.patientCount < 8) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    public boolean hasMaxVolunteers() {
    	if(this.volunteerCount < 5) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    public boolean assignVolunteer(Employee volunteer) {
    	this.volunteerCount += 1;
    	if(!(this.volunteerList.contains(volunteer)) && this.volunteerCount <= 5) {
    		this.volunteerList.add(volunteer);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public ArrayList extractValunterDetail() {
    	return this.volunteerList;
    }
    
    public ArrayList extractPatientDetail() {
    	return this.patientList;
    }
}
class NoSpaceException extends Exception{
    public NoSpaceException() {
        return;
    }
    public NoSpaceException(String e) {
        return;
    }
}

class NoVolunteersException extends Exception{
    public NoVolunteersException() {
        return;
    }
    public NoVolunteersException(String e) {
        return;
    }
}

class NoSpecialtyException extends Exception{
    public NoSpecialtyException() {
        return;
    }
    public NoSpecialtyException(String e) {
        return;
    }
}
package sg.nus.iss.service.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate date;
	private LocalTime time;
	private int queue_number;
	private String medical_condition;
	private AppointmentStatusEnum status;

	@OneToOne(mappedBy = "appointment")
	@JsonIgnore
	private Feedback feedback;

	@ManyToOne
	@JsonProperty
	private Patient patient;

	@ManyToOne
	@JsonProperty
	private Staff staff;
	
	@ManyToOne
	@JsonIgnore
	private Customer customer;
	
	public Appointment() {
	}

	public Appointment(LocalDate date, LocalTime time, int queue_number, String medical_condition) {
		this.date = date;
		this.time = time;
		this.queue_number = queue_number;
		this.medical_condition = medical_condition;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setDate(String date) {
		this.date = LocalDate.parse(date);
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public void setTime(String time) {
		this.time = LocalTime.parse(time);
	}

	public int getQueue_number() {
		return queue_number;
	}

	public void setQueue_number(int queue_number) {
		this.queue_number = queue_number;
	}

	public String getMedical_condition() {
		return medical_condition;
	}

	public void setMedical_condition(String medical_condition) {
		this.medical_condition = medical_condition;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AppointmentStatusEnum getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatusEnum status) {
		this.status = status;
	}

	public Staff getStaff() {
		return staff;
	}


	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}

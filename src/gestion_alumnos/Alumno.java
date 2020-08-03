package gestion_alumnos;

import java.io.Serializable;

public class Alumno implements Serializable {
	
	String key, name, surname;
	byte age;
	Address address;
	
	public Alumno(String key, String name, String surname, String age, String street, String number, String postCode) {
		this.key = key;
		this.name = name;
		this.surname = surname;
		setAge(age);
		address = new Address(street, number, postCode);
	}
	
	public String getKey() {
		return key;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getAge() {
		return String.valueOf(age);
	}
	
	public void setAge(String age) {
		if(age.isEmpty()) age = "0";
		this.age = Byte.valueOf(age);
	}
	
	public String getStreet() {
		return address.street;
	}
	
	public void setStreet(String street) {
		address.street = street;
	}
	
	public String getNumber() {
		return address.number;
	}
	
	public void setNumber(String number) {
		address.number = number;
	}
	
	public String getPostCode() {
		return address.postCode;
	}
	
	public void setPostCode(String postCode) {
		address.postCode = postCode;
	}
	
	public boolean needSaving() {
		return needSaving();
	}
}

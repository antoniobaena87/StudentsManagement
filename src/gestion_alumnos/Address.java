package gestion_alumnos;

import java.io.Serializable;

public class Address implements Serializable{
	
	String street, number, postCode;
	
	public Address(String street, String number, String postCode) {
		this.street = street;
		this.number = number;
		this.postCode = postCode;
	}
}

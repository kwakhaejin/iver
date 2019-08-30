package iver.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class TableDto {
	int empNo;
	Date birthDate;
	String firstName;
	String lastName;
	String gender;
	Date hireDate;
}

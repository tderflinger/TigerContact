package org.tigercontact.contacts

import java.sql.Timestamp

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import org.hibernate.validator.constraints.Email



@Entity
class Contact implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id
	
	Timestamp timeStamp

	@NotNull
	@Size(min = 1, max=200)
	String name
	
	@NotNull
	@Email
	String email
	
	String phone
	
	boolean moreInfo
	
	@NotNull
	@Size(min=1, max=1000)
	String message
	
}

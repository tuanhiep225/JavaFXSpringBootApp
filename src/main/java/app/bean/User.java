package app.bean;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ram Alapure
 * @since 05-04-2017
 */

//@Entity
//@Table(name="User")
@Document(collection="User")
public class User {

	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name = "id", updatable = false, nullable = false)
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate dob;
	
	private String gender;
	
	private String role;
	
	private String email;
	
	private String password;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", email="
				+ email + "]";
	}

	
}

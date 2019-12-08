package com.example.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name = "modules")
public class Module implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	//private Integer id;
	private Long id;
	
	@Column(name = "username")
	private String username;
	
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;
	
    @Override
	public String toString() {
		return name;
	}
    
//    @JsonBackReference
//    @OneToMany(mappedBy="module", cascade = CascadeType.ALL)
//    List<Student> students;
//    
//    @JsonBackReference
//    @OneToMany(mappedBy="module", cascade = CascadeType.ALL)
//    List<Book> books;
    
	public void setCode(String code) {this.code = code;}
	public String getCode() {return code;}
	
	public void setName(String name) {this.name = name;}
	public String getName() {return name;}

//	public Integer getId() { return id; }
//	public void setId(Integer id) { this.id = id; }
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
//	public List<Student> getStudents() {
//		return students;
//	}
//	public void setStudents(List<Student> students) {
//		this.students = students;
//	}
//	public List<Book> getBooks() {
//		return books;
//	}
//	public void setBooks(List<Book> books) {
//		this.books = books;
//	}
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
	
	
	
}
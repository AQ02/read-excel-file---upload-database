package com.assignment.excel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {
	
	@Id
	  @Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	  private long id;
 
  @Column(name = "Name")
  private String name;

  @Column(name = "Class")
  private Integer className;

  @Column(name = "Subject")
  private String subject;
  

@Column(name = "Marks")
  private Integer marks;

  public Student() {

  }


public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Integer getClassName() {
	return className;
}

public void setClassName(Integer className) {
	this.className = className;
}

public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public Integer getMarks() {
	return marks;
}

public void setMarks(Integer marks) {
	this.marks = marks;
}

  @Override
public String toString() {
	return "Tutorial [name=" + name + ", className=" + className + ", subject=" + subject + ", marks=" + marks
			+ ", getName()=" + getName() + ", getClassName()=" + getClassName() + ", getSubject()=" + getSubject()
			+ ", getMarks()=" + getMarks() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
			+ ", toString()=" + super.toString() + "]";
}
}

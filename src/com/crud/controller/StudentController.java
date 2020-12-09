package com.crud.controller;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.crud.entity.Student;

public class StudentController {

	public static Session getSession() {
		return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();
	}

	public static void main(String args[]) {
		/*
		 * List<Student> students = save(new Student(0, "Vijay", "Ahmedabad")); Student
		 * student = findById(13); student.setId(student.getId());
		 * student.setName("Ajay"); student.setCity("Baroda"); // List<Student> students
		 * = update(student);
		 * 
		 * List<Student> students = delete(13);
		 * 
		 * System.out.println(students);
		 */

		List<Student> students = delete(1);
		System.out.println(students);
	}

	public static List<Student> findAll() {
		Session session = getSession();
		session.beginTransaction();
		Query query = session.createQuery("from Student", Student.class);
		List<Student> students = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return students;
	}

	public static Student findById(int id) {
		Session session = getSession();
		Student student = session.get(Student.class, id);
		session.close();
		return student;
	}

	public static List<Student> save(Student student) {
		Session session = getSession();
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
		session.close();
		return findAll();
	}

	public static List<Student> update(Student student) {
		Session session = getSession();
		session.beginTransaction();
		session.update(student);
		session.getTransaction().commit();
		session.close();
		return findAll();
	}

	public static List<Student> delete(int id) {
		Session session = getSession();
		Student student = session.get(Student.class, id);
		session.beginTransaction();
		session.delete(student);
		session.getTransaction().commit();
		session.close();
		return findAll();
	}
}

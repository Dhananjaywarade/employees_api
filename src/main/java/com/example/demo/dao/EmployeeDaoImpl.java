package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	private EntityManager theEntityManager;

	
	@Autowired
	public EmployeeDaoImpl(EntityManager theEntityManager) {
		this.theEntityManager = theEntityManager;
	}



	@Override
	public List<Employee> findAll() {
		TypedQuery<Employee>query=theEntityManager.createQuery("FROM Employee", Employee.class);
		List<Employee> theEmployees=query.getResultList();
		return theEmployees;
	}



	@Override
	public Employee findById(int theId) {
		Employee theEmployee=theEntityManager.find(Employee.class, theId);
		return theEmployee;
	}



	@Override
	public Employee save(Employee theEmployee) {
		Employee dbEmployee=theEntityManager.merge(theEmployee);
		return dbEmployee;
	}



	@Override
	public void deleteById(int theId) {
		Employee theEmployee=theEntityManager.find(Employee.class, theId);
		theEntityManager.remove(theEmployee);	
	}

}

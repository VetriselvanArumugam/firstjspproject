package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.commonutil.ExceptionManager;
import com.chainsys.commonutil.InvalidInputDataException;
import com.chainsys.commonutil.Validator;
import com.chainsys.jspproject.dao.EmployeeDao;
import com.chainsys.jspproject.pojo.Employee;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		List<Employee> allEmployees = EmployeeDao.getAllEmployee();
		Iterator<Employee> empIterator = allEmployees.iterator();
		while (empIterator.hasNext()) {
			Employee result = empIterator.next();
//			out.println("Employee id: " + "\t" + "Employee first name: " + "\t" + "Employee last name: " + "\t"
//					+ "Employee email: " + "\t" + "Employee hiredate: " + "\t" + "Employee job id: " + "\t"
//					+ "Employee salary: " + "\t");
			out.println("<hr/>");

			out.println(result.getEmp_ID() + "," + result.getFirst_name() + "," + result.getLast_name() + ","
					+ result.getEmail() + "," + result.getHire_date() + "," + result.getJob_id() + ","
					+ result.getSalary() + ",");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    		if (request.getParameter("change").equals("Add")) {
    		String source="AddNewEmployee";
    		String message="<h1>Error while "+source+"</h1>";
    		PrintWriter out = response.getWriter();
    		Employee emp = new Employee();
    		try {

    		String emp_id = request.getParameter("id");
    		try {
    		Validator.checkStringForParse(emp_id);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Employee id input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		                   return;
    		}
    		int id = Integer.parseInt(emp_id);
    		try {
    		Validator.CheckNumberForGreaterThanZero(id);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Employee id input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message); out.print(errorPage);
    		                   return;
    		}
    		emp.setEmp_ID(id);

    		String emp_Firstname = request.getParameter("fname");
    		try {
    		Validator.checkStringOnly(emp_Firstname);
    		} catch (InvalidInputDataException e) {
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		                   return;
    		}
    		emp.setFirst_name(emp_Firstname);
    		String emp_LastName = request.getParameter("Lname");
    		try {
    		Validator.checkStringOnly(emp_LastName);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Last Name input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		                   return;
    		}
    		emp.setLast_name(emp_LastName);
    		String emp_email = request.getParameter("email");
    		try {
    		Validator.checkMail(emp_email);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in email input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		                   return;
    		}
    		emp.setEmail(emp_email);
    		SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
    		String emp_HireDate = request.getParameter("hdate");
    		// Date hire_date=hire_dateFormate.parse(emp_HireDate);

    		try {
    		Validator.checkDateFormat(emp_HireDate);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Hire Date input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		                   return;
    		}
    		try {
    		emp.setHire_date(hire_dateFormate.parse(emp_HireDate));
    		} catch (ParseException e) {
    		message +=" Error in Hire Date input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		                   return;
    		}
    		String emp_Job_id = request.getParameter("jobid");
    		try {
    		Validator.checkjob(emp_Job_id);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Job Id input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		                   return;
    		}
    		emp.setJob_id(emp_Job_id);
    		// ---------------------------------------------------------------------------------
    		String emp_salary = null;
    		try {
    		emp_salary = request.getParameter("salary");
    		Validator.checklengthOfString(emp_salary);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Salary input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		                   return;
    		}
    		int salary = Integer.parseInt(emp_salary);
    		emp.setSalary(salary);
    		// ---------------------------------------------------------------------------------
    		int result = EmployeeDao.insertEmployee(emp);
    		out.println(result + "Successfully row inserted");
    		} catch (Exception e) {
    		message +=" Error while inserting record </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		               return;
    		}
    		try {
    		out.close();
    		} catch (Exception e) {
    		message +="Message: "+e.getMessage();
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		               return;
    		}
    		} else if (request.getParameter("change").equals("update")) {
    		doPut(request, response);
    		} else if (request.getParameter("change").equals("Delete")) {
    		doDelete(request, response);
    		}
    		}
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    		String source="AddNewEmployee";
    		String message="<h1>Error while "+source+"</h1>";
    		PrintWriter out = response.getWriter();
    		Employee emp = new Employee();
    		try {

    		String emp_id = request.getParameter("id");
    		try {
    		Validator.checkStringForParse(emp_id);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Employee id input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		               return;

    		}
    		int id = Integer.parseInt(emp_id);
    		try {
    		Validator.CheckNumberForGreaterThanZero(id);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Employee id input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		               return;
    		}
    		emp.setEmp_ID(id);

    		String emp_Firstname = request.getParameter("fname");
    		try {
    		Validator.checkStringOnly(emp_Firstname);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Employee first Name input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		               return;
    		}
    		emp.setFirst_name(emp_Firstname);
    		String emp_LastName = request.getParameter("Lname");
    		try {
    		Validator.checkStringOnly(emp_LastName);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Employee first Name input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		               return;
    		}
    		emp.setLast_name(emp_LastName);
    		String emp_email = request.getParameter("email");
    		try {
    		Validator.checkMail(emp_email);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Employee email input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		               return;
    		}
    		emp.setEmail(emp_email);
    		SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
    		String emp_HireDate = request.getParameter("hdate");
    		// Date hire_date=hire_dateFormate.parse(emp_HireDate);

    		try {
    		Validator.checkDateFormat(emp_HireDate);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Employee Hire Date input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		               return;
    		}
    		try {
    		emp.setHire_date(hire_dateFormate.parse(emp_HireDate));
    		} catch (ParseException e) {
    		message +=" Error in Employee Hire Date input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		               return;
    		}
    		String emp_Job_id = request.getParameter("jobid");
    		try {
    		Validator.checkjob(emp_Job_id);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Employee jobid input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		               return;

    		}
    		emp.setJob_id(emp_Job_id);
    		String emp_salary = request.getParameter("salary");
    		try {
    		Validator.checklengthOfString(emp_salary);
    		} catch (InvalidInputDataException e) {
    		message +=" Error in Employee Salary input </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		               return;

    		}
    		float salary = Float.parseFloat(emp_salary);
    		emp.setSalary(salary);
    		int result = EmployeeDao.updateEmployee(emp);
    		out.println(result + "Successfully row Updated");
    		} catch (Exception e) {
    		message +=" Error in Employee Update not Completed </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		           return;
    		}
    		try {
    		out.close();
    		} catch (Exception e) {
    		message +=" Error in Employee Update not Completed </p>";
    		String errorPage=ExceptionManager.handleException(e, source, message);
    		out.print(errorPage);
    		           return;
    		}
    		}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source = "DeleteEmployee";
		String message = "<h1>Error while" + source + "</h1>";
		int result = 0;
		String id = null;
		try {
			id = request.getParameter("id");
			try {
				Validator.checkStringForParse(id);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee id input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			int empId = Integer.parseInt(id);
			try {
				Validator.CheckNumberForGreaterThanZero(empId);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee id input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			result = EmployeeDao.deleteEmployee(empId);
			System.out.println(result + " Deleted Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print("<div> Deleted Employee: " + result + "</div>");
	}



}

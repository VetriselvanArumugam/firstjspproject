<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>update Employee</title>
</head>
<body>
<form action="AddEmployee" method="post"><!-- need to mention method =post -->
        <center>
        <div>
            ID: <input type='text' name='id' placeholder="Employee Id">
            </div>
            <div>
            First Name: <input type='text' name='fname' placeholder="Employee fName">
            </div>
            <div>
            Last Name: <input type='text' name='Lname' placeholder="Employee lName">
            </div>
            <div>
            Email: <input type='text' name='email' placeholder="abcd@mail.com">
            </div>
            <div>
            Hire date: <input type='text' name='hdate' placeholder="DD/MM/YYYY">
            </div>
            <div>
            Job id: <input type='text' name='jobid' placeholder="Job_Id">
            </div>
            <div>
            Salary: <input type='text' name='salary' placeholder="00.000"><br>
            </div>
            <input type=submit name='change' value='update'>
        </center>
        </form>
</body>
</html>
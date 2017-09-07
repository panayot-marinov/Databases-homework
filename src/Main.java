import java.util.*;
import java.sql.*;
public class Main 
{
	public static void main(String[] args) throws SQLException
	{
		String url = "jdbc:postgresql://localhost/Homework";
		Properties props = new Properties();
		props.setProperty("user", "postgres");
		props.setProperty("password", "parola123");
		Connection conn = DriverManager.getConnection(url, props);
		PreparedStatement prepareStatement = null;
		
		try
		{
			conn.prepareStatement("CREATE TABLE FACULTIES(name VARCHAR(50) UNIQUE NOT NULL)").executeUpdate();
			conn.prepareStatement("INSERT INTO FACULTIES(name) VALUES('FMI')").executeUpdate();
			conn.prepareStatement("INSERT INTO FACULTIES(name) VALUES('TECHNIVAL UNIVERSITY')").executeUpdate();
			conn.prepareStatement("INSERT INTO FACULTIES(name) VALUES('PHILOSOPHIAN UNIVERSITY')").executeUpdate();
			conn.prepareStatement("INSERT INTO FACULTIES(name) VALUES('SOFTWARE UNIVERSITY')").executeUpdate();
			conn.prepareStatement("INSERT INTO FACULTIES(name) VALUES('HISTORICAL UNIVERSITY')").executeUpdate();
			conn.prepareStatement("INSERT INTO FACULTIES(name) VALUES('PSYCHOLOGICAL UNIVERSITY')").executeUpdate();
			conn.prepareStatement("INSERT INTO FACULTIES(name) VALUES('SOFIA UNIVERSITY')").executeUpdate();

			
			conn.prepareStatement("CREATE TABLE STUDENTS(ID INTEGER PRIMARY KEY, faculty_name VARCHAR(50) REFERENCES faculties(name) ON DELETE CASCADE, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL)").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENTS(ID, faculty_name, first_name, last_name) VALUES (3204, 'FMI', 'IVAN', 'IVANOV')").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENTS(ID, faculty_name, first_name, last_name) VALUES (3114, 'TECHNIVAL UNIVERSITY', 'PANAYOT', 'MARINOV')").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENTS(ID, faculty_name, first_name, last_name) VALUES (2843, 'PHILOSOPHIAN UNIVERSITY', 'ROSEN', 'BAKURDJIEV')").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENTS(ID, faculty_name, first_name, last_name) VALUES (2845, 'SOFTWARE UNIVERSITY', 'DRAGAN', 'PETKOV')").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENTS(ID, faculty_name, first_name, last_name) VALUES (1845, 'HISTORICAL UNIVERSITY', 'PESHO', 'PESHEV')").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENTS(ID, faculty_name, first_name, last_name) VALUES (2222, 'PSYCHOLOGICAL UNIVERSITY', 'IVAILO', 'DOICHEV')").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENTS(ID, faculty_name, first_name, last_name) VALUES (4444, 'SOFIA UNIVERSITY', 'HARRY', 'TABAKOV')").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENTS(ID, faculty_name, first_name, last_name) VALUES (9899, 'TECHNIVAL UNIVERSITY', 'LIUBOMIR', 'DAMIANOV')").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENTS(ID, faculty_name, first_name, last_name) VALUES (8988, 'SOFTWARE UNIVERSITY', 'PAVEL', 'SIDEROV')").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENTS(ID, faculty_name, first_name, last_name) VALUES (8999, 'TECHNIVAL UNIVERSITY', 'IVAN', 'PETKOV')").executeUpdate();
			conn.prepareStatement("DELETE FROM STUDENTS WHERE first_name = 'PAVEL'").executeUpdate();
			conn.prepareStatement("DELETE FROM FACULTIES WHERE name = 'PSYCHOLOGICAL UNIVERSITY'").executeUpdate();


			conn.prepareStatement("CREATE TABLE COURSES(ID INTEGER PRIMARY KEY, course_name VARCHAR(50), description VARCHAR(50), credits INTEGER)").executeUpdate();
			conn.prepareStatement("INSERT INTO COURSES(ID, course_name, description, credits) VALUES (3, 'JAVA', 'IT_IS_VERY_FUNNY!', 18)").executeUpdate();
			conn.prepareStatement("INSERT INTO COURSES(ID, course_name, description, credits) VALUES (2, 'HTML_CSS', 'WEB_DESIGN', 12)").executeUpdate();
			conn.prepareStatement("INSERT INTO COURSES(ID, course_name, description, credits) VALUES (1, 'C#', 'LEARNING_C#_IS_FUNNY!', 13)").executeUpdate();
			conn.prepareStatement("INSERT INTO COURSES(ID, course_name, description, credits) VALUES (4, 'JAVASCRIPT', 'LEARN_TO_CODE_WITH_US!', 15)").executeUpdate();
			conn.prepareStatement("DELETE FROM COURSES WHERE course_name = 'JAVASCRIPT'").executeUpdate();


			conn.prepareStatement("CREATE TABLE STUDENT_COURSES(STUDENT_ID INTEGER NOT NULL, COURSE_ID INTEGER)").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENT_COURSES(STUDENT_ID, COURSE_ID) VALUES(3204, 1)").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENT_COURSES(STUDENT_ID, COURSE_ID) VALUES(3114, 2)").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENT_COURSES(STUDENT_ID, COURSE_ID) VALUES(2843, 3)").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENT_COURSES(STUDENT_ID, COURSE_ID) VALUES(2845, 4)").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENT_COURSES(STUDENT_ID, COURSE_ID) VALUES(1845, 3)").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENT_COURSES(STUDENT_ID, COURSE_ID) VALUES(2222, 2)").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENT_COURSES(STUDENT_ID, COURSE_ID) VALUES(4444, 1)").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENT_COURSES(STUDENT_ID, COURSE_ID) VALUES(9899, 2)").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENT_COURSES(STUDENT_ID, COURSE_ID) VALUES(8988, 3)").executeUpdate();
			conn.prepareStatement("INSERT INTO STUDENT_COURSES(STUDENT_ID, COURSE_ID) VALUES(8999, 4)").executeUpdate();
			
			
			System.out.println("Faculties list:");
			
			prepareStatement = conn.prepareStatement("SELECT name FROM FACULTIES");
			ResultSet executeQuery2 = conn.prepareStatement("SELECT name FROM FACULTIES").executeQuery();
			while(executeQuery2.next())
			{
				System.out.println(executeQuery2.getString(1));
			}
			System.out.println();
			
			System.out.println("Courses list:");
			
			prepareStatement = conn.prepareStatement("SELECT course_name FROM COURSES");
			ResultSet executeQuery3 = conn.prepareStatement("SELECT course_name FROM COURSES").executeQuery();
			while(executeQuery3.next())
			{
				System.out.println(executeQuery3.getString(1));
			}
			System.out.println();
			System.out.println("Students and courses list:");
			
        	prepareStatement = conn.prepareStatement("SELECT COURSES.course_name, STUDENTS.first_name, STUDENTS.last_name FROM STUDENTS INNER JOIN STUDENT_COURSES ON STUDENT_COURSES.STUDENT_ID = STUDENTS.ID INNER JOIN COURSES ON STUDENT_COURSES.COURSE_ID = COURSES.ID");
			ResultSet executeQuery = conn.prepareStatement("SELECT COURSES.course_name, STUDENTS.first_name, STUDENTS.last_name FROM STUDENTS INNER JOIN STUDENT_COURSES ON STUDENT_COURSES.STUDENT_ID = STUDENTS.ID INNER JOIN COURSES ON STUDENT_COURSES.COURSE_ID = COURSES.ID").executeQuery();
			while(executeQuery.next())
			{
				System.out.println(executeQuery.getString(1) + " | " + executeQuery.getString(2)  + " " + executeQuery.getString(3));
			}
				
			System.out.println();
			System.out.println("Students and faculties list:");
			
			prepareStatement = conn.prepareStatement("SELECT faculty_name, first_name, last_name FROM STUDENTS");
			ResultSet executeQuery1 = conn.prepareStatement("SELECT faculty_name, first_name, last_name FROM STUDENTS").executeQuery();
			while(executeQuery1.next())
			{
				System.out.println(executeQuery1.getString(1) + " | " + executeQuery1.getString(2)  + " " + executeQuery1.getString(3));
			}
			
			System.out.println();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		finally
		{
			conn.close();
		}
	}
}

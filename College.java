import javax.management.ListenerNotFoundException;

/**
 * Represents a college, and college management operations.
 * A college has courses, and students. Students take courses and get grades.
 * (See the Course, Student, and CourseTaken classes for more details).
 */
public class College {
	
	private String name; // the name of this college
	private LinkedList<Course> courses;
	private LinkedList<Student> students;
	
	/**
	 * Constructs a new college, with empty student and course lists.
	 */
	public College(String name) {
		this.name = name;
		this.courses = new LinkedList<Course>();
		this.students = new LinkedList<Student>();
	}
	
	/** 
	 * Adds the given course to the course list of this college.
	 * @param cid   course id.
	 * @param title course title.
	 */
	public void addCourse(int cid, String title) {
		// Replace the return statement with your code.
		if(getCourse(cid) == null){
		Course newCourse = new Course(cid, title);
		courses.add(newCourse);
}
	}
	
	/**
	 * Returns a list of all the courses. within the college
	 */
	public LinkedList<Course> coursesList() {
		// Replace the return statement with your code.
		
		return courses;
	}

	/** 
	 * If the given course is in this college, removes it and returns true.
	 * Otherwise returns false.
	 * @param  cid the course to remove.
	 * @return True if the course was removed, false if there is no such course. 
	 */
	public boolean removeCourse(int cid) {
		// Replace the return statement with your code.
		// Note: You can get the course with the given cid by calling getCourse
		return courses.remove(getCourse(cid));
	}
	
	// Returns the course that has the given id, or null if there is no such course.
	public Course getCourse(int cid) {
		// Replace the return statement with your code.
		ListIterator<Course> iterator = courses.iterator();
		Course toFind = new Course(cid, "");
		while(iterator.hasNext()){
			Course current = iterator.next();
			if(current.equals(toFind)){
				return current;
			} 
		}
		return null;
	}
	
	/** 
	 * Adds the given student to the students list of this college.
	 * @param sid   student id
	 * @param name  student name
	 */
	public void addStudent(int sid, String name) {
		// Replace the return statement with your code.
		if(getStudent(sid) == null){
		Student student = new Student (sid, name);
		students.add(student);
	}
	
}
	
	/**
	 * Returns a list of all the students who enroll in the school.
	 */
	public LinkedList<Student> studentsList() {
		// Replace the return statement with your code.// TODO
		return students;
	}
	
	/** 
	 * If the given student is in this college, removes it and returns true.
	 * Otherwise returns false.
	 * @param  sid  the student's id.
	 * @return True if the student was removed, false if there is no such student. 
	 */
	public boolean removeStudent(int sid) {
		// Replace the return statement with your code.
		// Note: You can get the student with the given sid by calling the getStudent method.
		return students.remove(this.getStudent(sid));
	}
	
	
	// Returns the student that has the given id, or null if there is no such student.
	public Student getStudent(int sid) {
		// Replace the return statement with your code.
		ListIterator<Student> iterator = students.iterator();
		Student toFind = new Student(sid, "");
		while(iterator.hasNext()){
			Student current = iterator.next();
			if(current.equals(toFind)){
				return current;
			}
		}
		return null;
	}
	
	/** 
	 * Adds the given course to the course list of the given student.
	 * @param sid   student id
	 * @param cid   course id
	 * @param grade student's grade in the course 
	 */
	public void addCourseTaken(int sid, int cid, int grade) {
		// Replace the return statement with your code.
		Student studentWithCourse = this.getStudent(sid);
		if(getCourse(cid) != null){
		studentWithCourse.addCourse(this.getCourse(cid), grade);
		}

	}
	
	/** 
	 * Returns all the students' student report.
	 * See the Student class for more details.
	 * @param sid  student id
	 */
	public String studentReport(int sid) {
		// Your code should call the student's studentReport method
		if(this.getStudent(sid) == null){
			return null;
		}
		return this.getStudent(sid).studentReport();
	}
	
	// Returns a list of all the students who took the given course
	public LinkedList<Student> studentsWhoTook(Course c) {
		// replace the following statement with your code.
		LinkedList<Student> studentsWhoTookCourse = new LinkedList<Student>();
		ListIterator<Student> iterator = students.iterator();
		while(iterator.hasNext()){
			Student current = iterator.next();
			if(current.tookCourse(c)){
				studentsWhoTookCourse.add(current);

		}
	}

		return studentsWhoTookCourse;

	}
	
	// Returns a list of the all the student got the given grade or above it 
	public LinkedList<Student> studentsWithGrade(Course c, int grade) {
		// replace the following statement with your code.
		LinkedList<Student> studentsWhoTook = new LinkedList<Student>();
		ListIterator<Student> listIterator = this.students.iterator();
		while(listIterator.hasNext()){
			Student current = listIterator.next();
			if(current.gradeInCourse(c)>=grade){
				studentsWhoTook.add(current);
			} 
		}
		return studentsWhoTook;

	}
	

	// Returns a list of courses with the grade averages of all the students who took it
	// If no student took the course return -1
	public LinkedList<CourseTaken> getCoursesAverages() {
		// replace the following statement with your code.
		LinkedList<CourseTaken> coursesTaken = new LinkedList<CourseTaken>();
		ListIterator<Course> courseListIterator = this.courses.iterator();
		while(courseListIterator.hasNext()){
			Course currentCourse = courseListIterator.next();
			LinkedList<Student> studentsInClass = studentsWhoTook(currentCourse);
			int grade = 0;
			ListIterator<Student> studentListIterator = students.iterator();
		while(studentListIterator.hasNext()){
			   Student currentStudent = studentListIterator.next();
				grade += currentStudent.gradeInCourse(currentCourse);
		} if(studentsInClass.size() == 0){
			grade = -1;
		}else{
			grade = (int) (1.0*grade)/(studentsInClass.size());
		}
		coursesTaken.add(new CourseTaken(currentCourse, grade));
	    
    }
		return coursesTaken;

	}


	/** 
	 * Returns the students with the highest grade in the given course.
	 * @param cid  course id
	 */
	public LinkedList<Student> topPerfomerReport(int cid) {
		// replace the following statement with your code.
		
		if(getCourse(cid) == null){
			return null;
		}
		ListIterator<Student> studentListIterator = studentsWhoTook(getCourse(cid)).iterator();
		int grade = 0;
		while(studentListIterator.hasNext()){
			Student student = studentListIterator.next();
			if(student.gradeInCourse(getCourse(cid))> grade){
			grade = student.gradeInCourse(getCourse(cid));
			
		}
	}
		return studentsWithGrade(getCourse(cid), grade);
	}

	/** 
	 * Returns the college name
	 * @return the college name 
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * A textual representation of this college, along with its courses and students.
	 */
	public String toString() {
		String str = name + "\n";
		str += "courses:" + "\n";
		str += courses.toString() + "\n";
		str += "students:" + "\n";
		str += students.toString() + "\n";
		return str;
	}
}

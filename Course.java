/** 
 * Represents a course.
 */
public class Course {

	private int cid;
	private String title;
	
	/** 
	 * Constructs a new course.
	 * @param cid   the course id
	 * @param title the course title
	 */
	public Course(int cid, String title) {
		this.cid = cid;
		this.title = title;
	}
	
	/**
	 * returns the course id
	 * @return the course id
	 */
	public int getCid() {
		return this.cid;
	}
	
	/**
	 * returns the course title
	 * @return the course title
	 */
	public String getTitle() {
		return this.title;
	}

	/** 
	 * Two courses will be consdired equals iff they have the same cid
	 * @param other the other course
	 * @return true/false
	 */
	public boolean equals(Course other){
		// Replace the return statement with your code.
		if(this.getCid() == other.getCid()){
			return true;
		}
		return false;
	}
		
	/**
	 * A textual representation of this course.
	 */
	public String toString() {
		return "Course " + this.cid + ": " + this.title;
	}
}

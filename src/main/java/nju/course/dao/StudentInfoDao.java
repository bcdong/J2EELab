package nju.course.dao;

import java.util.ArrayList;

/**
 * Created by Mr.Zero on 2016/12/30.
 */
public interface StudentInfoDao {
    /**
     * get student name
     * @param student_id
     * @return student name if id exists, null if not exist
     */
    public String getName(int student_id);

    /**
     * get courses the student majored in
     * @param student_id
     * @return course list or empty list if no major courses
     */
    public ArrayList<String> getMajorCourses(int student_id);
}

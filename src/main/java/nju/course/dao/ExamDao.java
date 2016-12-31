package nju.course.dao;

import java.util.ArrayList;

/**
 * Created by Mr.Zero on 2016/12/30.
 */
public interface ExamDao {
    /**
     * get absent exams
     * @param student_id
     * @return a list of exam names or empty list if no absent exams
     */
    public ArrayList<String> getAbsentExams(int student_id);
}

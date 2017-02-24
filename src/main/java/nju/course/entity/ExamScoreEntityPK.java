package nju.course.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Mr.Zero on 2017/2/24.
 */
public class ExamScoreEntityPK implements Serializable {
    private int examId;
    private int studentId;

    @Column(name = "exam_id", nullable = false)
    @Id
    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    @Column(name = "student_id", nullable = false)
    @Id
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamScoreEntityPK that = (ExamScoreEntityPK) o;

        if (examId != that.examId) return false;
        if (studentId != that.studentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = examId;
        result = 31 * result + studentId;
        return result;
    }
}

package nju.course.entity;

import javax.persistence.*;

/**
 * Created by Mr.Zero on 2017/2/24.
 */
@Entity
@Table(name = "exam_score", schema = "course", catalog = "")
@IdClass(ExamScoreEntityPK.class)
public class ExamScoreEntity {
    private int examId;
    private int studentId;
    private Integer score;

    @Id
    @Column(name = "exam_id", nullable = false)
    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    @Id
    @Column(name = "student_id", nullable = false)
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "score", nullable = true)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamScoreEntity that = (ExamScoreEntity) o;

        if (examId != that.examId) return false;
        if (studentId != that.studentId) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = examId;
        result = 31 * result + studentId;
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }
}

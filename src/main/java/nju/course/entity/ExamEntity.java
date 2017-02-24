package nju.course.entity;

import javax.persistence.*;

/**
 * Created by Mr.Zero on 2017/2/24.
 */
@Entity
@Table(name = "exam", schema = "course", catalog = "")
public class ExamEntity {
    private int id;
    private String examName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "exam_name", nullable = true, length = 255)
    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamEntity that = (ExamEntity) o;

        if (id != that.id) return false;
        if (examName != null ? !examName.equals(that.examName) : that.examName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (examName != null ? examName.hashCode() : 0);
        return result;
    }
}

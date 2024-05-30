package student;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private List<Subject> subjects;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.subjects = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public static class Subject {
        private String name;
        private int[] scores;

        public Subject(String name, int[] scores) {
            this.name = name;
            this.scores = scores;
        }

        public String getName() {
            return name;
        }

        public int[] getScores() {
            return scores;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setScores(int[] scores) {
            this.scores = scores;
        }
    }
}

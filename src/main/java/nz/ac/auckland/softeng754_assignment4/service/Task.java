package nz.ac.auckland.softeng754_assignment4.service;

public class Task {
    private int id;
    Reviewer reviewer = new Reviewer();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

}

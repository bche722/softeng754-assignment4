package nz.ac.auckland.softeng754_assignment4.service;

import java.util.ArrayList;
import java.util.List;

public class Allocation {
    private IDatabase iDatabase;
    Reviewer reviewer;
    List<Reviewer> reviewers = new ArrayList<Reviewer>();


    public Allocation(IDatabase db) {
        this.iDatabase = db;
    }

    public void addReviewersToReviewers(List<Reviewer> reviewers){
        for (Reviewer reviewer: reviewers){
            this.reviewers.add(reviewer);
        }
    }

    public boolean addReviewersToDB(List<Reviewer> reviewers){
        return iDatabase.updateReviewers(reviewers);
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public List<Reviewer> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<Reviewer> reviewers) {
        this.reviewers = reviewers;
    }
}

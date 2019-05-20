package nz.ac.auckland.softeng754_assignment4.service;


import java.util.List;
import java.util.Random;

import nz.ac.auckland.softeng754_assignment4.model.Reviewer;
import nz.ac.auckland.softeng754_assignment4.model.Task;

public class AllocationService {
    private IDatabase iDatabase;

    public AllocationService(IDatabase db) {
        this.iDatabase = db;
    }

    public void addReviewers(List<Reviewer> reviewers){
        iDatabase.addReviewers(reviewers);
    }

    public void deleteReviewers(List<Reviewer> reviewers){
        iDatabase.deleteReviewers(reviewers);
    }

    public Reviewer chooseReviewer(){
        int LowestCount = iDatabase.getLowestCount();
        List<Reviewer> reviewers = iDatabase.getReviewersByCount(LowestCount);
        Random rand = new Random();
        return reviewers.get(rand.nextInt() % reviewers.size());
    }

    public void allocateTask(Task task, Reviewer reviewer) {
        task.setReviewer(reviewer.getUsername());
    }

    public void updateTask(Task task) {
        iDatabase.updateTask(task);
    }

    public void addCount(Reviewer chosenReviewer) {
        int currentCount = chosenReviewer.getCount();
        chosenReviewer.setCount(currentCount + 1);
    }

    public void saveCount(Reviewer chosenReviewer) {
        iDatabase.updateReviewer(chosenReviewer);
    }

//    public boolean isReviewerExist(Reviewer reviewer){
//        String username = reviewer.getUsername();
//        reviewer = iDatabase.getReviewerByUsername(username);
//        if (reviewer == null){
//            throw ReviewerExistsException();
//        }
//        return true;
//    }
//
//    public Reviewer getReviewer() {
//        return reviewer;
//    }
//
//    public void setReviewer(Reviewer reviewer) {
//        this.reviewer = reviewer;
//    }
//
//    public List<Reviewer> getReviewers() {
//        return reviewers;
//    }
//
//    public void setReviewers(List<Reviewer> reviewers) {
//        this.reviewers = reviewers;
//    }
}

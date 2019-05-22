package nz.ac.auckland.softeng754_assignment4.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nz.ac.auckland.softeng754_assignment4.exception.UsernameExistsException;
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

    public Reviewer chooseReviewer() throws NullPointerException{
        int LowestCount = iDatabase.getLowestCount();
        List<Reviewer> reviewers = iDatabase.getReviewersByCount(LowestCount);
        Random rand = new Random();
        return reviewers.get(rand.nextInt(reviewers.size()));
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

    public void checkUsernames(List<Reviewer> reviewers) throws UsernameExistsException {
        for (Reviewer reviewer: reviewers){
            if (iDatabase.getReviewerByUsername(reviewer.getUsername()) != null){
                System.out.println(reviewer.getUsername() + " exists");
                throw new UsernameExistsException("user: " + reviewer.getUsername() + "exists");
            }
        }
    }

}

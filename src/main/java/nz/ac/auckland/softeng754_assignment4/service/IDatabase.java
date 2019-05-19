package nz.ac.auckland.softeng754_assignment4.service;

import nz.ac.auckland.softeng754_assignment4.Model.Reviewer;
import nz.ac.auckland.softeng754_assignment4.Model.Task;

import java.util.List;

public interface IDatabase {
    void addReviewers(List<Reviewer> reviewers);
    void deleteReviewers(List<Reviewer> reviewers);

    int getLowestCount();
    List<Reviewer> getReviewersByCount(int count);

    void updateTask(Task task);

    void updateReviewer(Reviewer chosenReviewer);

//    Reviewer getReviewerByUsername(String username);

//    List<Reviewer> getAllReviewers();

}

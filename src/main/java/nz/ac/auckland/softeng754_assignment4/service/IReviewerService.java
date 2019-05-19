package nz.ac.auckland.softeng754_assignment4.service;

import nz.ac.auckland.softeng754_assignment4.model.Reviewer;
import nz.ac.auckland.softeng754_assignment4.model.Task;

import java.util.ArrayList;
import java.util.List;

public interface IReviewerService {
    List<Reviewer> reviewers = new ArrayList<Reviewer>();

    Reviewer addReviewer(String username);
    Reviewer getReviewer(int id);
    void deleteReviewer(Reviewer reviewer);
    Task allocateTask(Task task, Reviewer reviewer);
    Reviewer chooseReviewer(List<Reviewer> reviewers);
}

package nz.ac.auckland.softeng754_assignment4.service;

public interface IReviewerService {
    Reviewer addReviewer(String username);
    Reviewer getReviewer(int id);
    void deleteReviewer(Reviewer reviewer);
}

package nz.ac.auckland.softeng754_assignment4.service;

import java.util.List;

import nz.ac.auckland.softeng754_assignment4.model.CodeAnomaly;
import nz.ac.auckland.softeng754_assignment4.model.Reviewer;

public interface INetworkService {

	public void sendReviewResult(Reviewer reviewer, CodeAnomaly codeAnomaly);

	public List<CodeAnomaly> receiveReviewResult(Reviewer reviewer);

	public void sendFeedback(CodeAnomaly codeAnomaly, String comments);

}

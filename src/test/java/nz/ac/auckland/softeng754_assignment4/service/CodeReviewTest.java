package nz.ac.auckland.softeng754_assignment4.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import nz.ac.auckland.softeng754_assignment4.exception.NoReviewerException;
import nz.ac.auckland.softeng754_assignment4.exception.NoReviewException;
import nz.ac.auckland.softeng754_assignment4.model.CodeAnomaly;
import nz.ac.auckland.softeng754_assignment4.model.Reviewer;

@RunWith(MockitoJUnitRunner.class)
public class CodeReviewTest {

	@Mock
	INetworkService _networkService;

	CodeReviewService _codeReviewService;

	CodeInspectionService _codeInspectionService;

	Reviewer _reviewer;

	@Before
	public void SetUp() throws IOException {
		_codeReviewService = new CodeReviewService(_networkService);
		_codeInspectionService = new CodeInspectionService();
		_reviewer = new Reviewer("Mamo");

	}

	@Test
	public void shouldSendReviewSuccessfullyWithRightReviewer() throws NoReviewerException {
		CodeAnomaly codeAnomaly = _codeInspectionService.inspector("defect").get(0);
		Mockito.doNothing().when(_networkService).sendReviewResult(_reviewer, codeAnomaly);
		_codeReviewService.sendReviewResult(_reviewer, codeAnomaly);
	}

	@Test(expected = NoReviewerException.class)
	public void shouldNotSendReviewWithNoReviewer() throws NoReviewerException {
		CodeAnomaly codeAnomaly = _codeInspectionService.inspector("defect").get(0);
		Mockito.doNothing().when(_networkService).sendReviewResult(null, codeAnomaly);
		_codeReviewService.sendReviewResult(null, codeAnomaly);
	}

	@Test
	public void receiveAllReviewResult() throws NoReviewException {
		List<CodeAnomaly> list = new ArrayList<CodeAnomaly>();
		CodeAnomaly codeAnomaly1 = _codeInspectionService.inspector("defect").get(0);
		CodeAnomaly codeAnomaly2 = _codeInspectionService.inspector("smell").get(0);
		list.add(codeAnomaly1);
		list.add(codeAnomaly2);
		Mockito.doNothing().when(_networkService).sendReviewResult(_reviewer, codeAnomaly1);
		Mockito.doNothing().when(_networkService).sendReviewResult(_reviewer, codeAnomaly2);
		Mockito.doReturn(list).when(_networkService).receiveReviewResult(_reviewer);

		List<CodeAnomaly> result = _codeReviewService.receiveReviewResult(_reviewer);

		assertEquals(result, list);
	}

	@Test(expected = NoReviewException.class)
	public void receiveWithNoReviewResult() throws NoReviewException {
		Mockito.doReturn(null).when(_networkService).receiveReviewResult(_reviewer);
		_codeReviewService.receiveReviewResult(_reviewer);
	}

	@Test
	public void shouldSendFeedback() {

		CodeAnomaly codeAnomaly = _codeInspectionService.inspector("defect").get(0);
		Mockito.doNothing().when(_networkService).sendFeedback(codeAnomaly, "ahhhhhh");
		_codeReviewService.sendFeedback(codeAnomaly, "ahhhhhh");

	}

}

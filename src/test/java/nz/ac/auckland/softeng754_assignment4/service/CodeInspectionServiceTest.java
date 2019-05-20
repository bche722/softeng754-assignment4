package nz.ac.auckland.softeng754_assignment4.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.RepositoryContents;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import nz.ac.auckland.softeng754_assignment4.model.CodeAnomaly;

@RunWith(MockitoJUnitRunner.class)
public class CodeInspectionServiceTest {

    @Mock
    IGitHubService githubService;

    ICodeInspectionService codeInspectionService;

    @Mock
    PullRequest pullRequest;

    @Mock
    RepositoryContents contents;

    @Before
    public void SetUp() throws IOException
    {
        // mockup
        when(githubService.getContents(pullRequest)).thenReturn(Arrays.asList(contents));
        codeInspectionService = new CodeInspectionService();
    }

    @Test
    public void shouldClassifiedDefectTypeAnomaly() throws IOException
    {
        // Given
        when(contents.getContent()).thenReturn("mockup defect code here");
        githubService.getContents(pullRequest);

        // When
        List<CodeAnomaly> anomalies = new ArrayList<CodeAnomaly>();
        List<CodeAnomaly> anomaly = codeInspectionService.inspector(contents);
        anomalies.addAll(anomaly);

        // Then
        assertTrue(anomalies.toArray().length == 1);
        assertEquals(anomalies.get(0).getType(), CodeAnomaly.Type.DEFECT);
    }

    @Test
    public void shouldClassifiedSmellTypeAnomaly() throws IOException
    {
        // Given
        when(contents.getContent()).thenReturn("mockup smell code here");
        githubService.getContents(pullRequest);

        // When
        List<CodeAnomaly> anomalies = new ArrayList<CodeAnomaly>();
        List<CodeAnomaly> anomaly = codeInspectionService.inspector(contents);
        anomalies.addAll(anomaly);

        // Then
        assertTrue(anomalies.toArray().length == 1);
        assertEquals(anomalies.get(0).getType(), CodeAnomaly.Type.SMELL);
    }

    @Test
    public void shouldClassifiedMaliciousTypeAnomaly() throws IOException
    {
        // Given
        when(contents.getContent()).thenReturn("mockup malicious code here");
        githubService.getContents(pullRequest);

        // When
        List<CodeAnomaly> anomalies = new ArrayList<CodeAnomaly>();
        List<CodeAnomaly> anomaly = codeInspectionService.inspector(contents);
        anomalies.addAll(anomaly);

        // Then
        assertTrue(anomalies.toArray().length == 1);
        assertEquals(anomalies.get(0).getType(), CodeAnomaly.Type.MALICIOUS);
    }
}

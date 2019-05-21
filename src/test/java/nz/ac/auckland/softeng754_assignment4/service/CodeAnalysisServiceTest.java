package nz.ac.auckland.softeng754_assignment4.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import nz.ac.auckland.softeng754_assignment4.model.CodeAnalysisReport;
import nz.ac.auckland.softeng754_assignment4.model.CodeAnomaly;

@RunWith(MockitoJUnitRunner.class)
public class CodeAnalysisServiceTest {

    @Mock
    IGitHubService githubService;

    @Mock
    ICodeInspectionService inspectionService;

    @Mock
    ICodeAnalysisService codeAnalysisService;

    @Mock
    PullRequest pullRequest;

    @Mock
    RepositoryContents content;

    @Mock
    CodeAnomaly codeAnomaly;

    @Before
    public void SetUp() throws IOException
    {
        // mockup
        when(githubService.getContents(pullRequest)).thenReturn(Arrays.asList(content));
        when(inspectionService.inspector(content)).thenReturn(Arrays.asList(codeAnomaly));
        codeAnalysisService = new CodeAnalysisService();
        codeAnalysisService.setInspectionService(inspectionService);
    }

    @Test
    public void shouldGetAnalysisReport() throws IOException
    {
        // Given
        List<RepositoryContents> contents =  githubService.getContents(pullRequest);

        // When
        CodeAnalysisReport report = codeAnalysisService.analysis(contents);

        // Then
        assertNotNull(report);
        assertTrue(report.getCodeAnomalies().contains(codeAnomaly));
        assertNotNull(report.getAbstraction());
    }
}

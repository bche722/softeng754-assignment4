package nz.ac.auckland.softeng754_assignment4.service;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.softeng754_assignment4.model.CodeAbstraction;
import nz.ac.auckland.softeng754_assignment4.model.CodeAnalysisReport;
import nz.ac.auckland.softeng754_assignment4.model.CodeAnomaly;

import org.eclipse.egit.github.core.RepositoryContents;

public class CodeAnalysisService implements ICodeAnalysisService
{
    ICodeInspectionService inspectionService;

    @Override
    public CodeAnalysisReport analysis(List<RepositoryContents> contents) {
        List<CodeAnomaly> anomalies = new ArrayList<CodeAnomaly>();
        List<String> fileContents = new ArrayList<String>();
        for(RepositoryContents content: contents) {
          String fileContent = content.getContent();
          fileContents.add(fileContent);
          List<CodeAnomaly> result = inspectionService.inspector(content);
          anomalies.addAll(result);
        };
        CodeAbstraction abstraction = new CodeAbstraction(fileContents);
        CodeAnalysisReport report = new CodeAnalysisReport(abstraction, anomalies);
        return report;
    }

    @Override
    public void setInspectionService(ICodeInspectionService service) {
        this.inspectionService = service;
    }
}
package nz.ac.auckland.softeng754_assignment4.service;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.softeng754_assignment4.model.CodeAnomaly;
import nz.ac.auckland.softeng754_assignment4.model.CodePosition;

import org.eclipse.egit.github.core.RepositoryContents;

public class CodeInspectionService implements ICodeInspectionService
{
    @Override
    public List<CodeAnomaly> inspector(RepositoryContents content) {
        String codeContent = content.getContent();
        return this.inspector(codeContent);
    }

    @Override
    public List<CodeAnomaly> inspector(String content) {
        List<CodeAnomaly> anomalies = new ArrayList<>();
        if (content.indexOf("defect") != -1) {
            int column = content.indexOf("defect");
            CodePosition start = new CodePosition(0, column);
            CodePosition end = new CodePosition(0, column + "defect".length());
            anomalies.add(new CodeAnomaly(start, end, CodeAnomaly.Type.DEFECT));
        }
        if (content.indexOf("smell") != -1) {
            int column = content.indexOf("smell");
            CodePosition start = new CodePosition(0, column);
            CodePosition end = new CodePosition(0, column + "smell".length());
            anomalies.add(new CodeAnomaly(start, end, CodeAnomaly.Type.SMELL));
        }
        if (content.indexOf("malicious") != -1) {
            int column = content.indexOf("malicious");
            CodePosition start = new CodePosition(0, column);
            CodePosition end = new CodePosition(0, column + "malicious".length());
            anomalies.add(new CodeAnomaly(start, end, CodeAnomaly.Type.MALICIOUS));
        }
        return anomalies;
    }
}
package nz.ac.auckland.softeng754_assignment4.model;

import java.util.List;

public class CodeAnalysisReport
{
    CodeAbstraction codeAbstraction;

    List<CodeAnomaly> codeAnomalies;

    public CodeAnalysisReport(CodeAbstraction codeAbstraction, List<CodeAnomaly> codeAnomalies)
    {
      this.codeAbstraction = codeAbstraction;
      this.codeAnomalies = codeAnomalies;
    }

    public CodeAbstraction getAbstraction()
    {
        return this.codeAbstraction;
    }

    public List<CodeAnomaly> getCodeAnomalies()
    {
      return this.codeAnomalies;
    }
}
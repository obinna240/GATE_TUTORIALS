package gatecourse.module8;

import java.util.concurrent.atomic.AtomicInteger;

import gate.Resource;
import gate.creole.AbstractLanguageAnalyser;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.creole.metadata.CreoleResource;

@CreoleResource(name = "Shared document statistics",
        comment = "Document statistics PR that keeps a running total")
public class SharedStats extends AbstractLanguageAnalyser {
  
  private AtomicInteger totalCount;
  
  @Override
  public void execute() throws ExecutionException {
    int tokens = document.getAnnotations().get("Token").size(); 
    document.getFeatures().put("token_count", tokens);
    // add the count from this document to the running total, and set the
    // result as another feature.
    document.getFeatures().put("running_total", totalCount.addAndGet(tokens));
  }
  
  @Override
  public Resource init() throws ResourceInstantiationException {
    totalCount = new AtomicInteger(0);
    return this;
  }
}

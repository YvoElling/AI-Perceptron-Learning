import java.util.List;

public class PerceptronLearner {
    
    /**
     * The method that implements perceptron learning
     * @return answer string
     */
    public String execute(List<PVector> positive, List<PVector> negative, Boolean bias, Integer maxIterations, List<PVector> queries)
    {
        int iterations = 0;
        while ( iterations < maxIterations) {
            
            //Iterate over all datapoints in @positive
            for (PVector coordinate : positive) {
                
            }
            
            //Iterate over all datapoints in @negative
            for (PVector coordinate : negative) {
                
            }
            
            //new iteration passed
            iterations++;
        }
        
        //implementation here
        
        return "your output";
    }
}

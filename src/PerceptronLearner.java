import java.util.List;

public class PerceptronLearner {
    
    /**
     * The method that implements perceptron learning
     * @return answer string
     */
    public String execute(List<PVector> positive, List<PVector> negative, Boolean bias, Integer maxIterations, List<PVector> queries)
    {
        //Initialize iterations counter
        int iterations = 0;
       
        //Create w-vector to determine weights for neural network
        PVector w = new PVector(0, 0);
        
        //Iterate at most until we have reached our limit
        while ( iterations < maxIterations) {
            //If boolean is set to true, then we know that training set is correct 
            boolean done = true;
            
            //Iterate over all datapoints in @positive
            for (PVector coordinate : positive) {
                //If a coordinate of the positive set is found to be less or
                //equal to zero, than we need to modify w in order for it
                //to be on the right side of the linear seperation
                if ( w.dotProduct(coordinate) <= 0) {
                    //Add the coordinate to our current value for w
                    w.add(coordinate);
                    //We found an incorrect item this rond, so we won't 
                    //be done this round. Set @code done to false in order
                    //to continue for another iteration
                    done = false;
                }
            }
            
            //Iterate over all datapoints in @negative
            for (PVector coordinate : negative) {
                //If a coordinate of the negative training set is still on 
                //the wrong side of the linear seperation, then w is incorrect
                //and we need to update w
                if ( w.dotProduct(coordinate) > 0) {
                    //Subtract our coordinate from the current w value
                    w.subtract(coordinate);
                    //We found an incorrect datapoint, so we will not be done
                    //this iteration. Classify again. 
                    done = false; 
                }
            }
            
            //new iteration passed
            iterations++;
        
            //If the boolean was not set to false (i.e. no incorrect value
            //was found, that means we have processed all testing data and 
            //we are ready for the real data
            if (done) {
                break;
            }
        }
        
        //implementation here
        
        return "your output";
    }
}

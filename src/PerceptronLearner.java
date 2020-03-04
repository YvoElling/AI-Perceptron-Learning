import java.util.List;
import java.util.ArrayList;

public class PerceptronLearner {
    

    /**
     * executes the perceptron learning algorithm using the training datasets
     * and eventually runs the trained algorithm on the input data to generate
     * the output. 
     * 
     * @param positive the positive classifier for training
     * @param negative the negative classifier for tarining
     * @param bias whether a bias is required for the dataset
     * @param maxIterations maximum of allowed iterations
     * @param queries list of points who generate the output
     * @return string containing the amount of iterations required and classified
     */
    public String execute(List<PVector> positive, List<PVector> negative, Boolean bias, Integer maxIterations, List<PVector> queries)
    {
        //If bias is set, then we add an extra dimension for every PVector 
        //collection
        if (bias) {
            //Add dimension to positive training set
            for (PVector coordinate : positive) {
                //by adding a new value to its underlying datastucture
                //that is, making a it a vector of dimension n+1;
                coordinate.addCoord(1);
            }
            //Add dimension to negative training set
            for (PVector coordinate : negative) {
                coordinate.addCoord(1);
            }
            //Add dimension to queries
            for (PVector coordinate : queries) {
                coordinate.addCoord(1);
            }
            
        }
        
        
        //Initialize iterations counter
        int iterations = 0;
       
        //Create w-vector to determine weights for neural network
        PVector w = PVector.constant(positive.get(0).size(), 1);
        
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
                    w = w.add(coordinate);
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
                    w = w.subtract(coordinate);
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
        
        //Check that we got out of the for loop through done and not because
        //we have reached the maximum amount of iteratoins
        if (iterations == maxIterations) {
            //Return just the total amount of iterations without finding 
            //solution
            return Integer.toString(iterations);
        }
        
        //Create a string and add the amount of iterations taken already
        String output = Integer.toString(iterations) + " ";
        
        //training has been completed. W should now be properly set
        //we continue with processing the input data to generate the output
        for (PVector query : queries) {
            //If the value is of the right hand side of the linear seperation
            //then we add a '+' for this query
            if ( w.dotProduct(query) > 0) {
                output = output + "+";
            } else {// <= 0 {
                //Else, the value is on the left hand side of the seperation,
                //hence we add a '-' for this query
                output = output + "-";
            }
        }
        
        //Return the output value that contains the number of iterations and
        //a token for in which region the provided value falls
        return output;
    }
}

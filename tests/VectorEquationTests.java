import net.mega2223.math.VectorEquations;

public class VectorEquationTests {
    public static void main(String[] args) {
        float[] vec1 = {5,5,5,5};
        float[] transformationMatrix = {1,0,0,0 , 1,1,0,0 , 0,0,0,1 , 0,0,1,0};

        VectorEquations.printVector(VectorEquations.multiplyVectorByMatrix(vec1,transformationMatrix));

    }
}

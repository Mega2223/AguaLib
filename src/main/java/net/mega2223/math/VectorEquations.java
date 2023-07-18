package net.mega2223.math;

public class VectorEquations {
    /**
     * Multiplies a vector by a matrix, matrix **MUST** have a lenght that can be divided by the vector's lenght
     * */
    public static float[] multiplyVectorByMatrix(float[] v, float[] m){
        float[] ret = new float[v.length];
        if (m.length%v.length != 0) {return null;}
        for (int i = 0; i < m.length; i++) {
            ret[i/v.length] += m[i] * v[i % v.length];
        }
        return ret;
    }

    public static int[] multVecConst(int[] vec, int c){
        for (int i = 0; i < vec.length; i++) {
            vec[i]*=c;
        }
        return vec;
    }

    public static void addToVector(float[] vec1, float[] vec2){
        for (int i = 0; i < vec1.length; i++) {
            vec1[i]+=vec2[i];
        }
    }
    public static void addToVector(double[] vec1, double[] vec2){
        for (int i = 0; i < vec1.length; i++) {
            vec1[i]+=vec2[i];
        }
    }

    public static void printVector(float[] vectir){
        StringBuilder stringBuilder = new StringBuilder("V=[");
        for (int i = 0; i < vectir.length-1; i++) {
            stringBuilder.append(vectir[i]).append(",");
        }
        stringBuilder.append(vectir[vectir.length - 1]).append("]");
        System.out.println(stringBuilder);
    }
    public static void printMatrix(float[] matrix, int rowSize){
        StringBuilder out = new StringBuilder();
        for (int x = 0; x < matrix.length; x+= rowSize) {
            out.append("[");
            for (int y = 0; y < rowSize - 1; y++) {
                out.append(matrix[x+y]).append(",");
            }
            out.append(matrix[x+rowSize-1]);
            if(x < matrix.length - 1){out.append("]\n");}
        }

        System.out.println(out);
    }
}

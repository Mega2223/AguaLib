package net.Mega2223.neural.neuralLib;

public class Matrix {

    double [][] dados;
    int rows,cols;

    public Matrix(int rows, int cols){
        dados = new double[][]{};

        for(int f = 0; f < rows; f++){
            for(int g = 0; g < cols; g++){
                dados[f][g] = 0;
            }
        }

        this.rows = rows;
        this.cols = cols;
    }

    public void run(){
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                dados[i][j]=Math.random()*2-1;
            }
        }
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        Matrix temp=new Matrix(a.rows,a.cols);
        for(int i=0;i<a.rows;i++)
        {
            for(int j=0;j<a.cols;j++)
            {
                temp.dados[i][j]=a.dados[i][j]-b.dados[i][j];
            }
        }
        return temp;
    }
    public static Matrix transpose(Matrix a) {
        Matrix temp=new Matrix(a.cols,a.rows);
        for(int i=0;i<a.rows;i++)
        {
            for(int j=0;j<a.cols;j++)
            {
                temp.dados[j][i]=a.dados[i][j];
            }
        }
        return temp;
    }

    public void multiply(Matrix a) {
        for(int i=0;i<a.rows;i++)
        {
            for(int j=0;j<a.cols;j++)
            {
                this.dados[i][j]*=a.dados[i][j];
            }
        }

    }

    public void multiply(double a) {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                this.dados[i][j]*=a;
            }
        }

    }
}

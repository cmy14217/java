package com.cultivation.javaBasicExtended.matrixMultiplication;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings({"WeakerAccess", "unused"})
class Matrix {
    private final int[][] storage;

    public int rows() {return storage.length;}

    public int columns() {return storage[0].length;}

    public Matrix(int[][] matrixArray) {
        // TODO: please implement the constructor of a matrix.
        // <--start
        String message = "";
        if (matrixArray == null) {
            message = "Raw matrix is null";
        } else if (matrixArray.length == 0){
            message = "Raw matrix contains 0 row";
        } else {
            int lengthOfFirstRowOfArray = matrixArray[0].length;
            for (int[] oneRow : matrixArray) {
                if (oneRow == null) {
                    message = "Raw matrix contains null row";break;
                } else if (oneRow.length == 0) {
                    message = "At least one row of raw matrix contains 0 column";break;
                } else if (lengthOfFirstRowOfArray != oneRow.length) {
                    message = "Raw matrix is not rectangle";break;
                }
            }
        }
        if(!message.equals("")){
            throw new IllegalArgumentException(message);
        }
        this.storage = matrixArray;
        // --end-->
    }

    public static Matrix multiply(Matrix left, Matrix right) {
        // TODO: please implement the method to pass the tests.
        // <--start
        if (left == null || right == null){
            throw new IllegalArgumentException();
        }
        if (left.rows() != right.columns() && left.columns() != right.rows()){
            throw new IllegalArgumentException();
        }
        int resultRow = 0;
        int resultColumn = 0;
        if(left.columns() == right.rows()){
            resultRow = left.rows();
            resultColumn = right.columns();
        }
        int[][] resultMatrix = new int[resultRow][resultColumn];
        for (int i = 0; i < resultRow; i++) {
            for (int j = 0; j < resultColumn; j++) {
                int sum = 0;
                for (int k = 0; k < left.columns(); k++) {
                    sum += left.storage[i][k] * right.storage[k][j];
                }
                resultMatrix[i][j] = sum;
            }
        }
        return new Matrix(resultMatrix);
        // --end-->
    }

    // TODO: you can add some helper method if you like.
    // <--start

    // --end->

    public int[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows()) { throw new IllegalArgumentException(); }
        return storage[rowIndex];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (this == obj) { return true; }
        if (Matrix.class != obj.getClass()) { return false; }

        Matrix matrix = (Matrix) obj;
        if (rows() != matrix.rows() || columns() != matrix.columns()) {
            return false;
        }

        int rows = rows();
        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            if (!Arrays.equals(getRow(rowIndex), matrix.getRow(rowIndex))) { return false; }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = Arrays.hashCode(getRow(0));
        int rows = rows();
        for (int rowIndex = 1; rowIndex < rows; ++rowIndex) {
            hash ^= Arrays.hashCode(getRow(rowIndex));
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(storage)
            .forEach(row -> formatRow(builder, row));
        return builder.toString();
    }

    private void formatRow(StringBuilder builder, int[] row) {
        for (int item : row) {
            builder.append(String.format("%-10s", Integer.toString(item)));
        }
        builder.append("\n");
    }
}

/*
Габидуллин А.Э.
Вариант №15.
Задача 2.35.
Дана целочисленная прямоугольная матрица размера 𝑚 × 𝑛, заполненная случайными числами. Дано натуральное число 𝑘 ≤ 𝑛.
Выполнить циклический сдвиг элементов матрицы на k позиций «по змейке», которая начинается с левого верхнего угла и
проходит по строкам слева направо. Часть элементов каждой строки, «выходящих» за пределы матрицы вправо, переходит на
следующую строку, элементы последней строки сдвигаются в верхнюю строчку.
 */

package theme2;

import java.util.Scanner;

public class Matrix {
    private int countLines;
    private int countColumn;

    private static int[][] matrixArray;


    public Matrix() throws Exception {
        this.countLines = setParameter("количество строк");
        this.countColumn = setParameter("количество столбцов");

        matrixArray = createMatrix(countLines, countColumn);

    }

    public int getCountLines() {
        return countLines;
    }

    public int getCountColumn() {
        return countColumn;
    }

    private static void setMatrixArray(int[][] matrixArray) {
        Matrix.matrixArray = matrixArray;
    }

    private int[][] getMatrixArray() {
        return matrixArray;
    }

    private int setShiftParameter() throws Exception {
        System.out.println("Введите параметр сдвига:");
        Scanner scanner = new Scanner(System.in);
        int shiftParameter = scanner.nextInt();
        if (shiftParameter < 0) {
            throw new Exception("Параметр сдвига должен быть целым числом >= 0");
        }

        return shiftParameter;
    }

    private int setParameter(String parameter) throws Exception {
        System.out.println("Введите " + parameter);
        Scanner input = new Scanner(System.in);
        int inputParameter = input.nextInt();
        if (inputParameter < 1) {
            throw new Exception(parameter + " должно быть целым положительным числом");
        }

        return inputParameter;
    }


    public static void main(String[] args) throws Exception {
        Matrix matrix = new Matrix();
        showArray(matrix.getMatrixArray());
        shiftMatrix(matrix.getMatrixArray(), matrix.setShiftParameter());
        //shiftArray(matrix.getMatrixArray(), matrix.setShiftParameter());
        showArray(matrix.getMatrixArray());
    }


    private static int[][] createMatrix(int countLines, int countColumn) {
        int[][] matrix = new int[countLines][countColumn];

        for (int i = 0; i < countLines; i++) {
            for (int j = 0; j < countColumn; j++) {
                matrix[i][j] =(int) (Math.random() * 10);
            }
        }
        return matrix;
    }

    private static void showArray(int[][] array){
        for (int[] str: array) {
            for (int i = 0; i < array[0].length; i++) {
                System.out.print(str[i] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void shiftMatrix(int[][] array, int parameter){
        int countLines = array.length;
        int countColumns = array[0].length;
        int buff = 0;
        int temp = 0;

        while (parameter > 0){
            for (int i = 0; i < countLines ; i++) {
                for (int j = 0; j < countColumns; j++) {
                    if (i == 0 && j == 0){
                        buff = array[i][j];
                        array[i][j] = array[countLines - 1][countColumns - 1];
                    }else {
                        temp = array[i][j];
                        array[i][j] = buff;
                        buff = temp;
                    }
                }
            }
            parameter--;
        }
    }

    private static void shiftArray(int[][] array, int parameter){
        int countLines = array.length;                                      //количество строк
        int countColumns = array[0].length;                                 //количество столбцов
        int[][] shiftedArray = new int[countLines][countColumns];

        for (int i = 0; i < countLines; i++) {
            for (int j = 0; j < countColumns; j++) {
                if (j + parameter < countColumns){
                    shiftedArray[i][j + parameter] = array[i][j];
                }
                else if (i < (countLines - 1)) {
                    shiftedArray[i + 1][parameter - (countColumns - j)] = array[i][j];
                }
                else {
                    shiftedArray[0][parameter - (countColumns - j)] = array[i][j];
                }
            }
        }

        setMatrixArray(shiftedArray);
    }
}

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

public class task2_2 {
    public static void main(String[] args) {
        int[][] array = {{1,1,1,1}, {2,2,2,2}, {3,3,3,3}, {4,4,4,4}, {5,5,5,5}};

        System.out.println("Матрица до сдвига:");
        showArray(array);
        int[][] shiftedArray = shiftArray(array,4);
        System.out.println("Матрица после сдвига:");
        showArray(shiftedArray);
        shiftArrayTest(array, 11);
        showArray(array);

    }

    private static int[][] shiftArray(int[][] array, int parameter){
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

        return shiftedArray;
    }

    private static void shiftArrayTest(int[][] array, int parameter){
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

    private static void showArray(int[][] array){
        for (int[] str: array) {
            for (int i = 0; i < array[0].length; i++) {
                System.out.print(str[i] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}

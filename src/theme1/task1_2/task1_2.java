/*
Габидуллин А.Э.
Вариант №15.
Задача 1.35.
Вводить с клавиатуры последовательность целых чисел до 0 (сам 0 не входит в последовательность).
Найти количество элементов, имеющих наименьшее значение. Не использовать массивы для хранения последовательности.
 */
package theme1.task1_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class task1_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        int minValue = 0;

        while (true) {
            int num = Integer.parseInt(reader.readLine());
            if (num != 0){
                if (minValue == 0){
                    minValue = num;
                    count++;
                }
                else if (num == minValue) {
                    count ++;
                }
                else if (num < minValue){
                    minValue = num;
                    count = 1;
                }
            }
            else break;
        }

        reader.close();
        System.out.println("Количество элементов, имеющих наименьшее значение равно: " + count + " Значение равно " + minValue);
    }
}

/*
Даны два массива - список фамилий абитуриентов и список соответствующих им оце- нок по ЭГЭ.
Отсортировать список абитуриентов по убыванию оценок, в случае совпа- дения оценки сравнивать абитуриентов по фамилии.
 */

package theme2;

public class task2_1 {
    public static void main(String[] args) {
        String[] fam = {"Иванов", "Петров", "Сидоров", "Семенов", "Аникин", "Валуев", "Богатырев", "Арбузов", "Васечкин", "Абалуев"};
        int[] eval = {5, 3, 2, 5, 5, 4, 5, 2, 4, 5};

        System.out.println("До сортировки:");
        showArrays(fam, eval);
        sortArrays(fam, eval);
        System.out.println("После сортировки:");
        showArrays(fam, eval);
    }

    private static void showArrays(String[] fam, int[] eval){
        for (int i = 0; i < eval.length; i++) {
            System.out.println(fam[i] + ": \t" + eval[i]);
        }
        System.out.println();
    }

    private static void sortArrays(String[] fam, int[] eval ){
        // сортируем оба массива шаттлом

        for (int i = 1; i < eval.length; i++) {
            if (eval[i] >= eval[i - 1]) {
                swap(eval, i, i - 1);
                swap(fam, i, i - 1);
                if ((eval[i] == eval[i - 1]) && (fam[i].compareTo(fam[i - 1]) < 0 )) {
                    swap(fam, i, i - 1);
                }
                for (int z = i - 1; (z - 1) >= 0; z--) {
                    if (eval[z] >= eval[z - 1]) {
                        swap(eval, z, z - 1);
                        swap(fam, z, z - 1);
                        if((eval[z] == eval[z - 1]) && (fam[z].compareTo(fam[z - 1]) < 0 )) {
                            swap(fam, z, z - 1);
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private static void swap(int[] array, int a, int b){
        int buff = array[a];
        array[a] = array[b];
        array[b] = buff;
    }

    private static void swap(String[] array, int a, int b){
        String buff = array[a];
        array[a] = array[b];
        array[b] = buff;
    }
}




// сортировка посимвольно старый вариант
/* for (int i = 1; i < eval.length; i++) {
            if (eval[i] >= eval[i - 1]) {
                swap(eval, i, i - 1);
                swap(fam, i, i - 1);
                if ((eval[i] == eval[i - 1]) && (fam[i].charAt(0) < fam[i - 1].charAt(0))) {
                    swap(fam, i, i - 1);
                }
                for (int z = i - 1; (z - 1) >= 0; z--) {
                    if (eval[z] >= eval[z - 1]) {
                        swap(eval, z, z - 1);
                        swap(fam, z, z - 1);
                        if((eval[z] == eval[z - 1]) && (fam[z].charAt(0) < fam[z - 1].charAt(0))) {
                            swap(fam, z, z - 1);
                        }
                    } else {
                        break;
                    }
                }
            }
        } */
       /* for (int i = 1; i < fam.length; i++) {
                if ((eval[i] == eval[i - 1]) && (fam[i].charAt(0) < fam[i - 1].charAt(0))){
                    swap(fam, i, i - 1);
                for (int z = i - 1; (z - 1) >= 0; z--) {
                        if((eval[z] == eval[z - 1]) && (fam[z].charAt(0) < fam[z - 1].charAt(0))){
                            swap(fam, z, z - 1);
                    } else {
                        break;
                    }
                }
            }
        }
        */
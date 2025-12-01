import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Exercise7 {
    private static final int ERROR = -1;
    private static final int NOT_ERROR = 0;

    public static void main(String[] args) {
        int file_error = NOT_ERROR, len = ERROR;
        Scanner file_name = new Scanner(System.in);
        Scanner file = null;
        double[] array = new double[0];
        try {
            file = new Scanner(new File(file_name.nextLine()));
        } catch (FileNotFoundException e) {
            file_error = ERROR;
        }
        if (file_error != ERROR) {
            if (file.hasNextLine()) {
                len = check(file.nextLine());
                if (len > 0) {
                    array = new double[len];
                } else {
                    len = ERROR;
                }
            }
            if (len != ERROR) {
                array = read_file(file, len);
                if (len == array.length) {
                    write_file(array);
                }
            }
            file.close();
        }
        file_name.close();
        output(array, len, file_error);
    }

    public static void write_file (double[] array) {
        /* Запись в файл */
        try (FileWriter writer = new FileWriter("result.txt", false)) {
            String text = min(array) + " " + max(array);
            writer.append(text);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static double min (double[] array) {
        /* Нахождение минимума в массиве */
        double min = array[0];
        for (double i : array) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    public static double max (double[] array) {
        /* Нахождение максимума в массиве */
        double max = array[0];
        for (double i : array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    public static double[] read_file (Scanner file, int len) {
        /* Чтение массива из файла */
        int count = 0;
        double[] array = new double[len];
        while (file.hasNextLine()) {
            String scString = file.nextLine().trim();
            String[] scStrings = scString.split(" ");
            for (String i : scStrings) {
                try {
                    array[count] = Double.parseDouble(i);
                }
                catch (NumberFormatException e) {
                    continue;
                }
                count++;
                if (count == len) {
                    break;
                }
            }
            if (count == len) {
                break;
            }
        }
        if (array.length != count) {
            array = new double[0];
        }
        return array;
    }

    public static int check(String str) {
        /* Проверка вводимых значений */
        int result;
        try {
            result = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            result = ERROR;
        }
        return result;
    }

    public static void output (double[] array, int len, int file_error) {
        /* Вывод */
        if (file_error == ERROR) {
            System.out.println("Input error. File doesn't exist");
        }
        else if (len == ERROR) {
            System.out.println("Input error. Size <= 0");
        }
        else if (array.length < len) {
            System.out.println("Input error. Insufficient number of elements");
        }
        else {
            System.out.println(len);
            for (double i : array) {
                System.out.print(i + " ");
            }
            System.out.println("\nSaving min and max values in file");
        }
    }
}

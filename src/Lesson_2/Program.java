package Lesson_2;
import java.util.*;

public class Program {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '○';
    private static final char DOT_EMPTY = ' ';
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field; // Двумерный массив поля с 1 элементом
    private static int WINHUMAN = 0; // Счетчик побед Человека
    private static int WINAI = 0; // Счетчик побед АИ
    private static boolean STATUS = false; // Проверка проинициализирован ли размер поля и комбинация
    private  static  int DRAW = 0; // Ничья
    private static String TESTVERSION = "DISABLE";
    private static int fieldSize = 3; // Размеры поля N x N
    private static int WIN_COUNT; // Выигрышная комбинация

    /**
     * Инициализация объектов игры
     */
    static void initialize(int size){
        fieldSize = size;
        field = new char[fieldSize][fieldSize];

        for (int x = 0; x < fieldSize; x++){
            for (int y = 0; y < fieldSize; y++){
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Настройки игры(размер поля, кол-во жетонов для победы
     */
    static void settings(){
        while(true){
            System.out.print("Введите размеры поля(мин 3): ");
            try{
                fieldSize = scanner.nextInt();
                if (fieldSize == 3 || fieldSize > 3){
                    break;
                }
            } catch (Exception ex){System.out.println(ex);};
        }
        while(true){
            System.out.print("Введите минимальную выигрышную комбинацию\n(минимум 3, максимум "
                    + fieldSize + "): ");
            try{
                WIN_COUNT = scanner.nextInt();
                if (WIN_COUNT >= 3 & WIN_COUNT <= fieldSize){
                    break;
                }
            }catch (Exception ex){System.out.println(ex);}
        }
        STATUS = true;
    }
    /**
     * Печать текущего состояния игрового поля
     */
    static void printField(){
        System.out.println("------------------");
        System.out.print("+");
        for (int i = 0; i < fieldSize; i++){
            System.out.print("-" + (i + 1));
        }
        System.out.println("-");

        for (int x = 0; x < fieldSize; x++){
            System.out.print(x + 1 + "|");
            for (int y = 0; y < fieldSize; y++){
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }
        System.out.println("------------------");
    }

    /**
     * Ход игрока (человека)
     */
    static void humanTurn(){
        int x;
        int y;
        do {
            System.out.print("Введите координаты хода X и Y\n(от 1 до " + fieldSize + ") через пробел: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Ход игрока (компьютера)
     */
    static void aiTurn(){
        int x = 0;
        int y = 0;
        do{
            if(TESTVERSION.equals("ENABLED")){
                int[] temp = checkAiTurn(WIN_COUNT);
                if(temp[0] != -1){
                    x = temp[0];
                    y = temp[1];
                } else {
                    x = random.nextInt(fieldSize);
                    y = random.nextInt(fieldSize);
                }
            }
            else {
                x = random.nextInt(fieldSize);
                y = random.nextInt(fieldSize);
            }
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    /**
     * Поиск координат, для блокировки победы Человека
     *
     * @param WIN_COUNT - комбинация для выигрыша
     * @return - возврат массива с координатами
     */
    static int[] checkAiTurn(int WIN_COUNT) {
        int[] arr = {-1};
        for (int i = 0; i < fieldSize; i++) {
            int count = 0;
            int emptyCount = 0;
            int emptyIndex = -1;
            int countsec = 0;
            int emptyCountsec = 0;
            int emptyIndexsec = -1;
            for (int j = 0; j < fieldSize; j++) {
                if (field[i][j] == DOT_HUMAN) {
                    count++;
                } else if (field[i][j] == ' ') {
                    emptyCount++;
                    emptyIndex = j;
                    break;
                }

                if (field[j][i] == DOT_HUMAN) {
                    countsec++;
                } else if (field[j][i] == ' ') {
                    emptyCountsec++;
                    emptyIndexsec = j;
                    break;
                }
            }
            if (count >= WIN_COUNT - 1 && emptyCount == 1) {
                System.out.println("Нашол и пытаюсь");
                return new int[] {i, emptyIndex}; // Вернуть координаты для блокировки комбинации
            }
            if (countsec >= WIN_COUNT - 1 && emptyCountsec == 1) {
                System.out.println("Нашол и пытаюсь");
                return new int[] {emptyIndexsec, i}; // Вернуть координаты для блокировки комбинации
            }
        }

        return arr;
    }

    /**
     * Проверка, является ли ячейка игрового поля пустой
     * @param x координата
     * @param y координата
     * @return результат проверки
     */
    static boolean isCellEmpty(int x, int y){
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка валидности координат хода
     * @param x координата
     * @param y координата
     * @return результат проверки
     */
    static boolean isCellValid(int x, int y){
        return x >= 0 && x < fieldSize && y >= 0 && y < fieldSize;
    }

    /**
     * Поверка на ничью (все ячейки игрового поля заполнены фишками человека или компьютера)
     * @return - возврат флага
     */
    static boolean checkDraw(){
        for (int x = 0; x < fieldSize; x++){
            for (int y = 0; y < fieldSize; y++){
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    /**
     * Проверка по всем осям поля
     * @param dot - Фишка AI или Комьпютера
     * @return - возврат флага
     */
    static boolean checkForWin(char dot, int WIN_COUNT) {
        // Проверка по горизонтали
        for (int i = 0; i < fieldSize; i++) {
            int first_count = 0;
            int second_count = 0;
            for (int j = 0; j < fieldSize; j++) {
                if (field[i][j] == dot) {
                    first_count++;
                    if (first_count == WIN_COUNT) {
                        return true;
                    }
                } else {
                    first_count = 0;
                }
                if (field[j][i] == dot) {
                    second_count++;
                    if (second_count == WIN_COUNT) {
                        return true;
                    }
                } else {
                    second_count = 0;
                }
            }
        }

        // Проверка по диагоналям
        int count = 0;
        // Главная диагональ
        for (int i = 0; i < fieldSize; i++) {
            if (field[i][i] == dot) {
                count++;
            } else {
                count = 0; // Обнуляем счетчик при нахождении другого символа
            }
            if (count == WIN_COUNT) {
                return true;
            }
        }

        count = 0;
        // Побочная диагональ
        for (int i = 0; i < fieldSize; i++) {
            if (field[i][fieldSize - 1 - i] == dot) {
                count++;
            } else {
                count = 0; // Обнуляем счетчик при нахождении другого символа
            }
            if (count == WIN_COUNT) {
                return true;
            }
        }

        return false;
    }
    /**
     * Проверка состояния игры
     * @param dot фишка игрока
     * @param s победный слоган
     * @return состояние игры
     */
    static boolean checkState(char dot, String s){
        if (checkForWin(dot, WIN_COUNT)){
            if (dot == DOT_HUMAN){WINHUMAN++;}
            else{WINAI++;}
            System.out.println(s);
            return true;
        }
        else if (checkDraw()){
            System.out.println("Ничья!");
            DRAW++;
            return true;
        }
        // Игра продолжается
        return false;
    }

    /**
     * Вывод статистики на экран
     */
    private static void print_state(){
        System.out.println("------------------\nState:\n\tHuman: "+WINHUMAN
                +".\n\tAI: " + WINAI+".\n\tDRAW: "+DRAW + ".\n------------------");
    }

    /**
     * Начало игры - СТАРТ
     */
    private static void start(){
        if (STATUS){
            initialize(fieldSize);
            printField();
            while (true) {
                humanTurn();
                System.out.println("\nХод HUMAN:");
                printField();
                if (checkState(DOT_HUMAN, "Победил Человек!"))
                    break;
                aiTurn();
                if (TESTVERSION.equals("ENABLED")){
                    System.out.println("Ход AI(TEST VERSION):");
                }else {System.out.println("Ход AI:");}
                printField();
                if (checkState(DOT_AI, "Победил AI"))
                    break;
            }
        }else{
            settings();
            initialize(fieldSize);
            start();
        }
    }

    /**
     * Вывод меню на экран - МЕНЮ
     */
    public static void menu(){
        boolean flag = true;
        while (flag){
            System.out.print("------------------\n   ✖ & ○   "
                    + "\n1. START\n2. Settings\n3. State\n4. Use TEST(Glitch) version With AI. STATUS: " +
                    TESTVERSION + "\n5. Exit \n------------------\n\tEnter: ");
            int key = scanner.nextInt();
            switch (key){
                case 1:
                    start();
                    break;
                case 2:
                    settings();
                    break;
                case 3:
                    print_state();
                    break;
                case 4:
                    if (TESTVERSION.equals("ENABLED")){
                        TESTVERSION = "DISABLED";
                    }else{TESTVERSION = "ENABLED";}
                    break;
                case 5:
                    flag = false;
            }
        }
    }

    public static void main(String[] args) {
        menu();
    }
}

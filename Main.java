import functions.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа #3 ===");
        
        try {
            // Тестирование ArrayTabulatedFunction
            System.out.println("\n--- Тестирование ArrayTabulatedFunction ---");
            testArrayFunction();
            
            // Тестирование LinkedListTabulatedFunction
            System.out.println("\n--- Тестирование LinkedListTabulatedFunction ---");
            testLinkedListFunction();
            
            // Тестирование исключений
            System.out.println("\n--- Тестирование исключений ---");
            testExceptions();
            
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Метод для вывода всех точек функции
    private static void printAllPoints(TabulatedFunction func, String message) {
        System.out.println(message);
        System.out.println("Текущий набор точек (" + func.getPointsCount() + " точек):");
        for (int i = 0; i < func.getPointsCount(); i++) {
            try {
                FunctionPoint point = func.getPoint(i);
                System.out.printf("  Точка %d: (%.2f, %.2f)%n", i, point.getX(), point.getY());
            } catch (Exception e) {
                System.out.println("  Ошибка при получении точки " + i + ": " + e.getMessage());
            }
        }
        System.out.println();
    }
    
    private static void testArrayFunction() {
        try {
            // Создание функции через массив значений Y
            double[] values = {1.0, 4.0, 9.0, 16.0, 25.0};
            TabulatedFunction arrayFunc = new ArrayTabulatedFunction(0, 4, values);
            
            printAllPoints(arrayFunc, "Создана ArrayTabulatedFunction:");
            
            // Тестирование вычисления значения 
            System.out.println("Тестирование вычисления значений (с оптимизацией):");
            System.out.printf("  f(0.0) = %.2f (точное совпадение - использует существующий Y)%n", arrayFunc.getFunctionValue(0.0));
            System.out.printf("  f(1.0) = %.2f (точное совпадение - использует существующий Y)%n", arrayFunc.getFunctionValue(1.0));
            System.out.printf("  f(1.5) = %.2f (интерполяция)%n", arrayFunc.getFunctionValue(1.5));
            System.out.printf("  f(2.0) = %.2f (точное совпадение - использует существующий Y)%n", arrayFunc.getFunctionValue(2.0));
            System.out.printf("  f(3.7) = %.2f (интерполяция)%n", arrayFunc.getFunctionValue(3.7));
            System.out.println();
            
            // Тестирование модификации точек
            System.out.println("Модификация точек:");
            arrayFunc.setPoint(2, new FunctionPoint(2.0, 10.0));
            printAllPoints(arrayFunc, "После изменения точки 2 на (2.0, 10.0):");
            
            // Тестирование добавления точки
            arrayFunc.addPoint(new FunctionPoint(2.5, 6.25));
            printAllPoints(arrayFunc, "После добавления точки (2.5, 6.25):");
            
            // Тестирование удаления точки
            arrayFunc.deletePoint(1);
            printAllPoints(arrayFunc, "После удаления точки 1:");
            
        } catch (Exception e) {
            System.out.println("Ошибка в ArrayTabulatedFunction: " + e.getMessage());
        }
    }
    
    private static void testLinkedListFunction() {
        try {
            // Создание функции через указание границ и количества точек
            TabulatedFunction linkedFunc = new LinkedListTabulatedFunction(0, 4, 5);
            
            printAllPoints(linkedFunc, "Создана LinkedListTabulatedFunction:");
            
            // Установка значений для тестирования
            for (int i = 0; i < linkedFunc.getPointsCount(); i++) {
                linkedFunc.setPointY(i, i * i); // y = x²
            }
            printAllPoints(linkedFunc, "После установки значений Y = x²:");
            
            // Тестирование вычисления значения с демонстрацией оптимизации
            System.out.println("Тестирование вычисления значений (с оптимизацией):");
            System.out.printf("  f(0.0) = %.2f (точное совпадение - использует существующий Y)%n", linkedFunc.getFunctionValue(0.0));
            System.out.printf("  f(1.0) = %.2f (точное совпадение - использует существующий Y)%n", linkedFunc.getFunctionValue(1.0));
            System.out.printf("  f(1.2) = %.2f (интерполяция)%n", linkedFunc.getFunctionValue(1.2));
            System.out.printf("  f(2.0) = %.2f (точное совпадение - использует существующий Y)%n", linkedFunc.getFunctionValue(2.0));
            System.out.printf("  f(2.5) = %.2f (интерполяция)%n", linkedFunc.getFunctionValue(2.5));
            System.out.printf("  f(3.8) = %.2f (интерполяция)%n", linkedFunc.getFunctionValue(3.8));
            System.out.println();
            
            // Тестирование модификации точек
            System.out.println("Модификация точек:");
            linkedFunc.setPoint(3, new FunctionPoint(3.0, 15.0));
            printAllPoints(linkedFunc, "После изменения точки 3 на (3.0, 15.0):");
            
            // Тестирование добавления точки
            linkedFunc.addPoint(new FunctionPoint(1.5, 3.0));
            printAllPoints(linkedFunc, "После добавления точки (1.5, 3.0):");
            
        } catch (Exception e) {
            System.out.println("Ошибка в LinkedListTabulatedFunction: " + e.getMessage());
        }
    }
    
    private static void testExceptions() {
        System.out.println("Тестирование корректных исключений:");
        
        try {
            // Некорректные границы (левая граница больше правой)
            new ArrayTabulatedFunction(5, 0, 3);
            System.out.println("ОШИБКА: Не выброшено исключение для некорректных границ");
        } catch (IllegalArgumentException e) {
            System.out.println("Корректно: " + e.getMessage());
        }
        
        try {
            // Слишком мало точек (меньше 2)
            new LinkedListTabulatedFunction(0, 5, 1);
            System.out.println("ОШИБКА: Не выброшено исключение для малого количества точек");
        } catch (IllegalArgumentException e) {
            System.out.println("Корректно: " + e.getMessage());
        }
        
        try {
            // Выход за границы индекса (получение точки)
            TabulatedFunction func = new ArrayTabulatedFunction(0, 2, 3);
            printAllPoints(func, "Создана функция для теста выхода за границы:");
            func.getPoint(10);
            System.out.println("ОШИБКА: Не выброшено исключение для выхода за границы");
        } catch (FunctionPointIndexOutOfBoundsException e) {
            System.out.println("Корректно: " + e.getMessage());
        }
        
        try {
            // Отрицательный индекс
            TabulatedFunction func = new LinkedListTabulatedFunction(0, 3, 4);
            printAllPoints(func, "Создана функция для теста отрицательного индекса:");
            func.getPoint(-1);
            System.out.println("ОШИБКА: Не выброшено исключение для отрицательного индекса");
        } catch (FunctionPointIndexOutOfBoundsException e) {
            System.out.println(" Корректно: " + e.getMessage());
        }
        
        try {
            // Некорректная точка - setPoint
            TabulatedFunction func = new LinkedListTabulatedFunction(0, 3, 4);
            printAllPoints(func, "Создана функция для теста нарушения порядка:");
            // Попытка установить точку с X меньше предыдущей
            func.setPoint(2, new FunctionPoint(0.5, 0));
            System.out.println("ОШИБКА: Не выброшено исключение для некорректной точки в setPoint");
        } catch (InappropriateFunctionPointException e) {
            System.out.println("Корректно: " + e.getMessage());
        }
        
        try {
            // Дублирование координаты X - addPoint
            TabulatedFunction func = new ArrayTabulatedFunction(0, 2, 3);
            printAllPoints(func, "Создана функция для теста дублирования X:");
            func.addPoint(new FunctionPoint(1.0, 5.0)); // X=1.0 уже существует
            System.out.println("ОШИБКА: Не выброшено исключение для дублирования X в addPoint");
        } catch (InappropriateFunctionPointException e) {
            System.out.println("Корректно: " + e.getMessage());
        }
        
        try {
            // Удаление при малом количестве точек
            TabulatedFunction func = new ArrayTabulatedFunction(0, 2, 3); // 3 точки
            printAllPoints(func, "Создана функция для теста удаления:");
            func.deletePoint(0); // останется 2 точки - нормально
            printAllPoints(func, "После первого удаления (2 точки осталось):");
            func.deletePoint(0); // останется 1 точка - должно быть исключение
            System.out.println("ОШИБКА: Не выброшено исключение при удалении последней точки");
        } catch (IllegalStateException e) {
            System.out.println("Корректно: " + e.getMessage());
        }
        
        try {
            // Некорректный индекс при удалении
            TabulatedFunction func = new ArrayTabulatedFunction(0, 2, 3);
            printAllPoints(func, "Создана функция для теста некорректного индекса:");
            func.deletePoint(5); 
            System.out.println("ОШИБКА: Не выброшено исключение для некорректного индекса при удалении");
        } catch (FunctionPointIndexOutOfBoundsException e) {
            System.out.println("Корректно: " + e.getMessage());
        }
        
        System.out.println("\n Все тесты исключений пройдены успешно!");
    }
}
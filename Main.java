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
    
    private static void testArrayFunction() {
        try {
            // Создание функции через массив значений Y
            double[] values = {1.0, 4.0, 9.0, 16.0, 25.0};
            TabulatedFunction arrayFunc = new ArrayTabulatedFunction(0, 4, values);
            
            System.out.println("ArrayTabulatedFunction: точки от " + arrayFunc.getLeftDomainBorder() + 
                             " до " + arrayFunc.getRightDomainBorder() + 
                             ", количество точек: " + arrayFunc.getPointsCount());
            
            // Вывод всех точек
            System.out.println("Все точки функции:");
            for (int i = 0; i < arrayFunc.getPointsCount(); i++) {
                FunctionPoint point = arrayFunc.getPoint(i);
                System.out.printf("  Точка %d: (%.1f, %.1f)%n", i, point.getX(), point.getY());
            }
            
            // Тестирование вычисления значения в различных точках
            System.out.println("\nВычисление значений функции:");
            System.out.printf("  f(0.0) = %.2f%n", arrayFunc.getFunctionValue(0.0));
            System.out.printf("  f(1.5) = %.2f%n", arrayFunc.getFunctionValue(1.5));
            System.out.printf("  f(2.0) = %.2f%n", arrayFunc.getFunctionValue(2.0));
            System.out.printf("  f(3.7) = %.2f%n", arrayFunc.getFunctionValue(3.7));
            
            // Тестирование модификации точек
            System.out.println("\nМодификация точек:");
            arrayFunc.setPoint(2, new FunctionPoint(2.0, 10.0));
            System.out.printf("  После изменения: точка 2 = (%.1f, %.1f)%n", 
                            arrayFunc.getPoint(2).getX(), arrayFunc.getPoint(2).getY());
            
            // Тестирование добавления точки
            arrayFunc.addPoint(new FunctionPoint(2.5, 6.25));
            System.out.println("  После добавления точки (2.5, 6.25):");
            System.out.println("  Количество точек: " + arrayFunc.getPointsCount());
            
            // Тестирование удаления точки
            arrayFunc.deletePoint(1);
            System.out.println("  После удаления точки 1:");
            System.out.println("  Количество точек: " + arrayFunc.getPointsCount());
            
        } catch (Exception e) {
            System.out.println("Ошибка в ArrayTabulatedFunction: " + e.getMessage());
        }
    }
    
    private static void testLinkedListFunction() {
        try {
            // Создание функции через указание границ и количества точек
            TabulatedFunction linkedFunc = new LinkedListTabulatedFunction(0, 4, 5);
            
            System.out.println("LinkedListTabulatedFunction: точки от " + linkedFunc.getLeftDomainBorder() + 
                             " до " + linkedFunc.getRightDomainBorder() + 
                             ", количество точек: " + linkedFunc.getPointsCount());
            
            // Вывод всех точек
            System.out.println("Все точки функции:");
            for (int i = 0; i < linkedFunc.getPointsCount(); i++) {
                FunctionPoint point = linkedFunc.getPoint(i);
                System.out.printf("  Точка %d: (%.1f, %.1f)%n", i, point.getX(), point.getY());
            }
            
            // Тестирование вычисления значения в различных точках
            System.out.println("\nВычисление значений функции:");
            System.out.printf("  f(0.0) = %.2f%n", linkedFunc.getFunctionValue(0.0));
            System.out.printf("  f(1.2) = %.2f%n", linkedFunc.getFunctionValue(1.2));
            System.out.printf("  f(2.5) = %.2f%n", linkedFunc.getFunctionValue(2.5));
            System.out.printf("  f(3.8) = %.2f%n", linkedFunc.getFunctionValue(3.8));
            
            // Тестирование модификации точек
            System.out.println("\nМодификация точек:");
            linkedFunc.setPoint(3, new FunctionPoint(3.0, 15.0));
            System.out.printf("  После изменения: точка 3 = (%.1f, %.1f)%n", 
                            linkedFunc.getPoint(3).getX(), linkedFunc.getPoint(3).getY());
            
            // Тестирование добавления точки
            linkedFunc.addPoint(new FunctionPoint(1.5, 3.0));
            System.out.println("  После добавления точки (1.5, 3.0):");
            System.out.println("  Количество точек: " + linkedFunc.getPointsCount());
            
            // Вывод всех точек после модификаций
            System.out.println("Все точки после модификаций:");
            for (int i = 0; i < linkedFunc.getPointsCount(); i++) {
                FunctionPoint point = linkedFunc.getPoint(i);
                System.out.printf("  Точка %d: (%.1f, %.1f)%n", i, point.getX(), point.getY());
            }
            
        } catch (Exception e) {
            System.out.println("Ошибка в LinkedListTabulatedFunction: " + e.getMessage());
        }
    }
    
    private static void testExceptions() {
        System.out.println("\nТестирование корректных исключений:");
        
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
            func.getPoint(10);
            System.out.println("ОШИБКА: Не выброшено исключение для выхода за границы");
        } catch (FunctionPointIndexOutOfBoundsException e) {
            System.out.println("Корректно: " + e.getMessage());
        }
        
        try {
            // Отрицательный индекс
            TabulatedFunction func = new LinkedListTabulatedFunction(0, 3, 4);
            func.getPoint(-1);
            System.out.println("ОШИБКА: Не выброшено исключение для отрицательного индекса");
        } catch (FunctionPointIndexOutOfBoundsException e) {
            System.out.println("Корректно: " + e.getMessage());
        }
        
        try {
            // Некорректная точка - setPoint
            TabulatedFunction func = new LinkedListTabulatedFunction(0, 3, 4);
            // Попытка установить точку с X меньше предыдущей
            func.setPoint(2, new FunctionPoint(0.5, 0));
            System.out.println("ОШИБКА: Не выброшено исключение для некорректной точки в setPoint");
        } catch (InappropriateFunctionPointException e) {
            System.out.println("Корректно: " + e.getMessage());
        }
        
        try {
            // Дублирование координаты X - addPoint
            TabulatedFunction func = new ArrayTabulatedFunction(0, 2, 3);
            func.addPoint(new FunctionPoint(1.0, 5.0)); // X=1.0 уже существует
            System.out.println("ОШИБКА: Не выброшено исключение для дублирования X в addPoint");
        } catch (InappropriateFunctionPointException e) {
            System.out.println("Корректно: " + e.getMessage());
        }
        
        try {
            // Удаление при малом количестве точек
            TabulatedFunction func = new ArrayTabulatedFunction(0, 2, 3); // 3 точки
            func.deletePoint(0); // останется 2 точки - нормально
            func.deletePoint(0); // останется 1 точка - должно быть исключение
            System.out.println("ОШИБКА: Не выброшено исключение при удалении последней точки");
        } catch (IllegalStateException e) {
            System.out.println("Корректно: " + e.getMessage());
        }
        
        try {
            // Некорректный индекс при удалении
            TabulatedFunction func = new ArrayTabulatedFunction(0, 2, 3);
            func.deletePoint(5); 
            System.out.println("ОШИБКА: Не выброшено исключение для некорректного индекса при удалении");
        } catch (FunctionPointIndexOutOfBoundsException e) {
            System.out.println("Корректно: " + e.getMessage());
        }
        
        System.out.println("\nВсе тесты исключений пройдены успешно!");
    }
}
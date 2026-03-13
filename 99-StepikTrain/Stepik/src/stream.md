1.
        List<Integer> zerosList = IntStream.generate(() -> 0)
            .limit(size - data.size())
            .boxed()
            .toList();

- IntStream.generate(() -> 0) — создает бесконечный поток целых чисел, каждый элемент которого равен нулю.
- .limit(5) — ограничивает размер потока пятью элементами.
- .boxed() — преобразует поток примитивных типов (IntStream) в поток объектов (Stream<Integer>).
- .collect(Collectors.toList()) — собирает поток элементов в объект типа List.
2.
        Lisr<Integer> date = data.stream().sorted().map(i -> i * n).collect(Collectors.toList());

- .map(i -> i * n) - умножаем все числа в списке на n и собираем поток элементов в List
3.
        String str = data.stream().map(Object::toString).collect(Collectors.joining(", "));

- .map(Object::toString) - все объекты списка преобразовываем в строку.
- .collect(Collectors.joining(", ") - добавляем разделитель между элементами.
4.
        List<List<Integer>> data = [[1,2,3],[4,5,6],[7,8,9]];
        List<Integer> newData = new ArrayList<>();
        data.forEach(newData::addAll);

- таким образом можно все элементы двумерного массива переместить в одномерный.
5.
        data.stream().filter(num -> num != 0).toList();

- .filter(num -> num != 0) - в списке будет сортировка, элемент не должен быть равен 0.
6.
        Collections.sort(data);

- сортировка коллекции.
7.
        Collections.reverse(data);

- разворот коллекции.
8.
        strings.stream().distinct().toList();

- удаление дублей.
9.
        str.chars().forEach(c -> System.out.print((char)c + " "));

- Метод chars() превращает строку в поток целочисленных значений.
10.
        int count = Character.getNumericValue(ch);

- преобразование символа в число.
11.
        String.valueOf(newCh)

- преобразование символа в строку
12.
        int sum = grid.stream()
                .flatMap(List::stream)
                .mapToInt(Integer::intValue)
                .sum();

- .flatMap(List::stream) Преобразуем каждый внутренний список в общий поток
- .mapToInt(Integer::intValue) Приводим элементы потока к типу int
13.
        int diagonalSum = IntStream.range(0, grid.size())
                                  .map(i -> grid.get(i).get(i))
                                  .sum() +
                                IntStream.range(0, grid.size())
                                  .map(i -> grid.get(i).get(grid.size()-1-i))
                                  .sum();

- подсчет суммы диагоналей двумерного массива
- .map(i -> grid.get(i).get(i)) Главная диагональ
- .map(i -> grid.get(i).get(grid.size()-1-i)) Вторая диагональ
14.
        result = Arrays.stream(data)
        .takeWhile(d -> d <= end)
        .filter(d -> d >= start)
        .sum();

- Arrays.stream(data) создаёт поток элементов массива.
- .takeWhile(d -> d <= end) Продолжаем пока элемент меньше либо равен 'end' 
- .filter(d -> d >= start) Оставляем только элементы больше или равные 'start'
15.
        List<Integer> copyData = new ArrayList<>(data);

- копирование всего списка в другой список
16.
        int max = data.stream()
        .max(Integer::compareTo)
        .orElse(0);

- нахождение максимального числа в списке
17.
        files.stream().filter(f -> f.contains(search)).toList();

- нахождение подстроки в строке
18. 
        files.stream().filter(f -> f.startsWith(search)).toList();

- строка начинается со строки

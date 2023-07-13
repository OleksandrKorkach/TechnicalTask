import java.util.*;
import java.io.*;


/**
    Під час розробки програми використовував такі джерела як Stack Overflow,
    для більш раціональної роботи зі строками та ChatGpt для розширення
    розуміння тонкощів розбиття строк та деяких методів певних класів.
    Все інше, включно з алгоритмом розробляв самостійно!

    Для написання використовував IntelliJ IDEA Ultimate, але весь код
    запускається на 100% коректно на безкоштовній Community Edition,
    або іншій платформі чи онлайн компіляторі.

    Також цей код є у мене в гітхабі ->

        Опис алгоритму:

    1. Зчитати текст з консолі
    2. Розділити текст на слова і зберігти їх в масив.
    3. Для кожного СЛОВА в масиві слів:
        a. Створити пусті масиви для символів та їх дублікатів.
        b. Розбити СЛОВО на масив символів.
        с. Для кожного СИМВОЛУ в масиві символів:
            Якщо СИМВОЛ не присутній в основному масиві то додати його в кінець,
            якщо такий символ вже присутній то додаємо його в дублікати.
        d. Видалити з масиву символів всі символи які присутні в масиві дублікатів.
        e. Повертаємо перший елемент з основного масиву.
    До цього етапу ми отримали масив символів які зустрічались першими та не повторювалися
    в межах своїх слів, але сам набір цих символів ще не є унікальним (символи можуть повторюватись).
    4. Знов створюємо основний масив та масив для дублікатів.
    5. Додоємо до основного масиву символи без дублікатів, всі інші в дублікати.
    6. Видаляємо з основного масиву символи наявні в дублікатах.
    7. І нарешті повертаємо результат - перший елемент основного масиву.

 */
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder inputText = new StringBuilder();

        System.out.println("Write something and press enter to go to a new line or to save what you have written.");
        System.out.println("Press Ctrl + D on Windows or Ctrl + Z on UNIX-like to get result");
        readTextLine(inputText, reader);

        String[] words = inputText.toString().split("[\\p{Punct}\\s]+|\\n");

        List<Character> mainCharacters = new ArrayList<>();
        Set<Character> duplicates = new LinkedHashSet<>();

        for (String word : words) {
            Character character = getFirstUniqueCharacterInWord(word);
            if (!mainCharacters.contains(character)) {
                mainCharacters.add(character);
            } else {
                duplicates.add(character);
            }
        }

        duplicates.forEach(mainCharacters::remove);
        mainCharacters.removeAll(Collections.singletonList(null));
        printResult(mainCharacters);
    }

    static void readTextLine(StringBuilder text, BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            text.append(line).append("\n");
        }
    }

    public static Character getFirstUniqueCharacterInWord(String word) {
        List<Character> mainCharacters = new ArrayList<>();
        List<Character> duplicates = new ArrayList<>();

        setCharactersAndDuplicatesFromCharArray(word.toCharArray(), mainCharacters, duplicates);
        duplicates.forEach(mainCharacters::remove);

        if (!mainCharacters.isEmpty()) {
            return mainCharacters.get(0);
        } else {
            return null;
        }
    }

    static void setCharactersAndDuplicatesFromCharArray(char[] charArray, List<Character> characters, List<Character> duplicates) {
        for (Character ch : charArray) {
            if (!characters.contains(ch)) {
                characters.add(ch);
            } else {
                duplicates.add(ch);
            }
        }
    }

    static void printResult(List<Character> characters) {
        try {
            System.out.println("Result: " + characters.get(0));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("None of the characters meets the criteria!");
        }
    }
}
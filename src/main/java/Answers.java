import java.util.ArrayList;
import java.util.Random;

public class Answers {

    private static ArrayList<String> correctAnswers = new ArrayList<>();
    private static ArrayList<String> wrongAnswers = new ArrayList<>();

    public Answers() {
        correctAnswers.add("Верно!");
        correctAnswers.add("Молодец!");
        correctAnswers.add("Географ подъехал!");
        correctAnswers.add("Господин Друзь?");
        correctAnswers.add("В точку!");
        correctAnswers.add("Ты жжешь!");
        correctAnswers.add("Красава!");
        correctAnswers.add("Жи есть!");

        wrongAnswers.add("Неа");
        wrongAnswers.add("Давай попробуем еще раз");
        wrongAnswers.add("Не такой страны");
        wrongAnswers.add("Может на другой планете и есть, но на нашей пока нет");
        wrongAnswers.add("Прикалываешься? Давай посерьёзнее");
        wrongAnswers.add("Мдааа...");
        wrongAnswers.add("Я был лучшего о тебе мнения");
    }

    public String getRandomCorrectAnswer(){
       return correctAnswers.get(new Random().nextInt(correctAnswers.size()));
    }
    public String getRandomWrongAnswer(){
        return wrongAnswers.get(new Random().nextInt(wrongAnswers.size()));
    }


}

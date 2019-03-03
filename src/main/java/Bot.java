import javassist.tools.web.BadHttpRequest;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Bot extends TelegramLongPollingBot {
    Map<String,Integer> list = new HashMap<>();
    String temp="";
    static ArrayList<String> correctAnswers = new ArrayList<>();
    static ArrayList<String> wrongAnswers = new ArrayList<>();
    static final String[] shortName={"NR","AB","AU","AT","AZ","AX","AL","DZ","AI","AO","AD","AQ","AG","AR","AM","AW","AF","BS","BD","BB","BH","BY","BZ","BE","BJ","BM","BG","BO","BA","BW","BR","VG","BN","BF","BI","BT","VU","VA","GB","HU","VE","VI","AC","VN","GA","HT","GY","GM","GH","GP","GT","GN","GW","DE","GI","HN","HK","GD","GL","GR","GE","GU","DK","DJ","DM","DO","EG","ZM","ZW","IL","IN","ID","JO","IQ","IR","IE","IS","ES","IT","YE","CV","KZ","KY","KH","CM","CA","QA","KE","CY","KG","KI","CN","KP","CC","CO","KM","CD","CG","KR","CR","CI","CU","KW","CK","LA","LV","LS","LR","LB","LY","LT","LI","LU","MU","MR","MG","YT","MO","MK","MW","MY","ML","MV","MT","MA","MQ","MH","MX","FM","MZ","MD","MC","MN","MS","MM","NA","NP","NE","NG","NL","NI","NU","NZ","NC","NO","NF","AE","OM","PK","PW","PS","PA","PG","PY","PE","PN","PL","PT","PR","RE","CX","RU","RW","RO","SV","WS","AS","SM","ST","SA","SZ","SH","MP","SC","BL","MF","PM","SN","VC","KN","LC","RS","SG","SY","SK","SI","SB","SO","SD","SR","US","SL","TJ","TH","TW","TZ","TC","TG","TK","TO","TT","TV","TN","TM","TR","UG","UZ","UA","WF","UY","FO","FJ","PH","FI","FK","FR","PF","HM","HR","CF","TD","ME","CZ","CL","CH","SE","LK","EC","GQ","ER","EE","ET","ZA","GS","JM","JP"};
    static final String[] fullName={"Науру","Абхазия","Австралия","Австрия","Азербайджан","Аландские острова","Албания","Алжир","Ангилья","Ангола","Андорра","Антарктика","Антигуа и Барбуда","Аргентина","Армения","Аруба","Афганистан","Багамы","Бангладеш","Барбадос","Бахрейн","Беларусь","Белиз","Бельгия","Бенин","Бермудские острова","Болгария","Боливия","Босния и Герцеговина","Ботсвана","Бразилия","Британские Виргинские острова","Бруней","Буркина Фасо","Бурунди","Бутан","Вануату","Ватикан","Великобритания","Венгрия","Венесуэла","Виргинские острова","Вознесения остров","Вьетнам","Габон","Гаити","Гайяна","Гамбия","Гана","Гваделупа","Гватемала","Гвинея","Гвинея-Бисау","Германия","Гибралтар","Гондурас","Гонконг","Гренада","Гренландия","Греция","Грузия","Гуам","Дания","Джибути","Доминика","Доминиканская республика","Египет","Замбия","Зимбабве","Израиль","Индия","Индонезия","Иордания","Ирак","Иран","Ирландия","Исландия","Испания","Италия","Йемен","Кабо-Верде","Казахстан","Каймановы острова","Камбоджа","Камерун","Канада","Катар","Кения","Кипр","Киргизия","Кирибати","Китай","КНДР","Кокосовые острова","Колумбия","Коморы","Конго (ДР)","Конго (Республика)","Корея Южная","Коста-Рика","Кот Д Ивуар","Куба","Кувейт","Кука острова","Лаос","Латвия","Лесото","Либерия","Ливан","Ливия","Литва","Лихтенштейн","Люксембург","Маврикий","Мавритания","Мадагаскар","Майотта остров","Макао","Македония","Малави","Малайзия","Мали","Мальдивские острова","Мальта","Марокко","Мартиника","Маршалловы острова","Мексика","Микронезия","Мозамбик","Молдавия","Монако","Монголия","Монтсеррат","Мьянма","Намибия","Непал","Нигер","Нигерия","Нидерланды","Никарагуа","Ниуэ, остров","Новая Зеландия","Новая Каледония","Норвегия","Норфолк, остров","ОАЕ","Оман","Пакистан","Палау","Палестина","Панама","Папуа - Новая Гвинея","Парагвай","Перу","Питкэрн, остров","Польша","Португалия","Пуэрто-Рико","Реюньон","Рождества остров","Россия","Руанда","Румыния","Сальвадор","Самоа","Самоа Американское","Сан Марино","Сан Томе и Принсипе","Саудовская Аравия","Свазиленд","Святой Елены остров","Северные Марианские острова","Сейшелы","Сен-Бартелеми","Сен-Мартен","Сен-Пьер и Микелон","Сенегал","Сент-Винсент и Гренадины","Сент-Китс и Невис","Сент-Люсия","Сербия","Сингапур","Сирия","Словакия","Словения","Соломоновы Острова","Сомали","Судан","Суринам","США","Сьерра-Леоне","Таджикистан","Таиланд","Тайвань","Танзания","Теркс и Кайкос","Того","Токелау острова","Тонга острова","Тринидад и Тобаго","Тувалу","Тунис","Туркменистан","Турция","Уганда","Узбекистан","Украина","Уоллис и Футуна острова","Уругвай","Фарерские острова","Фиджи","Филиппины","Финляндия","Фолклендские острова","Франция","Французская Полинезия","Херд и Макдональд острова","Хорватия","Центральноафриканская республика","Чад","Черногория","Чешская Республика","Чили","Швейцария","Швеция","Шри Ланка","Эквадор","Экваториальная Гвинея","Эритрея","Эстония","Эфиопия","ЮАР","Южная Георгия и Ю.Сандвичевы о-ва","Ямайка","Япония"};




    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return "ИМЯ_ПОЛЬЗОВАТЕЛЯ_ВАШЕГО_БОТА";
    }

    @Override
    public String getBotToken() {
        return "739164691:AAGg44HjBh9srbpdUg_nYi5UfESRMIsbrNY";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (!list.containsKey(message.getChat().getUserName())) {
            list.put(message.getChat().getUserName(), 0);
        }
        if (message != null && message.hasText()) {
            if (message.getText().equals("/help")) {
                sendMsg(message, "Привет, ");
            }
            else
                sendMsg(message, "Я не знаю что ответить на это");

        }
    }

    private void sendMsg(Message message, String text) {
        ArrayList<Score> userlist = new ArrayList<>();

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

        SendMessage sendMessage = new SendMessage();
        SendPhoto sendPhoto = new SendPhoto();
        sendMessage.enableMarkdown(true);
        sendMessage.setText("Правила игры: \n" +
                "Нужно вспомнить как можно больше стран. Вводить нужно сокращенное название на английском языке из двух букв, например RU\n" +
                "Если будет введено верное значение, то Вы увидите флаг этой страны и получите 1 золотую монету\n" +
                "Если будет введено неверное значение, Вам придет в ответ пиратский флаг и Вы потеряете 1 золотую монету\n" +
                "Удачной игры!");

        sendPhoto.setChatId(message.getChatId().toString());
        sendMessage.setChatId(message.getChatId().toString());

        for (int i = 0; i < userlist.size(); i++) {
            if (!userlist.get(i).userId.equals(message.getChat().getUserName())){
                userlist.add(new Score(message.getChat().getUserName()));
            }
        }

        for (int i = 0; i < userlist.size(); i++) {
            System.out.println(userlist.get(i).userId);
        }
        if (message.getText().startsWith("/help") || message.getText().startsWith("/start")) {
            try {
                sendMessage(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            File file = new File("flags"+File.separator+ message.getText().toLowerCase() + ".png");
            if (file.exists()) {
                for (int i = 0; i < shortName.length; i++) {
                    if (message.getText().toUpperCase().equals(shortName[i]))
                        temp = fullName[i];
                }
                sendPhoto.setNewPhoto(file);
                sendMessage.setText(correctAnswers.get(new Random().nextInt(8))+"\nЭто флаг страны : "+temp+"" +
                        "\nВот ссылка, почитай про эту страну : https://ru.wikipedia.org/wiki/"+temp);
                list.put(message.getChat().getUserName(),list.get(message.getChat().getUserName())+1);
                System.out.println(message.getChat().getUserName()+" : "+temp);

            } else {

                sendPhoto.setNewPhoto(new File( "flags"+File.separator+"pirate.jpg"));
                sendMessage.setText(wrongAnswers.get(new Random().nextInt(8)));
                list.put(message.getChat().getUserName(),list.get(message.getChat().getUserName())-1);
                System.out.println(message.getChat().getUserName()+" : "+message.getText());
            }

            try {
                sendPhoto(sendPhoto);
                sendMessage(sendMessage);
                for (Map.Entry<String, Integer> pair : list.entrySet())
                {
                    String key = pair.getKey();
                    Integer value = pair.getValue();
                    System.out.println(key + ":" + value);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


}
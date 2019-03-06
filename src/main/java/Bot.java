
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Bot extends TelegramLongPollingBot {

   // String PATH_NAME = "src/main/resources/";

    SQLHandler sqlHandler = new SQLHandler();

    String temp="";
    static ArrayList<String> correctAnswers = new ArrayList<>();
    static ArrayList<String> wrongAnswers = new ArrayList<>();
    static final String[] shortName={"TL","NR","AB","AU","AT","AZ","AX","AL","DZ","AI","AO","AD","AQ","AG","AR","AM","AW","AF","BS","BD","BB","BH","BY","BZ","BE","BJ","BM","BG","BO","BA","BW","BR","VG","BN","BF","BI","BT","VU","VA","GB","HU","VE","VI","AC","VN","GA","HT","GY","GM","GH","GP","GT","GN","GW","DE","GI","HN","HK","GD","GL","GR","GE","GU","DK","DJ","DM","DO","EG","ZM","ZW","IL","IN","ID","JO","IQ","IR","IE","IS","ES","IT","YE","CV","KZ","KY","KH","CM","CA","QA","KE","CY","KG","KI","CN","KP","CC","CO","KM","CD","CG","KR","CR","CI","CU","KW","CK","LA","LV","LS","LR","LB","LY","LT","LI","LU","MU","MR","MG","YT","MO","MK","MW","MY","ML","MV","MT","MA","MQ","MH","MX","FM","MZ","MD","MC","MN","MS","MM","NA","NP","NE","NG","NL","NI","NU","NZ","NC","NO","NF","AE","OM","PK","PW","PS","PA","PG","PY","PE","PN","PL","PT","PR","RE","CX","RU","RW","RO","SV","WS","AS","SM","ST","SA","SZ","SH","MP","SC","BL","MF","PM","SN","VC","KN","LC","RS","SG","SY","SK","SI","SB","SO","SD","SR","US","SL","TJ","TH","TW","TZ","TC","TG","TK","TO","TT","TV","TN","TM","TR","UG","UZ","UA","WF","UY","FO","FJ","PH","FI","FK","FR","PF","HM","HR","CF","TD","ME","CZ","CL","CH","SE","LK","EC","GQ","ER","EE","ET","ZA","GS","JM","JP"};
    static final String[] fullName={"Восточный_Тимор","Науру","Абхазия","Австралия","Австрия","Азербайджан","Аландские острова","Албания","Алжир","Ангилья","Ангола","Андорра","Антарктика","Антигуа_и_Барбуда","Аргентина","Армения","Аруба","Афганистан","Багамы","Бангладеш","Барбадос","Бахрейн","Беларусь","Белиз","Бельгия","Бенин","Бермудские_острова","Болгария","Боливия","Босния_и_Герцеговина","Ботсвана","Бразилия","Британские_Виргинские_острова","Бруней","Буркина-Фасо","Бурунди","Бутан","Вануату","Ватикан","Великобритания","Венгрия","Венесуэла","Виргинские острова","Остров_Вознесения","Вьетнам","Габон","Гаити","Гайяна","Гамбия","Гана","Гваделупа","Гватемала","Гвинея","Гвинея-Бисау","Германия","Гибралтар","Гондурас","Гонконг","Гренада","Гренландия","Греция","Грузия","Гуам","Дания","Джибути","Доминика","Доминиканская_Республика","Египет","Замбия","Зимбабве","Израиль","Индия","Индонезия","Иордания","Ирак","Иран","Ирландия","Исландия","Испания","Италия","Йемен","Кабо-Верде","Казахстан","Острова_Кайман","Камбоджа","Камерун","Канада","Катар","Кения","Кипр","Киргизия","Кирибати","Китай","КНДР","Кокосовые_острова","Колумбия","Коморы","Демократическая_Республика_Конго","Республика_Конго","Республика_Корея","Коста-Рика","Кот-Д'Ивуар","Куба","Кувейт","Острова_Кука","Лаос","Латвия","Лесото","Либерия","Ливан","Ливия","Литва","Лихтенштейн","Люксембург","Маврикий","Мавритания","Мадагаскар","Майотта","Макао","Македония","Малави","Малайзия","Мали","Мальдивы","Мальта","Марокко","Мартиника","Маршаллов_Острова","Мексика","Микронезия","Мозамбик","Молдавия","Монако","Монголия","Монтсеррат","Мьянма","Намибия","Непал","Нигер","Нигерия","Нидерланды","Никарагуа","Ниуэ, остров","Новая_Зеландия","Новая Каледония","Норвегия","Норфолк, остров","ОАЕ","Оман","Пакистан","Палау","Палестина","Панама","Папуа - Новая Гвинея","Парагвай","Перу","Питкэрн, остров","Польша","Португалия","Пуэрто-Рико","Реюньон","Рождества остров","Россия","Руанда","Румыния","Сальвадор","Самоа","Самоа Американское","Сан Марино","Сан Томе и Принсипе","Саудовская Аравия","Свазиленд","Святой Елены остров","Северные Марианские острова","Сейшелы","Сен-Бартелеми","Сен-Мартен","Сен-Пьер и Микелон","Сенегал","Сент-Винсент и Гренадины","Сент-Китс и Невис","Сент-Люсия","Сербия","Сингапур","Сирия","Словакия","Словения","Соломоновы Острова","Сомали","Судан","Суринам","США","Сьерра-Леоне","Таджикистан","Таиланд","Тайвань","Танзания","Теркс и Кайкос","Того","Токелау острова","Тонга острова","Тринидад и Тобаго","Тувалу","Тунис","Туркменистан","Турция","Уганда","Узбекистан","Украина","Уоллис и Футуна острова","Уругвай","Фарерские острова","Фиджи","Филиппины","Финляндия","Фолклендские острова","Франция","Французская Полинезия","Херд и Макдональд острова","Хорватия","Центральноафриканская республика","Чад","Черногория","Чешская Республика","Чили","Швейцария","Швеция","Шри Ланка","Эквадор","Экваториальная Гвинея","Эритрея","Эстония","Эфиопия","ЮАР","Южная Георгия и Ю.Сандвичевы о-ва","Ямайка","Япония"};




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
        return "Страны мира";
    }

    @Override
    public String getBotToken() {
       // return "645703802:AAHsSt7Tc7jmLd52AzbjzsVfukz7wZPH6QI";
       return "739164691:AAGg44HjBh9srbpdUg_nYi5UfESRMIsbrNY";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            sqlHandler.connect();
            if (!sqlHandler.isUserRegistered(message.getChat().getUserName())) {
                sqlHandler.registerUser(message.getChat().getUserName(), message.getChat().getFirstName());
               // sqlHandler.disconnect();
            }
            if (message.getText().equals("/help")) {
                sendMsg(message, "Привет, ");
            }
            else
                sendMsg(message, "Я не знаю что ответить на это");

        }
    }


    private void sendMsg(Message message, String text) {

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

        SendMessage sendMessage = new SendMessage().setChatId(message.getChatId().toString());
        SendPhoto sendPhoto = new SendPhoto().setChatId(message.getChatId().toString());
        sendMessage.enableHtml(false);
        sendMessage.enableMarkdown(false);
        sendMessage.setText("Правила игры: \n" +
                "Нужно вспомнить как можно больше стран. Вводить нужно сокращенное название на английском языке из двух букв, например RU\n" +
                "Если будет введено верное значение, то Вы увидите флаг этой страны и получите 1 золотую монету\n" +
                "Если будет введено неверное значение, Вам придет в ответ пиратский флаг и Вы потеряете 1 золотую монету\n" +
                "Удачной игры!" +
                "\nБольшая просьба добавить себе ник в telegram если его еще нет" +
                "\nПосмотреть топ 10 - /top");




        if (message.getText().startsWith("/help") || message.getText().startsWith("/start")) {
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

       else if (message.getText().equals("/top")) {
            try {
                execute(sendMessage.setText(sqlHandler.top10()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else
        if (message.getText().length()!=2) {
            try {
                execute(sendMessage.setText("Нужно ввести 2 буквы, давай попробуем еще раз"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }



        else {
            File file = new File("flags"+File.separator+ message.getText().toLowerCase() + ".png");
            if (file.exists()) {
                for (int i = 0; i < shortName.length; i++) {
                    if (message.getText().toUpperCase().equals(shortName[i]))
                        temp = fullName[i];
                }
                if (sqlHandler.isCountryAlreadyFound(message.getChat().getUserName(),message.getText().toUpperCase())){
                    sendPhoto.setNewPhoto(new File( "flags"+File.separator+"repeat.jpg"));
                    sendMessage.setText("Это "+temp+" но ты уже отгадывал эту страну, счёт остается прежним : "+sqlHandler.currentScore(message.getChat().getUserName()));
                }
                else {

                    sqlHandler.addFoundedCountry(message.getChat().getUserName(),message.getText().toUpperCase());
                    sendPhoto.setNewPhoto(file);
                    sqlHandler.plusPoint(message.getChat().getUserName());
                    sendMessage.setText(correctAnswers.get(new Random().nextInt(correctAnswers.size()))+"\nЭто флаг страны : "+temp+"" +
                            "\nВот ссылка, почитай про эту страну : https://ru.wikipedia.org/wiki/"+temp+"" +
                            "\nМонетка твоя!\nТвой текущий счёт : "+sqlHandler.currentScore(message.getChat().getUserName()));
                }





            } else {
                sqlHandler.minusPoint(message.getChat().getUserName());
                sendPhoto.setNewPhoto(new File( "flags"+File.separator+"pirate.jpg"));
                sendMessage.setText(wrongAnswers.get(new Random().nextInt(wrongAnswers.size()))+"\nТы теряешь одну монету." +
                        "\nТвой текущий счёт :"+sqlHandler.currentScore(message.getChat().getUserName()));

            }

            sqlHandler.info();

            try {
                sendPhoto(sendPhoto);
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


}
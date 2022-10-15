import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args)
    {
        String inputData = "2356 523985 Владивосток Русская 57н 110 123-456-789 01";
        boolean result = CheckCorrectness.isCorrect(inputData, TypesOfString.personalData);
        System.out.println(result);
    }
}

enum TypesOfString
{
    postAddress, personalData, FIOPlusDateAndYearOfBirthdayPlusPlaceOfBirth, OutputDataAboutBook, OutputDataAboutArticle
}

class CheckCorrectness
{
    public static boolean isCorrect(String string, TypesOfString type)
    {
        switch (type)
        {
            case postAddress:
                return PostAddressIsCorrect(string);
            case personalData:
                return PersonalDataIsCorrect(string);
            default:
                System.out.println("Функционал пока не реализован:(");
                break;
        }

        return false;
    }

    //Структура почтового адреса: ФИО, улица, дом, подъезд, квартира; разделенные пробелом
    //ФИО - Слова русского алфавита, начинающиеся с заглавной буквы длиной больше 0
    //Улица - Слова русского алфавита, начинающиеся с заглавной буквы длиной больше 1
    //Дом - Строка вида "натуральное число U {0} плюс, возможно, строчная буква русского алфавита ИЛИ
    //плюс, возможно, косая черта, после которой стоит натуральное число U {0}"
    //Подъезд, квартира - натуральное число U {0}
    public static boolean PostAddressIsCorrect(String string)
    {
        Pattern pattern = Pattern.compile("^[А-Я][а-яё]* [А-Я][а-яё]* [А-Я][а-яё]* [А-Я][а-яё]+ \\d+([а-я]|/\\d)? [1-9][0-9]* [1-9][0-9]*$");

        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    //Структура персональных данных: Серия/номер паспорта, адрес регистрации, номер СНИЛС
    //Серия паспорта - строка из 4-ех цифр
    //Номер паспорта - строка из 6-ти цифр
    //Адрес регистрации - строка, состоящая из населенного пункта (строка, первая буква заглавная, длина > 0),
    //улицы (аналогично) и квартиры (натуральное число)
    public static boolean PersonalDataIsCorrect(String string)
    {
        Pattern pattern = Pattern.compile("^\\d{4} \\d{6} [А-Я][а-яё]+ [А-Я][а-яё]+ \\d+([а-я]|/\\d)? [1-9][0-9]* \\d{3}-\\d{3}-\\d{3} \\d{2}$");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
}

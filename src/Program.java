import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void Run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные, разделяя пробелом в соответствующем формате: \n" +
                "фамилия, имя, отчество - строки, датарождения - строка формата dd.mm.yyyy, \n" +
                 "номертелефона - целое беззнаковое число (6 символов), пол - символ латиницей f или m");
        String next = scanner.nextLine();
        String[] split = next.split(" ");

        try {
            int codeValid = validateInput(split);
            if(codeValid == -1){
                throw new InvalidDataFormatException("Not all elements have been introduced");
            }
            if(codeValid == 1){
                throw new InvalidDataFormatException("More elements have been introduced");
            }
            WriteFile(split);
        }catch (InvalidDataFormatException e){
            System.out.println("Invalid input: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int validateInput(String[] split) throws InvalidDataFormatException {
        //check the number of items
        if (split.length > 6) {
            return 1;
        }
        if (split.length < 6) {
            return -1;
        }
        //check format
        String date = split[3];
        String phone = split[4];
        String gender = split[5];
        //date
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
            format.setLenient(false);
            Date myDate = format.parse(date);
        }
        catch (ParseException e) {
            throw new InvalidDataFormatException("Invalid date format");
        }
        //phone
        if(phone.length() != 6){
            throw new InvalidDataFormatException("Incorrect number of characters in the phone");
        }
        try {
            int phoneNumber = Integer.parseInt(phone);
        }
        catch (NumberFormatException e) {
            throw new InvalidDataFormatException("The phone number contains incorrect characters");
        }
        //gender
        if(!(gender.equals("f") || gender.equals("m"))){
            throw new InvalidDataFormatException("Incorrect gender format has been introduced. Only f or m is allowed");
        }
        return 0;
    }

    public static void WriteFile(String[] split){
        String fileName = split[0] + ".txt";
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(fileName, true);
            fileWriter.write(String.valueOf(new Human(split)));
            fileWriter.flush();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
}

public class Human {
    String firstName;
    String secondName;
    String patronymic;
    String birthDate;
    String phoneNumber;
    String gender;

    public Human(String[] split){
        this.firstName = split[0];
        this.secondName = split[1];
        this.patronymic = split[2];
        this.birthDate = split[3];
        this.phoneNumber = split[4];
        this.gender = split[5];
    }

    @Override
    public String toString(){
        return String.format("<%s><%s><%s><%s><%s><%s>", firstName, secondName, patronymic, birthDate, phoneNumber, gender);
    }
}

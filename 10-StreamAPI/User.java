public class User {
    private final String name;
    private final int age;

    User (String userName, int userAge) {
        name = userName;
        age = userAge;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

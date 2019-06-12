package pubsub.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Generator
{
    private static List<String> names = Arrays.asList("David", "John", "Paul", "Mark", "James", "Andrew", "Scott",
        "Steven", "Robert", "Stephen", "William", "Craig", "Michael", "Stuart", "Christopher", "Alan", "Colin", "Brian",
        "Kevin", "Gary", "Richard", "Derek", "Martin", "Thomas", "Neil", "Barry", "Ian", "Jason", "Iain", "Gordon",
        "Alexander", "Graeme", "Peter", "Darren", "Graham", "George", "Kenneth", "Allan", "Simon", "Douglas", "Keith",
        "Lee", "Anthony", "Grant", "Ross", "Jonathan", "Gavin", "Nicholas", "Joseph", "Stewart", "Daniel", "Edward",
        "Matthew", "Donald", "Fraser", "Garry", "Malcolm", "Charles", "Duncan", "Alistair", "Raymond", "Philip",
        "Ronald", "Ewan", "Ryan", "Francis", "Bruce", "Patrick", "Alastair", "Bryan", "Marc", "Jamie", "Hugh", "Euan",
        "Gerard", "Sean", "Wayne", "Adam", "Calum", "Alasdair", "Robin", "Greig", "Angus", "Russell", "Cameron",
        "Roderick", "Norman", "Murray", "Gareth", "Dean", "Eric", "Adrian", "Gregor", "Samuel", "Gerald", "Henry",
        "Justin", "Benjamin", "Shaun", "Callum", "Campbell", "Frank", "Roy", "Timothy", "David", "John", "Paul",
        "James", "Mark", "Scott", "Andrew", "Steven", "Robert", "Stephen", "Craig", "Christopher", "Alan", "Michael",
        "Stuart", "William", "Kevin", "Colin", "Brian", "Derek", "Neil", "Richard", "Gary", "Barry", "Martin", "Thomas",
        "Ian", "Gordon", "Kenneth", "Alexander", "Graeme", "Peter", "Iain", "Graham", "Jason", "George", "Allan",
        "Keith", "Darren", "Simon", "Douglas", "Ross", "Stewart", "Lee", "Grant", "Nicholas", "Joseph", "Gavin",
        "Anthony", "Jonathan", "Daniel", "Fraser", "Matthew", "Donald", "Malcolm", "Alistair", "Edward", "Raymond",
        "Charles", "Philip", "Bruce", "Garry", "Jamie", "Ryan", "Bryan", "Francis", "Alastair", "Duncan", "Patrick",
        "Ronald", "Alasdair", "Ewan", "Marc", "Wayne", "Hugh", "Robin", "Sean", "Calum", "Euan", "Adam", "Russell",
        "Cameron", "Gerard", "Murray", "Norman", "Angus", "Greig", "Justin", "Gregor", "Gerald", "Roderick", "Roy",
        "Benjamin", "Timothy", "Dean", "Samuel", "Greg", "Shaun", "Adrian", "Campbell", "David", "John", "Paul",
        "James", "Andrew", "Steven", "Scott", "Mark", "Robert", "Stephen", "Craig", "Christopher", "Stuart", "Alan",
        "William", "Michael", "Kevin", "Colin", "Brian", "Derek", "Neil", "Richard", "Martin", "Gary", "Ross", "Thomas");

    private static List<String> eyeColors = Arrays.asList("Green", "Blue", "Brown", "Hazel", "Amber");

    public static String getRandomName()
    {
        return names.get(getRandomNumber(0, names.size()));
    }

    public static String getRandomDate()
    {
        int day = getRandomNumber(1, 28);
        int month = getRandomNumber(1, 12);
        int year = getRandomNumber(1930, 2000);

        return day + "/" + month + "/" + year;
    }

    public static Double getRandomHeight()
    {
        return Double.parseDouble("1." + getRandomNumber(55, 99));
    }

    public static String getRandomEyeColor()
    {
        return eyeColors.get(getRandomNumber(0, eyeColors.size()));
    }

    public static int getRandomHeartRate()
    {
        return getRandomNumber(50, 120);
    }

    public static int getRandomOperator()
    {
        return getRandomNumber(0, 6);
    }

    public static int getRandomNameOperator()
    {
        return getRandomNumber(0, 2);
    }

    public static int getRandomNumber(int min, int max)
    {
        return new Random().nextInt(max - min) + min;
    }

}

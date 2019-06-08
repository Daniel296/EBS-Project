package pubsub;

import java.time.LocalDate;
import java.util.Arrays;
import java.sql.Date;
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
        "William", "Michael", "Kevin", "Colin", "Brian", "Derek", "Neil", "Richard", "Martin", "Gary", "Ross", "Thomas",
        "Ian", "Iain", "Barry", "Gordon", "Graeme", "Graham", "Alexander", "Peter", "Kenneth", "Simon", "Allan",
        "Darren", "George", "Douglas", "Jason", "Lee", "Gavin", "Anthony", "Jonathan", "Stewart", "Jamie", "Keith",
        "Matthew", "Joseph", "Daniel", "Edward", "Nicholas", "Grant", "Ryan", "Philip", "Alistair", "Donald", "Charles",
        "Duncan", "Garry", "Malcolm", "Raymond", "Bryan", "Ewan", "Fraser", "Alastair", "Euan", "Patrick", "Bruce",
        "Ronald", "Greig", "Hugh", "Francis", "Gerard", "Russell", "Alasdair", "Adam", "Marc", "Sean", "Benjamin",
        "Gregor", "Calum", "Wayne", "Robin", "Roderick", "Murray", "Greg", "Angus", "Cameron", "Gerald", "Shaun",
        "Samuel", "Timothy", "Liam", "Campbell", "Gareth", "Niall", "Dean", "Justin", "David", "John", "Paul", "James",
        "Andrew", "Steven", "Scott", "Mark", "Robert", "Christopher", "Craig", "Stuart", "Kevin", "Alan", "Michael",
        "Stephen", "William", "Colin", "Brian", "Neil", "Richard", "Ross", "Thomas", "Gary", "Derek", "Iain", "Gordon",
        "Graeme", "Martin", "Barry", "Gavin", "Ian", "Kenneth", "Alexander", "Peter", "Graham", "Allan", "Darren",
        "Jamie", "Simon", "Lee", "George", "Keith", "Stewart", "Douglas", "Jonathan", "Matthew", "Daniel", "Grant",
        "Joseph", "Jason", "Anthony", "Ryan", "Edward", "Fraser", "Donald", "Charles", "Garry", "Duncan", "Nicholas",
        "Raymond", "Alistair", "Bryan", "Philip", "Alastair", "Malcolm", "Alasdair", "Russell", "Patrick", "Euan",
        "Ewan", "Gareth", "Bruce", "Adam", "Gerard", "Greig", "Marc", "Sean", "Robin", "Ronald", "Benjamin", "Francis",
        "Gregor", "Greg", "Hugh", "Calum", "Shaun", "Cameron", "Roderick", "Angus", "Gerald", "Rory", "Samuel", "Wayne",
        "Murray", "Norman", "Timothy", "Dean", "Martyn", "Roy", "Wesley", "David", "John", "Andrew", "Paul", "James",
        "Scott", "Christopher", "Steven", "Michael", "Mark", "Robert", "Craig", "Kevin", "Stuart", "Alan", "Stephen",
        "William", "Ross", "Colin", "Brian", "Richard", "Barry", "Neil", "Derek", "Gordon", "Thomas", "Graeme",
        "Martin", "Peter", "Gary", "Iain", "Graham", "Ian", "Kenneth", "Alexander", "Allan", "Jamie", "Gavin", "Darren",
        "George", "Douglas", "Simon", "Stewart", "Daniel", "Keith", "Lee", "Ryan", "Joseph", "Matthew", "Grant",
        "Anthony", "Jason", "Jonathan", "Duncan", "Fraser", "Donald", "Garry", "Nicholas", "Alistair", "Bryan",
        "Charles", "Raymond", "Philip", "Marc", "Euan", "Edward", "Gareth", "Sean", "Adam", "Alasdair", "Alastair",
        "Greig", "Ronald", "Malcolm", "Patrick", "Ewan", "Russell", "Greg", "Gregor", "Robin", "Benjamin", "Bruce",
        "Gerard", "Francis", "Hugh", "Calum", "Cameron", "Shaun", "Wayne", "Samuel", "Murray", "Roderick", "Barrie",
        "Callum", "Angus", "Liam", "Rory", "Niall", "Timothy", "Antony", "David", "John", "Andrew", "Paul", "James",
        "Christopher", "Steven", "Scott", "Mark", "Robert", "Craig", "Kevin", "Michael", "Stuart", "Stephen", "Alan",
        "Colin", "William", "Brian", "Barry", "Ross", "Gary", "Martin", "Thomas", "Richard", "Graeme", "Neil", "Peter",
        "Iain", "Gordon", "Derek", "Ian", "Alexander", "Kenneth", "Graham", "Allan", "Darren", "Gavin", "Jamie",
        "Douglas", "Ryan", "Simon", "George", "Matthew", "Lee", "Stewart", "Keith", "Daniel", "Anthony", "Grant",
        "Gareth", "Jonathan", "Joseph", "Alistair", "Charles", "Edward", "Fraser", "Duncan", "Garry", "Bryan", "Ewan",
        "Nicholas", "Donald", "Philip", "Alastair", "Adam", "Jason", "Euan", "Russell", "Sean", "Malcolm", "Patrick",
        "Raymond", "Alasdair", "Liam", "Greig", "Gregor", "Ronald", "Greg", "Shaun", "Wayne", "Bruce", "Marc", "Robin",
        "Cameron", "Francis", "Angus", "Gerard", "Kris", "Calum", "Benjamin", "Rory", "Dean", "Timothy", "Samuel",
        "Niall", "Hugh", "Lewis", "Murray", "Justin", "Roderick", "David", "John", "Andrew", "James", "Christopher",
        "Paul", "Steven", "Kevin", "Robert", "Scott", "Craig", "Michael", "Mark", "Stuart", "Stephen", "Alan",
        "William", "Gary", "Ross", "Colin", "Brian", "Barry", "Richard", "Martin", "Thomas", "Neil", "Peter", "Iain",
        "Graeme", "Ian", "Gordon", "Alexander", "Ryan", "Derek", "Kenneth", "Allan", "Jamie", "Graham", "Gavin",
        "Darren", "Stewart", "Gareth", "Jonathan", "Daniel", "Douglas", "Grant", "Lee", "George", "Joseph", "Simon",
        "Matthew", "Keith", "Anthony", "Fraser", "Garry", "Alistair", "Bryan", "Philip", "Adam", "Sean", "Duncan",
        "Edward", "Charles", "Ewan", "Russell", "Donald", "Patrick", "Alastair", "Euan", "Jason", "Nicholas", "Marc",
        "Raymond", "Malcolm", "Greig", "Alasdair", "Greg", "Liam", "Shaun", "Francis", "Ronald", "Benjamin", "Cameron",
        "Dean", "Niall", "Gerard", "Murray", "Robin", "Timothy", "Bruce", "Hugh", "Calum", "Kris", "Wayne", "Roderick",
        "Samuel", "Martyn", "Angus", "Gregor", "Jon", "Rory", "David", "John", "Andrew", "Christopher", "James", "Paul",
        "Steven", "Craig", "Michael", "Scott", "Kevin", "Robert", "Mark", "Stuart", "Stephen", "Gary", "Alan",
        "William", "Ross", "Colin", "Martin", "Thomas", "Barry", "Brian", "Neil", "Richard", "Graeme", "Peter", "Iain",
        "Gordon", "Ian", "Alexander", "Darren", "Derek", "Graham", "Ryan", "Jamie", "Kenneth", "Allan", "Gavin",
        "Jonathan", "Daniel", "George", "Douglas", "Stewart", "Matthew", "Anthony", "Keith", "Grant", "Sean", "Simon",
        "Gareth", "Garry", "Lee", "Fraser", "Alistair", "Adam", "Joseph", "Nicholas", "Bryan", "Charles", "Duncan",
        "Philip", "Russell", "Donald", "Edward", "Marc", "Alastair", "Greg", "Euan", "Raymond", "Shaun", "Ewan",
        "Jason", "Patrick", "Alasdair", "Gerard", "Malcolm", "Calum", "Liam", "Bruce", "Ronald", "Kris", "Hugh",
        "Lewis", "Dean", "Greig", "Benjamin", "Wayne", "Martyn", "Niall", "Callum", "Francis", "Cameron", "Gregor",
        "Angus", "Aaron", "Blair", "Dale", "Samuel", "Timothy", "David", "Christopher", "John", "Andrew", "James",
        "Paul", "Steven", "Craig", "Scott", "Mark", "Michael", "Robert", "Kevin", "Stuart", "William", "Alan",
        "Stephen", "Gary", "Ross", "Martin", "Colin", "Richard", "Thomas", "Brian", "Barry", "Neil", "Graeme", "Gordon",
        "Ian", "Iain", "Peter", "Darren", "Alexander", "Jamie", "Ryan", "Graham", "Jonathan", "Derek", "Kenneth",
        "Daniel", "Allan", "Matthew", "Gavin", "Lee", "Stewart", "Douglas", "George", "Sean", "Nicholas", "Simon",
        "Fraser", "Keith", "Anthony", "Joseph", "Adam", "Gareth", "Grant", "Bryan", "Duncan", "Philip", "Euan",
        "Alistair", "Garry", "Ewan", "Charles", "Edward", "Jason", "Marc", "Shaun", "Donald", "Russell", "Alastair",
        "Patrick", "Calum", "Liam", "Alasdair", "Greg", "Callum", "Raymond", "Benjamin", "Gerard", "Malcolm", "Cameron",
        "Aaron", "Ronald", "Bruce", "Greig", "Lewis", "Francis", "Dean", "Gregor", "Niall", "Jordan", "Kris", "Martyn",
        "Robin", "Samuel", "Blair", "Hugh", "Mohammed", "Murray", "Wayne", "David", "Christopher", "Andrew", "John",
        "James", "Craig", "Steven", "Paul", "Michael", "Scott", "Robert", "Mark", "William", "Kevin", "Stuart",
        "Stephen", "Alan", "Gary", "Ross", "Colin", "Richard", "Martin", "Thomas", "Neil", "Ryan", "Graeme", "Brian",
        "Peter", "Gordon", "Darren", "Ian", "Jamie", "Alexander", "Iain", "Graham", "Barry", "Allan", "Jonathan",
        "Daniel", "Gavin", "Derek", "Kenneth", "Sean", "Matthew", "Lee", "George", "Nicholas", "Anthony", "Stewart",
        "Fraser", "Douglas", "Keith", "Joseph", "Grant", "Marc", "Adam", "Simon", "Alistair", "Garry", "Alastair",
        "Bryan", "Liam", "Jason", "Duncan", "Euan", "Charles", "Greg", "Edward", "Philip", "Russell", "Shaun", "Gareth",
        "Ewan", "Calum", "Callum", "Donald", "Cameron", "Raymond", "Patrick", "Alasdair", "Dean", "Greig", "Lewis",
        "Benjamin", "Malcolm", "Niall", "Gerard", "Martyn", "Jordan", "Aaron", "Francis", "Hugh", "Gregor", "Mohammed",
        "Robin", "Samuel", "Angus", "Bruce", "Kris", "Ronald", "David", "Christopher", "Andrew", "John", "James",
        "Craig", "Steven", "Paul", "Michael", "Mark", "Scott", "Robert", "Stuart", "Kevin", "William", "Stephen",
        "Gary", "Alan", "Ross", "Richard", "Thomas", "Neil", "Colin", "Martin", "Ryan", "Graeme", "Peter", "Jamie",
        "Iain", "Darren", "Gordon", "Ian", "Graham", "Brian", "Daniel", "Alexander", "Sean", "Matthew", "Barry",
        "Allan", "Kenneth", "Lee", "Derek", "Jonathan", "Gavin", "Joseph", "Adam", "Stewart", "Fraser", "George",
        "Grant", "Nicholas", "Anthony", "Douglas", "Simon", "Marc", "Alistair", "Liam", "Keith", "Philip", "Shaun",
        "Garry", "Edward", "Greg", "Euan", "Alastair", "Alasdair", "Callum", "Patrick", "Calum", "Duncan", "Jason",
        "Bryan", "Raymond", "Charles", "Donald", "Ewan", "Russell", "Lewis", "Cameron", "Dean", "Benjamin", "Gareth",
        "Malcolm", "Samuel", "Niall", "Gerard", "Aaron", "Greig", "Ben", "Martyn", "Bruce", "Jordan", "Kieran",
        "Francis", "Wayne", "Kris", "Mohammed", "Murray", "Rory", "David", "Christopher", "Andrew", "John", "James",
        "Steven", "Craig", "Paul", "Michael", "Mark", "Scott", "Robert", "Stuart", "Stephen", "Gary", "William",
        "Kevin", "Ross", "Ryan", "Jamie", "Richard", "Alan", "Thomas", "Darren", "Martin", "Colin", "Graeme", "Daniel",
        "Brian", "Alexander", "Gordon", "Peter", "Iain", "Sean", "Ian", "Matthew", "Barry", "Jonathan", "Graham", "Lee",
        "Adam", "Neil", "Kenneth", "Derek", "Allan", "Gavin", "Stewart", "Grant", "Fraser", "Nicholas", "George",
        "Joseph", "Anthony", "Douglas", "Shaun", "Liam", "Jason", "Calum", "Marc", "Callum", "Greg", "Duncan",
        "Alistair", "Philip", "Bryan", "Keith", "Simon", "Euan", "Garry", "Donald", "Cameron", "Edward", "Ewan",
        "Charles", "Lewis", "Alasdair", "Gareth", "Raymond", "Alastair", "Dean", "Patrick", "Benjamin", "Russell",
        "Kieran", "Martyn", "Samuel", "Gerard", "Malcolm", "Bruce", "Greig", "Jordan", "Kyle", "Ben", "Gregor", "Rory",
        "Blair", "Aaron", "Murray", "Niall", "Wayne", "David", "Christopher", "Andrew", "James", "John", "Craig",
        "Steven", "Michael", "Scott", "Mark", "Paul", "Robert", "Stuart", "Ross", "Stephen", "Gary", "Kevin", "William",
        "Jamie", "Martin", "Ryan", "Alan", "Graeme", "Thomas", "Daniel", "Darren", "Richard", "Sean", "Colin",
        "Alexander", "Peter", "Iain", "Lee", "Ian", "Brian", "Graham", "Matthew", "Gordon", "Barry", "Adam", "Gavin",
        "Jonathan", "Neil", "Allan", "Marc", "Kenneth", "Kyle", "Fraser", "Shaun", "Jason", "Derek", "Grant", "Liam",
        "Anthony", "Joseph", "Stewart", "Greg", "Douglas", "Nicholas", "Calum", "George", "Simon", "Duncan", "Alistair",
        "Callum", "Dean", "Bryan", "Keith", "Euan", "Garry", "Philip", "Patrick", "Donald", "Edward", "Jordan",
        "Cameron", "Charles", "Gareth", "Martyn", "Lewis", "Ewan", "Aaron", "Benjamin", "Alastair", "Raymond", "Kieran",
        "Rory", "Alasdair", "Mohammed", "Samuel", "Malcolm", "Murray", "Blair", "Niall", "Kristopher", "Wayne", "Bruce",
        "Gerard", "Kris", "Russell", "David", "Christopher", "Andrew", "James", "John", "Scott", "Craig", "Michael",
        "Mark", "Steven", "Paul", "Ross", "Robert", "Stuart", "Ryan", "Gary", "Stephen", "William", "Jamie", "Kevin",
        "Martin", "Thomas", "Daniel", "Graeme", "Sean", "Alan", "Darren", "Richard", "Colin", "Matthew", "Lee", "Iain",
        "Graham", "Alexander", "Peter", "Brian", "Ian", "Neil", "Gordon", "Kyle", "Adam", "Jonathan", "Marc", "Shaun",
        "Fraser", "Jason", "Liam", "Gavin", "Grant", "Joseph", "Anthony", "Kenneth", "Stewart", "Barry", "Callum",
        "Allan", "Derek", "George", "Nicholas", "Greg", "Alistair", "Dean", "Calum", "Simon", "Philip", "Douglas",
        "Duncan", "Lewis", "Jordan", "Bryan", "Patrick", "Garry", "Cameron", "Edward", "Keith", "Euan", "Martyn",
        "Samuel", "Benjamin", "Donald", "Aaron", "Alasdair", "Ewan", "Charles", "Gareth", "Alastair", "Kieran", "Ben",
        "Rory", "Blair", "Raymond", "Jack", "Dale", "Bruce", "Murray", "Kristopher", "Mohammed", "Greig", "Kris",
        "Gregor", "David", "Christopher", "Scott", "Andrew", "James", "John", "Craig", "Michael", "Mark", "Steven",
        "Paul", "Ross", "Robert", "Stuart", "Ryan", "Stephen", "Gary", "Jamie", "Kevin", "William", "Sean", "Darren",
        "Martin", "Daniel", "Thomas", "Graeme", "Alan", "Jonathan", "Richard", "Lee", "Alexander", "Colin", "Jason",
        "Iain", "Liam", "Matthew", "Adam", "Calum", "Peter", "Graham", "Shaun", "Brian", "Gavin", "Ian", "Marc", "Neil",
        "Grant", "Kyle", "Fraser", "Gordon", "Callum", "Dean", "Nicholas", "Allan", "Joseph", "Lewis", "Kenneth",
        "Stewart", "Barry", "Anthony", "Greg", "Euan", "Derek", "George", "Simon", "Alistair", "Garry", "Douglas",
        "Jordan", "Duncan", "Patrick", "Samuel", "Aaron", "Charles", "Bryan", "Ewan", "Philip", "Benjamin", "Martyn",
        "Kieran", "Donald", "Jack", "Alasdair", "Cameron", "Rory", "Keith", "Gareth", "Blair", "Edward", "Alastair",
        "Luke", "Dale", "Kristopher", "Greig", "Raymond", "Gregor", "Malcolm", "Ben", "Niall", "Sam", "David",
        "Christopher", "Scott", "James", "Andrew", "Michael", "Craig", "John", "Ross", "Mark", "Jamie", "Paul",
        "Steven", "Stuart", "Robert", "Daniel", "Ryan", "Sean", "Stephen", "Gary", "William", "Darren", "Kevin",
        "Martin", "Graeme", "Thomas", "Jonathan", "Matthew", "Alan", "Alexander", "Iain", "Richard", "Jason", "Lee",
        "Callum", "Liam", "Calum", "Adam", "Colin", "Peter", "Fraser", "Shaun", "Neil", "Dean", "Brian", "Graham",
        "Grant", "Gavin", "Ian", "Gordon", "Marc", "Greg", "Nicholas", "Allan", "Stewart", "Anthony", "Kyle", "Lewis",
        "Jordan", "Joseph", "Kenneth", "Alistair", "Derek", "Barry", "Euan", "Douglas", "Philip", "Kieran", "Simon",
        "Aaron", "Jack", "Cameron", "George", "Ewan", "Garry", "Benjamin", "Blair", "Duncan", "Samuel", "Charles",
        "Bryan", "Dale", "Patrick", "Alasdair", "Alastair", "Rory", "Joshua", "Nathan", "Martyn", "Keith", "Donald",
        "Greig", "Ben", "Daryl", "Gregor", "Luke", "Edward", "Robbie", "Gareth", "Murray", "David", "Christopher",
        "Craig", "Scott", "Andrew", "Michael", "James", "John", "Ross", "Daniel", "Steven", "Jamie", "Mark", "Paul",
        "Ryan", "Stephen", "Sean", "Robert", "Stuart", "Gary", "Darren", "William", "Kevin", "Thomas", "Martin",
        "Matthew", "Liam", "Jonathan", "Callum", "Alan", "Calum", "Graeme", "Jordan", "Alexander", "Lee", "Richard",
        "Iain", "Adam", "Fraser", "Lewis", "Colin", "Grant", "Peter", "Dean", "Gavin", "Shaun", "Marc", "Ian", "Greg",
        "Gordon", "Brian", "Graham", "Nicholas", "Neil", "Stewart", "Euan", "Anthony", "Joseph", "Kieran", "Kyle",
        "Jack", "Jason", "Alistair", "Samuel", "Allan", "Aaron", "Kenneth", "George", "Barry", "Cameron", "Joshua",
        "Douglas", "Ewan", "Derek", "Benjamin", "Nathan", "Duncan", "Dale", "Blair", "Rory", "Patrick", "Simon",
        "Alasdair", "Garry", "Alastair", "Martyn", "Philip", "Charles", "Sam", "Daryl", "Gregor", "Edward", "Niall",
        "Donald", "Murray", "Keith", "Ben", "Bryan", "Connor", "Luke", "David", "Christopher", "Andrew", "Craig",
        "Scott", "Michael", "James", "Daniel", "Ryan", "Steven", "John", "Ross", "Jamie", "Mark", "Sean", "Paul",
        "Robert", "Stephen", "Stuart", "Matthew", "Gary", "Darren", "Thomas", "William", "Liam", "Jordan", "Kevin",
        "Callum", "Grant", "Martin", "Calum", "Alexander", "Lewis", "Adam", "Jonathan", "Shaun", "Kyle", "Fraser",
        "Lee", "Graeme", "Marc", "Peter", "Dean", "Alan", "Richard", "Greg", "Kieran", "Gavin", "Iain", "Cameron",
        "Euan", "Colin", "Neil", "Graham", "Ian", "Joseph", "Gordon", "Nicholas", "Jack", "Jason", "Aaron", "Connor",
        "Samuel", "Stewart", "Ewan", "Anthony", "Joshua", "Brian", "Conor", "Nathan", "Kenneth", "Allan", "Alistair",
        "Douglas", "Blair", "Benjamin", "Duncan", "Rory", "Barry", "Patrick", "Alasdair", "Dale", "Sam", "Declan",
        "Ben", "Derek", "George", "Charles", "Simon", "Gregor", "Edward", "Garry", "Greig", "Owen", "Donald", "Keith",
        "Alastair", "Philip", "Daryl", "Luke", "Robbie", "David", "Scott", "Andrew", "James", "Christopher", "Michael",
        "Craig", "Ryan", "Daniel", "Ross", "Jamie", "Sean", "John", "Jordan", "Robert", "Steven", "Liam", "Mark",
        "Paul", "Stuart", "Matthew", "Stephen", "Thomas", "Callum", "Darren", "Gary", "Lewis", "William", "Connor",
        "Calum", "Martin", "Grant", "Adam", "Alexander", "Kyle", "Lee", "Kevin", "Jonathan", "Shaun", "Fraser",
        "Kieran", "Jack", "Dean", "Cameron", "Peter", "Alan", "Iain", "Marc", "Greg", "Graeme", "Conor", "Ian", "Euan",
        "Gavin", "Richard", "Blair", "Colin", "Joseph", "Aaron", "Dale", "Neil", "Sam", "Jason", "Joshua", "Gordon",
        "Samuel", "Stewart", "Nathan", "Nicholas", "Anthony", "Douglas", "Rory", "Alistair", "Brian", "Allan", "Ewan",
        "George", "Duncan", "Graham", "Ben", "Benjamin", "Gregor", "Declan", "Patrick", "Dylan", "Kenneth", "Derek",
        "Alasdair", "Owen", "Greig", "Barry", "Aidan", "Gareth", "Josh", "Charles", "Daryl", "Garry", "Simon", "Robbie",
        "Alastair", "Andrew", "Ryan", "David", "Scott", "James", "Michael", "Christopher", "Ross", "Craig", "Jordan",
        "Daniel", "Jamie", "John", "Steven", "Sean", "Mark", "Liam", "Lewis", "Stuart", "Matthew", "Paul", "Thomas",
        "Robert", "Stephen", "Connor", "Callum", "Cameron", "Darren", "Calum", "Jack", "Gary", "Adam", "Alexander",
        "William", "Kieran", "Kyle", "Jonathan", "Fraser", "Grant", "Dean", "Kevin", "Shaun", "Martin", "Lee", "Alan",
        "Iain", "Gavin", "Peter", "Dylan", "Euan", "Dale", "Aaron", "Conor", "Greg", "Jason", "Marc", "Alistair",
        "Nathan", "Joseph", "Joshua", "Richard", "Anthony", "Nicholas", "Blair", "Samuel", "Declan", "Neil");

    private static List<String> eyeColors = Arrays.asList("Green", "Blue", "Brown", "Hazel", "Amber");

    public static String getRandomName()
    {
        return names.get(getRandomNumber(0, names.size()));
    }

    public static java.util.Date getRandomDate()
    {
        int day = getRandomNumber(1, 28);
        int month = getRandomNumber(1, 12);
        int year = getRandomNumber(1930, 2000);

        return Date.valueOf(LocalDate.of(year, month, day));
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

    private static int getRandomNumber(int min, int max)
    {
        return new Random().nextInt(max - min) + min;
    }

}

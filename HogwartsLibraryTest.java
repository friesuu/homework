package VIVA3;

public class HogwartsLibraryTest 
{
    public static void main(String[] args) 
    {
        Library hogwartsLibrary = new Library();
        Book book1 = new Book("The Standard Book of Spells", "Miranda Goshawk", "9452297");
        Book book2 = new Book("Advanced Potion-Making", "Libatius Borage", "4826972");
        Book book3 = new Book("Fantastic Beasts and Where to Find Them", "Newt Scamander", "1564815");
        Book book4 = new Book("The Dark Forces: A Guide to Self-Protection", "Quirinus Quirrell", "1891568");
        Book book5 = new Book("Forbidden Spells", "Salazar Slytherin", "7134567");

        hogwartsLibrary.addBook(book1);
        hogwartsLibrary.addBook(book2);
        hogwartsLibrary.addBook(book3);
        hogwartsLibrary.addBook(book4);
        hogwartsLibrary.addBook(book5);

        System.out.println("Searching for books by author:");
        hogwartsLibrary.findBooksByAuthor("Miranda Goshawk");


        System.out.println("\nSearching for books by title:");
        hogwartsLibrary.findBooksByTitle("The Standard Book of Spells");


        boolean canBorrow = hogwartsLibrary.borrowBook("9452297");
        if (canBorrow) 
        {
            System.out.println("\nYou've borrowed a book!");
        } 
        else 
        {
            System.out.println("\nSorry, this book is not available for borrowing.");
        }

        hogwartsLibrary.sortBooks();

        hogwartsLibrary.removeBook("9452297");

        System.out.println("\nUpdated Library:");
        hogwartsLibrary.displayLibrary();
    }
}




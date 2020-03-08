package vip.designpattern.composite.homework;

public class Test {
    public static void main(String[] args) {

        Book book1 = new Book("book1");
        Book book2 = new Book("book2");
        Book book3 = new Book("book3");

        BookCatalog bookStack1 = new BookCatalog("bookStack1", 2);

        Book book4 = new Book("book4");
        Book book5 = new Book("book5");
        Book book6 = new Book("book6");

        bookStack1.add(book4);
        bookStack1.add(book5);
        bookStack1.add(book6);

        BookCatalog bookStackRoot = new BookCatalog("bookStackRoot", 1);

        bookStackRoot.add(book1);
        bookStackRoot.add(book2);
        bookStackRoot.add(book3);
        bookStackRoot.add(bookStack1);

        bookStackRoot.show();
        bookStackRoot.list();



    }
}

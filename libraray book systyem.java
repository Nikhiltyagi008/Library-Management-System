import java.util.ArrayList;
import java.util.HashMap;

// Book class
class Book {
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        this.isIssued = true;
    }

    public void returnBook() {
        this.isIssued = false;
    }
}

// Member class
class Member {
    private String name;
    private int memberId;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }
}

// Library class
class Library {
    private ArrayList<Book> books;
    private HashMap<Book, Member> issuedBooks;

    public Library() {
        this.books = new ArrayList<>();
        this.issuedBooks = new HashMap<>();
    }

    // Add a new book to the library
    public void addBook(String title, String author) {
        Book newBook = new Book(title, author);
        books.add(newBook);
        System.out.println("Book added: " + title + " by " + author);
    }

    // Issue a book to a member
    public void issueBook(String title, Member member) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isIssued()) {
                    book.issueBook();
                    issuedBooks.put(book, member);
                    System.out.println("Book issued: " + title + " to " + member.getName());
                    return;
                } else {
                    System.out.println("Book is already issued to someone else.");
                    return;
                }
            }
        }
        System.out.println("Book not found in the library.");
    }

    // Return a book from a member
    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.isIssued()) {
                    book.returnBook();
                    Member member = issuedBooks.remove(book);
                    System.out.println("Book returned: " + title + " by " + member.getName());
                    return;
                } else {
                    System.out.println("This book was not issued.");
                    return;
                }
            }
        }
        System.out.println("Book not found in the library.");
    }

    // Show issued books and their respective members
    public void showIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println("No books are currently issued.");
        } else {
            for (HashMap.Entry<Book, Member> entry : issuedBooks.entrySet()) {
                System.out.println("Book: " + entry.getKey().getTitle() + " is issued to " + entry.getValue().getName());
            }
        }
    }
}

// Main class for testing
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        
        // Adding books to the library
        library.addBook("To Kill a Mockingbird", "Harper Lee");
        library.addBook("1984", "George Orwell");
        
        // Creating members
        Member member1 = new Member("Alice", 1);
        Member member2 = new Member("Bob", 2);
        
        // Issue books to members
        library.issueBook("1984", member1);
        library.issueBook("To Kill a Mockingbird", member2);
        
        // Try to issue an already issued book
        library.issueBook("1984", member2);
        
        // Show issued books
        library.showIssuedBooks();
        
        // Return a book
        library.returnBook("1984");
        
        // Show issued books after return
        library.showIssuedBooks();
    }
}

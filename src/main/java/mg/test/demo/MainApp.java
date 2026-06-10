package mg.test.demo;

import mg.test.demo.model.Book;
import mg.test.demo.repository.BookRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class MainApp{

	private final BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {

        if (bookRepository.count() > 0) {
            return;
        }

        bookRepository.saveAll(createBooks());
    }

    private static final String PROGRAMMING_CATEGORY = "Programming";

    private List<Book> createBooks() {
        return List.of(

                create("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 10.99, "Classic novel set in the Roaring Twenties.", "Classic", 218),
                create("To Kill a Mockingbird", "Harper Lee", "9780061120084", 7.99, "Racial injustice in the Deep South.", "Fiction", 336),
                create("1984", "George Orwell", "9780451524935", 9.99, "Dystopian surveillance society.", "Dystopian", 328),
                create("Brave New World", "Aldous Huxley", "9780060850524", 11.50, "Science fiction dystopia.", "Sci-Fi", 288),
                create("The Hobbit", "J.R.R. Tolkien", "9780547928227", 14.99, "Fantasy adventure.", "Fantasy", 310),

                create("The Lord of the Rings", "J.R.R. Tolkien", "9780618640157", 29.99, "Epic fantasy trilogy.", "Fantasy", 1178),
                create("Clean Code", "Robert C. Martin", "9780132350884", 45.99, "Clean coding practices.", PROGRAMMING_CATEGORY, 464),
                create("Effective Java", "Joshua Bloch", "9780134685991", 55.99, "Best Java practices.", PROGRAMMING_CATEGORY, 416),
                create("Spring in Action", "Craig Walls", "9781617297571", 49.99, "Spring framework guide.", PROGRAMMING_CATEGORY, 520),
                create("Design Patterns", "GoF", "9780201633610", 59.99, "Classic design patterns.", PROGRAMMING_CATEGORY, 395),

                create("Refactoring", "Martin Fowler", "9780134757599", 54.99, "Code refactoring techniques.", PROGRAMMING_CATEGORY, 448),
                create("Domain-Driven Design", "Eric Evans", "9780321125217", 64.99, "DDD concepts.", "Architecture", 560),
                create("Microservices Patterns", "Chris Richardson", "9781617294549", 57.99, "Microservices architecture.", "Architecture", 520),
                create("The Pragmatic Programmer", "Andrew Hunt", "9780135957059", 42.99, "Programming philosophy.", PROGRAMMING_CATEGORY, 352),
                create("Head First Design Patterns", "Eric Freeman", "9781492078005", 44.99, "Easy design patterns.", PROGRAMMING_CATEGORY, 694),

                create("Java Concurrency in Practice", "Brian Goetz", "9780321349606", 51.99, "Concurrency in Java.", PROGRAMMING_CATEGORY, 432),
                create("Algorithms", "Robert Sedgewick", "9780321573513", 69.99, "Algorithms & data structures.", "CS", 955),
                create("Cracking the Coding Interview", "Gayle Laakmann", "9780984782857", 39.99, "Interview prep.", "Interview", 706),
                create("Docker Deep Dive", "Nigel Poulton", "9781521822807", 34.99, "Docker explained.", "DevOps", 380),
                create("Kubernetes Up and Running", "Brendan Burns", "9781098110208", 47.99, "Kubernetes guide.", "DevOps", 400),

                create("System Design Interview", "Alex Xu", "9781736049112", 49.99, "System design concepts.", "Architecture", 300),
                create("Grokking Algorithms", "Aditya Bhargava", "9781617292231", 29.99, "Easy algorithms.", "CS", 256),
                create("You Don't Know JS", "Kyle Simpson", "9781491904244", 39.99, "JavaScript deep dive.", PROGRAMMING_CATEGORY, 278),
                create("Eloquent JavaScript", "Marijn Haverbeke", "9781593279509", 32.99, "JS fundamentals.", PROGRAMMING_CATEGORY, 472),
                create("Java Puzzlers", "Joshua Bloch", "9780321336781", 37.99, "Java pitfalls.", PROGRAMMING_CATEGORY, 384),

                create("Soft Skills", "John Sonmez", "9781617292392", 27.99, "Career development.", "Career", 300),
                create("The Clean Coder", "Robert C. Martin", "9780137081073", 41.99, "Professional programmer.", PROGRAMMING_CATEGORY, 256),
                create("Architecture Patterns", "Mark Richards", "9781491971437", 52.99, "Software architecture.", "Architecture", 432),
                create("Fundamentals of Software Architecture", "Richards & Ford", "9781492043454", 58.99, "Architecture principles.", "Architecture", 460),
                create("Learning Spring Boot", "Greg Turnquist", "9781786463784", 38.99, "Spring Boot basics.", PROGRAMMING_CATEGORY, 340)
        );
    }

    private Book create(
            String title,
            String author,
            String isbn,
            double price,
            String description,
            String category,
            int pageCount
    ) {
        return Book.builder()
                .title(title)
                .author(author)
                .isbn(isbn)
                .price(price)
                .description(description)
                .category(category)
                .pageCount(pageCount)
                .publicationDate(LocalDate.now().minusYears(5))
                .publisherEmail("default@books.com")
                .stockQuantity(50)
                .available(true)
                .build();
    }
}

}
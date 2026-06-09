package mg.test.demo;

import mg.test.demo.model.Book;
import mg.test.demo.repository.BookRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class MainApp implements CommandLineRunner {

	private final BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book1 = new Book(null, "The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565", 10.99, "A classic novel set in the Roaring Twenties.");
		Book book2 = new Book(null, "To Kill a Mockingbird", "Harper Lee", "978-0061120084", 7.99, "A novel about racial injustice in the Deep South.");
		Book book3 = new Book(null, "1984", "George Orwell", "978-0451524935", 9.99, "A dystopian novel about totalitarianism and surveillance.");
		bookRepository.save(book1);
		bookRepository.save(book2);
		bookRepository.save(book3);
	}

}

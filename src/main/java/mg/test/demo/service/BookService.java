package mg.test.demo.service;

import mg.test.demo.dto.BookDto;
import mg.test.demo.exception.ResourceNotFoundException;
import mg.test.demo.model.Book;
import mg.test.demo.repository.BookRepository;

import java.util.stream.Collectors;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;


    public BookDto addBook(BookDto book) {
            return convertToDto(bookRepository.save(convertToEntity(book)));
    }

    private Book convertToEntity(BookDto book) {
        if(book == null) {
           return null;
        }
         return new Book(
            book.id(),
            book.title(),
            book.author(),
            book.isbn(),
            book.price(),
            book.description()
        );

    }

    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return convertToDto(book);
    }

public BookDto updateBook(Long id, BookDto updatedBook) {

    Book existingBook = bookRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Book not found with id: " + id));

    existingBook.setTitle(updatedBook.title());
    existingBook.setAuthor(updatedBook.author());
    existingBook.setIsbn(updatedBook.isbn());
    existingBook.setPrice(updatedBook.price());
    existingBook.setDescription(updatedBook.description());

    Book savedBook = bookRepository.save(existingBook);

    return convertToDto(savedBook);
}

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Page<BookDto> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(this::convertToDto);
    }

    private BookDto convertToDto(Book book) {
        return new BookDto(
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getIsbn(),
            book.getPrice(),
            book.getDescription()
        );
    }

}
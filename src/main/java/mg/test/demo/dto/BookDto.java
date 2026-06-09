package mg.test.demo.dto;

public record BookDto(
    Long id,
    String title,
    String author,
    String isbn,
    double price,
    String description
) {
}
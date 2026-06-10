package mg.test.demo.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Version;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(min = 3, max = 100,
          message = "Title must contain between 3 and 100 characters")
    @Column(nullable = false, unique = true)
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Size(min = 2, max = 50)
    private String author;

    @NotBlank(message = "ISBN is mandatory")
    @Pattern(
            regexp = "^(97(8|9))?\\d{9}(\\d|X)$",
            message = "Invalid ISBN format"
    )
    @Column(unique = true)
    private String isbn;

    @Positive(message = "Price must be positive")
    @Max(value = 1000,
         message = "Price cannot exceed 1000")
    private double price;

    @NotBlank(message = "Description is mandatory")
    @Size(min = 10, max = 1000)
    private String description;

    @NotNull(message = "Publication date is mandatory")
    @PastOrPresent(
            message = "Publication date cannot be in the future"
    )
    private LocalDate publicationDate;

    @NotBlank(message = "Category is mandatory")
    private String category;

    @Min(value = 1,
         message = "Page count must be at least 1")
    @Max(value = 10000,
         message = "Page count cannot exceed 10000")
    private int pageCount;

    @Email(message = "Invalid publisher email")
    private String publisherEmail;

    @NotNull(message = "Stock quantity is mandatory")
    @PositiveOrZero(
            message = "Stock quantity cannot be negative"
    )
    private Integer stockQuantity;

    @Column(nullable = false)
    private Boolean available;

    @Version
    private Long version;
}
package exercise.controller;

import java.util.List;

import exercise.dto.*;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    // BEGIN
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDTO>> index() {
        var books = bookService.getAll();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(books.size()))
                .body(books);
    }
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDTO> show(@PathVariable long id) {
        var book = bookService.findById(id);
        return ResponseEntity.ok()
                .body(book);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookCreateDTO bookCreateDTO) {
        var book = bookService.create(bookCreateDTO);
        return ResponseEntity.status(201)
                .body(book);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO update(@RequestBody @Valid BookUpdateDTO bookData, @PathVariable Long id) {
        var book = bookService.update(bookData, id);
        return book;
    }

    @DeleteMapping(path = "")
    @ResponseStatus(HttpStatus.GONE)
    public void delete(@PathVariable long id) {
        bookService.delete(id);
    }
    // END
}

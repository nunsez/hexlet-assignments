package exercise.service;


import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        var books = bookRepository.findAll();
        return books.stream()
            .map(bookMapper::map)
            .toList();
    }

    public BookDTO findById(Long id) {
        var books = bookRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Not found"));
        return bookMapper.map(books);
    }

    public BookDTO create(BookCreateDTO data) {
        var book = bookMapper.map(data);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDTO update(Long id, BookUpdateDTO data) {
        var book = bookRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Not found"));
        bookMapper.update(data, book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}

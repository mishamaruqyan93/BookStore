package am.itspace.bookstore.controller;

import am.itspace.bookstore.entity.Book;
import am.itspace.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/book/{id}")
    public String bookSinglePage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Book> bookId = bookService.findById(id);
        if (bookId.isEmpty()) {
            return "redirect:/";
        }
        modelMap.addAttribute("book", bookId.get());
        return "singleBook";
    }
}

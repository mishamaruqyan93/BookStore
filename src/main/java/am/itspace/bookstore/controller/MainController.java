package am.itspace.bookstore.controller;

import am.itspace.bookstore.entity.Book;
import am.itspace.bookstore.entity.User;
import am.itspace.bookstore.entity.UserRole;
import am.itspace.bookstore.sequrity.CurrentUser;
import am.itspace.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final BookService bookService;


    @GetMapping("/")
    public String mainPage(ModelMap modelMap) {
        List<Book> books = bookService.findLast20Books();
        modelMap.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getUserRole() == UserRole.ADMIN) {
                return "redirect:/admin";
            }
        }
        return "redirect:/";
    }
}


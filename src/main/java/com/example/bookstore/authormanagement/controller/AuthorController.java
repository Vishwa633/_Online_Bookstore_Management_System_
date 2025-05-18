package com.example.bookstore.authormanagement.controller;

import com.example.bookstore.authormanagement.model.Author;
import com.example.bookstore.authormanagement.model.GuestAuthor;
import com.example.bookstore.authormanagement.model.PermanentAuthor;
import com.example.bookstore.authormanagement.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "author_list";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("author", new GuestAuthor("", "", List.of(), ""));
        return "author_register";
    }

    @PostMapping("/register")
    public String registerAuthor(@RequestParam String name,
                                 @RequestParam String biography,
                                 @RequestParam String books,
                                 @RequestParam String type,
                                 @RequestParam(required = false) String affiliation,
                                 @RequestParam(required = false) String hireDate) {

        List<String> bookList = Arrays.asList(books.split(","));
        Author author;
        if (type.equals("GuestAuthor")) {
            author = new GuestAuthor(name, biography, bookList, affiliation != null ? affiliation : "");
        } else {
            author = new PermanentAuthor(name, biography, bookList, hireDate != null ? LocalDate.parse(hireDate) : LocalDate.now());
        }
        authorService.addAuthor(author);
        return "redirect:/authors";
    }

    @GetMapping("/edit/{name}")
    public String editAuthor(@PathVariable String name, Model model) {
        Author author = authorService.getAuthorByName(name);
        model.addAttribute("author", author);
        return "author_edit";
    }

    @PostMapping("/update")
    public String updateAuthor(@RequestParam String name,
                               @RequestParam String biography,
                               @RequestParam String books,
                               @RequestParam String type,
                               @RequestParam(required = false) String affiliation,
                               @RequestParam(required = false) String hireDate) {

        List<String> bookList = Arrays.asList(books.split(","));
        Author updated;
        if (type.equals("GuestAuthor")) {
            updated = new GuestAuthor(name, biography, bookList, affiliation != null ? affiliation : "");
        } else {
            updated = new PermanentAuthor(name, biography, bookList, hireDate != null ? LocalDate.parse(hireDate) : LocalDate.now());
        }
        authorService.updateAuthor(updated);
        return "redirect:/authors";
    }

    @GetMapping("/delete/{name}")
    public String deleteAuthor(@PathVariable String name) {
        authorService.deleteAuthor(name);
        return "redirect:/authors";
    }
}

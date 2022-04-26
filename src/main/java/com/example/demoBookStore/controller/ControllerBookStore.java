package com.example.demoBookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerBookStore {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    class Book {
        public Integer id;
        public String name;
        public String author;
        public String phone;
        public String condition;
        public Integer price;
        public String stName;
        public String email;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }

        public String getPhone() {
            return phone;
        }

        public String getCondition() {
            return condition;
        }

        public Integer getPrice() {
            return price;
        }

        public String getStName() {
            return stName;
        }

        public String getEmail() {
            return email;
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/search-results", method = RequestMethod.GET)
    public String searchResults(Model model, @RequestParam String search) {
        String pattern = '%' + search + '%';
        List<Book> books = jdbcTemplate.query(
        "SELECT * from allbooks WHERE book LIKE ? OR author LIKE ?",
                new Object[]{pattern, pattern},
                new int[] { Types.VARCHAR, Types.VARCHAR },
                (RowMapper<Book>) (rs, i) -> {
                    Book book = new Book();
                    book.id = rs.getInt("id");
                    book.name = rs.getString("book");
                    book.author = rs.getString("author");
                    book.condition = rs.getString("bookcond");
                    book.price = rs.getInt("price");
                    book.stName = rs.getString("stname");
                    book.email = rs.getString("email");
                    book.phone = rs.getString("phone");
                    return book;
                }
        );
        model.addAttribute("books", books);
        return "search-results";
    }

    @RequestMapping(value = "/bookadded", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("books")Book book,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
//        model.addAttribute("name", book.getName());
//        model.addAttribute("author", book.getAuthor());
//        model.addAttribute("condition", book.getCondition());
//        model.addAttribute("price", book.getPrice());
//        model.addAttribute("stname", book.getStName());
//        model.addAttribute("email", book.getEmail());
//        model.addAttribute("phone", book.getPhone());
        model.addAttribute("book", book);
        return "bookadded";
    }


}

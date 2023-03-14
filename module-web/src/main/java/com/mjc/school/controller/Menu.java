package com.mjc.school.controller;

import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsCreateDto;
import com.mjc.school.service.dto.NewsUpdateDto;
import com.mjc.school.service.exception.CustomException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Menu {
    private Map<Integer, MenuItem> menu = new LinkedHashMap<>();
    private NewsService service;

    public MenuItem getMenuItemById(int id) {
        return menu.get(id);
    }

    public Menu(NewsService service) {
        createMenu();
        this.service = service;
    }

    void addMenuItem(int index, String title, Consumer<Scanner> run) {
        menu.put(index, new MenuItem(index, title) {
            @Override
            public void run(Scanner sc) {
                run.accept(sc);
            }
        });
    }

    private void createMenu() {
        addMenuItem(1, "Get all news.", scanner -> {
            System.out.println(service.readAllDto());
            showMenu();
        });

        addMenuItem(2, "Get news by id.", scanner -> {
            System.out.println("Operation: Get news by id.\n" + "Enter news id:");
            Long newsId = scanner.nextLong();
            System.out.println(service.readByIdDto(newsId));
            showMenu();
        });

        addMenuItem(3, "Create news.", scanner -> {
            System.out.println("Operation: Create news.");
            System.out.println("Enter news title:");
            scanner.nextLine();
            String title = scanner.nextLine();
            System.out.println("Enter news content:");
            String content = scanner.nextLine();
            System.out.println("Enter author id:");
            int authorId = scanner.nextInt();
            try {
                System.out.println(service.createNewsDto(new NewsCreateDto(authorId, title, content)));
            } catch (CustomException e) {
                System.out.println("ERROR_CODE: " + e.getCode() + " ERROR_MESSAGE: " + e.getMessage());
            }
            showMenu();
        });

        addMenuItem(4, "Update news.", scanner -> {
            System.out.println("Operation: Update news.");
            System.out.println("Enter news id:");
            int newsId = scanner.nextInt();
            System.out.println("Enter news title:");
            scanner.nextLine();
            String title = scanner.nextLine();
            System.out.println("Enter news content:");
            String content = scanner.nextLine();
            System.out.println("Enter author id:");
            int authorId = scanner.nextInt();
            try {
                NewsUpdateDto dto = new NewsUpdateDto(newsId, title, content, authorId);
                System.out.println(service.updateNewsByIdDto(dto));
            } catch (CustomException e) {
                System.out.println("ERROR_CODE: " + e.getCode() + " ERROR_MESSAGE: " + e.getMessage());
            }
            showMenu();
        });

        addMenuItem(5, "Remove news by id.", scanner -> {
            System.out.println("Operation: Remove news by id.\n" + "Enter news id:");
            Long newsId = scanner.nextLong();
            System.out.println(service.deleteNewsByIdDto(newsId));
            showMenu();
        });

        addMenuItem(0, "Exit.", scanner -> {
        });
    }

    public void showMenu() {
        System.out.println("Enter the number of operation:");
        for (MenuItem item : menu.values()) {
            System.out.println(item.toString());
        }
    }
}

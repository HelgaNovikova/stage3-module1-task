package com.mjc.school.controller;

import com.mjc.school.repository.utils.CodedException;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.PieceOfNewsCreateDto;
import com.mjc.school.service.dto.PieceOfNewsUpdateDto;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

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

    private void createMenu() {
        menu.put(1, new MenuItem(1, "Get all news.") {
            @Override
            public void run(Scanner sc) {
                System.out.println(service.getAllNewsDto());
                showMenu();
            }
        });

        menu.put(2, new MenuItem(2, "Get news by id.") {
            @Override
            public void run(Scanner sc) {
                System.out.println("Operation: Get news by id.\n" + "Enter news id:");
                Long newsId = sc.nextLong();
                System.out.println(service.getNewsByIdDto(newsId));
                showMenu();
            }
        });

        menu.put(3, new MenuItem(3, "Create news.") {
            @Override
            public void run(Scanner sc) {
                System.out.println("Operation: Create news.");
                System.out.println("Enter news title:");
                sc.nextLine();
                String title = sc.nextLine();
                System.out.println("Enter news content:");
                String content = sc.nextLine();
                System.out.println("Enter author id:");
                int authorId = sc.nextInt();
                try {
                    System.out.println(service.createPieceOfNewsDto(new PieceOfNewsCreateDto(authorId, title, content)));
                } catch (CodedException e) {
                    System.out.println("ERROR_CODE: " + e.getCode() + " ERROR_MESSAGE: " + e.getMessage());
                }
                showMenu();
            }
        });

        menu.put(4, new MenuItem(4, "Update news.") {
            @Override
            public void run(Scanner sc) {
                System.out.println("Operation: Update news.");
                System.out.println("Enter news id:");
                int newsId = sc.nextInt();
                System.out.println("Enter news title:");
                sc.nextLine();
                String title = sc.nextLine();
                System.out.println("Enter news content:");
                String content = sc.nextLine();
                System.out.println("Enter author id:");
                int authorId = sc.nextInt();
                try {
                    PieceOfNewsUpdateDto dto = new PieceOfNewsUpdateDto(newsId, title, content, authorId);
                    System.out.println(service.updatePieceOfNewsByIdDto(dto));
                } catch (CodedException e) {
                    System.out.println("ERROR_CODE: " + e.getCode() + " ERROR_MESSAGE: " + e.getMessage());
                }
                showMenu();
            }
        });

        menu.put(5, new MenuItem(5, "Remove news by id.") {
            @Override
            public void run(Scanner sc) {
                System.out.println("Operation: Remove news by id.\n" + "Enter news id:");
                Long newsId = sc.nextLong();
                System.out.println(service.deleteNewsByIdDto(newsId));
                showMenu();
            }
        });

        menu.put(0, new MenuItem(0, "Exit.") {
            @Override
            public void run(Scanner sc) {
            }
        });
    }

    public void showMenu() {
        System.out.println("Enter the number of operation:");
        for (MenuItem item : menu.values()) {
            System.out.println(item.toString());
        }
    }
}

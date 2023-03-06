package com.mjc.school;

import com.mjc.school.controller.Menu;
import com.mjc.school.repository.impl.FileRepository;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.impl.NewsServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FileRepository repo = new FileRepository();
        repo.generateNews("news", "authors", "content");

        NewsService service = new NewsServiceImpl(repo);
        Menu menu = new Menu(service);
        menu.showMenu();
        Scanner sc = new Scanner(System.in);
        int userChoice = sc.nextInt();
        while (userChoice != 0){
            menu.getMenuItemById(userChoice).run(sc);
            userChoice = sc.nextInt();
        }
    }
}

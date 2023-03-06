package com.mjc.school;

import com.mjc.school.controller.Menu;
import com.mjc.school.repository.impl.FileRepository;
import com.mjc.school.repository.utils.DataSource;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.impl.NewsServiceImpl;
import com.mjc.school.service.utils.NewsValidator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DataSource ds = new DataSource();
        ds.generateNews("news", "authors", "content");
        FileRepository repo = new FileRepository(ds);
        NewsService service = new NewsServiceImpl(repo, new NewsValidator());
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

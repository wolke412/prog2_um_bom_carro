package br.feevale;

import br.feevale.views.pages.MainPage;

public class App {
    public static void main(String[] args) {
        
        PostgresClient client = new PostgresClient();
        
        new MainPage();
    }
}

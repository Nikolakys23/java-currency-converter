package com.github.panteliszara.currencyconverter;

import com.github.panteliszara.currencyconverter.ui.BootstrapView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class CurrencyConverterApp extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Java Currency Converter");
        stage.setScene(new Scene(BootstrapView.create(), 640, 400));
        stage.setMinWidth(480);
        stage.setMinHeight(300);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package com.github.panteliszara.currencyconverter.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public final class BootstrapView {

    private BootstrapView() {
    }

    public static VBox create() {
        Label title = new Label("Java Currency Converter");
        Label status = new Label("Application foundation ready. Currency conversion is not implemented yet.");
        status.setWrapText(true);

        VBox root = new VBox(16, title, status);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(32));
        return root;
    }
}

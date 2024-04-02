package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;

public class TitleScreen {
    GraphicsContext gc;
    GamePanel gp;
    Font Courier_New_40 = new Font("Courier New", 40);
    Font Courier_New_96_Bold = Font.font("Courier New", FontWeight.BOLD, 96);
    public TitleScreen(GamePanel gp) {
        this.gp = gp;
    }
    public void draw() {
        Color semiTransparentWhite = Color.web("#FFFFFF80");
        gc.setFill(semiTransparentWhite);
        Color borderColor = Color.web("#D1D5DB");
        double borderWidth = 1;
        gc.setStroke(borderColor);
        gc.setLineWidth(borderWidth);
        double borderRadius = 20;

        int boxMargin = 50;
        gc.fillRoundRect(boxMargin, boxMargin, gp.getScreenWidth()-boxMargin*2, gp.getScreenHeight()-boxMargin*2, borderRadius, borderRadius);
        gc.strokeRoundRect(boxMargin, boxMargin, gp.getScreenWidth()-boxMargin*2, gp.getScreenHeight()-boxMargin*2, borderRadius, borderRadius);
        gc.setFill(Color.WHITE);
        gc.setFont(Courier_New_96_Bold);
        String text = "Dekhere";
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);
        gc.setEffect(ds);
        gc.fillText(text, getXforcenteredText(text), 500);
        gc.setFont(Font.getDefault());
        gc.setEffect(null);

    }
    public void setGraphicsContext(GraphicsContext gc) {
        this.gc = gc;
    }
    private double getXforcenteredText(String text) {
        Text tempText = new Text(text);
        tempText.setFont(gc.getFont());
        double textWidth = tempText.getLayoutBounds().getWidth();
        return (gp.getScreenWidth() - textWidth) / 2;
    }
}
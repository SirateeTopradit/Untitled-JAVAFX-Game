package main;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
/**
 * KeyHandler class is responsible for handling the key inputs in the game.
 * This includes setting up the key codes, handling key press and key release events.
 */
public class KeyHandler {
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean ctrlOPressed;
    /**
     * Handles the key press events.
     *
     * @param e  the key event
     */
    public void keyPressed(KeyEvent e) {
        KeyCode code = e.getCode();
        if (code == KeyCode.W || code == KeyCode.UP) {
            setUpPressed(true);
        }
        if (code == KeyCode.S || code == KeyCode.DOWN) {
            setDownPressed(true);
        }
        if (code == KeyCode.A || code == KeyCode.LEFT) {
            setLeftPressed(true);
        }
        if (code == KeyCode.D || code == KeyCode.RIGHT) {
            setRightPressed(true);
        }
        KeyCombination ctrlO = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
        if (ctrlO.match(e)) {
            setCtrlOPressed(true);
        }
    }
    /**
     * Handles the key release events.
     *
     * @param e  the key event
     */
    public void keyReleased(KeyEvent e) {
        KeyCode code = e.getCode();
        if (code == KeyCode.W || code == KeyCode.UP) {
            setUpPressed(false);
        }
        if (code == KeyCode.S || code == KeyCode.DOWN) {
            setDownPressed(false);
        }
        if (code == KeyCode.A || code == KeyCode.LEFT) {
            setLeftPressed(false);
        }
        if (code == KeyCode.D || code == KeyCode.RIGHT) {
            setRightPressed(false);
        }
        KeyCombination ctrlO = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
        if (ctrlO.match(e)) {
            setCtrlOPressed(false);
        }
    }
    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }
    public boolean isCtrlOPressed() {
        return ctrlOPressed;
    }
    public void setCtrlOPressed(boolean ctrlOPressed) {
        this.ctrlOPressed = ctrlOPressed;
    }
}
package main;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler {
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean enterPressed;

    public boolean isEnterPressed() {
        return enterPressed;
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }

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
        if (code == KeyCode.ENTER) {
            setEnterPressed(true);
        }
    }

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
        if (code == KeyCode.ENTER) {
            setEnterPressed(false);
        }
    }

    public boolean isKeyPressed() {
        return (isUpPressed() || isDownPressed() || isLeftPressed() || isRightPressed());
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
}
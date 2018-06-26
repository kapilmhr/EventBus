package app.frantic.eventbus;

public class ButtonEvent {
    public String message;

    public ButtonEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package views;

public interface showMessage {
    default void showMessage(String type, String message) {
        String typeDecorator = "";
        if (type.equals("success")) {
            typeDecorator = "\u2714️";
        } else if (type.equals("error")) {
            typeDecorator = "\u274C️";
        } else if (type.equals("warning")) {
            typeDecorator = "\u26A0️";
        } else {
            typeDecorator = "?";
        }
        System.out.println("[" + typeDecorator + "] : " + message);
    }
}

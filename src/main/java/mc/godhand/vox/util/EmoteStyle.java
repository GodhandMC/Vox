package mc.godhand.vox.util;

public enum EmoteStyle {
    ALWAYS("All your roleplay chat will be emoted by default"),
    QUOTATIONS("Roleplay chat will be sent as an emote as long as you use a quotation mark (\")"),
    EXPLICIT("You must explicitly declare your intent to emote by starting chat with an asterix (*)");

    private final String description;

    EmoteStyle(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

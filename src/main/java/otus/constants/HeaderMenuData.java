package otus.constants;

public enum HeaderMenuData {
    STUDY("Обучение"),
    INFORMATION("Информация");

    private final String displayName;

    HeaderMenuData(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

public class WriteFile {
    private boolean isTest;
    private String path;

    //region Constructors
    public WriteFile() {}

    public WriteFile(boolean isTest, String path) {
        this.isTest = isTest;
        this.path = path;
    }
    //endregion

    //region Getters and setters
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    //endregion
}
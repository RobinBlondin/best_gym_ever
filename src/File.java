public class File {
    private String path;

    public File() {}

    public File(boolean testMode, String path) {
        this.path = path;
    }

    //region Getters / Setters
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    //endregion

    public void errorMessage(String message) {
        System.out.println(message);
    }
}

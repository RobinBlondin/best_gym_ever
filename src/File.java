public class File {
    private boolean isTest;
    private String path;

    public File() {}

    public File(boolean isTest, String path) {
        this.isTest = isTest;
        this.path = path;
    }

    //region Getters / Setters
    public boolean isTest() {
        return isTest;
    }

    public void setTest(boolean test) {
        isTest = test;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    //endregion

    protected void errorMessage(Exception e, String message) throws Exception {
        if(isTest) {
            throw e;
        } else {
            System.out.println(message);
        }
    }
}

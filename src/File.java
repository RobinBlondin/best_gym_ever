public class File {
    private boolean testMode;
    private String path;

    public File() {}

    public File(boolean testMode, String path) {
        this.testMode = testMode;
        this.path = path;
    }

    //region Getters / Setters
    public boolean testMode() {
        return testMode;
    }

    public void setTest(boolean test) {
        testMode = test;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    //endregion

    protected void errorMessage(Exception e, String message) throws Exception {
        if(testMode) {
            throw e;
        } else {
            System.out.println(message);
        }
    }
}

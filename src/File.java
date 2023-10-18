public abstract class File {
    private boolean testMode;
    private String path;

    public File() {}

    public File(boolean testMode, String path) {
        this.testMode = testMode;
        this.path = path;
    }

    //region Getters / Setters

    public boolean isTestMode() {
        return testMode;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    //endregion

    public void errorMessage(Exception e, String  message) throws Exception {
        if(testMode) {
            throw e;
        } else {
            System.out.println(message + e.getMessage());
            pause();
        }
    }

    public void pause() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press ENTER To continue...");
        sc.nextLine();
    }
}

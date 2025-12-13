public class AutoSaveThread extends Thread {

    private final String fileName;
    private final StudentManager manager;
    private final long intervalMs;
    private final Object fileLock;
    private volatile boolean running = true;

    public AutoSaveThread(String fileName, StudentManager manager, long intervalMs, Object fileLock) {
        this.fileName = fileName;
        this.manager = manager;
        this.intervalMs = intervalMs;
        this.fileLock = fileLock;
        setName("AutoSaveThread");
    }

    public void stopAutoSave() {
        running = false;
        interrupt();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(intervalMs);

                synchronized (fileLock) {

                    FileHandler.saveToFile(fileName, manager.getStudents());
                }

                System.out.println("[AutoSave] Saved successfully.");

            } catch (InterruptedException e) {
                if (!running) break;
                ErrorHandler.log("AutoSave interrupted", e);

            } catch (Exception e) {
                ErrorHandler.log("AutoSave unexpected error", e);
            }
        }

        System.out.println("[AutoSave] Stopped.");
    }
}


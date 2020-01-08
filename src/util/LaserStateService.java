package util;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import view.RootLayoutController;

public class LaserStateService extends Service<Void>
{
    private RootLayoutController rootLayoutController;
    private Logger logger;
    private boolean go;



    public LaserStateService(RootLayoutController rootLayoutController, Logger logger)
    {
        this.rootLayoutController = rootLayoutController;
        this.logger = logger;
    }


    @Override
    public Task<Void> createTask()
    {
        return new Task<Void> ()
        {
            @Override
            protected Void call() throws Exception
            {
                go = true;
                while(go)
                {

                    System.out.println(">>enter to loop");
                    rootLayoutController.displayLaserInfo();
                    System.out.println("middle of the loop");
                    logger.writeLog();                  // uncomment if you would like to write log-file


                    try {
                        Thread.sleep(1000);         // force the thread to sleep
                    } catch(InterruptedException e) {
                        System.out.println(e);
                    }

                    System.out.println("<<exit from loop\n");
                }
                return null;
            };
        };
    }


    public void finish()
    {
        go = false;
    }
}
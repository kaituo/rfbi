package edu.umass.cs.rfbi.util;

/**
 * @author Kaituo
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StreamGobbler extends Thread
{
    InputStream is;
    String type;
    Logger logger;

    public StreamGobbler(InputStream is, String type, Logger logger)
    {
        this.is = is;
        this.type = type;
        this.logger = logger;
    }



    @Override
    public void run()
    {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                logger.log(Level.FINE, type + ">" + line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}


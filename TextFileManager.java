import java.io.*;

public class TextFileManager
{
    /** Reader object to access the file */
    private BufferedReader reader = null;
    private BufferedWriter writer = null;

    /**
    * Open a text file for read, if possible. It will be closed
    * when we open a new file, or get to the end of the old one.
    * @param filename   File to open
    * @return true if successfully opened, false if not found.
    */
    public boolean openRead(String filename)
    {
        boolean bOk = true;
        try 
        {
            if(reader != null)
                reader.close();
        } 
        catch (IOException io) 
        {
            reader = null;
        }
        try 
        {
            reader = new BufferedReader(new FileReader(filename));
        } 
        catch (FileNotFoundException fnf) 
        {
            bOk = false;
            reader = null;
        }
        return bOk;
    }

    /**
    * Open a text file for write, if possible. It will be closed
    * when we open a new file, or get to the end of the old one.
    * @param filename   File to open
    * @return true if successfully opened, false if not found.
    */
    public boolean openWrite(String filename)
    {
        boolean bOk = true;
        try 
        {
            if(writer != null)
                writer.close();
        } 
        catch (IOException io) 
        {
            writer = null;
        }
        try 
        {
            writer = new BufferedWriter(new FileWriter(filename));
        } 
        catch (FileNotFoundException fnf) 
        {
            bOk = false;
            writer = null;
        }
        return bOk;
    }

    /** 
    * Try to read a line from the open file.
    * @return Line as a string, or null if an error occurred.
    */ 
    public String getNextLine()
    {
        String lineReader = null;
        try 
        {
            if(reader != null)  /* if reader is null, file is not open */
            {
                lineReader = reader.readLine();
                if(lineReader == null)  /* end of the file */
                {
                    reader.close();
                }
            }   /* end if reader not null */
        }
        catch (IOException ioe) 
        {
            lineReader = null;
        }
        return lineReader;
    }

    /** 
    * Try to wirte a line to the open file.
    * @param data Data to write in to file.
    * @return Line as a string, or null if an error occurred.
    */ 
    public boolean writeNextLine(String data)
    {
        boolean success = true;
        try 
        {
            if(writer != null)  /* if writer is null, file is not open */
            {
                writer.append(data);
                writer.newLine();
            }  
        }
        catch (IOException ioe) 
        {
            success = false;
        }
        return success;
    }

    /** 
     * Explicitly close the reader to free resources 
     */
    public void close()
    {
        try 
        {
            reader.close();
        } 
        catch (IOException ioe) 
        {
            
        }
    }
}
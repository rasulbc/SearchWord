import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler implements Runnable{
    private String keyword;
    private File myfile;
    private SearchHandler parent;
    int counter;
    Scanner scan;

    public FileHandler(SearchHandler x,File myfile, String key){
        parent = x;
        this.myfile = myfile;
        keyword = key;
        counter=0;
        try{
            scan = new Scanner(myfile);
        }catch(IOException a)
        {
            System.out.println("\nUnable to open the file at location "+myfile.getAbsolutePath()+"\n");
        }
    };
    public int product(){
        String line;
        String[] words;

        while (scan.hasNext()){

            line = scan.nextLine();
            words = line.split("[ X][ X]*");
            for (String word:words){
                if (word.equals(keyword))
                {

                    counter++;
                    System.out.println(word);
                }
            }
        }

        return counter;
    }

    public void run(){
        int s = product();
        synchronized (parent){
            parent.incrementWordCount(s);
        }


    }

}
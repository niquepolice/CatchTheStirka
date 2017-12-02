import sun.java2d.pipe.OutlineTextRenderer;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by 1 on 28.09.2017.
 */
public class UrlReader {
    public static final int bufSize = 80;
    private ArrayList<Byte> oldPage = new ArrayList<>();
    private ArrayList<Byte> newPage = new ArrayList<>();
    URL url;

    public UrlReader(String surl) throws Exception{
        url = new URL(surl);
    }

    public void readWrite(int days) throws Exception {

        int j = 0;

        int i;
        int pluses = 0;
        byte[] b = new byte[bufSize];

        Sound.play();
        while (true) {
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            int day = 0;
            int f = 1;
            while (true) {
                f = in.read(b);
                if (f < 0)
                    break;
                for (i = 0; i < bufSize; i++) {
                    if (b[i] == 43) {
                        pluses++;
                        if(pluses == 59) {
                            pluses = 0;
                            day++;
                            break;
                        }
                    }
                }
                if (day >= days + 1) {
                    for (int k = 0; k < i; k++) newPage.add(b[k]);
                    break;
                } else {
                    for (int k = 0; k < f; k++) newPage.add(b[k]);
                }

            }
            in.close();
           // System.out.println(day + " " + newPage.size() + " "+  oldPage.size());

            if (newPage.size() != oldPage.size()) {
                write();
                if (oldPage.size() > 0) {
                    System.out.println("!! " + oldPage.size() + " " + newPage.size());
                    write();
                    Sound.siren();
                }
            } else for (int k = 0; k < newPage.size(); k++) {
                if (!newPage.get(k).equals(oldPage.get(k))) {
                    System.out.println(k);
                    write();
                    Sound.siren();
                }
            }
            oldPage.clear();
            oldPage.addAll(newPage);
            newPage.clear();
            j++;
            System.out.println(j);
            Thread.sleep(10000);


        }
    }

        private void write() throws Exception{
            //System.out.println("writing");
            FileOutputStream oldf =  new FileOutputStream("oldfile.html");
            FileOutputStream newf =  new FileOutputStream("newfile.html");

            for (int i = 0; i < oldPage.size(); i++) {
                oldf.write(oldPage.get(i));
            }
            for (int i = 0; i < newPage.size(); i++) {
                newf.write(newPage.get(i));
            }
            oldf.close();
            newf.close();

        }


}

package com.example.IDEAAndroid;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: MT-ZJG
 * Date: 13-1-30
 * Time: 上午11:35
 * To change this template use File | Settings | File Templates.
 */
public class Server {

    public static void main(String args[]) {
        System.out.println();

        try {
            ServerSocket server = new ServerSocket(8888);
            Socket client = server.accept();
           final InputStream in = client.getInputStream();
            final OutputStream out = client.getOutputStream();

            new Thread(){
                public void run(){
                    while (true){
                        try{
                            byte bs[] = new byte[1024];
                            in.read(bs);
                            String str = new String(bs);
                            System.out.println(str);
                            out.write("Server send".getBytes());
                            out.flush();
                        }catch (Exception e)
                        {

                        }

                    }
                }
            }.start();




        } catch (Exception e) {
        }
    }


}

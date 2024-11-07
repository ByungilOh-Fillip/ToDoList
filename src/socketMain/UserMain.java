package socketMain;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class UserMain {
    public static void main(String[] args) {
        Socket socket = null;

        BufferedReader br = null;
        BufferedWriter bw = null;

        Scanner scanner = null;


        try {
            socket = new Socket("localhost", 2222);
            System.out.println("ToDo Server Connect Completed!");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            scanner = new Scanner(System.in);
            System.out.println("Please, press any key");

            while (true) {
                String requestUser = scanner.nextLine();
                StringBuilder content = new StringBuilder();

                bw.write(requestUser);
                bw.newLine();
                bw.flush();

                String line;
                while((line=br.readLine()) != null){
                    if(line.equals("EOF")){ break; }
                    content.append(line).append("\n");
                } // 여러줄 출력

                System.out.println(content);

                if (requestUser.equals("stop")){
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }

            } // 서버 계속 접속 상태 stop 시 종료
        } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
            if (br != null) try {
                br.close();
            } catch (IOException e) {
            }

            if (bw != null) try {
                bw.close();
            } catch (IOException e) {
            }

            if (socket != null) try {
                socket.close();
            } catch (IOException e) {
            }
        } // 이용한 서버 및 스트림 닫기.
    }
}

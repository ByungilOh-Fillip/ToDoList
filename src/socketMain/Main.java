package socketMain;

import model.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

// this class is one of todoList server for socket
public class Main {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;

        BufferedWriter bw = null;
        BufferedReader br = null;

        String_UI ui = new String_UI();
        TodoDAO dao = new TodoDAO();

        try {
            serverSocket = new ServerSocket(2222);
            socket = serverSocket.accept();
            System.out.println("User Connect Complete!");

            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            bw.write(ui.menu());
            bw.newLine();
            bw.flush();

            while(true) {
                String responseUser = br.readLine();
                if( responseUser.equals("stop") ) break;

                switch (responseUser) {
                    case "1":
                        bw.write(ui.selectToday() + "\nEOF\n");
                        bw.flush();
                        break;

                    case "2":
                        bw.write("todo의 날짜를 적어주세요 (ex : 2024-01-01)\nEOF\n");
                        bw.flush();
                        String createDate = br.readLine();

                        try {
                            bw.write(ui.selectDate(Date.valueOf(createDate)) + "\n내용을 입력해주세요!\nEOF\n");
                            bw.flush();
                            ui.insertTodo(createDate +"/"+br.readLine());
                            bw.write("입력이 완료되었습니다.\nEOF\n");
                        } catch (IllegalArgumentException e) {
                            bw.write("잘못된 날짜 형식입니다. yyyy-MM-dd 형식으로 입력해주세요.\nEOF\n");
                        }
                        bw.flush();
                        break;

                    case "3":
                        bw.write("변경을 희망하는 날짜를 적어주세요 (ex : 2024-01-01)\nEOF\n");
                        bw.flush();

                        try {
                            String updateDate = br.readLine();
                            bw.write(ui.selectDate(Date.valueOf(updateDate)) + "\n수정할 id와 content를 입력해주세요! (/로 구분해주세요)\nEOF\n");
                            bw.flush();
                            String[] updateIDAndContent = br.readLine().split("/");

                            if (updateIDAndContent.length == 2) {
                                dao.updateTodoContent(Integer.parseInt(updateIDAndContent[0]), updateIDAndContent[1]);
                                bw.write("변경이 완료되었습니다.\nEOF\n");
                            } else {
                                bw.write("입력 형식이 잘못되었습니다. id와 content를 /로 구분하여 입력해주세요.\nEOF\n");
                            }
                        } catch (IllegalArgumentException e) {
                            bw.write("잘못된 날짜 형식입니다. yyyy-MM-dd 형식으로 입력해주세요.\nEOF\n");
                        }
                        bw.flush();
                        break;

                    case "4":
                        bw.write("삭제를 희망하는 날짜를 적어주세요 (ex : 2024-01-01)\nEOF\n");
                        bw.flush();

                        try {
                            String deleteDate = br.readLine();
                            bw.write(ui.selectDate(Date.valueOf(deleteDate)) + "\n삭제할 id를 입력해주세요.\nEOF\n");
                            bw.flush();
                            int deleteId = Integer.parseInt(br.readLine());
                            dao.deleteTodo(deleteId);
                            bw.write("삭제가 완료되었습니다.\nEOF\n");
                        } catch (NumberFormatException e) {
                            bw.write("잘못된 id 형식입니다. 정수를 입력해주세요.\nEOF\n");
                        } catch (IllegalArgumentException e) {
                            bw.write("잘못된 날짜 형식입니다. yyyy-MM-dd 형식으로 입력해주세요.\nEOF\n");
                        }
                        bw.flush();
                        break;

                    case "5":
                        bw.write("개발자에게 하고 싶으신 말을 남겨주세요.\nEOF\n");
                        bw.flush();

                        MassageDAO massageDAO = new MassageDAO();
                        massageDAO.sendMassage(br.readLine());
                        bw.write("전송이 완료되었습니다.\nEOF\n");
                        bw.flush();
                        break;

                    default:
                        bw.write("입력에 맞는 기능이 없습니다.\n다시 입력해주세요 : \nEOF\n");
                        bw.flush();
                        break;
                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {

            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                }
            }

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }

            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                }
            }

        }
    }
}
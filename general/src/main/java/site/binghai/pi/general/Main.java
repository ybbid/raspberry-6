package site.binghai.pi.general;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.model.Message;
import site.binghai.pi.common.enums.CommandType;
import site.binghai.pi.common.queue.Command;
import site.binghai.pi.general.cmd.CmdPoster;
import site.binghai.pi.general.cmd.SubClientListener;


import java.util.Scanner;

/**
 * Created by binghai on 2017/12/4.
 *
 * @ raspberry
 */
public class Main {
    public static void main(String[] args) {
        SubClientListener listener = new SubClientListener();
        listener.start();

        CommandType commandType = CommandType.NORMAL;

        CmdPoster poster = new CmdPoster();

        Scanner sc = new Scanner(System.in);
        String line;
        while ((line = sc.nextLine()) != null) {
            Command command = new Command(commandType, line);
            Message message = poster.publish(JSONObject.toJSONString(command));
            System.out.println("SUCCESS:" + message.getMessageId());
        }
        sc.close();
    }
}
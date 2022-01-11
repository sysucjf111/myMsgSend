import com.sun.javafx.image.BytePixelSetter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.Scanner;

public class MsgSend {
    public static void main(String[] args) {
        startSendMsg(args);
    }

    private static void startSendMsg(String[] args) {
        if (args == null || args.length == 0){
            System.out.println("未输入任何运行参数，请重新输入。");
            return;
        }
        int index = -1;
        for (int i = 0;i<args.length;i++) {
            if ("-phone".equals(args[i])){
                index = i;
                break;
            }
        }
        if (index == -1){
            System.out.println("未输入手机号码的运行参数或者手机号码不在在-phone之后，请重新输入。");
            return;
        }else if (index+1>=args.length){
            System.out.println("未输入手机号码，请重新输入。");
            return;
        }
        SendMsg(args[index+1]);
    }

    private static void SendMsg(String mobileNumber) {
        System.out.println("向号码"+mobileNumber+"发信");
        String result = null;
        //确定目标地址——这个一天只能用一次
        String url="https://user.daojia.com/mobile/getcode?mobile=18312674405";
        //创建httpclient客户端对象
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //创建请求类型
        HttpGet httpGet=new HttpGet(url);
        HttpResponse httpResponse = null;
        //发起http请求
        try {
            httpResponse=httpClient.execute(httpGet);
            //判断状态码是否为200
            System.out.println("状态码为:"+httpResponse.getStatusLine().getStatusCode());
            HttpEntity entity = httpResponse.getEntity();
            System.out.println("响应内容为:" + EntityUtils.toString(entity));
        } catch (IOException e) {
            System.out.println("http请求执行错误");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

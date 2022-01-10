import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class MsgSend {
    public static void main(String[] args) {

        SendMsg("13660021070");

    }

    private static void SendMsg(String mobileNumber) {
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
            System.out.println(httpResponse.getStatusLine().getStatusCode());
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

import com.feign.response.UserResponse;

/**
 * Created by Administrator on 2018/06/11.
 */
public class TestResponse {
    public static void main(String[] args) {
        UserResponse response = new UserResponse();
        response.globalTicket();
        response.errT();
        response.logBizData();
        response.monitorTrackId();
        response.returnCode();
        response.returnMsg();
        response.responseVo();
        response.responseSuccess();
    }
}

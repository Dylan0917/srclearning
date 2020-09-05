package yu.quartzjob;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import yu.dao.ResultUtils;
import yu.weixin.GetAccessToken;
import yu.weixin.HttpUtil;
import yu.weixin.WeiXinConst;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/8/20 17:31
 */

public class SendWeixinJob {

    GetAccessToken getAccessToken;
    DruidDataSource dataSource;
    ResultUtils resultUtils = new ResultUtils();
    public void execute(){

        String getSql = "SELECT *FROM NEWSINFO WHERE SETTIMES < 2";
        String upSql = "UPDATE NEWSINFO SET SETTIMES = SETTIMES + 1 WHERE ID = ?";
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String token = getAccessToken.getToken();
            connection = dataSource.getConnection();
            resultUtils.setSql(getSql);
            ps = resultUtils.createPreparedStatement(connection);
            rs = ps.executeQuery();
            List<Map<String, Object>> sendInfos = ResultUtils.resultSetToListMap(rs);
            for (int i = 0; i < sendInfos.size(); i++) {
                Map<String, Object> bean = sendInfos.get(i);
                System.out.println(bean.get("CONTENT"));
                sendMsg(WeiXinConst.SEND_MSG_URL_01+"access_token="+ token,
                        bean.get("CONTENT") == null ? "":bean.get("CONTENT").toString(),"@all","1");
                resultUtils.setSql(upSql);
                ps = resultUtils.createPreparedStatement(connection);
                ps.setString(1,bean.get("ID").toString());
                ps.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
    public String sendMsg(String url,String msg,String userID,String partId) throws IOException {
        JSONObject jsonMsgObject = new JSONObject();
        jsonMsgObject.put("content",msg);
        JSONObject mainObj = new JSONObject();
        mainObj.put("touser",userID);
        mainObj.put("toparty",partId);
        mainObj.put("msgtype","text");
        mainObj.put("agentid","1000003");
        mainObj.put("text",jsonMsgObject);
        mainObj.put("safe","0");
        mainObj.put("enable_id_trans","0");
        mainObj.put("enable_duplicate_check","0");
        String ret = HttpUtil.send(url,mainObj,"utf-8");
        return ret;
    }
    public GetAccessToken getGetAccessToken() {
        return getAccessToken;
    }

    public void setGetAccessToken(GetAccessToken getAccessToken) {
        this.getAccessToken = getAccessToken;
    }

    public DruidDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DruidDataSource dataSource) {
        this.dataSource = dataSource;
    }
}

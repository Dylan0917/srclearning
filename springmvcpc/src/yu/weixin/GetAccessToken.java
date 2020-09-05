package yu.weixin;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import yu.bean.WxMsg;
import yu.dao.ResultUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/2/29 11:49
 */
public class GetAccessToken {

      ResultUtils ru;
      DruidDataSource druidDataSource;
    /**
     * 获取生效的token
     * @return
     */
    public String getToken(){
        //1.查询数据库
        String sql = "SELECT * FROM WXMSG WHERE TTIME = (SELECT MAX(TTIME) FROM WXMSG)";
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String accessToken = null;
        try {
            connection = druidDataSource.getConnection();
            ru.setSql(sql);
            ps = ru.createPreparedStatement(connection);
            rs = ps.executeQuery();
            List list = ResultUtils.resultSetToListBean(rs, WxMsg.class);
            if (list.size() > 0){
                long curMills = System.currentTimeMillis();
                WxMsg msg = (WxMsg) list.get(0);
                long expireMills = Long.valueOf(msg.getExpiresin()) * 1000;
                long betweenMills = curMills - Long.valueOf(msg.getTtime());
                if (betweenMills < expireMills){
                    return msg.getTmsg();
                }
            }
        //2.从网络获取
        String url = " https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        String method = "GET";
        url = url + "?" + WeiXinConst.TOKEN_GET_PARAM;
        String retmsg = HttpUtil.httpsRequest(url,method,null);
        JSONObject jsonObject = JSON.parseObject(retmsg);
        int errorcode = Integer.parseInt(jsonObject.get("errcode").toString()) ;
        accessToken = (String) jsonObject.get("access_token");
        String expiresIn = jsonObject.get("expires_in").toString();
        String id = UUID.randomUUID().toString().toUpperCase().replace("-","");
        String insertSql = "INSERT INTO WXMSG VALUES(?,?,?,?)";
        if (errorcode!= 0){
            return null;
        }
        ru.setSql(insertSql);
            ps = ru.createPreparedStatement(connection);
            ps.setString(1,id);
            ps.setString(2,accessToken);
            ps.setString(3,String.valueOf(System.currentTimeMillis()));
            ps.setString(4,expiresIn);
            ps.executeUpdate();
        } catch (Exception e) {
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
        return accessToken;
    }


    public ResultUtils getRu() {
        return ru;
    }

    public void setRu(ResultUtils ru) {
        this.ru = ru;
    }

    public DruidDataSource getDruidDataSource() {
        return druidDataSource;
    }

    public void setDruidDataSource(DruidDataSource druidDataSource) {
        this.druidDataSource = druidDataSource;
    }
}

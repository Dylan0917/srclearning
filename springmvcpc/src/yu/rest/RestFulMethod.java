package yu.rest;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yu.bean.PachongBean;
import yu.dao.ResultUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/8/20 10:46
 */
@RestController
@RequestMapping("/rest")
public class RestFulMethod {
    @Autowired
    private DruidDataSource dataSource;
    @Autowired
    private ResultUtils resultUtils;
    @RequestMapping(value = "test",method = RequestMethod.POST)
     public String test(@RequestBody String pachongBean){
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dataSource.getConnection();
            JSONObject obj = JSONObject.parseObject(pachongBean);
            PachongBean bean = JSONObject.toJavaObject(obj,PachongBean.class);
            String sql = "SELECT COUNT(1) CNT FROM NEWSINFO WHERE TIMESTR = ?";
            resultUtils.setSql(sql);
            ps = resultUtils.createPreparedStatement(connection);
            ps.setString(1,bean.getTimeStr());
            rs = ps.executeQuery();
            List cntList = ResultUtils.resultSetToListMap(rs);
            Map cntMap = (Map) cntList.get(0);
            int cnt = Integer.parseInt(cntMap.get("CNT").toString());
            if (cnt == 0){ //插入数据
                String id = UUID.randomUUID().toString().toUpperCase().replace("-","");
                String inSql = "INSERT INTO NEWSINFO VALUES(?,?,?,?)"; //ID,timestr,content,settimes
                resultUtils.setSql(inSql);
                ps = resultUtils.createPreparedStatement(connection);
                ps.setString(1,id);
                ps.setString(2,bean.getTimeStr());
                ps.setString(3,bean.getContent());
                ps.setInt(4,0);
                ps.executeUpdate();
                System.out.println("插入一条数据");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
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
        return "success";
     }
}

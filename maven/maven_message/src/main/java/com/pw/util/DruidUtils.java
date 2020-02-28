package com.pw.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DruidUtils {
    private static DataSource ds=null;
    static{
        //以字节流的方式打开配置文件
        InputStream is = DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties props = new Properties();
        try {
            props.load(is);//把配置信息读到Properties对象中
            ds = DruidDataSourceFactory.createDataSource(props);//根据配置文件的信息创建数据源(连接池)对象
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对外返回创建出来的数据源
     * @return
     */
    public static DataSource getDs(){
        return ds;
    }
}

package edu.fzu.zhishe.reservesystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * @author xjliang
 */
@Component
public class DateUtil {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");

    public String format(Date date) {
        return formatter.format(date);
    }
}

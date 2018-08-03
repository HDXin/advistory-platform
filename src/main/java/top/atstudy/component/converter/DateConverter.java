package top.atstudy.component.converter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-08-02
 * Time: 18:28
 */
public class DateConverter implements Converter<String, Date> {
    private static Logger logger = LoggerFactory.getLogger(DateConverter.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String DATE_TIME_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_TIME_FORMAT_3 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String DATE_TIME_FORMAT_4 = "MMddHHmmssSSS";
    private static final String DATE_TIME_FORMAT_5 = "yyyy-MM";
    private static final String DATE_TIME_FORMAT_6 = "HH:mm";
    private static final String DATE_TIME_FORMAT_7 = "yyyy-MM-dd HH:mm";
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Pattern DATE_TIME_PATTERN_1 = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}[T]\\d{2}:\\d{2}:\\d{2}$");
    private static final Pattern DATE_TIME_PATTERN_2 = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$");
    private static final Pattern DATE_TIME_PATTERN_3 = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{3}[Z]$");
    private static final Pattern DATE_TIME_PATTERN_4 = Pattern.compile("^\\d{11}$");
    private static final Pattern DATE_TIME_PATTERN_5 = Pattern.compile("^\\d{4}-\\d{2}$");
    private static final Pattern DATE_TIME_PATTERN_6 = Pattern.compile("^\\d{2}:\\d{2}$");
    private static final Pattern DATE_TIME_PATTERN_7 = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}$");

    @Override
    public Date convert(String dateString) {
        Date date = null;
        if (!StringUtils.isBlank(dateString)) {
            try {
                if (DATE_PATTERN.matcher(dateString).find()) {
                    date = new SimpleDateFormat(DATE_FORMAT).parse(dateString);
                } else if (DATE_TIME_PATTERN_1.matcher(dateString).find()) {
                    date = new SimpleDateFormat(DATE_TIME_FORMAT_1).parse(dateString);
                } else if (DATE_TIME_PATTERN_2.matcher(dateString).find()) {
                    date = new SimpleDateFormat(DATE_TIME_FORMAT_2).parse(dateString);
                } else if (DATE_TIME_PATTERN_5.matcher(dateString).find()) {
                    date = new SimpleDateFormat(DATE_TIME_FORMAT_5).parse(dateString);
                }else if (DATE_TIME_PATTERN_6.matcher(dateString).find()) {
                    date = new SimpleDateFormat(DATE_TIME_FORMAT_6).parse(dateString);
                }else if (DATE_TIME_PATTERN_7.matcher(dateString).find()) {
                    date = new SimpleDateFormat(DATE_TIME_FORMAT_7).parse(dateString);
                }
            } catch (ParseException e) {
                logger.debug("Exception: {}", e);
            }
        }

        return date;
    }
}

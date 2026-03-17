package cn.kmbeast.utils;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import cn.kmbeast.pojo.vo.ChartVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间工具类
 */
public class DateUtil {

    /**
     * 构造时间查询器，即指定的开始时间、结束时间
     *
     * @param days 时间范围
     * @return PagerDTO
     */
    public static QueryDto startAndEndTime(Integer days) {
        // 查全部
        if (days == -1) {
            return new QueryDto();
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextDayStart = now.minusDays(days).plusDays(1).with(LocalTime.of(0, 0)); // 下一天的开始时间
        LocalDateTime daysAgoEnd = nextDayStart.minusSeconds(1);
        return QueryDto.builder().startTime(daysAgoEnd).endTime(now).build();
    }

    /**
     * 统计指定天数内的记录数
     *
     * @param dayRange 往前查多少天
     * @param dates    数据源
     * @return Map<String, Integer>
     */
    public static List<ChartVO> countDatesWithinRange(Integer dayRange, List<LocalDateTime> dates) {
        LocalDate startDate = LocalDate.now().minusDays(dayRange);
        List<ChartVO> chartVOS = new ArrayList<>();
        for (int offset = 0; offset <= dayRange; offset++) {
            LocalDate currentDate = startDate.plusDays(offset);
            String dateKey = String.format("%02d月%02d日", currentDate.getMonthValue(), currentDate.getDayOfMonth());
            int count = (int) dates.stream()
                    .filter(dateTime -> dateTime.toLocalDate().equals(currentDate))
                    .count();
            //chartVOS.add(new ChartVO(dateKey, count));
            // 只统计不为0的数据
            if (count != 0){
                chartVOS.add(new ChartVO(dateKey, count));
            }
        }
        return chartVOS;
    }

    /**
     * 将日期字符串和时间段组合成一个 LocalDateTime 对象，并根据结束时间进行年份调整。
     *
     * @param dateStr 日期字符串，格式为 "12月31日"
     * @param timeRange 时间段字符串，格式为 "9:00-10:00"
     * @param referenceTime 用于比较的参考时间，默认可以是当前时间
     * @return 组合后的 LocalDateTime 对象，仅包含时间段中的结束时间
     */
    public static LocalDateTime createLocalDateTime(String dateStr, String timeRange, LocalDateTime referenceTime) {
        // 解析日期字符串为月份和日期
        Pattern pattern = Pattern.compile("(\\d+)月(\\d+)日");
        Matcher matcher = pattern.matcher(dateStr);
        if (!matcher.find()) {
            throw new IllegalArgumentException("日期格式不正确，请使用 'xx月xx日' 格式。");
        }
        int month = Integer.parseInt(matcher.group(1));
        int day = Integer.parseInt(matcher.group(2));
        // 解析时间段字符串为结束时间
        String endTimeStr = timeRange.split("-")[1].trim();
        LocalTime endTime = LocalTime.parse(endTimeStr, DateTimeFormatter.ofPattern("H:mm"));
        // 创建初始的 LocalDate 和 LocalDateTime 对象
        LocalDate localDate = LocalDate.of(referenceTime.getYear(), month, day);
        LocalDateTime dateTime = LocalDateTime.of(localDate, endTime);
        // 检查是否需要调整到下一年
        if (dateTime.isBefore(referenceTime)) {
            dateTime = dateTime.plusYears(1);
        }
        System.out.println("数据库预约时间：" + dateTime);
        return dateTime;
    }

}

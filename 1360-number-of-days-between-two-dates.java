class Solution {
    private static final int YEAR_BASE = 1968;

    private static final int[] DAYS_IN_MONTH = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

    private static final int[] DAYS_IN_MONTH_LEAP_YEAR = new int[]{0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366};

    private static final int DAYS_IN_YEAR = 365;
    private static final int DAYS_IN_LEAP_YEAR = 366;

    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(calculateDay(date2) - calculateDay(date1));
    }

    private int calculateDay(String date) {
        final int year = Integer.parseInt(date.substring(0, 4));
        final int month = Integer.parseInt(date.substring(5, 7));
        final int day = Integer.parseInt(date.substring(8, 10));

        final var basedYear = year - YEAR_BASE;
        final int leapYearsSoFar = ((basedYear - 1) / 4);
        final var daysFromYear = leapYearsSoFar * DAYS_IN_LEAP_YEAR + (basedYear - leapYearsSoFar) * DAYS_IN_YEAR;

        final var isCurrentYearLeap = year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0);
        final var daysFromMonth = isCurrentYearLeap ? DAYS_IN_MONTH_LEAP_YEAR[month - 1] : DAYS_IN_MONTH[month - 1];

        return daysFromYear + daysFromMonth + day;
    }
}
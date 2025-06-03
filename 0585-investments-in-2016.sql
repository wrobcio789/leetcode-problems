SELECT ROUND(SUM(ins.tiv_2016), 2) AS tiv_2016
FROM Insurance ins
WHERE (lat, lon) IN (
        SELECT lat, lon
        FROM Insurance
        GROUP BY lat, lon
        HAVING COUNT(pid) = 1
    )
    AND tiv_2015 IN (
        SELECT tiv_2015
        FROM Insurance
        GROUP BY tiv_2015
        HAVING COUNT(pid) > 1
    )
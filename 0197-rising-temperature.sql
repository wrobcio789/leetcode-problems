SELECT a.id
FROM Weather a
    INNER JOIN Weather b ON DATE_SUB(a.recordDate, INTERVAL 1 DAY) = b.recordDate
WHERE a.temperature > b.temperature;
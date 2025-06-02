SELECT *
FROM (
    SELECT 
        DISTINCT visited_on,
        SUM(amount) OVER week_window AS amount,
        ROUND((SUM(amount) OVER week_window) / 7, 2) AS average_amount
    FROM Customer
    WINDOW week_window AS (
        ORDER BY visited_on
        RANGE BETWEEN INTERVAL 6 DAY PRECEDING AND current ROW
    )
    ORDER BY visited_on) all_results
WHERE visited_on >= (
    SELECT DATE_ADD(MIN(visited_on), INTERVAL 6 DAY)
    FROM Customer
)
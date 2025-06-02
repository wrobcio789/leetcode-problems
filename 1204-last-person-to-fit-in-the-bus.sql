SELECT person_name
FROM (
    SELECT
        person_name,
        SUM(weight) OVER (ORDER BY turn) AS cum_weight
    FROM Queue) q_cum_weight
WHERE cum_weight <= 1000
ORDER BY cum_weight DESC
LIMIT 1
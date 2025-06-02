SELECT 
    current.id,
    (
        CASE
        WHEN current.id % 2 = 0 THEN prev.student
        WHEN next.student IS NOT NULL THEN next.student
        ELSE current.student END
    ) as student
FROM Seat current
LEFT JOIN Seat next
    ON current.id + 1 = next.id
LEFT JOIN Seat prev
    ON current.id - 1 = prev.id    



-- Alternative:

SELECT
    (
        CASE
        WHEN id % 2 = 0 THEN id - 1
        WHEN id = (SELECT COUNT(*) FROM Seat) THEN id
        ELSE id + 1 END
    ) AS id,
    student
FROM Seat
ORDER BY id ASC

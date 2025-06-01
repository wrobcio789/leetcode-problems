-- MySQL

Create table If Not Exists Logs (id int, num int)


SELECT DISTINCT(num) AS ConsecutiveNums
FROM (
    SELECT num,
        LEAD(num, 1) OVER (
            ORDER BY id
        ) as nextNum,
        LEAD(num, 2) OVER (
            ORDER BY id
        ) as nextNum2
    FROM Logs
) NumsWithFollowings
WHERE num = nextNum AND num = nextNum2;
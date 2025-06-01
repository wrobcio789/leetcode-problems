-- MySQL

Create table If Not Exists Employee (Id int, Salary int)

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    DECLARE OFFSET_VAL INT;
    SET OFFSET_VAL = N - 1;
    RETURN (
        SELECT DISTINCT(salary)
        FROM Employee
        ORDER BY salary DESC
        LIMIT 1 OFFSET OFFSET_VAL
    );
END
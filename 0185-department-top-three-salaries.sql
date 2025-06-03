SELECT
    dp.name AS Department,
    emp.name AS Employee,
    emp.salary AS salary
FROM Employee emp
    INNER JOIN Department dp
    ON emp.departmentId = dp.id
WHERE (emp.departmentId, emp.salary) IN (
    SELECT departmentId, salary
    FROM (
        SELECT
            departmentId,
            salary,
            DENSE_RANK() OVER(PARTITION BY departmentId ORDER BY salary DESC) AS rank_level
        FROM Employee) ranked_salaries
    WHERE rank_level <= 3
)
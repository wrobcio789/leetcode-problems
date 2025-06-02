SELECT
    emps.employee_id,
    emps.name,
    managers.suboardinate AS reports_count,
    ROUND(managers.suboardinate_age, 0) AS average_age
FROM Employees emps
INNER JOIN (
    SELECT reports_to AS manager_id, COUNT(employee_id) AS suboardinate, AVG(age) AS suboardinate_age
    FROM Employees
    GROUP BY reports_to
) managers ON emps.employee_id = managers.manager_id
ORDER BY employee_id

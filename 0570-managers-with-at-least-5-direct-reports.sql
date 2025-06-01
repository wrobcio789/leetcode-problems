SELECT Employee.name
FROM Employee
INNER JOIN (
    SELECT managerId
    FROM Employee
    GROUP BY managerId
    HAVING COUNT(id) >= 5) managers
ON managers.managerId = Employee.id

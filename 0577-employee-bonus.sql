SELECT emp.name, bonus.bonus
FROM Employee emp
    LEFT JOIN Bonus bonus ON emp.empId = bonus.empId
WHERE bonus.bonus < 1000 OR bonus.bonus IS NULL
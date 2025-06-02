SELECT all_categories.category, COUNT(cats.category) AS accounts_count
FROM (
    SELECT 'Low Salary' AS category
    UNION ALL
    SELECT 'Average Salary'
    UNION ALL
    SELECT 'High Salary'
) all_categories
LEFT JOIN (
    SELECT CASE
        WHEN income < 20000 THEN 'Low Salary'
        WHEN income <= 50000 THEN 'Average Salary'
        ELSE 'High Salary'
    END as category
    FROM Accounts) cats
ON all_categories.category = cats.category
GROUP BY all_categories.category
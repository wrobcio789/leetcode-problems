SELECT
    CONCAT(EXTRACT(YEAR FROM trans_date), '-', LPAD(EXTRACT(MONTH FROM trans_date), 2, '0')) AS month,
    country,
    COUNT(id) AS trans_count,
    SUM(state = 'approved') as approved_count,
    SUM(amount) as trans_total_amount,
    SUM((state = 'approved') * amount) as approved_total_amount
FROM Transactions
GROUP BY month, country
SELECT Customer.customer_id
FROM Customer
GROUP BY Customer.customer_id
HAVING COUNT(DISTINCT Customer.product_key) = (SELECT COUNT(*) FROM Product)
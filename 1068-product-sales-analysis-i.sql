SELECT product.product_name, sales.year, sales.price
FROM Sales sales
INNER JOIN Product product ON product.product_id = sales.product_id
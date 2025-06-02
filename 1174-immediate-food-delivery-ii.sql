
SELECT ROUND(100 * AVG(delivery.order_date = delivery.customer_pref_delivery_date), 2) AS immediate_percentage
FROM Delivery delivery
    INNER JOIN (
        SELECT customer_id, MIN(order_date) AS first_order_date
        FROM Delivery
        GROUP BY customer_id) first_order
    ON delivery.customer_id = first_order.customer_id
WHERE delivery.order_date = first_order.first_order_date



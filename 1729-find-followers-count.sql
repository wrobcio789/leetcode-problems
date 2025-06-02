SELECT user_id, COUNT(USER_ID) AS followers_count
FROM Followers
GROUP BY user_id
ORDER BY user_id ASC
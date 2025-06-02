(
    SELECT users.name AS results
    FROM Users users
        INNER JOIN MovieRating rating
        ON users.user_id = rating.user_id
    GROUP BY users.user_id
    ORDER BY COUNT(rating.movie_id) DESC, users.name ASC
    LIMIT 1
)
UNION ALL
(
    SELECT movies.title
    FROM Movies movies
        INNER JOIN MovieRating rating
        ON movies.movie_id = rating.movie_id
    WHERE YEAR(rating.created_at) = 2020 AND MONTH(rating.created_at) = 2
    GROUP BY movies.movie_id
    ORDER BY AVG(rating.rating) DESC, movies.title ASC
    LIMIT 1
)
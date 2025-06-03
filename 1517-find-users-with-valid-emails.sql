SELECT *
FROM Users
WHERE mail REGEXP '^[a-zA-Z][\\w\\.\\-\\_]*@leetcode\\.com$'

-- CREATE TABLES
CREATE TABLE Users (
  user_id INT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  city VARCHAR(100) NOT NULL,
  registration_date DATE NOT NULL
);

CREATE TABLE Events (
  event_id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  description TEXT,
  city VARCHAR(100) NOT NULL,
  start_date DATETIME NOT NULL,
  end_date DATETIME NOT NULL,
  status ENUM('upcoming', 'completed', 'cancelled'),
  organizer_id INT,
  FOREIGN KEY (organizer_id) REFERENCES Users(user_id)
);

CREATE TABLE Sessions (
  session_id INT PRIMARY KEY AUTO_INCREMENT,
  event_id INT,
  title VARCHAR(200) NOT NULL,
  speaker_name VARCHAR(100) NOT NULL,
  start_time DATETIME NOT NULL,
  end_time DATETIME NOT NULL,
  FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE Registrations (
  registration_id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  event_id INT,
  registration_date DATE NOT NULL,
  FOREIGN KEY (user_id) REFERENCES Users(user_id),
  FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE Feedback (
  feedback_id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  event_id INT,
  rating INT CHECK (rating BETWEEN 1 AND 5),
  comments TEXT,
  feedback_date DATE NOT NULL,
  FOREIGN KEY (user_id) REFERENCES Users(user_id),
  FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE Resources (
  resource_id INT PRIMARY KEY AUTO_INCREMENT,
  event_id INT,
  resource_type ENUM('pdf', 'image', 'link'),
  resource_url VARCHAR(255) NOT NULL,
  uploaded_at DATETIME NOT NULL,
  FOREIGN KEY (event_id) REFERENCES Events(event_id)
);



-- INSERT DATA

INSERT INTO Users (user_id, full_name, email, city, registration_date) VALUES
(1, 'Nina Patel', 'nina.patel@example.com', 'Seattle', '2025-01-10'),
(2, 'Rajiv Menon', 'rajiv.menon@example.com', 'Austin', '2025-01-12'),
(3, 'Sara Kim', 'sara.kim@example.com', 'Denver', '2025-02-03'),
(4, 'Leo Martinez', 'leo.m@example.com', 'Boston', '2025-02-10'),
(5, 'Fatima Noor', 'fatima.noor@example.com', 'Atlanta', '2025-03-01');

INSERT INTO Events (event_id, title, description, city, start_date, end_date, status, organizer_id) VALUES
(1, 'Cloud Tech Summit', 'Exploring cloud infrastructure and services.', 'Seattle', '2025-08-05 09:00:00', '2025-08-05 17:00:00', 'upcoming', 1),
(2, 'UX/UI Design Conference', 'Creative design thinking for product success.', 'Austin', '2025-04-20 10:00:00', '2025-04-20 18:00:00', 'completed', 2),
(3, 'Data Analytics Bootcamp', 'Hands-on analytics tools and techniques.', 'Denver', '2025-09-01 09:00:00', '2025-09-03 17:00:00', 'upcoming', 3);

INSERT INTO Sessions (session_id, event_id, title, speaker_name, start_time, end_time) VALUES
(1, 1, 'Cloud Security Essentials', 'Dr. Emily Wang', '2025-08-05 09:00:00', '2025-08-05 10:30:00'),
(2, 2, 'Mobile First Design', 'Sara Kim', '2025-04-20 11:00:00', '2025-04-20 12:30:00'),
(3, 3, 'SQL for Data Insights', 'Leo Martinez', '2025-09-01 10:00:00', '2025-09-01 12:00:00'),
(4, 3, 'Python Visualization', 'Fatima Noor', '2025-09-02 13:00:00', '2025-09-02 15:00:00');

INSERT INTO Registrations (registration_id, user_id, event_id, registration_date) VALUES
(1, 2, 1, '2025-07-01'),
(2, 3, 1, '2025-07-05'),
(3, 4, 2, '2025-03-15'),
(4, 5, 2, '2025-03-18'),
(5, 1, 3, '2025-08-20');

INSERT INTO Feedback (feedback_id, user_id, event_id, rating, comments, feedback_date) VALUES
(1, 4, 2, 5, 'Loved the design challenges!', '2025-04-21'),
(2, 5, 2, 4, 'Great content, but room layout was crowded.', '2025-04-21'),
(3, 3, 1, 3, 'Informative but could be more interactive.', '2025-08-06');

INSERT INTO Resources (resource_id, event_id, resource_type, resource_url, uploaded_at) VALUES
(1, 1, 'pdf', 'https://example.com/resources/cloud_slides.pdf', '2025-07-25 14:00:00'),
(2, 2, 'image', 'https://example.com/resources/ui_awards.jpg', '2025-04-10 10:30:00'),
(3, 3, 'link', 'https://example.com/resources/data_bootcamp_repo', '2025-08-28 16:00:00');


-- Query 1
SELECT u.full_name, e.title, e.city, e.start_date
FROM Users u
JOIN Registrations r ON u.user_id = r.user_id
JOIN Events e ON r.event_id = e.event_id
WHERE e.status = 'upcoming' AND u.city = e.city
ORDER BY e.start_date;

-- Query 2
SELECT e.title, AVG(f.rating) AS avg_rating, COUNT(f.feedback_id) AS feedback_count
FROM Feedback f
JOIN Events e ON f.event_id = e.event_id
GROUP BY e.event_id
HAVING COUNT(f.feedback_id) >= 10
ORDER BY avg_rating DESC;

-- Query 3
SELECT full_name
FROM Users
WHERE user_id NOT IN (
  SELECT user_id
  FROM Registrations
  WHERE registration_date >= CURDATE() - INTERVAL 90 DAY
);

-- Query 4
SELECT e.title, COUNT(s.session_id) AS session_count
FROM Sessions s
JOIN Events e ON s.event_id = e.event_id
WHERE TIME(s.start_time) >= '10:00:00' AND TIME(s.end_time) <= '12:00:00'
GROUP BY e.event_id;

-- Query 5
SELECT u.city, COUNT(DISTINCT r.user_id) AS user_count
FROM Users u
JOIN Registrations r ON u.user_id = r.user_id
GROUP BY u.city
ORDER BY user_count DESC
LIMIT 5;

-- Query 6
SELECT e.title,
       SUM(resource_type = 'pdf') AS pdf_count,
       SUM(resource_type = 'image') AS image_count,
       SUM(resource_type = 'link') AS link_count
FROM Events e
LEFT JOIN Resources r ON e.event_id = r.event_id
GROUP BY e.event_id;

-- Query 7
SELECT u.full_name, f.comments, e.title
FROM Feedback f
JOIN Users u ON f.user_id = u.user_id
JOIN Events e ON f.event_id = e.event_id
WHERE f.rating < 3;

-- Query 8
SELECT e.title, COUNT(s.session_id) AS session_count
FROM Events e
LEFT JOIN Sessions s ON e.event_id = s.event_id
WHERE e.status = 'upcoming'
GROUP BY e.event_id;

-- Query 9
SELECT u.full_name,
       SUM(e.status = 'upcoming') AS upcoming,
       SUM(e.status = 'completed') AS completed,
       SUM(e.status = 'cancelled') AS cancelled
FROM Events e
JOIN Users u ON e.organizer_id = u.user_id
GROUP BY u.user_id;

-- Query 10
SELECT DISTINCT e.title
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
WHERE e.event_id NOT IN (
  SELECT DISTINCT event_id FROM Feedback
);

-- Query 11
SELECT registration_date, COUNT(*) AS new_users
FROM Users
WHERE registration_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY registration_date;

-- Query 12
SELECT e.title, COUNT(s.session_id) AS session_count
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
GROUP BY e.event_id
ORDER BY session_count DESC
LIMIT 1;

-- Query 13
SELECT e.city, AVG(f.rating) AS avg_rating
FROM Feedback f
JOIN Events e ON f.event_id = e.event_id
GROUP BY e.city;

-- Query 14
SELECT e.title, COUNT(r.registration_id) AS total_registrations
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
GROUP BY e.event_id
ORDER BY total_registrations DESC
LIMIT 3;

-- Query 15
SELECT s1.event_id, s1.title AS session1, s2.title AS session2
FROM Sessions s1
JOIN Sessions s2 ON s1.event_id = s2.event_id
  AND s1.session_id < s2.session_id
  AND s1.start_time < s2.end_time
  AND s2.start_time < s1.end_time;

-- Query 16
SELECT *
FROM Users
WHERE registration_date >= CURDATE() - INTERVAL 30 DAY
AND user_id NOT IN (
  SELECT DISTINCT user_id FROM Registrations
);

-- Query 17
SELECT speaker_name, COUNT(*) AS session_count
FROM Sessions
GROUP BY speaker_name
HAVING COUNT(*) > 1;

-- Query 18
SELECT e.title
FROM Events e
LEFT JOIN Resources r ON e.event_id = r.event_id
WHERE r.resource_id IS NULL;

-- Query 19
SELECT e.title,
       COUNT(DISTINCT r.user_id) AS total_registrations,
       ROUND(AVG(f.rating), 2) AS avg_rating
FROM Events e
LEFT JOIN Registrations r ON e.event_id = r.event_id
LEFT JOIN Feedback f ON e.event_id = f.event_id
WHERE e.status = 'completed'
GROUP BY e.event_id;

-- Query 20
SELECT u.full_name,
       COUNT(DISTINCT r.event_id) AS events_attended,
       COUNT(DISTINCT f.feedback_id) AS feedbacks_given
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
LEFT JOIN Feedback f ON u.user_id = f.user_id
GROUP BY u.user_id;

-- Query 21
SELECT u.full_name, COUNT(f.feedback_id) AS feedback_count
FROM Feedback f
JOIN Users u ON f.user_id = u.user_id
GROUP BY f.user_id
ORDER BY feedback_count DESC
LIMIT 5;

-- Query 22
SELECT user_id, event_id, COUNT(*) AS registration_count
FROM Registrations
GROUP BY user_id, event_id
HAVING COUNT(*) > 1;

-- Query 23
SELECT DATE_FORMAT(registration_date, '%Y-%m') AS month, COUNT(*) AS registrations
FROM Registrations
WHERE registration_date >= CURDATE() - INTERVAL 12 MONTH
GROUP BY month
ORDER BY month;

-- Query 24
SELECT e.title,
       ROUND(AVG(TIMESTAMPDIFF(MINUTE, s.start_time, s.end_time)), 2) AS avg_duration_mins
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
GROUP BY e.event_id;

-- Query 25
SELECT e.title
FROM Events e
LEFT JOIN Sessions s ON e.event_id = s.event_id
WHERE s.session_id IS NULL;
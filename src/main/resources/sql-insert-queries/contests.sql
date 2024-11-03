-- INSERT INTO public.contests(
-- 	contest_id, contest_date, contest_description, contest_link, status, total_participants)
-- 	VALUES (?, ?, ?, ?, ?, ?);

INSERT INTO public.contests (total_participants, status, contest_description, contest_link, contest_date) VALUES
(0, 'Open', '<p>Join us for the annual coding contest!</p>', 'contest1.com', '2024-12-01T10:00:00+00:00'),
(0, 'Closed', '<p>Spring Boot programming challenge</p>', 'contest2.com', '2024-11-15T14:00:00+00:00'),
(0, 'Open', '<p>Data Structures contest</p>', 'contest3.com', '2024-11-20T09:00:00+00:00'),
(0, 'Open', '<p>Database skills showdown</p>', 'contest4.com', '2024-11-25T12:00:00+00:00'),
(0, 'Closed', '<p>Web development competition</p>', 'contest5.com', '2024-11-10T08:00:00+00:00'),
(0, 'Open', '<p>Machine Learning hackathon</p>', 'contest6.com', '2024-12-05T10:00:00+00:00'),
(0, 'Pending', '<p>Java programming bootcamp</p>', 'contest7.com', '2024-12-15T15:00:00+00:00'),
(0, 'Open', '<p>Full-stack developer competition</p>', 'contest8.com', '2024-12-18T09:00:00+00:00'),
(0, 'Closed', '<p>Cloud computing workshop</p>', 'contest9.com', '2024-11-17T11:00:00+00:00'),
(0, 'Open', '<p>UI/UX design challenge</p>', 'contest10.com', '2024-12-03T08:30:00+00:00'),
(0, 'Closed', '<p>Networking fundamentals quiz</p>', 'contest11.com', '2024-11-22T13:00:00+00:00'),
(0, 'Pending', '<p>Python programming contest</p>', 'contest12.com', '2024-12-12T16:00:00+00:00'),
(0, 'Open', '<p>Embedded systems battle</p>', 'contest13.com', '2024-12-20T10:30:00+00:00'),
(0, 'Open', '<p>Cybersecurity challenge</p>', 'contest14.com', '2024-11-30T09:30:00+00:00'),
(0, 'Pending', '<p>Artificial intelligence competition</p>', 'contest15.com', '2024-12-08T10:00:00+00:00');

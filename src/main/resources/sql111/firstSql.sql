--CREATE INDEX idx_email ON t_email(hashtext(email));

CREATE INDEX idx_batch_branch ON Student(batch , branch);

CREATE INDEX idx_gsuite_email on Student(hashtext(gsuite_email));


-- Insert records into the student table with division
INSERT INTO student (erp_id, current_year, batch, tp_id, personal_email, gsuite_email, phone, branch, division) VALUES
(103221112, 2, 'B3', 'TP2023', 'student1@example.com', '23456@tcetmumbai.in', '9876543211', 'Electronics', 'A'),
(103221113, 1, 'B1', 'TP2024', 'student2@example.com', '34567@tcetmumbai.in', '9876543212', 'Information Technology', 'B'),
(103221114, 4, 'B8', 'TP2021', 'student3@example.com', '45678@tcetmumbai.in', '9876543213', 'Mechanical Engineering', 'C'),
(103221115, 3, 'B6', 'TP2022', 'student4@example.com', '56789@tcetmumbai.in', '9876543214', 'Civil Engineering', 'A'),
(103221116, 2, 'B5', 'TP2023', 'student5@example.com', '67890@tcetmumbai.in', '9876543215', 'Computer Science', 'B'),
(103221117, 1, 'B2', 'TP2024', 'student6@example.com', '78901@tcetmumbai.in', '9876543216', 'Electronics', 'C'),
(103221118, 4, 'B7', 'TP2021', 'student7@example.com', '89012@tcetmumbai.in', '9876543217', 'Information Technology', 'A'),
(103221119, 3, 'B6', 'TP2022', 'student8@example.com', '90123@tcetmumbai.in', '9876543218', 'Mechanical Engineering', 'B'),
(103221120, 2, 'B4', 'TP2023', 'student9@example.com', '01234@tcetmumbai.in', '9876543219', 'Civil Engineering', 'C'),
(103221121, 1, 'B1', 'TP2024', 'student10@example.com', '12345@tcetmumbai.in', '9876543220', 'Computer Science', 'A'),
(103221122, 2, 'B3', 'TP2023', 'student11@example.com', '23456@tcetmumbai.in', '9876543221', 'Electronics', 'B'),
(103221123, 4, 'B8', 'TP2021', 'student12@example.com', '34567@tcetmumbai.in', '9876543222', 'Information Technology', 'C'),
(103221124, 3, 'B6', 'TP2022', 'student13@example.com', '45678@tcetmumbai.in', '9876543223', 'Mechanical Engineering', 'A'),
(103221125, 1, 'B2', 'TP2024', 'student14@example.com', '56789@tcetmumbai.in', '9876543224', 'Civil Engineering', 'B'),
(103221126, 4, 'B7', 'TP2021', 'student15@example.com', '67890@tcetmumbai.in', '9876543225', 'Computer Science', 'C'),
(103221127, 2, 'B5', 'TP2023', 'student16@example.com', '78901@tcetmumbai.in', '9876543226', 'Electronics', 'A'),
(103221128, 1, 'B1', 'TP2024', 'student17@example.com', '89012@tcetmumbai.in', '9876543227', 'Information Technology', 'B'),
(103221129, 3, 'B6', 'TP2022', 'student18@example.com', '90123@tcetmumbai.in', '9876543228', 'Mechanical Engineering', 'C'),
(103221130, 4, 'B8', 'TP2021', 'student19@example.com', '01234@tcetmumbai.in', '9876543229', 'Civil Engineering', 'A'),
(103221131, 2, 'B4', 'TP2023', 'student20@example.com', '12345@tcetmumbai.in', '9876543230', 'Computer Science', 'B');

-- INSERT INTO public.students(
-- 	erp_id, batch, branch, current_year, division, gsuite_email, personal_email, phone, sh_id, tp_id)
-- 	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

INSERT INTO public.students (erp_id, current_year, division, batch, sh_id, tp_id, personal_email, gsuite_email, phone, branch) VALUES
(10001, 1, 'A', '2021', '@SH001', 'TP001', 'johndoe@example.com', '10001@tcetmumbai.in', '9876543210', 'CSE'),
(10002, 2, 'B', '2021', '@SH002', 'TP002', 'janedoe@example.com', '10002@tcetmumbai.in', '9876543211', 'IT'),
(10003, 3, 'C', '2021', '@SH003', 'TP003', 'michael@example.com', '10003@tcetmumbai.in', '9876543212', 'EXTC'),
(10004, 1, 'D', '2022', '@SH004', 'TP004', 'sarah@example.com', '10004@tcetmumbai.in', '9876543213', 'CIVIL'),
(10005, 2, 'E', '2022', '@SH005', 'TP005', 'william@example.com', '10005@tcetmumbai.in', '9876543214', 'MECH'),
(10006, 3, 'F', '2023', '@SH006', 'TP006', 'olivia@example.com', '10006@tcetmumbai.in', '9876543215', 'CSE'),
(10007, 1, 'G', '2023', '@SH007', 'TP007', 'emma@example.com', '10007@tcetmumbai.in', '9876543216', 'IT'),
(10008, 2, 'A', '2023', '@SH008', 'TP008', 'noah@example.com', '10008@tcetmumbai.in', '9876543217', 'EXTC'),
(10009, 3, 'B', '2024', '@SH009', 'TP009', 'liam@example.com', '10009@tcetmumbai.in', '9876543218', 'CIVIL'),
(10010, 1, 'C', '2024', '@SH010', 'TP010', 'sophia@example.com', '10010@tcetmumbai.in', '9876543219', 'MECH'),
(10011, 2, 'D', '2025', '@SH011', 'TP011', 'isabella@example.com', '10011@tcetmumbai.in', '9876543220', 'CSE'),
(10012, 3, 'E', '2025', '@SH012', 'TP012', 'charlotte@example.com', '10012@tcetmumbai.in', '9876543221', 'IT'),
(10013, 1, 'F', '2025', '@SH013', 'TP013', 'amelia@example.com', '10013@tcetmumbai.in', '9876543222', 'EXTC'),
(10014, 2, 'G', '2026', '@SH014', 'TP014', 'james@example.com', '10014@tcetmumbai.in', '9876543223', 'CIVIL'),
(10015, 3, 'A', '2026', '@SH015', 'TP015', 'lucas@example.com', '10015@tcetmumbai.in', '9876543224', 'MECH');

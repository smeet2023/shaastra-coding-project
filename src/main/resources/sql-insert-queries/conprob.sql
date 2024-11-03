-- INSERT INTO public.contest_problem(
-- 	contest_problem_id, keywords, problem_description, problem_difficulty, problem_solution, problem_title)
-- 	VALUES (?, ?, ?, ?, ?, ?);

INSERT INTO public.contest_problem (problem_title, problem_description, problem_solution, problem_difficulty, keywords) VALUES
('Binary Search Algorithm', 'Implement binary search for sorted arrays.', 'solution1.pdf', 'Intermediate', ARRAY['search', 'binary', 'algorithm']),
('Sorting Algorithms', 'Discuss various sorting algorithms with examples.', 'solution2.pdf', 'Beginner', ARRAY['sorting', 'algorithm', 'arrays']),
('Graph Theory Basics', 'Intro to graph theory, including BFS and DFS.', 'solution3.pdf', 'Intermediate', ARRAY['graphs', 'BFS', 'DFS']),
('Dynamic Programming', 'Explain DP concepts with examples like knapsack.', 'solution4.pdf', 'Advanced', ARRAY['dynamic', 'programming', 'optimization']),
('Data Structures', 'Overview of common data structures and usage.', 'solution5.pdf', 'Beginner', ARRAY['structures', 'arrays', 'linkedlists']),
('Recursion', 'Recursive approaches to problem solving.', 'solution6.pdf', 'Intermediate', ARRAY['recursion', 'algorithm']),
('Greedy Algorithms', 'Techniques and examples of greedy algorithms.', 'solution7.pdf', 'Intermediate', ARRAY['greedy', 'optimization']),
('Backtracking', 'Solving problems using backtracking approaches.', 'solution8.pdf', 'Advanced', ARRAY['backtracking', 'search']),
('String Manipulation', 'Methods for efficient string handling.', 'solution9.pdf', 'Beginner', ARRAY['strings', 'manipulation']),
('Tree Traversal', 'Explain various tree traversal methods.', 'solution10.pdf', 'Intermediate', ARRAY['trees', 'traversal']),
('Mathematics in Coding', 'Math problems commonly used in coding contests.', 'solution11.pdf', 'Advanced', ARRAY['math', 'algorithms']),
('Number Theory', 'Prime numbers, modular arithmetic, and more.', 'solution12.pdf', 'Advanced', ARRAY['numbers', 'theory', 'prime']),
('Hashing Techniques', 'Implementing hash tables and hash functions.', 'solution13.pdf', 'Intermediate', ARRAY['hashing', 'tables']),
('Linked Lists', 'Working with singly and doubly linked lists.', 'solution14.pdf', 'Beginner', ARRAY['linked', 'lists']),
('File I/O Operations', 'Handling file input and output in Java.', 'solution15.pdf', 'Beginner', ARRAY['file', 'IO', 'java']);

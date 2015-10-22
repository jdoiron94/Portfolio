CREATE TABLE Classroom
  (`id` int, `class_name` varchar(255), `capacity` int)
;

CREATE TABLE Teacher
  (`id` int, `teacher_name` varchar(255), `class_id` int, `specialty` varchar(255))
;

CREATE TABLE Student
  (`id` int, `student_name` varchar(255), `emergency_number` varchar(255), `emergency_name` varchar(255), 
   `p1` int, `p2` int, `p3` int, `p4` int, `p5` int, `p6` int)
;
	
INSERT INTO Classroom
  (`id`, `class_name`, `capacity`)
VALUES
  (250, 'Trigonometry', 25),
  (130, 'Intro American Lit.', 25),
  (270, 'Environmental Science', 20),
  (410, 'MD/US History', 30),
  (155, 'Intermediate Spanish I', 20),
  (615, 'Sculpture', 15)
;

INSERT INTO Teacher
  (`id`, `teacher_name`, `class_id`, `specialty`)
VALUES
  (219, 'Mr. Sanders', 250, 'Linear equations'),
  (229, 'Mrs. Brady', 130, 'Shakespeare'),
  (248, 'Mr. Nye', 270, 'Biology'),
  (770, 'Mrs. Willis', 410, 'American History'),
  (619, 'Mr. Neilson', 155, 'Spanish'),
  (609, 'Mr. Johnson', 615, 'Pottery')
;

INSERT INTO Student
  (`id`, `student_name`, `emergency_number`, `emergency_name`, `p1`, `p2`, `p3`, `p4`, `p5`, `p6`)
VALUES
  (536460, 'Sean Sean', '(410)-555-0142', 'Raymond Sean', 250, 130, 270, 410, 155, 615),
  (872224, 'Mark Walhberg', '(410)-444-0116', 'Slauch Wahlberg', 250, 130, 270, 410, 155, 615),
  (891385, 'Bob Burger', '(225)-555-0199', 'Denby Burger', 250, 615, 410, 155, 130, 270),
  (881573, 'Jack Rippa', '(225)-555-0195l', 'Schimek Rippa', 130, 250, 410, 615, 270, 155),
  (800407, 'Robert Johnson', '(701)-555-0169', 'Vanlandscot Johnson', 130, 250, 270, 155, 615, 410),
  (823309, 'Jenna Dewitt', '(701)-555-0100', 'Ruvolo Dewitt', 130, 250, 155, 615, 270, 410),
  (595457, 'Anne DeCleves', '(512)-555-0517', 'Baleja DeCleves', 270, 410, 130, 250, 155, 615),
  (886301, 'Tiffany Desayuno', '(512)-555-0108', 'Beauchamp Desayuno', 155, 615, 250, 270, 410, 130),
  (243767, 'Sarah Bernhardt', '(614)-555-0159', 'Patric Bernhardt', 615, 155, 270, 130, 410, 250),
  (963980, 'Elizabeth Lizard', '(999)-211-7654', 'Mary lizard', 270, 130, 410, 250, 615, 155),
  (190054, 'Landon Bridge', '(121)-222-6543', 'Sam Bridge', 410, 270, 130, 250, 155, 615),
  (110523, 'John Doe', '(232)-233-5432', 'Scott Doe', 410, 270, 250, 130, 615, 155),
  (120977, 'Mary Janel', '(343)-244-4321', 'Elizabeth Jane', 410, 130, 250, 270, 155, 615),
  (136041, 'Morgan Freeman', '(454)-255-3210', 'Jimmy Dean', 155, 410, 615, 270, 250, 130),
  (740937, 'Tristan Donahue', '(565)-266-1555', 'Jack Donahue', 155, 615, 250, 270, 410, 130)
;
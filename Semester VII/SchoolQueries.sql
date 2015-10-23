# Tells us where a given student is during a given class period
SELECT `p1` from Student
WHERE Student.id=536460

# Tells us if a given classroom is in use during a given class period
SELECT COUNT(*) from Classroom
LEFT JOIN Student
ON Classroom.id=Student.p1
WHERE Classroom.id=250

# Tells us if a given teacher is teaching during a given class period
SELECT COUNT(*) from Teacher
LEFT JOIN Student
ON Teacher.class_id=Student.p1
WHERE Teacher.id=219

# Tells us what teachers a given student has
SELECT `teacher_name` from Teacher
LEFT JOIN Student
ON Teacher.class_id=Student.p1
OR Teacher.class_id=Student.p2
OR Teacher.class_id=Student.p3
OR Teacher.class_id=Student.p4
OR Teacher.class_id=Student.p5
OR Teacher.class_id=Student.p6
WHERE Student.id=536460

# Tells us what specialties a given student's teachers have
SELECT `specialty` from Teacher
LEFT JOIN Student
ON Teacher.class_id=Student.p1
OR Teacher.class_id=Student.p2
OR Teacher.class_id=Student.p3
OR Teacher.class_id=Student.p4
OR Teacher.class_id=Student.p5
OR Teacher.class_id=Student.p6
WHERE Student.id=536460

# Gives us a given student's emergency contact number
SELECT `emergency_number` FROM Student
WHERE `id`=536460

# Tells us the room capacity of a classroom, given its room id
SELECT `capacity` FROM Classroom
WHERE Classroom.id=250
const db = require ('../database/database');

module.exports = class Subject{
    constructor (subjectId, subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        // this.studentId = studentId;
    } 
   
    static fetchAll() { 
        console.log("Inner Join");
        return db.execute ('SELECT subjects.subjectName, subjects.subjectId, subjects.studentId, student.studentName FROM subjects INNER JOIN student ON subjects.studentId=student.studentId')
    } 
} 
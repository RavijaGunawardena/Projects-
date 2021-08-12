const db = require ('../database/database');

module.exports = class Subject{
    constructor (subjectId, subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        // this.studentId = studentId;
    } 
   
    static fetchAll() { 
        console.log("subjectName");
        return db.execute ('SELECT * FROM subjects')
    }

    static post(subjectName, studentId) {
        console.log("subjectName will be", subjectName);
        console.log("Student Id will be", studentId);
        return db.execute('INSERT INTO subjects (subjectName, studentId) VALUES (?, ?)', [subjectName, studentId]); 
    }

    static delete(subjectId) {
        console.log("Delete subject is", subjectId);
        return db.execute('DELETE FROM subjects WHERE subjectId = ?', [subjectId]);
    }

};

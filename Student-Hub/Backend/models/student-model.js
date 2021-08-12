const db = require ('../database/database');

module.exports = class Student {
    constructor (studentId, studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    static fetchAll() {  
        return db.execute ('SELECT * FROM student') 
    }

    static post(studentName) {
        return db.execute('INSERT INTO student (studentName) VALUES (?)', [studentName]);      
    }

    static update(studentId, studentName) {
        return db.execute('UPDATE student SET studentName = ? WHERE studentId = ?', [studentName, studentId]);
    }

    static delete(studentId) {
        return db.execute('DELETE FROM student WHERE studentId = ?', [studentId]);
      }
};
const express = require ('express');

const studentController = require ('../controller/student-controller');

const router = express.Router();

router.get('/', studentController.getAllStudents);

router.post('/', studentController.postStudent); 
 
router.put('/', studentController.putStudent);

router.delete('/:studentId', studentController.deleteStudent); 
    
module.exports = router;   
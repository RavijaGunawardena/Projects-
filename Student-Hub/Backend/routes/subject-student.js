const express = require ('express');

const subjectController = require ('../controller/subject-student');

const router = express.Router();

router.get('/', subjectController.getAllSubjects);

router.post('/', subjectController.postSubject);

router.delete('/:subjectId', subjectController.deleteSubject);  

module.exports = router;    
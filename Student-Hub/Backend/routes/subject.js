const express = require ('express');

const subjectController = require ('../controller/subject');

const router = express.Router();

router.get('/', subjectController.getAllSubjectstoSubjectPage);

module.exports = router;       
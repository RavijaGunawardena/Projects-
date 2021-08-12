const Subject = require ('../models/subject-student-model');

exports.getAllSubjects = async (req, res, next) => {
    try {
        const [allSubjects] = await Subject.fetchAll();
        res.status(200).json(allSubjects);
    }catch (err){
        if(!err.statusCode){ 
            err.statusCode = 500
        } 
        next(err)  
    }
};

exports.postSubject = async (req, res, next) => { 
    try {
      const postResponse = await Subject.post(req.body.subjectName, req.body.studentId);
      res.status(201).json(postResponse);
    } catch (err) {
      if (!err.statusCode) {
        err.statusCode = 500;
      }
      next(err);
    }
  };

exports.deleteSubject = async (req, res, next) => {
  try {
    const deleteResponse = await Subject.delete(req.params.subjectId);
    res.status(200).json(deleteResponse);
  } catch (err) {
    if (!err.statusCode) {
      err.statusCode = 500;
    }
    next(err);
  }
};
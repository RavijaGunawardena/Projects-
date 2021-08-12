const Student = require ('../models/student-model');

exports.getAllStudents = async (req, res, next) => {
    try {
        const [allStudents] = await Student.fetchAll();
        res.status(200).json(allStudents);
    }catch (err){  
        if(!err.statusCode){
            err.statusCode = 500
        }  
        next(err)
    }
};

exports.postStudent = async (req, res, next) => {
    try {
      const postResponse = await Student.post(req.body.studentName);
      res.status(201).json(postResponse);
    } catch (err) {
      if (!err.statusCode) {
        err.statusCode = 500;
      }
      next(err);
    }
};

exports.putStudent = async (req, res, next) => {
    try {
      const putResponse = await Student.update(req.body.studentId, req.body.studentName);
      res.status(200).json(putResponse);
    } catch (err) {
      if (!err.statusCode) {
        err.statusCode = 500;
      }
      next(err);
    }
};

exports.deleteStudent = async (req, res, next) => {
  try {
    const deleteResponse = await Student.delete(req.params.studentId);
    res.status(200).json(deleteResponse);
  } catch (err) {
    if (!err.statusCode) {
      err.statusCode = 500;
    }
    next(err);
  }
};
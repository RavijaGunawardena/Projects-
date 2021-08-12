const Subject = require ('../models/subject');

exports.getAllSubjectstoSubjectPage = async (req, res, next) => {
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
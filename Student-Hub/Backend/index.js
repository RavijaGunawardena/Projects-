const express = require ('express');

const bodyParser = require ('body-parser');

const bodyParser1 = require ('body-parser');

const studentRoutes = require ('./routes/student-routes')

const subjectRoutes = require ('./routes/subject-student')

const toSubjectPageTable = require ('./routes/subject')

const errorController = require ('./controller/error')

const app = express ();

const app1 = express ();

const ports = process.env.PORT || 3000;

const ports2 = process.env.PORT || 3200;

app.use (bodyParser.json());

app1.use (bodyParser1.json());

app.use((req, res, next) => {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE');
    res.setHeader ('Access-Control-Allow-Headers','Content-Type, Authorization');
    next();
})

app1.use((req, res, next) => {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, DELETE');
    res.setHeader ('Access-Control-Allow-Headers','Content-Type, Authorization');
    next();
})

app.use ('/student', studentRoutes);

app.use ('/subject', subjectRoutes);

app1.use ('/tbsubjects', toSubjectPageTable);

app.use (errorController.get404);

app.use (errorController.get500);

app1.use (errorController.get404);

app1.use (errorController.get500);

app.listen (ports, () => console.log ('Listning on port ${ports}'));

app1.listen (ports2, () => console.log ('Listning on port ${ports2}'));
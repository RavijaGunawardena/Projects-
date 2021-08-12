const mySql = require ('mysql2');

const config = require ('../config/database-config.json');

const pool = mySql.createPool ({
    host: config.host,
    user: config.user,
    database: config.database,
    password: config.password,
});

module.exports = pool.promise();    
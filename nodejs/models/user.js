var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var UserSchema = new Schema({
	username:String,
	password:String,
	createdAt :{type:Date, default:Date.now}


});

mongoose.model('user', UserSchema);
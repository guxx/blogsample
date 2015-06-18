var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var PostSchema = new Schema({
	author:String,
	title: String,
	body: String,
	category: String,
	votes:Number,
	password:String,
	createdAt :{type:Date, default:Date.now}
});

mongoose.model('post', PostSchema);
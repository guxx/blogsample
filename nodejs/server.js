/* Models required */
require('./models/user');
require('./models/post');

//modules
var express = require('express'),
	bodyParser = require('body-parser'),
	multer = require('multer'),
	post_controller = require('./controllers/posts_controllers'),
	port = 8020,
	mongoose = require('mongoose'),
	app = express();

//connect db
mongoose.connect('mongodb://localhost/blog1');

//url paths
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
app.use(multer());
app.get('/posts', post_controller.getPosts);
app.get('/posts/:category', post_controller.getPosts);
app.post('/post/new', post_controller.addNewPost);
app.get('/post/:postId', post_controller.getPost);
app.get('/post/:postId', post_controller.deletePost);


app.listen(port);
console.log('server in %s', port);


// mongo_client.connect('mongodb//127.0.0.1:27017/blog', function(err,db){
// 	if(err) throw err;
// 	var user_collection = db.collection('user'),
// 		post_collection = db.collection('post');
// 	app.use(express.bodyParser());
// 	app.get('/posts', post_controller.getPosts);
// 	app.post('/post/new', post_controller.addNewPost);
// 	app.get('/post/:postId', post_controller.getPost);
// 	app.get('/post/:postId', post_controller.deletePost);


// 	app.listen(port);
// 	console.log('server in %s', port);

// });


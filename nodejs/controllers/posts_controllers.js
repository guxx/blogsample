var crypto = require('crypto'),
	mongoose = require('mongoose'),
	UserModel = mongoose.model('user'),
	PostModel = mongoose.model('post'),
	mongoose = require('mongoose');

module.exports.getPosts = function ( req, res)
{
	var cat = req.params.category;
	console.log(cat);
	var query;
	if (cat)
	{
		query = PostModel.find().where('category', cat).select('id title body author category createdAt').sort('-createdAt');
	}
	else
	{
		query = PostModel.find().select('id title body author category createdAt').sort('-createdAt');
	}
	
	query.exec(function(err,posts)
	{
		if (err) throw err;
		console.log(posts);
		res.json(posts);
	}


		);
};


module.exports.addNewPost = function ( req, res)
{
	console.log(req.body);
	if (("author" in req.body && req.body.name !== '')&&
		("title" in req.body && req.body.title !== '') &&
		("body" in req.body && req.body.body !== '') && ("category" in req.body && req.body.category !== ''))
	{
		//creating the post
		PostModel.create(req.body, function(err, todo)
		{
			if (err) throw err;
			console.log(todo);
			res.json(todo);

		});
	}
	else
	{
		res.status(500).json({error:'Fields Missing'});
	}

};

module.exports.getPost = function ( req, res)
{

};
module.exports.deletePost = function ( req, res)
{

};

module.exports.getUser = function ( req, res)
{

};
module.exports.createUser = function()
{

	UserModel.create({username: "gxsot", password: crypto.createHash('md5').update('test1234').digest('hex')}, 
		function(err,docs)
		{
			if (err) throw err;
			console.log(docs);
		}


		);
};
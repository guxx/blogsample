package controllers;

import play.*;
import play.mvc.*;
import play.libs.ws.*;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.data.Form;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import views.html.*;
import models.Post;
import javax.inject.Inject;
import java.util.*;

public class Application extends Controller {

	private String url = "http://localhost:8020/";
    @Inject WSClient ws;

    public Result posts()
    {
    	WSRequest request = ws.url(url+"posts");
    	try
    	{
    	Promise<JsonNode> jsonPromise = request.get().map(response ->{return response.asJson();});
    	Promise<List<Post>> postList = jsonPromise.map(
    		new Function<JsonNode, List<Post>>()
    		{
    			@Override
    			public List<Post> apply(JsonNode jsonNode) throws Throwable
    			{
    				
    				List<Post> postL = new ArrayList<Post>();
    				for(JsonNode node: jsonNode)
    				{
    					System.out.println(node);
    					Post p = new Post();
    					p.setId(node.get("_id").asText());
			    		p.setTitle(node.get("title").asText());
			    		p.setBody(node.get("body").asText());
			    		p.setAuthor(node.get("author").asText());
			    		p.setCreatedAt(node.get("createdAt").asText());
			    		if (node.get("category") != null)
			    			p.setCategory(node.get("category").asText());
			    		postL.add(p);
    				}
    				return postL;
    			}
    		}
    		);
     		return ok(views.html.posts.render(postList.get(30000)));
    	}
    	catch(Exception e){
    		return ok(views.html.posts.render(new ArrayList<Post>()));
    	}
    }
    public Result postsCategory(String category)
    {
    	WSRequest request = ws.url(url+"posts/"+category);
    	try{
	    	Promise<JsonNode> jsonPromise = request.get().map(response ->{return response.asJson();});
	    	Promise<List<Post>> postList = jsonPromise.map(
	    		new Function<JsonNode, List<Post>>()
	    		{
	    			@Override
	    			public List<Post> apply(JsonNode jsonNode) throws Throwable
	    			{
	    				
	    				List<Post> postL = new ArrayList<Post>();
	    				for(JsonNode node: jsonNode)
	    				{
	    					System.out.println(node);
                            Post p = new Post();
                            p.setId(node.get("_id").asText());
                            p.setTitle(node.get("title").asText());
                            p.setBody(node.get("body").asText());
                            p.setAuthor(node.get("author").asText());
                            p.setCreatedAt(node.get("createdAt").asText());
                            if (node.get("category") != null)
                                p.setCategory(node.get("category").asText());
                            postL.add(p);
	    				}
	    				return postL;
	    			}
	    		}
	    		);
            System.out.println("here");
	     	return ok(views.html.posts.render(postList.get(30000)));
     	}
    	catch(Exception e){
            System.out.println(e);
    		return ok(views.html.posts.render(new ArrayList<Post>()));
    	}
    }
    public Result addPost()
    {
    	Form<Post> postForm = Form.form(Post.class); 
    	return ok(views.html.add.render(postForm, false));
    }
    public  Result addSubmit()
    {
    	Form<Post> postForm = Form.form(Post.class); 
    	Post post = postForm.bindFromRequest().get();
    	System.out.println(post.getCategory());
    	WSRequest request = ws.url(url+"post/new");
    	
    	try{
    	request.setContentType("application/x-www-form-urlencoded").post(post.toString());
    	Promise<JsonNode> jsonPromise = request.get().map(response ->{return response.asJson();});
    	Promise<Post> postList = jsonPromise.map(
    		new Function<JsonNode, Post>()
    		{
    			@Override
    			public Post apply(JsonNode node) throws Throwable
    			{
    					System.out.println(node);
    					Post p = new Post();
    					p.setId(node.get("_id").asText());
			    		p.setTitle(node.get("title").asText());
			    		p.setBody(node.get("body").asText());
			    		p.setAuthor(node.get("author").asText());
    				return p;
    			}
    		}
    		);
    	post = postList.get(30000);

    	return redirect(controllers.routes.Application.posts());
    	}
    	catch(Exception e){
    		System.out.println(e);
    		return ok(views.html.add.render(postForm, true));
    	}
    	
    }

}

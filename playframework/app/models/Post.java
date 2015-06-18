package models ;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.net.URLEncoder;
public class Post {
	protected String author;
	protected String id;
	protected String body;
	protected String title;
	protected String category;
	protected Date createdAt;
	public void setAuthor(String author)
	{
		this.author = author;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public void setBody(String body)
	{
		this.body = body;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public void setCreatedAt(String createdAt)
	{
		try{
		DateFormat format = new SimpleDateFormat("yyyyy-MM-dd'T'HH:mm:ss.SSSX");

		this.createdAt = format.parse(createdAt);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String getAuthor()
	{
		return author;
	}
	public String getId()
	{
		return id;
	}
	public String getTitle()
	{
		return title;
	}
	public String getBody()
	{
		return body;
	}
	public String getCategory()
	{
		return category;
	}
	public Date getCreatedAt()
	{
		return createdAt;
	}
	public String getCreatedDateString()
	{
		DateFormat formd = new SimpleDateFormat("E, MMM dd yyyy");
		return formd.format(createdAt);

	}
	public String toString() 
	{
		try{
			return "title="+URLEncoder.encode(title, "UTF-8")+"&body="+URLEncoder.encode(body, "UTF-8")+"&author="+URLEncoder.encode(author, "UTF-8")+"&category="+URLEncoder.encode(category, "UTF-8");
		}
		catch(java.io.UnsupportedEncodingException e)
		{
			return "";
		}
	}
}
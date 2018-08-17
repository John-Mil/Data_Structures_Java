package structures;

public class Node<T> 
{
	private Node<T> link;
	private T info;
	
	public Node(T element) 
	{
		link = null;
		info = element;
	}

	public Node<T> getLink() 
	{
		return link;
	}

	public void setLink(Node<T> link) 
	{
		this.link = link;
	}

	public T getInfo() 
	{
		return info;
	}

	public void setInfo(T info) 
	{
		this.info = info;
	}
	
	
}

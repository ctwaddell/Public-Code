import java.util.EmptyStackException;

public class MesssageStack implements StackADT<Message>
{
  LinkedNode<Message> top;
  int size;
  
  @Override
  public void push(Message element)
  {
    if(size == 0)
    {
      LinkedNode<Message> newTop = new LinkedNode<Message>(element);
      top = newTop;
      size++;
    }
    else
    {
      LinkedNode<Message> newTop = new LinkedNode<Message>(element, top);
      top = newTop;
      size++;
    }
  }
  
  @Override
  public Message pop() 
  {
    if(size == 0)
    {
      throw new EmptyStackException();
    }
    LinkedNode<Message> returnTop = top;
    top = top.getNext();
    size--;
    return returnTop.getData();
  }
  
  @Override
  public Message peek() 
  {
    if(size == 0)
    {
      throw new EmptyStackException();
    }
    return top.getData();
  }
  
  @Override
  public boolean isEmpty() 
  {
    if(size == 0)
    {
      return true;
    }
    else
    {
    return false;
    }
  }
  
  @Override
  public int size() 
  {
    return size;
  }
}

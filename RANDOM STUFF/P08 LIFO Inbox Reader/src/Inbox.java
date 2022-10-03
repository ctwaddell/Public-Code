//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Inbox - Class which creates an Inbox object containing two linked lists
//                   to organize Message objects better with various methods
// Course:   CS 300 Fall 2020
//
// Author:   Casey Waddell
// Email:    ctwaddell@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Inbox is a class that utilizes various other methods and applies them in a way representing
 * what an email inbox might look like
 * @author casey
 *
 */
public class Inbox
{
  private MessageStack readMessageBox;
  private MessageStack unreadMessageBox;
  
  /**
   * This constructor makes a new Inbox object with empty linked lists and no arguments
   */
  public Inbox()
  {
    readMessageBox = new MessageStack();
    unreadMessageBox = new MessageStack();
  }
  
  /**
   * readMessage takes the latest Message object in the linked list and returns it in a legible
   * format for user. Likewise, this takes from the unreadMessageBox linked list and adds it
   * to the readMessageBox
   * 
   * @return String representing what the message is roughly about
   */
  public String readMessage()
  {
    if(unreadMessageBox.size() == 0)
    {
      return "Nothing in Unread";
    }
    Message readMessage = unreadMessageBox.pop();
    String returnString = "";
    returnString += readMessage.getSUBJECT() + ": ";
    returnString += readMessage.getTEXT();
    readMessageBox.push(readMessage);
    return returnString;
  }
  
  /**
   * peekReadMessage takes the latest Message object in the linked list and returns it in
   * a legible format for the user without changing its state in both arrays
   * 
   * @return String representing what the message is roughly about
   */
  public String peekReadMessage()
  {
    if(readMessageBox.size() == 0)
    {
      return "Nothing in Read";
    }
    Message readMessage = readMessageBox.peek();
    String returnString = "";
    returnString += readMessage.getSUBJECT() + ": ";
    returnString += readMessage.getTEXT();
    return returnString;
  }
  
  /**
   * markAllMessagesAsRead moves all the messages in the unreadMessageBox linked list into the
   * readMessageBox linked list without reading the contents
   * 
   * @return int representing how many messages were marked as read / moved
   */
  public int markAllMessagesAsRead()
  {
    int returnInt = 0;
    while(unreadMessageBox.size() > 0)
    {
      readMessageBox.push(unreadMessageBox.pop());
      returnInt++;
    }
    return returnInt;
  }
  
  /**
   * receiveMessage adds a message object into the unreadMessageBox linked list
   * at the top of the stack
   * 
   * @param newMessage, Message object to add to the unreadMessageBox stack
   */
  public void receiveMessage(Message newMessage)
  {
    unreadMessageBox.push(newMessage);
  }
  
  /**
   * emptyReadMessagesBox removes all the messages from the readMessagesBox and tells how many
   * were removed
   * @return int representing how many emails were cleared from the stack
   */
  public int emptyReadMessageBox()
  {
    int returnInt = 0;
    while(readMessageBox.size() > 0)
    {
      readMessageBox.pop();
      returnInt++;
    }
    return returnInt;
  }
  
  /**
   * getStatistics analyzes the amount of unread and read messages in the Inbox object
   * 
   * @return String in a format that states how many of each messages are in the Inbox
   */
  public String getStatistics()
  {
    String returnString = "";
    returnString += "Unread " + unreadMessageBox.size() + "\n" + "Read " + readMessageBox.size();
    return returnString;
  }
  
  /**
   * traverseUnreadMessages counts how many messages are in the unreadMessagesBox and then
   * takes their ID and SUBJECT fields of each and converts that into a long string for users
   * 
   * @return String representing the amount and brief description of each Message in the stack
   */
  public String traverseUnreadMessages() 
  {
    String returnString = "Unread " + unreadMessageBox.size() + "\n";
    for(Message mail : unreadMessageBox)
    {
      returnString += mail.getID() + " " + mail.getSUBJECT() + "\n";
    }
    return returnString;
 }
  
  /**
   * traverseReadMessages counts how many messages are in the readMessagesBox and then
   * takes their ID and SUBJECT fields of each and converts that into a long string for users
   * 
   * @return String representing the amount and brief description of each Message in the stack
   */
  public String traverseReadMessages() 
  {
    String returnString = "Read " + readMessageBox.size() + "\n";
    for(Message mail : readMessageBox)
    {
      returnString += mail.getID() + " " + mail.getSUBJECT() + "\n";
    }
    return returnString;
  }
 
}

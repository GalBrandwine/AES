package root.dao.message;

import java.util.ArrayList;

import root.dao.app.Course;
import root.dao.app.Exam;
import root.dao.app.LoginInfo;
import root.dao.app.Question;
import root.dao.app.Subject;
import root.dao.app.User;
import root.server.managers.dbmgr.GetFromDB;

public class MessageFactory {
	private static MessageFactory instance=null;
	
	private MessageFactory(){
		
	}
	
	public static MessageFactory getInstance() {
		if(instance==null) {
			instance=new MessageFactory();
		}
		return instance;
	}
	
	public AbstractMessage getMessage(String msg,Object payload) {
		String[] msgContent=msg.toLowerCase().split("-");
		switch(msgContent[0]) {
		case "ok":
			return getOkMessage(msgContent,payload);
		case "login":
			return getLoginMessage(msgContent,payload);
		case "get":
			return getGetMessage(msgContent,payload);
		case "set":
			return getSetMessage(msgContent,payload);
		case "put":
			return getPutMessage(msgContent,payload);
		case "delete":
			return getDelMessage(msgContent,payload);
		case "error":
			return getErrorMessage(msgContent,payload);
		}
		return new ErrorMessage(new Exception("Invalid request"));
	}
	
	private AbstractMessage getErrorMessage(String[] msgContent, Object payload) {
		return new ErrorMessage((Exception)payload);
		
		// TODO Auto-generated method stub
		
	}

	private AbstractMessage getDelMessage(String[] msgContent, Object payload) {
		return null;
		// TODO Auto-generated method stub
		
	}

	private AbstractMessage getPutMessage(String[] msgContent, Object payload) {
		return null;
		// TODO Auto-generated method stub
		
	}

	private AbstractMessage getSetMessage(String[] msgContent, Object payload) {
		return null;
		// TODO Auto-generated method stub
		
	}

	private AbstractMessage getGetMessage(String[] msgContent, Object payload) {
		switch(msgContent[1]) {
		case "subjects":
			return new SubjectMessage((String)payload);
		case "courses":
			return new CourseMessage((Subject)payload);
		}
		
		return null;
		
		
	}

	private AbstractMessage getLoginMessage(String[] msgContent, Object payload) {
		
		return new LoginMessage((LoginInfo)payload);	
	}

	@SuppressWarnings("unchecked")
	public AbstractMessage getOkMessage(String[] msgContent,Object payload) {
		switch(msgContent[1]) {
		case "login":
			return new UserMessage((User)payload);
		case "get":
			return  getOkGetMessage(msgContent,payload);
		case "set":
			return new SimpleMessage("ok-set-"+msgContent[2]);
		case "put":
			return new SimpleMessage("ok-put-"+msgContent[2]);
		case "delete":
			return new SimpleMessage("ok-delete-"+msgContent[2]);

		}
		return new ErrorMessage(new Exception("Invalid request"));
	}
	
	public AbstractMessage getOkGetMessage(String[] msgContent,Object payload)
	{
		switch(msgContent[2]) {
			case "questions":
				if(payload instanceof ArrayList<?>)
					return new QuestionsMessage((ArrayList<Question>) payload);
				else return new ErrorMessage(new Exception("Your payload is not arraylist"));
			case "exams":
				if(payload instanceof ArrayList<?>)
					return new ExamMessage((ArrayList<Exam>) payload);
				else return new ErrorMessage(new Exception("Your payload is not arraylist"));
			case "subjects":
				if(payload instanceof ArrayList<?>)
					return new SubjectMessage((ArrayList<Subject>)payload);
				else return new ErrorMessage(new Exception("Your payload is not arraylist"));
			case "courses":
				if(payload instanceof ArrayList<?>)
					return new CourseMessage((ArrayList<Course>)payload);
				else return new ErrorMessage(new Exception("Your payload is not arraylist"));
			
		}
		return new ErrorMessage(new Exception("Invalid request"));
	}
	
	

}

package discussion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import discussion.model.DiscussionPost;
import discussion.model.FileDiscussionHandler;

/**
 * Servlet implementation class CreateNewPost
 */
@WebServlet("/CreateNewPost")
public class CreateNewPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");
		
		String textBlock = request.getParameter("txtBlock");
		String userName = (String) request.getSession().getAttribute("username");
		
		userName = (userName == null) ? "Unauthenticated Guest" : userName;
		
		DiscussionPost newPost = new DiscussionPost(textBlock, userName);

		FileDiscussionHandler handler = new FileDiscussionHandler(dataDirectory + "discussionPosts.txt");
		handler.addPost(newPost);
		
		response.sendRedirect("showPosts");

	}

}

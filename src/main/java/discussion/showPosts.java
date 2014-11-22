package discussion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import discussion.model.DiscussionDataHandler;
import discussion.model.FileDiscussionHandler;

/**
 * Servlet implementation class showPosts
 */
@WebServlet("/showPosts")
public class showPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showPosts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");
		
		dataDirectory = (dataDirectory == null ? "" : dataDirectory);
		
		DiscussionDataHandler handler = new FileDiscussionHandler(dataDirectory + "discussionPosts.txt");
		request.setAttribute("DiscussionPosts", handler.getPosts());

        request.getRequestDispatcher("DiscussionPosts.jsp").forward(request,response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

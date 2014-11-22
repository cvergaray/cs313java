package discussion.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class FileDiscussionHandler implements DiscussionDataHandler {

	private String fileName;

	public FileDiscussionHandler(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void addPost(DiscussionPost newPost) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					getFileName(), true));
			writer.write(newPost.toFileString() + "\n");
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<DiscussionPost> getPosts() {
		List<DiscussionPost> list = new ArrayList<DiscussionPost>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					getFileName()));

			String line;

			while ((line = reader.readLine()) != null) {
				DiscussionPost newPost = new DiscussionPost();
				newPost.loadFromFileString(line);
				list.add(newPost);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	    Collections.sort(list, new PostTimeComparator());
		
		return list;
	}

	/**
	 * An implementation of the Comparator interface. Used to sort the couples
	 * in order based on the sum of the scores they have received up to this
	 * point. When used in a sort, couples are placed in descending order by the
	 * sum of their scores
	 */
	class PostTimeComparator implements Comparator<DiscussionPost> {
		/**
		 * Creates a new PlaceTallyComparator object.
		 *
		 * @param pIndex
		 *            The max score used in the summations
		 */
		PostTimeComparator() {
		}

		/**
		 * Compares the timestamps of two posts to determine which one is older.
		 * A negative number indicates that the first post was earlier than the
		 * other, positive numbers indicate that the second post was earlier.
		 * Zero means they are the same.
		 *
		 * @param o1
		 *            The first post to be compared
		 * @param o2 The second post to be compared
		 *
		 * @return an integer indicating which post was posted earlier
		 */
		@Override
		public int compare(DiscussionPost o1, DiscussionPost o2) {
			return o2.getTime().compareTo(o1.getTime());
		}
	}
}

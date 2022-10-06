import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {


	List<String> s = new ArrayList<String>();
	public String handleRequest(URI url) {
		if (url.getPath().equals("/")) {
			return "200";
		}
		else if (url.getPath().contains("/add")) {
			String[] params = url.getQuery().split("=");
			s.add(params[1]);
			return params[1] + " added";
		}
		else if (url.getPath().contains("/search")) {
			String[] params = url.getQuery().split("=");
			String rtn = "";
			for (int i = 0; i < s.size(); ++i) {
				if (s.get(i).contains(params[1])) {
					rtn = rtn + s.get(i) + "\n";
				}
			}
			return rtn;
		}
		else {
			return "404";
		}
	}
}

class SearchEngine {
	public static void main(String[] args) throws IOException {
		int port = Integer.parseInt(args[0]);
 		Server.start(port, new Handler());
	}
}

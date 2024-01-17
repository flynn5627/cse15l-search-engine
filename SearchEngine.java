import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    ArrayList<String> strings = new ArrayList<>();
    public String handleRequest(URI url){
        if (url.getPath().startsWith("/add")){
            String[] parts = url.getQuery().split("=");
            if (parts[0].equals("s")){
                strings.add(parts[1]);
                return "Added new string: " + parts[1];
            }
            return "Invalid input.";
        } else if (url.getPath().startsWith("/search")){
            try{
                String[] parts = url.getQuery().split("=");
                if (parts[0].equals("s")){
                    String result = "";
                    for (String str: this.strings){
                        if (str.contains(parts[1])){
                            result += "- " + str + "\n";
                        }
                    }
                    return "Your search results:\n" + result;
                }
            } catch(Exception e) {
                return "Invalid search query.";
            }
             return "Invalid search query.";
        } else {
            return "Invalid path. Use /add or /search";
        }
    }
}

class SearchEngine{
    public static void main(String[] args) throws IOException{
        if (args.length == 0){
            System.out.println("Enter a valid port number");
        } else{
            int port = Integer.parseInt(args[0]);
            Server.start(port, new Handler());
        }
    }
}

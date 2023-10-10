import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
        ArrayList<Map<String, String>> testCases = generateTestCases();
        File input, output, results = new File("results.html");
        FileWriter fileWriter, resultWriter;
        
        try {
            results.createNewFile();
            resultWriter = new FileWriter(results);
            resultWriter.write(getHTMLHead());

            for (int x = 0; x < 2; x++) {
                for( int i = 0; i < testCases.size(); i++) {
                    int caseNum = i + (x * testCases.size());
                    if (caseNum == 72) {
                        break;
                    }
                    input = new File("testCases/in/" + (caseNum+1));
                    output = new File("testCases/out/" + (caseNum+1));
                    input.createNewFile();
                    output.createNewFile();
    
                    Map<String, String> data = testCases.get(i);
                    String ticketNum = data.get("ticket");
                    String rowNum = data.get("row");
                    String seatType = data.get("seat");
                    String params = String.format("%s %s %s", ticketNum, rowNum, seatType);
                    if (x == 1) {
                        params += "\n" + params;
                    }
    
                    fileWriter = new FileWriter(input);
                    fileWriter.write(params);
                    fileWriter.close();
        
                    String actual = runProgram(input.getPath(), output.getPath());
                    if (actual.equals("pass")) {
                        Scanner s = new Scanner(output);
                        String firstLine = s.nextLine();
                        if (!firstLine.equals("")) {
                            actual = firstLine;
                        }
                        s.close();
                    }

                    String expected = "pass";
                    if (data.get("valid").equals("I") || data.get("valid").equals("B") && seatType.equals("M") || x == 1) {
                        expected = "fail";
                    }
                    resultWriter.write(createHTMLRow(caseNum+1, ticketNum, rowNum, seatType, expected, actual));
                }
            }

            resultWriter.write(getHTMLFoot());
            resultWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String runProgram(String input, String output) {
        try {
            File file = new File("/bin/SeatAssignmentDecisionTableAssignment.class");
            URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});
            Class<?> clazz = classLoader.loadClass("SeatAssignmentDecisionTableAssignment");
            Method mainMethod = clazz.getDeclaredMethod("main", String[].class);

            String[] args = new String[]{input, output};
            mainMethod.invoke(null, (Object)args);
            classLoader.close();
        } catch (Exception e) {
            // something went wrong..
            e.printStackTrace();
            return e.getCause().getMessage();
        }
        return "pass";
    }

    private ArrayList<Map<String, String>> generateTestCases() {
        ArrayList<Map<String, String>> results = new ArrayList<>();
        
        char[] sections = new char[]{'B', 'E', 'I'};
        char[] seatTypes = new char[]{'W', 'M', 'A'};

        for (char section : sections) {
            for (char seatType : seatTypes) {
                for (int seatNum = 1; seatNum < 7; seatNum++) {
                    Map<String, String> param = new Hashtable<>();
                    int rowNum = rowParam(section);
                    int ticketNum = ticketNum(section, rowNum, seatNum);

                    param.put("row", rowNum + "");
                    param.put("ticket", ticketNum + "");
                    param.put("seat", seatType + "");
                    param.put("valid", section + "");
                    results.add(param);
                }
            }
        }
        return results;
    }

    private int ticketNum(char section, int rowNum, int seatNum) {
        if(rowNum <= 6) {
            return (rowNum - 1) * 4 + seatNum + 1000;
        }
        return (rowNum - 6) * 6 + seatNum + 1024;
    }

    private int rowParam(char section) {
        if(section == 'B') {
            return (int)((Math.random() * 6) + 1);
        }
        else if(section == 'E') {
            return (int)((Math.random() * 18) + 7);
        }
        else {
            return (int)((Math.random() * 100) + 50);
        }
    }

    private String getHTMLHead() {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>\n");
        htmlBuilder.append("<head>\n");
        htmlBuilder.append("<title>Tanner Turba - Seat Assignment Decision Table</title>\n");
        htmlBuilder.append("</head>\n");
        htmlBuilder.append("<body>\n");

        htmlBuilder.append("<table id=\"table1\" width=\"100%\" border=\"1\">\n");
        htmlBuilder.append("<tbody>\n");
        htmlBuilder.append("<tr>\n");

        htmlBuilder.append("<td rowspan=\"2\" align=\"center\">\n");
        htmlBuilder.append("<font size=\"4\">Test case #</font>\n");
        htmlBuilder.append("</td>\n");

        htmlBuilder.append("<td colspan=\"3\" align=\"center\">\n");
        htmlBuilder.append("<font size=\"4\">Input Parameters</font>\n");
        htmlBuilder.append("</td>\n");

        htmlBuilder.append("<td rowspan=\"2\" align=\"center\">\n");
        htmlBuilder.append("<font size=\"4\">Expected Output</font>\n");
        htmlBuilder.append("</td>\n");

        htmlBuilder.append("<td rowspan=\"2\" align=\"center\">\n");
        htmlBuilder.append("<font size=\"4\">Actual Output</font>\n");
        htmlBuilder.append("</td>\n");
        htmlBuilder.append("</tr>");

        htmlBuilder.append("<tr>\n");
        htmlBuilder.append("</td>\n");

        htmlBuilder.append("<td align=\"center\">Ticket number</td>\n");
        htmlBuilder.append("<td align=\"center\">Row number</td>\n");
        htmlBuilder.append("<td align=\"center\">Seat type</td>\n");

        htmlBuilder.append("</td>\n");
        htmlBuilder.append("</tr>");

        return htmlBuilder.toString();
    }

    private String createHTMLRow(int testCase, String ticketNum, String rowNum, String seatType, String expected, String actual) {
        String seat = "";
        switch (seatType) {
            case "W" : seat = "Window"; break;
            case "M" : seat = "Middle"; break;
            default  : seat = "Aisle"; break;
        }

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<tr>\n");
        htmlBuilder.append("</td>\n");

        htmlBuilder.append("<td align=\"center\">" + testCase + "</td>\n");
        htmlBuilder.append("<td align=\"center\">" + ticketNum + "</td>\n");
        htmlBuilder.append("<td align=\"center\">" + rowNum + "</td>\n");
        htmlBuilder.append("<td align=\"center\">" + seat + "</td>\n");
        htmlBuilder.append("<td align=\"center\">" + expected + "</td>\n");
        htmlBuilder.append("<td align=\"center\">" + actual + "</td>\n");

        htmlBuilder.append("</td>\n");
        htmlBuilder.append("</tr>");
        return htmlBuilder.toString();
    }

    private String getHTMLFoot() {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("</tbody>\n");
        htmlBuilder.append("</table>\n");
        htmlBuilder.append("</body>\n");
        htmlBuilder.append("</html>\n");
        return htmlBuilder.toString();
    }
}

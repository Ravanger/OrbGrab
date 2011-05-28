//package Project;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.ArrayList;
//
//public class ASEParser_bak {
//
//    private String filePath = null;
//    private int bufferSize = 1000;
//    private int SIZE_VERTICES, SIZE_FACES;
//
//    public ASEParser_bak(String filePath) {
//        setFilePath(filePath);
//        setBufferSize(1000);
//    }
//
//    public ASEParser_bak(String filePath, int bufferSize) {
//        setFilePath(filePath);
//        setBufferSize(bufferSize);
//    }
//
//    public int getBufferSize() {
//        return bufferSize;
//    }
//
//    public String getFilePath() {
//        return filePath;
//    }
//
//    public int getSIZE_FACES() {
//        return SIZE_FACES;
//    }
//
//    public int getSIZE_VERTICES() {
//        return SIZE_VERTICES;
//    }
//
//    private void setBufferSize(int bufferSize) {
//        this.bufferSize = bufferSize;
//    }
//
//    private void setFilePath(String filePath) {
//        this.filePath = filePath;
//    }
//
//    /**
//     * Reads the ASE file and returns an ArrayList which contains all information
//     */
//    public ArrayList read() throws java.io.FileNotFoundException, java.io.IOException {
//        FileReader fr = new FileReader(getFilePath());
//        BufferedReader br = new BufferedReader(fr);
//        ArrayList aList = new ArrayList(getBufferSize());
//        String line = null;
//        while ((line = br.readLine()) != null) {
//            aList.add(line);
//        }
//        br.close();
//        fr.close();
//        return aList;
//    }
//
//    /**
//     * Returns double[] array which contains information (3 values) for every vertex (x,y and z)
//     * and information (4 values) for every Face (number of face and numbers of the verteces in the face).
//     */
//    public double[] readASE(String filePath) {
//        String line;
//        double[] arrObj = new double[0];
//        double x, y, z;
//        int ind, ind2;
//        try {
//            ArrayList arrFile = read();
//            line = (String) arrFile.get(0);// 1st line
//            int seek = line.indexOf(' ', 1) + 1;// finds the first space and goes to next char
//            SIZE_VERTICES = Integer.parseInt(line.substring(seek, line.length()));// parses the number in string to int.
//            line = (String) arrFile.get(1);//2nd Line
//            seek = line.indexOf(' ', 1) + 1;
//            SIZE_FACES = Integer.parseInt(line.substring(seek, line.length()));
//            //The above lines save the number of vertices and faces in the ASE file
//            arrObj = new double[SIZE_FACES * 4 + SIZE_VERTICES * 3]; //Total length of the array
//            int i, j;
//            if (arrFile.size() > 4) //Checks if the file has more than 1 vertex
//            {
//                //Saves the verteces first
//                for (i = 3, j = 0; i < SIZE_VERTICES + 3; i++, j += 3) {
//                    line = (String) arrFile.get(i); //next line
//                    ind = line.indexOf(".", 1) - 2; //finds the "." from the 1 place, then goes to the place 2 chars back.
//                    if (line.charAt(ind) != '-') // If not '-', then empty
//                    {
//                        ind++;// goes to the next char
//                    }
//                    arrObj[j] = x = Double.parseDouble(line.substring(ind, line.indexOf(".", ind) + 5));// from the first char, to the dot and 5 more chars to the right (last char) and parses it to double
//                    ind = line.indexOf(".", ind + 3) - 2;// does the same thing for every value; looks for the dot from "ind+3" because previousle ind was at the first char, if it was looking from that place it would find the first dot from there, which is the first dot  over and pver again
//                    if (line.charAt(ind) != '-') {
//                        ind++;
//                    }
//                    arrObj[j + 1] = y = Double.parseDouble(line.substring(ind, line.indexOf(".", ind) + 5));
//                    ind = line.indexOf(".", ind + 3) - 2;
//                    if (line.charAt(ind) != '-') {
//                        ind++;
//                    }
//                    arrObj[j + 2] = z = Double.parseDouble(line.substring(ind, line.indexOf(".", ind) + 5));
//                }
//                double A, B, C;
//                int temp = i;
//                double place;
//                i += 2;
//                for (; i < (SIZE_FACES + temp + 2); i++, j += 4) // i can't "i=0" because i is the line number. because i need to do the loop for SIZE_FACES times, i add the previous i value
//                {
//                    line = (String) arrFile.get(i);
//                    ind = line.indexOf("A:", 1) + 2;// finds the "A:" (notice: not "A" because there's A in "FACE")
//                    ind2 = line.indexOf("B:", 1) - 1;// finds the "B:"
//                    String temp123 = line.substring(ind, ind2); // string which contains the chars between the ":" and the "B" (not including both)
//                    temp123.replaceAll(" ", "");// clears all the spaces and leaves only numbers
//                    arrObj[j + 1] = A = Double.parseDouble(temp123);//parses the string number to int and stores that.
//                    ind = line.indexOf("B:", ind) + 2;// all over again.
//                    ind2 = line.indexOf("C:", 1) - 1;
//                    temp123 = line.substring(ind, ind2);
//                    temp123.replaceAll(" ", "");
//                    arrObj[j + 2] = B = Double.parseDouble(temp123);
//                    ind = line.indexOf("C:", ind) + 2;
//                    ind2 = line.indexOf("AB:", 1) - 1;
//                    temp123 = line.substring(ind, ind2);
//                    temp123.replaceAll(" ", "");
//                    arrObj[j + 3] = C = Double.parseDouble(temp123);
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return arrObj;
//    }
// }

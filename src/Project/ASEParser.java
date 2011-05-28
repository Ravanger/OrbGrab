package Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ASEParser {

	private String filePath = null;
	private int bufferSize = 1000;
	private int NUMVERTEX, NUMFACES;

	public ASEParser(String filePath) {
		setFilePath(filePath);
		setBufferSize(1000);
	}

	public ASEParser(String filePath, int bufferSize) {
		setFilePath(filePath);
		setBufferSize(bufferSize);
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public int getNUMFACES() {
		return NUMFACES;
	}

	public int getNUMVERTEX() {
		return NUMVERTEX;
	}

	private void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	private void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Reads the ASE file and returns an ArrayList which contains all information, also saves number of Faces and vertexes.
	 */
	public ArrayList read() throws java.io.FileNotFoundException, java.io.IOException {
		FileReader fr = new FileReader(getFilePath());
		BufferedReader br = new BufferedReader(fr);
		ArrayList aList = new ArrayList(getBufferSize());
		String line = null;
		while ((line = br.readLine()) != null) {
			aList.add(line);
		}
		br.close();
		fr.close();
		// readFACENUM();
		// readVERTEXNUM();
		return aList;
	}

	/**
	 * Sets the value for the amount of faces (FACENUM).
	 */
	public int readFACENUM() {
		int numOfFaces = 0;
		try {
			ArrayList arrFile = read();
			String line = (String) arrFile.get(1); // Saves first line in String
			StringTokenizer token = new StringTokenizer(line, " ");
			while (token.hasMoreTokens()) {
				token.nextToken();// Skips *MESH_NUMVERTEX
				numOfFaces = Integer.parseInt(token.nextToken());// Saves the 2nd value in NUMFACES
				NUMFACES = numOfFaces;
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return numOfFaces;
	}

	/**
	 * Returns the value for the amount of vertexes (VERTEXNUM).
	 */
	public int readVERTEXNUM() {
		int numOfVertex = 0;
		try {
			ArrayList arrFile = read();
			String line = (String) arrFile.get(0); // Saves second line in String
			StringTokenizer token = new StringTokenizer(line, " ");
			while (token.hasMoreTokens()) {
				token.nextToken();// Skips *MESH_NUMFACES
				numOfVertex = Integer.parseInt(token.nextToken());// Saves the 2nd value in NUMVERTEX
				NUMVERTEX = numOfVertex;
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return numOfVertex;
	}

	/**
	 * Returns double[] array which contains information for every vertex (x, y, and z).
	 */
	public double[] readASEVertex() {
		String line;
		double[] arr = new double[0];// Declared
		try {
			ArrayList arrFile = read();
			this.readVERTEXNUM();
			arr = new double[NUMVERTEX * 3];// Makes array which is NUMVERTEX*3 long, because there are 3 values for every vertex
			if (arrFile.size() > 4) // Checks if the file has more than 1 vertex
			{
				int index = 0;// index of the array
				for (int i = 3; i < NUMVERTEX + 4; i++) { // starts at line 4, i is for lines, j is for vertex values
					line = (String) arrFile.get(i); // next line
					parseLine(line, arr, index);
					index += 3;
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return arr;
	}

	/**
	 * Returns double[] array which contains information for every face (Number of face and numbers of the vertexes in the face).
	 */
	public double[] readASEFace() {
		String line;
		double[] arr = new double[0];
		try {
			ArrayList arrFile = read();
			this.readFACENUM();
			arr = new double[NUMFACES * 3];// Makes array which is NUMFACES*3 long, because there are 3 values for every faces
			int linecounter = getNUMVERTEX() + 2;
			String templine = (String) arrFile.get(linecounter);
			String test = "*MESH_FACE";
			String test1 = "*MESH_FACE_";
			while (!templine.contains(test1) && !templine.contains(test)) {// We have to find the line where vertexes end and faces begin
				linecounter++;
				templine = (String) arrFile.get(linecounter);
			}
			if (templine.contains(test1)) {
				linecounter++;
			}
			int index = 0;// index of the array
			for (int i = linecounter; i < NUMFACES + linecounter; i++) {
				line = (String) arrFile.get(i); // next line
				parseLine(line, arr, index);
				index += 3;// parseLine can't change the global index, so we have to change it here
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return arr;
	}

	/**
	 * Gets current line, the array to fill, and the current index of the array. Parses the line and fills the values from the current line into the array.
	 * 
	 * @param line
	 * @param arr
	 * @param index
	 */
	private void parseLine(String line, double[] arr, int index) {
		StringTokenizer st = new StringTokenizer(line, ": \t");
		int counter = 0;
		if (line.contains(("FACE"))) {// for Faces
			while (st.hasMoreTokens()) {
				if (counter == 3 || counter == 5 || counter == 7) {// We need the 4th, 6th, and 8th values, the rest we can discard
					arr[index] = Double.parseDouble(st.nextToken());
					index++;
				}
				else {
					String temp = st.nextToken();// Skips the token.
				}
				counter++;
			}
		}
		else {
			while (st.hasMoreTokens()) {// for Vertexes
				if (counter == 2 || counter == 3 || counter == 4) {// We need the 3rd, 4th, and 5th values, the rest we can discard
					arr[index] = Double.parseDouble(st.nextToken());
					index++;
				}
				else {
					String temp = st.nextToken();// Skips the token.
				}
				counter++;
			}
		}
	}
}

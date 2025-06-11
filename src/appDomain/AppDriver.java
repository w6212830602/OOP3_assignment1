package appDomain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import shapes.*;
import utilities.*;

public class AppDriver
{

	public static Shape[] parseShapes(String filePath) throws IOException {
		
		Shape[] shapes;

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){

			String firstLine = reader.readLine(); // get the array size
			if (firstLine == null) {
				throw new IOException("File appears empty. Check file.");
			}

			int arraySize;
			try {
				arraySize = Integer.parseInt(firstLine.trim());
			} catch (NumberFormatException e) {
				throw new IOException("First line is not a valid number. Check file.");
			}
			shapes = new Shape[arraySize];
			String line;
			int count = 0;
			while ((line = reader.readLine()) != null && count < arraySize) {
				String[] parts = line.split(" ");
				if(parts.length < 3){
					System.err.println("Data incomplete. Skipping line at line " + line);
					continue;
				}

				String shapeType = parts[0].trim();
				double val1;
				double val2;
				
				try {
					val1 = Double.parseDouble(parts[1].trim()); // should always be height
					val2 = Double.parseDouble(parts[2].trim()); // should always be edge length, could be radius
				} catch (NumberFormatException e) {
					System.err.println("Invalid numeric value detected. Skipping line at " + line);
					continue;
				}
				Shape newShape = null;
				switch (shapeType.toLowerCase()) {
					case "cone":
						newShape = new Cone(val1, val2);
						break;
					
					case "cylinder":
						newShape = new Cylinder(val1, val2);
						break;
					
					case "octagonalprism":
						newShape = new OctagonalPrism(val1, val2);
						break;

					case "pentagonalprism": 

						newShape = new OctagonalPrism(val1, val2);
						break;
					
					case "pyramid":
						newShape = new Pyramid(val1, val2);
						break;
					
					case "squareprism": 
						newShape = new SquarePrism(val1, val2);
						break;

					case "triangularprism": 
						newShape = new TriangularPrism(val1, val2);
						break;

					default:
						System.err.println("Shape not found: " + shapeType + " at line " + line);
						break;
				}
				if (newShape != null){
					shapes[count] = newShape;
					count++;
				}
				
			}
		}
		return shapes;
	}

	public static void main( String[] args )
	{
		String fileName = null;
		String sortType = null;
		String compareType = null;
		Shape[] shapes = null;

		for (String arg: args) {
			if (arg.toLowerCase().startsWith("-f")) {
				fileName = arg.substring(2);
			} else if (arg.toLowerCase().startsWith("-s")) {
				sortType = arg.substring(2).toLowerCase();
			} else if (arg.toLowerCase().startsWith("-t")) {
				compareType = arg.substring(2).toLowerCase();
			}
		}

		// Validating the command line arguments
		if (fileName == null) {
    		System.out.println("No -f<filename> flag.	Please specify a filename.");
    		return;
		}

		fileName = fileName.replace("\\", "/").replace("\"", "");

		File file = fileName.contains(":") || fileName.startsWith("res/")
    		? new File(fileName)
    		: new File("res/" + fileName);

		try 
		{
    		shapes = parseShapes(file.getPath());
		} catch (IOException e) {
    	System.err.println("Error reading file: " + e.getMessage());
    	return; 
		}

		if (shapes == null || shapes.length == 0) {
    		System.err.println("No shapes loaded from the file.");
    		return;
		}

		if (compareType == null) {
    		System.out.println("No -t<h|v|a> flag. Please specify the comparison type:  h (height), v (volume), or a (base area).");
    		return;
		} else if (!compareType.matches("[hva]")) {
    		System.out.println("Invalid comparison type: " + compareType);
    		System.out.println("Use -t followed by: h (height), v (volume), or a (base area).");
    		return;
		}

		if (sortType == null) {
    		System.out.println("No -s<b|i|s|m|q|h> flag. Please specify the sorting type: b (bubble), i (insertion), s (selection), m (merge), q (quick), h (heap).");
    		return;
		} else if (!sortType.matches("[bismqh]")) {
    		System.out.println("Invalid sorting type: " + sortType);
    		System.out.println("Use -s followed by: b (bubble), i (insertion), s (selection), m (merge), q (quick), h (heap).");
    		return;
		}
	
		// Sorting the shapes based on the file and display the results
        // To select the comparator based on compareType
		Comparator<Shape> comparator;
		String metricLabel;
		String sortLabel;
		
		switch (compareType) {
    		case "h":
        		comparator = new HeightComparator();
				metricLabel = "Height";
        		break;
    		case "v":
        		comparator = new VolumeComparator();
				metricLabel = "Volume";
        		break;
    		case "a":
        		comparator = new BaseAreaComparator();
				metricLabel = "Base Area";
        		break;
    		default:
        	System.out.println("Invalid compare type.");
        	return;
		}

        // Sorting and time it took to sort
        long start = System.nanoTime();
        switch (sortType) {
            case "b": 
				sortLabel = "Bubble Sort";
				Sorter.bubbleSort(shapes, comparator); 
				break;
            case "i": 
				sortLabel = "Insertion Sort";	
				Sorter.insertionSort(shapes, comparator); 
				break;
            case "s":
				sortLabel = "Selection Sort"; 
				Sorter.selectionSort(shapes, comparator); 
				break;
            case "m":
				sortLabel = "Merge Sort"; 
				Sorter.mergeSort(shapes, comparator); 
				break;
            case "q": 
				sortLabel = "Quick Sort";
				Sorter.quickSort(shapes, comparator); 
				break;
            case "h": 
				sortLabel = "Heap Sort";
				Sorter.heapSort(shapes, comparator); 
				break;
            default:
                System.out.println("Invalid sort type.");
                return;
        }
        long end = System.nanoTime();

		for (int i = 0; i < shapes.length; i++) {
    		boolean isFirst = i == 0;
    		boolean isLast = i == shapes.length - 1;
    		boolean isEvery1000th = i % 1000 == 0;

    		if (isFirst || isEvery1000th || isLast) {
        		String label = isFirst
            		? "First element is:"
            		: isLast
                	? "Last element is:"
                	: String.format("%-6d-th element:", i);

        		double value;
        		switch (compareType) {
            		case "h": value = shapes[i].getHeight(); break;
            		case "v": value = shapes[i].calcVolume(); break;
            		case "a": value = shapes[i].calcBaseArea(); break;
            		default: value = 0;
        	}

        	System.out.printf("%-20s %30s   %s: %.15f%n",
            	label, shapes[i].getClass().getName(), metricLabel, value);
    		}	
		}

		System.out.printf("%s Sort run time was: %.2f milliseconds%n", sortLabel, (end - start) / 1_000_000.0);
	}
}

package appDomain;

import shapes.*;
import utilities.*;

import java.io.File;
import java.util.*;

public class AppDriver
{

	public static void main( String[] args )
	{
		// TODO Auto-generated method stub

		// refer to demo001 BasicFileIO.java for a simple example on how to
		// read data from a text file

		// refer to demo01 Test.java for an example on how to parse command
		// line arguments and benchmarking tests
		String fileName = null;
		String sortType = null;
		String compareType = null;

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

		// refer to demo02 Student.java for comparable implementation, and
		// NameCompare.java or GradeCompare for comparator implementations

		// refer to demo02 KittySort.java on how to use a custom sorting
		// algorithm on a list of comparables to sort using either the
		// natural order (comparable) or other orders (comparators)

	
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

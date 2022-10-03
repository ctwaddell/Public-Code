// --== CS400 File Header Information ==--
// Name: Kayla Thrane
// Email: krthrane@wisc.edu
// Team: KB blue
// Role: Data Wrangler
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: 

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.zip.DataFormatException;
import java.io.BufferedReader;

public class MovieDataReader implements MovieDataReaderInterface{
	
	List<MovieInterface> movies;
	
	/*
	 * Reads the data set and creates movie objects for each line in the file and puts all movies into a list
	 * @return  movies, the list of movie objects
	 * @throws IOException, DataFormatException for problems with the reader and number of columns, respectively
	 */
    public List<MovieInterface> readDataSet(Reader inputFileReader) throws IOException, DataFormatException {
    if (inputFileReader == null) {
    	throw new IOException("Reader is invalid");
    }
    movies = new ArrayList<MovieInterface>();
	BufferedReader bufread = new BufferedReader(inputFileReader);
	// reads headers to count columns and NOT include this line in movie list
	String[] headers = bufread.readLine().split(",");
	String temp[];
	String movieData = bufread.readLine();
	while (movieData != null) {
		// splits based on commas, unless those commas are within double quotes
	    temp = movieData.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	    // check to see if number of columns match
	    if (temp.length != headers.length) {
	    	bufread.close();
	    	throw new DataFormatException("The number of columns do not match.");
	    }
	    // add movie to list
	    Movie mov = new Movie(temp);
	    movies.add(mov);
	    movieData = bufread.readLine();
	}
	bufread.close();
	return movies;
    }


}

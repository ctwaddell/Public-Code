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

public class Movie implements MovieInterface {

    private String title;
    private Integer year;
    private List<String> genres;
    private String director;
    private String description;
    private Float avgVote;

    /*
     * Constructor that takes in a String array of data, and separates it into data fields for each movie
     */
    public Movie(String[] data) {
    	this.title = data[0];
    	this.year = Integer.parseInt(data[2]);	// have to convert string to int
    	this.genres = new ArrayList<String>();
    	String[] allGenres = data[3].replace("\"", "").split(","); // takes out any extra quotes. Split returns an array, so must be stored in array first
    	for (int i = 0; i < allGenres.length; i++) {
    		this.genres.add(allGenres[i].trim());	// move all items in array to arraylist
    	}
    	this.director = data[7].replace("\"", "");
    	this.description = data[11].replace("\"", "");
    	this.avgVote = Float.parseFloat(data[12]); 	// have to convert string to float
    }
    
    /*
     * Returns the title of this movie
     * @return title, a string containing the title
     */
    public String getTitle() {
    	return this.title;
    }

    /*
     * Returns the year of this movie
     * @return year, an integer containing the year
     */
    public Integer getYear() {
    	return this.year;
    }

    /*
     * Returns the genres of this movie
     * @return genres, a list containing all genres for this movie
     */
    public List<String> getGenres() {
    	return this.genres;
    }

    /*
     * Returns the director for this movie
     * @return director, a string containing the director(s)
     */
    public String getDirector() {
    	return this.director;
    }

    /*
     * Returns the description for this movie
     * @return description, a string containing the description
     */
    public String getDescription() {
    	return this.description;
    }

    /*
     * Returns the average vote for this movie
     * @return avgVote, a float containing the average vote
     */
    public Float getAvgVote() {
    	return this.avgVote;
    }

    /*
     * Compares two MovieInterface objects, based on their average vote. The movie with the higher average vote is greater.
     * @return 1 if this movie is better than otherMovie, -1 if the otherMovie is better than this movie, or 0 if they are the same
     */
    @Override
    public int compareTo(MovieInterface otherMovie) {
		int comparison;
		if (this.getAvgVote() > otherMovie.getAvgVote() ) {
			comparison = 1;
		}
		else if (this.getAvgVote() < otherMovie.getAvgVote() ) {
			comparison = -1;
		}
		else {
			comparison = 0;
		}
		return comparison;
    }
    
    
}
